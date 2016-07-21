/**
 * Created by Dexter on 7/16/2016.
 * important: when running w/ webstorm, make sure to run from rootpath and pass in api/server.js
 * on server its easy as node api/server.js
 */

'use strict';

const API_PORT = 8080;
const express = require('express');
const app = express();
const fs = require('fs');
const path = require('path');
const async = require('async');
const moment = require('moment');


// TODO: node env
// LOCALHOST debug
//const API_HOST = 'localhost';
//const REPLAYDIR = './testreplays';
//server
const REPLAYDIR = 'C:\\steamcmd\\reflex_ds\\replays';
const API_HOST = 'fragged.online';

// if several file changes come through at once, we want to throttle
const FILE_UPDATE_LATCH_SECONDS = 2;
let lastFolderChangeTime = moment();

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
let cachedJsonResp = null;

const reduceStats = fileStats => ({
    filename: fileStats.file,
    size: fileStats.stats.size, // bytes (st_size)
    time: fileStats.stats.mtime.getTime(),
    href: hrefStr + fileStats.file
});

const updateFiles = () => {
    cachedJsonResp = null;
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

const replayDirChanged = (event, filename) => {
    // this shouldnt be needed, but just in case.

    // this will trigger for EVERY file change, we need to latch in case, eg whole folder was deleted
    const timeDiff = moment().diff(lastFolderChangeTime, 'seconds');
    lastFolderChangeTime = moment();
    if (timeDiff < FILE_UPDATE_LATCH_SECONDS) {
        return;
    }
    // only two events come in, rename and change which is barbaric so dont try anything clever w/ it
    console.log(new Date().toLocaleTimeString(), REPLAYDIR, "changed:", event, filename, "updating cache");
    updateFiles();
};

const watcher = fs.watch(REPLAYDIR, replayDirChanged);

app.get('/getFiles', (req,res) => {
    if (!cachedJsonResp){
        cachedJsonResp = JSON.stringify(fileStatsCache);
    }
    res.send(cachedJsonResp);
});

let listener = app.listen(API_PORT, API_HOST, err => {
    if (err) {
        console.log(err);
        watcher.close();
    } else {
        // TODO: hardcoded http hehe
        hrefStr = 'http://' + listener.address().address + ":"+ listener.address().port + '/';
        console.log("App started on", hrefStr);
        // get files right away so initial 30 seconds arent empty
        updateFiles();
    }
});
