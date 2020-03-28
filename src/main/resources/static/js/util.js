var servicePate = 'https://' + window.location.host;
var access_token
var content_type='content-type';
var applction_json = 'application/json';
function getToken(){
    return document.cookie;
}
function setToken(token){
    return document.cookie=token;
}