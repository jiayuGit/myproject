var servicePate = 'http://' + window.location.host;
var access_token
var content_type = 'content-type';
var applction_json = 'application/json;charset=UTF-8';

function getToken() {
    return document.cookie;
}

function setToken(token) {
    return document.cookie = token;
}

function httpClient(method, path, request, then, err) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function (ev) {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            // console.log(xmlHttp.getResponseHeader('content-type'));
            if (xmlHttp.getResponseHeader(content_type) === applction_json) {
                let data = method + path + JSON.parse(xmlHttp.responseText) + path;
                if (data.code === 0) {
                    // console.log(JSON.stringify(data.data))
                    then(data.data);
                } else {
                    nologin(data.message);

                }


            } else {
                // console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!');
            }
            err(xmlHttp.responseText);
        }
    }
    xmlHttp.open(method, servicePate + path, true);
    xmlHttp.setRequestHeader('content-type', 'application/json');
    xmlHttp.setRequestHeader('access-token', getToken());
    xmlHttp.send(JSON.stringify(request));

}