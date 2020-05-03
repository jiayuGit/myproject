



var pageDate = {
    page: ''
};

var example1 = new Vue({
        el: '#example-10',
        data: {
            // user: {'name': '', 'age': '', 'school': ''},
            pageDate: pageDate
        },
        methods: {
            // remove(index) {
            //     console.log('remove' + JSON.stringify(index));
            //
            // }
            init(){
                $("#page").load("/templates/auth.html");
            }

        },
    mounted(){
        this.init();
    }
    }
);