console.log(getToken());
var pageButton = {
    all: 0, //总页数
    cur: 1//当前页码
}
var pageSize = 3;
var page = 1;
var pageDate = {
    page: page,
    list: null
};

var example1 = new Vue({
        el: '#example-1',
        data: {
            // user: {'name': '', 'age': '', 'school': ''},
            pageDate: pageDate,
            syalert: syalert
        },
        methods: {
            remove(index) {
                console.log('remove' + JSON.stringify(index));

            }

        }
    }
);
selectPage(page, pageSize);

function selectPage(pageNub, size) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function (ev) {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            console.log(xmlHttp.getResponseHeader('content-type'));
            if (xmlHttp.getResponseHeader(content_type) === applction_json) {
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code === 0) {
                    pageDate.list = data.data.data;
                    pageButton.all = parseInt(data.data.total / size + 1);
                    pageButton.cur = pageNub;
                    return;
                } else {
                    nologin(data.message);
                }


            } else {
                console.log(xmlHttp.responseText);
                nologin('服务器正在抢修中!!');
            }

        }
    }
    xmlHttp.open("POST", servicePate + '/role/userPage', true);
    xmlHttp.setRequestHeader('content-type', 'application/json');
    xmlHttp.setRequestHeader('access-token', getToken());
    xmlHttp.send(JSON.stringify({
        startPage: pageNub !== null ? pageNub : 1,
        pageSize: size != null ? size : 10
    }));
}


var pageBar = new Vue({
    el: '.page-bar',
    data: pageButton,
    watch: {
        cur: function (oldValue, newValue) {
            console.log(arguments);
        }
    },
    methods: {
        btnClick: function (data) {
            if (data != this.cur) {
                this.cur = data;
                selectPage(data, pageSize);
            }
        },
        pageClick: function () {
            selectPage(this.cur, pageSize);
            console.log('现在在' + this.cur + '页');
        }
    },
    computed: {
        indexs: function () {
            var left = 1;
            var right = this.all;
            var ar = [];
            if (this.all >= 5) {
                //这里最大范围从3到6，如果到达7，那么下边加2变成9，已经超过最大的范围值
                if (this.cur > 3 && this.cur < this.all - 1) {
                    //以4为参考基准，左面加2右边加2
                    left = this.cur - 2
                    right = this.cur + 2
                } else {
                    if (this.cur <= 3) {
                        left = 1
                        right = 5
                    } else {
                        right = this.all
                        left = this.all - 4
                    }
                }
            }
            while (left <= right) {
                ar.push(left)
                left++
            }
            console.log(ar);
            return ar
        }
    }
})

function ok(id) {
    console.log(id)
    syalert.syhide(id);
}
var example3Data =  {
    checkedNames: []
};
var example3 = new Vue({
    el: '#example-3',
    data:example3Data
});
