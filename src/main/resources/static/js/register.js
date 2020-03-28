var nameinput = document.querySelector("input#username");
var pwdinput = document.querySelector("input#password");
var affirmput = document.querySelector("input#affirm");
var authCodeput = document.querySelector("input#authCode");
var sendCodebut = document.querySelector("button#sendCode");
var submitbut = document.querySelector("button#submit");
var p1 = document.querySelector("p#p1");
var p2 = document.querySelector("p#p2");
var p3 = document.querySelector("p#p3");

var countdown = 60;

function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.innerText = "获取验证码";
        obj.style['background-color'] = '#fff';
        countdown = 60;
        return;
    } else {
        obj.setAttribute("disabled", true);
        obj.innerText = "重新发送(" + countdown + "s)";
        obj.style['background-color'] = '#ffa584';
        countdown--;
    }
    setTimeout(function () {
            settime(obj)
        }
        , 1000)
};
sendCodebut.onclick = function () {
    settime(sendCodebut);
    sendAuthCode();
};

function sendAuthCode() {
    let xmlHttp = new XMLHttpRequest();
     xmlHttp.onreadystatechange = function (ev) {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
            if ( xmlHttp.getResponseHeader(content_type)===applction_json) {
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code === 0) {
                    console.log("发送验证码到" + data.data + "成功");
                }else {
                    nologin(data.message,p3);
                }
            }else {
                console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!',p3);
            }
        } else {

        }
    };
    xmlHttp.open('POST', servicePate + '/send/authCode', true);
    xmlHttp.setRequestHeader(content_type, applction_json);

    xmlHttp.send(JSON.stringify({
        emaill: nameinput.value,
    }));
}

nameinput.onblur = function () {
    let value = nameinput.value;
    let emreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!emreg.test(value)) {
        p1.style.display = 'block';
        isCanSubmit(true);
    } else {
        isCanSubmit(false);
    }
};

function isCanSubmit(is) {
    submitbut.disabled = is;
    sendCodebut.disabled = is;
}

nameinput.onfocus = function () {
    p1.style.display = 'none';
};
submitbut.onclick = function () {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function (ev) {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            console.log(xmlHttp.getResponseHeader(content_type));
            if (xmlHttp.getResponseHeader(content_type) === applction_json) {
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code !== 0) {
                    nologin(data.message,p3);
                    access_token = data.data;

                    window.location.href = servicePate + '/index';
                    setToken(access_token + "666");
                    return;
                } else {
                    nologin(data.message,p3);
                }


            } else {
                console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!',p3);
            }

        }
    }
    xmlHttp.open("POST", servicePate + '/login/logoing', true);
    xmlHttp.setRequestHeader(content_type, applction_json);
    xmlHttp.send(JSON.stringify({
        name: nameinput.value,
        pwd: pwdinput.value
    }));

}

function nologin(massge,obj) {
    obj.innerText = massge;
    obj.style.display = 'block';
}

pwdinput.onfocus = function () {
    p2.style.display = 'none';
};
pwdinput.onblur = function () {
    if (affirmput.value) {
        if (affirmput.value === pwdinput.value) {
            p2.style.display = 'none';
        } else {
            isCanSubmit(true);
            p2.style.display = 'block';
        }

    } else {
        p2.style.display = 'none';
    }
};
affirmput.onblur = function () {
    if (affirmput.value === pwdinput.value) {
        p2.style.display = 'none';
    } else {
        p2.style.display = 'block';
    }
};
affirmput.onfocus = function () {
    p2.style.display = 'none';
};


