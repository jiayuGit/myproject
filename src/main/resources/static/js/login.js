var emaillinput = document.querySelector("input#emaill");
var pwdinput = document.querySelector("input#password");
var submitbut = document.querySelector("button#submit");
var p1 = document.querySelector("p#p1");
var p2 = document.querySelector("p#p2");
var registerA = document.querySelector("a#register");
var repwdA = document.querySelector("a#repwd");
registerA.oncilck=function(){
    window.location.href=servicePate+'/register';
};
emaillinput.onblur=function () {
    let value = emaillinput.value;
    let emreg=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(!emreg.test(value)){
        p1.style.display='block';
        setCanSubmit(true);
    }else {
        isCanSubmit(false);
    }

};
function isCanSubmit(is){
    submitbut.disabled=is;
}

emaillinput.onfocus=function () {
    p1.style.display='none';
};
submitbut.onclick=function () {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange=function (ev) {
        if(xmlHttp.readyState===4&&xmlHttp.status===200){
            console.log(xmlHttp.getResponseHeader('content-type'));
            if (xmlHttp.getResponseHeader(content_type)===applction_json){
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code===0) {
                    nologin(data.message);
                    access_token=data.data;

                    window.location.href=servicePate+'/index';
                    setToken(access_token);
                    return;
                }else {
                    nologin(data.message);
                }


            }else {
                console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!');
            }

        }
    }
    xmlHttp.open("POST",servicePate+'/login/logoing',true);
    xmlHttp.setRequestHeader('content-type','application/json');
    xmlHttp.send(JSON.stringify({
        emaill:emaillinput.value,
        pwd:pwdinput.value
    }));

}
function nologin(massge) {
    p2.innerText=massge;
    p2.style.display='block';
}
pwdinput.onfocus=function () {
    p2.style.display='none';
};


