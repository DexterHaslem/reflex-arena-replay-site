/**
 * Created by Dexter on 7/16/2016.
 */

// es6 wont work :-(

var express = require('express');
var app = express();

// respond with "hello world" when a GET request is made to the homepage
app.get('/', function(req, res) {
    res.send('hello world');
});

app.get('/getFiles', function(req,res){
    //res.send('get files hehe wee');
    const ret = {
        foo: "bar",
        baz: 123
    };
    res.json(ret);
});

app.get('/sendFile/:name', function(req,res){
    //res.send(req.params);
    //res.download()
    //res.sendFile();
});