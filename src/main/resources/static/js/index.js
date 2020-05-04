

var indexData= {
    menu:''
}


var example1 = new Vue({
        el: '#example-10',
        data: {
            // user: {'name': '', 'age': '', 'school': ''},
            indexData: indexData
        },
        methods: {
            // remove(index) {
            //     console.log('remove' + JSON.stringify(index));
            //
            // }
            change(path) {
                if (path != null) {
                    $("#page").load("/templates/" + path + ".html");
                }
            },
            init() {
                $("#page").load("/templates/auth.html");
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