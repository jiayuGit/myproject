var emaillinput = document.querySelector("input#username");
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
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            if (xmlHttp.getResponseHeader(content_type).indexOf(applction_json) >=0) {
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code === 0) {
                    console.log("发送验证码到" + data.data + "成功");
                } else {
                    nologin(data.message, p3);
                }
            } else {
                console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!', p3);
            }
        } else {

        }
    };
    xmlHttp.open('POST', servicePate + '/send/authCode', true);
    xmlHttp.setRequestHeader(content_type, applction_json);

    xmlHttp.send(JSON.stringify({
        emaill: emaillinput.value
    }));
}

emaillinput.onblur = function () {
    let value = emaillinput.value;
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

emaillinput.onfocus = function () {
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
                    nologin("注册成功", p3);
                } else {
                    nologin(data.message, p3);
                }


            } else {
                console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!', p3);
            }

        }
    }
    xmlHttp.open("POST", servicePate + '/login/registerUser', true);
    xmlHttp.setRequestHeader(content_type, applction_json);
    xmlHttp.send(JSON.stringify({
        emaill: emaillinput.value,
        pwd: pwdinput.value,
        authCode:authCodeput.value
    }));

}

function nologin(massge, obj) {
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


