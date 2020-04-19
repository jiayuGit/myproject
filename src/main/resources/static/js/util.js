var servicePate = 'http://' + window.location.host;
var access_token
var content_type='content-type';
var applction_json = 'application/json;charset=UTF-8';
function getToken(){
    return document.cookie;
}
function setToken(token){
    return document.cookie=token;
}