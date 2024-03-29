var http = require('http');
var url = require('url');
var fs = require('fs');
var path = require('path');

//配置
var config = {
    port: 8002,
    denyAccess: ['./httpserver.js', './src/requirecache.js'],
    localIPs: ['127.0.0.1'],
    srcpath: '/src'
};


//开始HTTP服务器
http.createServer(processRequestRoute).listen(config.port);
console.log("Server has started. port:" + config.port);

//路由URL
/**
 * 
 * @param {Object} request
 * @param {Object} response
 */
function processRequestRoute(request, response) {
    var pathname = url.parse(request.url).pathname;
    if (pathname === '/') {
        var urlStr = request.url;
        if (request.url.indexOf('/??') == 0) {
            //urlStr = urlStr.replace('/??','');
            var a = urlStr.split('?');
            if (!!a[2] && a[2] != '') {
                var params = a[2].split(',');
                //console.log(params)
            }

        } else {
            pathname = "/index.html"; //默认页面
        }
    }


    var ext = path.extname(pathname);
    var localPath = ''; //本地相对路径
    var staticres = false; //是否是静态资源

    if (ext.length > 0) {
        //localPath = '.' + pathname;
        localPath = '.' + decodeURIComponent(pathname);
        staticRes = true;
    } else {
        localPath = '.' + config.srcpath + pathname + '.js';
        staticRes = false;
    }

    //js、css代理
    if (request.url.indexOf('/??') == 0) {
        localPath = '.' + config.srcpath + '/jic_proxy.js';
        staticRes = false;
        /*
        //urlStr = urlStr.replace('/??','');
        var a = urlStr.split('?');
        if(!!a[2] && a[2] !=''){
            var params = a[2].split(',');
            console.log(params)
        }
        */
    }

    //js、css代理end
    //禁止远程访问
    if (config.denyAccess && config.denyAccess.length > 0) {
        var islocal = false;
        var remoteAddress = request.connection.remoteAddress;
        for (var j = 0; j < config.localIPs.length; j++) {
            if (remoteAddress === config.localIPs[j]) {
                islocal = true;
                break;
            }
        }
        if (!islocal) {
            for (var i = 0; i < config.denyAccess.length; i++) {
                if (localPath === config.denyAccess[i]) {
                    response.writeHead(403, { 'Content-Type': 'text/plain' });
                    response.end('403:Deny access to this page');
                    return;
                }
            }
        }
    }

    //禁止访问后端js
    if (staticRes && localPath.indexOf(config.port + config.srcpath) >= 0) {
        response.writeHead(403, { 'Content-Type': 'text/plain' });
        response.end('403:Deny access to this page');
        return;
    }

    fs.exists(localPath, function(exists) {
        if (exists) {

            if (request.url.indexOf("??") == -1 && request.url.indexOf('.css') > -1) {
                staticRes = true;
            }
            console.log(request.url, staticRes)
            if (request.url.indexOf('??') > -1) {
                console.log(request.url, staticRes)
                staticRes = false;
            }
            if (request.url.indexOf('model?m') > -1) {
                staticRes = false;
            }
            if (staticRes) {
                staticResHandler(localPath, ext, response); //静态资源
            } else {
                try {
                    var handler = require(localPath);
                    if (handler.processRequest && typeof handler.processRequest === 'function') {
                        handler.processRequest(request, response); //动态资源
                    } else {
                        response.writeHead(404, { 'Content-Type': 'text/plain' });
                        response.end('404:Handle Not found');
                    }
                } catch (exception) {
                    // console.log('error::url:' + request.url + 'msg:' + exception);
                    response.writeHead(500, { "Content-Type": "text/plain" });
                    response.end("Server Error:" + exception);
                }
            }
        } else { //资源不存在
            response.writeHead(404, { 'Content-Type': 'text/plain' });
            response.end('404:File Not found');
        }
    });
}

//处理静态资源
function staticResHandler(localPath, ext, response) {

    fs.readFile(localPath, "binary", function(error, file) {

        if (error) {
            response.writeHead(500, { "Content-Type": "text/plain2" });
            response.end("Server Error:" + error);
        } else {
            response.writeHead(200, { "Content-Type": getContentTypeByExt(ext), "maji": "ok" });
            response.end(file, "binary");
        }
    });
}

//得到ContentType
function getContentTypeByExt(ext) {
    ext = ext.toLowerCase();
    if (ext === '.htm' || ext === '.html')
        return 'text/html';
    else if (ext === '.js')
        return 'application/x-javascript';
    else if (ext === '.css')
        return 'text/css';
    else if (ext === '.jpe' || ext === '.jpeg' || ext === '.jpg')
        return 'image/jpeg';
    else if (ext === '.png')
        return 'image/png';
    else if (ext === '.json')
        return 'application/x-javascript';
    else if (ext === '.ico')
        return 'image/x-icon';
    else if (ext === '.zip')
        return 'application/zip';
    else if (ext === '.doc')
        return 'application/msword';
    else
        return 'text/plain';
}
