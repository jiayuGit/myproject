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
            }

        },
        mounted() {
            this.init();
        }
    }
);
