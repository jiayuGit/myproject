var nameinput = document.querySelector("input#username");
var pwdinput = document.querySelector("input#password");
var submitbut = document.querySelector("button#submit");
var p1 = document.querySelector("p#p1");
var p2 = document.querySelector("p#p2");

nameinput.onblur=function () {
    let value = nameinput.value;
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

nameinput.onfocus=function () {
    p1.style.display='none';
};
submitbut.onclick=function () {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange=function (ev) {
        if(xmlHttp.readyState===4&&xmlHttp.status===200){
            console.log(xmlHttp.getResponseHeader('content-type'));
            if (xmlHttp.getResponseHeader('content-type')==='application/json'){
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code!==0) {
                    nologin(data.message);
                    access_token=data.data;

                    window.location.href=servicePate+'/index';
                    setToken(access_token+"666");
                    return;
                }else {

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
        name:nameinput.value,
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


