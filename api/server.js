/**
 * Created by Dexter on 7/16/2016.
 * important: when running w/ webstorm, make sure to run from rootpath and pass in api/server.js
 * on server its easy as node api/server.js
 */

'use strict';

const API_PORT = 8080;
const API_HOST = 'localhost';
const express = require('express');
const app = express();
const fs = require('fs');
const path = require('path');

const REPLAYDIR = './testreplays';
//server
//const REPLAYDIR = 'C:\\steamcmd\\reflex_ds\\replays';

// turn off cors
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

let hrefStr = '';

let listener = app.listen(API_PORT, API_HOST, err => {
    if (err) {
        console.log(err);
    } else {
        // TODO: hardcoded http hehe
        hrefStr = 'http://' + listener.address().address + ":"+ listener.address().port + '/';
        console.log("App started on", hrefStr);
    }
});

// serve the folder as static files so we can use hrefs directly to 'em
// so we dont have to expose an api to send down
app.use(express.static(REPLAYDIR));

const toFileStats = (filename) => {
    const stats = fs.statSync(path.join(REPLAYDIR,filename)); //, (err, stats)=>({
    return {
        filename: filename,
        size: stats.size, // bytes (st_size)
        time: stats.mtime.getTime(),
        href: hrefStr + filename
    };
};

app.get('/getFiles', (req,res) => {
    fs.readdir(REPLAYDIR, (err, files)=> {
        if (err) {
            res.send(err);
            return;
        }
        res.json(files.map(toFileStats));
    });
});

// app.get('/sendFile/:name', (req,res)=> {
//     //res.send(req.params);
//     //res.download()
//     //res.sendFile();
// });
