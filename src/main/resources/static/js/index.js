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
                        window.location.href=servicePate+'/login';
                    },
                    function (err) {
                        errmessga(err);
                    })
            },
            change(path) {
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
                    });
            }

        },
        mounted() {
            this.init();
        }
    }
);
