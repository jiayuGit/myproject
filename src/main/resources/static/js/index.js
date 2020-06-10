var buttonClock = document.querySelector("button#buttonClock");
buttonClock.onclick=function () {
    console.log("button");
    httpClient("POST",'/login/clockIn',{},
        function (data) {
            buttonClock.innerText=data;
            if(data==="签退成功,再次点击更新下班时间"){
                buttonClock.style="height: 50px;width: 450px;font-size: 30px"
            }
            if(data==="签到成功,再次点击下班"){
                buttonClock.style="height: 50px;width: 340px;font-size: 30px"
            }
        }
        ,function (err) {
            console.log(err);
        })
};

var indexData = {
    menu: ''
}


var example1 = new Vue({
        el: '#example-10',
        data: {
            // user: {'name': '', 'age': '', 'school': ''},
            indexData: indexData
        },
        methods: {
            outlogin() {
                console.log('outlogin');
                httpClient('POST', '/login/out', {},
                    function (data) {
                        setToken('');
                        closeWebSocket();
                        window.location.href=servicePate+'/login';
                    },
                    function (err) {
                        errmessga(err);

                    })
            },
            change(path) {
                if (websocket!==null){
                    websocket.close();
                }
                if (path !== null&&path!=='') {
                    $("#page").load("/templates/" + path + ".html");
                }else {
                    $("#page").load("/templates/homePage.html");
                }

            },
            init() {
                $("#page").load("/templates/homePage.html");
                httpClient("POST", '/login/menu', null,
                    function (data) {
                        indexData.menu = data
                    },
                    function (err) {
                        console.log(err);
                        window.location.href=servicePate+'/login';
                    });
                httpClient("POST",'/login/check',{},
                    function (data) {
                        buttonClock.innerText=data;
                        if(data==="再次点击更新下班时间"){
                            buttonClock.style="height: 50px;width: 310px;font-size: 30px"
                        }
                        if(data==="上班签到"){
                            buttonClock.style="height: 50px;width: 150px;font-size: 30px"
                        }
                    }
                    ,function (err) {
                        console.log(err);
                    })
            }

        },
        mounted() {
            this.init();
        }
    }
);
