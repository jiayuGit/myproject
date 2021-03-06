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

/*
 * syalert.js
 * Version - 1.0
 * Copyright (c) 2019 sy
 */
var syalert = function () {
    return {
        syopen: function (id,data) {
            var list=data.list;
            example3Data.updataId=data.uuid;
            console.log(JSON.stringify(list))
            if (list!==null){

                example3Data.fruitIds=[];
                list.forEach(v =>{
                    example3Data.fruitIds.push(v.value);
                })
            }
            console.log(JSON.stringify( example3Data.fruitIds))

            var dom = $("#" + id);
            this.sycenter(dom);
            var name = dom.attr("sy-enter");
            var type = dom.attr("sy-type");
            var mask = dom.attr("sy-mask");
            dom.addClass(name);
            dom.show();
            var that = this;
            $("body").css({"overflow-y": "hidden"});
            if (mask == "true") {
                $("body").append("<div class='sy-mask' onclick=\"closeAlert('" + id + "')\"></div>");
                $(".sy-mask").fadeIn(300)
            }
            setTimeout(function () {
                dom.removeClass(name)
            }, 300);
            if (type == "tips") {
                setTimeout(function () {
                    that.syhide(id,data)
                }, 1500)
            }
        }, syhide: function (id) {
            if (typeof id == "undefined") {
                var dom = $(".sy-alert")
            } else {
                var dom = $("#" + id)
            }
            var name = dom.attr("sy-leave");
            dom.addClass(name);
            $(".sy-mask").fadeOut(300);
            setTimeout(function () {
                dom.hide();
                dom.removeClass(name);
                $(".sy-mask").remove();
                $("body").css({"overflow-y": "auto"})
            }, 300)
        }, sycenter: function (dom) {
            var mgtop = parseFloat(dom.height() / 2);
            dom.css({"top": "50%", "margin-top": "-" + mgtop + "px"})
        }
    }
}();

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
    if (id === 'alert4') {
        httpClient('POST', '/role/updateUser', {
                list: example3Data.fruitIds,
                uuid: example3Data.updataId
            }
            , function (data) {
                selectPage(pageButton.cur, pageSize);
            },
            function (err) {
                console.log(JSON.stringify(err));
            })
    }
    syalert.syhide(id);
}

var example3Data = {
    checkedNames: [],
    updataId: '',
    fruits: [],
    fruitIds: [],
    // 初始化全选按钮, 默认不选
    isCheckedAll: false
};
var example3 = new Vue({
    el: '#example-3',
    data() {
        return example3Data
    },
    methods: {
        checkedOne(fruitId) {
            let idIndex = this.fruitIds.indexOf(fruitId)

            if (idIndex >= 0) {
                // 如果已经包含了该id, 则去除(单选按钮由选中变为非选中状态)
                this.fruitIds.splice(idIndex, 1)
                console.log("存在")
            } else {
                // 选中该checkbox
                this.fruitIds.push(fruitId)
                console.log("不存在")
            }
            console.log(JSON.stringify(this.fruitIds))
        },
        checkedAll(data) {
            this.isCheckedAll = data
            console.log(data)
            if (data) {
                // 全选时
                this.fruitIds = []
                this.fruits.forEach(function (fruit) {
                    this.fruitIds.push(fruit.value)
                }, this)
            } else {
                this.fruitIds = []
            }
            console.log(JSON.stringify(this.fruitIds))
        },
        deleteFruits() {

        }
    }
});
selectRolePage(1, 100);

function selectRolePage(pageNub, size) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function (ev) {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            console.log(xmlHttp.getResponseHeader('content-type'));
            if (xmlHttp.getResponseHeader(content_type) === applction_json) {
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code === 0) {
                    example3Data.fruits = data.data;
                    console.log(JSON.stringify(data.data))
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
    xmlHttp.open("POST", servicePate + '/role/listKey', true);
    xmlHttp.setRequestHeader('content-type', 'application/json');
    xmlHttp.setRequestHeader('access-token', getToken());
    xmlHttp.send(JSON.stringify({
        startPage: pageNub !== null ? pageNub : 1,
        pageSize: size != null ? size : 10
    }));
}