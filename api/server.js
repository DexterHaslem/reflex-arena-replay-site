/**
 * Created by Dexter on 7/16/2016.
 * important: when running w/ webstorm, make sure to run from rootpath and pass in api/server.js
 * on server its easy as node api/server.js
 */

'use strict';

const API_PORT = 8080;
// LOCALHOST debug
const API_HOST = 'localhost';
//const API_HOST = 'fragged.online';
const express = require('express');
const app = express();
const fs = require('fs');
const path = require('path');
const async = require('async');

const REPLAYDIR = './testreplays';
//server
//const REPLAYDIR = 'C:\\steamcmd\\reflex_ds\\replays';

// list of files on disk is updated every this often
const FILE_UPDATE_SECONDS = 25;

// turn off cors
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

let hrefStr = '';

// serve the folder as static files so we can use hrefs directly to 'em
// so we dont have to expose an api to send down
app.use(express.static(REPLAYDIR));

// update files on a timer, instead of every request. was an invitation to get ddos'd
let fileStatsCache = [];

const reduceStats = fileStats => ({
    filename: fileStats.file,
    size: fileStats.stats.size, // bytes (st_size)
    time: fileStats.stats.mtime.getTime(),
    href: hrefStr + fileStats.filename
});

const updateFiles = () => {
    fs.readdir(REPLAYDIR, (err, files)=> {
        if (err) {
            return;
        }
        // smash filename in the stat callback. make sure to call async's callback. what a nightmare
        async.map(files, (file, cb) => {
            fs.stat(path.join(REPLAYDIR,file), (e, stats) =>
                cb(e, {file, stats})
            )},
            (statErr, results) => {
                if (!statErr) {
                    fileStatsCache = results.map(reduceStats);
                }
            }
        );
    });
};

// update files on disk once every 30 seconds
// TODO fs.watch
const updateTimer = setInterval(updateFiles, 1000 * FILE_UPDATE_SECONDS);

app.get('/getFiles', (req,res) => {
    res.json(fileStatsCache);
});

let listener = app.listen(API_PORT, API_HOST, err => {
    if (err) {
        console.log(err);
        clearInterval(updateTimer);
    } else {
        // TODO: hardcoded http hehe
        hrefStr = 'http://' + listener.address().address + ":"+ listener.address().port + '/';
        console.log("App started on", hrefStr);
        // get files right away so initial 30 seconds arent empty
        updateFiles();
    }
});

// app.get('/sendFile/:name', (req,res)=> {
//     //res.send(req.params);
//     //res.download()
//     //res.sendFile();
// });

