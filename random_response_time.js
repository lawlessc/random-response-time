/**
A simple node server for replying to requests at random times.

Christopher Lawless 2014
**/
var fs = require('fs');
var express = require('express');
var app = express();

var logFile = fs.createWriteStream('./myLog.log', {flags : 'a'});

app.use(express.logger({stream: logFile}));


app.get('/wait', function(request, response){
    console.log("request made");
    var maxtime =500;
    var mintime =0;

    var resptime= Math.floor(Math.random() * maxtime) + mintime;
    
    setTimeout(function() 
    { 
     console.log("Response sent"); 
     response.json({ delay: resptime })
     response.end("Hello World\n");    
    },resptime);
    console.log("response should arrive in "+resptime+" milliseconds");  
});


app.get('/waitfor/:waittime', function(request, response){ 
    
    console.log("request made for :"+request.params.waittime);
    var resptime=  parseInt(request.params.waittime);
    
    if(resptime <5000 && resptime !=null)
    {
        setTimeout(function() 
    { 
     console.log("Response sent"); 
     response.json({ delay: resptime })
     response.end("Hello World\n");    
    },resptime);
    console.log("response should arrive in "+resptime+" milliseconds"); 
    }
    else
    {
         console.log("invalid time");
         response.send(403);//Deprecated
        //repsonse.status(403).end() // Is recommended, but fails to close connection
    }
});


app.listen(9100);

console.log("Now listening port 9100");
