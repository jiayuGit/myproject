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
var example3Data = {
    role: {
        name: '',
        fid: ''
    }
};
var example5Data = {
    flow: {
        flowName: '',
        remark: '',
        list: []
    },
    items: [{text: 'A', value: 'a'}, {text: 'B', value: 'b'}, {text: 'C', value: 'c'}],

    form: {
        name: '',
        selectOne: '2',
        list: [{
            nodeName: '',
            roleFid: ''
        }]
    }
};
var example6Data = {
    fruits: []
};
var example3 = new Vue({
    el: '#example-3',
    data: example3Data
});
httpClient("POST", '/role/listKey',
    {
        startPage: 1,
        pageSize: 100
    },
    function (data) {
        example5Data.items = data;
    },
    function (err) {
        errmessga(err);
    })
var example5 = new Vue({
    el: '#example-5',
    data: example5Data,
    methods: {
        addAdministrator() {
            this.form.list.push({
                nodeName: ''
            })
        },
        delAdministrator(myindex) {
            console.log(myindex)
            this.form.list.splice(myindex, 1);// = this.form.list.filter((currentValue, index) = > index != myindex)
        },
        change(data, value) {
            data.roleFid = value;
        }

    }
});

var syalert = function () {
    return {
        syopen: function (id, data) {
            if (data !== null) {
                example3Data.role.fid = data.fid;
                httpClient("POST", '/flow/selectFlowNodeInfo',
                    {flowFid: data.fid},
                    function (data) {
                        example6Data.fruits=data;
                    },
                    function (err) {
                        console.log(err)
                    })
            }
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
                    that.syhide(id, data)
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
                if (data.code === 0 && data.data !== null) {

                    pageDate.list = data.data.data;
                    pageButton.all = parseInt(data.data.total / size) === data.data.total / size ? data.data.total / size : parseInt(data.data.total / size) + 1;
                    pageButton.cur = pageNub;
                    return;
                } else {
                    errmessga(data.message);
                }


            } else {
                console.log(xmlHttp.responseText);
                errmessga('服务器正在抢修中!!');
            }

        }
    }
    xmlHttp.open("POST", servicePate + '/flow/selectPage', true);
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
    if ('alert1' === id) {
        httpClient('POST', '/flow/delete',
            {
                fid: example3Data.role.fid
            },
            function (data) {
                selectPage(pageButton.cur, pageSize);
            },
            function (err) {
                console.log(JSON.stringify(err));
            })
    }
    if (id === 'alert4') {
        httpClient('POST', '/menu/upMenu',
            {
                fid: example3Data.role.fid,
                menuName: example3Data.role.name,
                path: example3Data.role.path
            },
            function (data) {
                selectPage(pageButton.cur, pageSize);
            },
            function (err) {
                console.log(JSON.stringify(err));
            })
    }
    if (id === 'alert5') {
        httpClient('POST', '/flow/create',
            {
                flowName: example5Data.flow.flowName,
                remark: example5Data.flow.remark,
                list: example5Data.form.list
            },
            function (data) {
                selectPage(pageButton.cur, pageSize);
            },
            function (err) {
                console.log(JSON.stringify(err));
            })
    }
    if (id === 'alert6') {
        httpClient('POST', '/menu/updateAuth',
            {
                fid: example6Data.updataId,
                list: example6Data.fruitIds
            },
            function (data) {
                selectPage(pageButton.cur, pageSize);
            },
            function (err) {
                console.log(JSON.stringify(err));
            })
    }
    syalert.syhide(id);
}

function selectAuthPage(pageNub, size) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function (ev) {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            console.log(xmlHttp.getResponseHeader('content-type'));
            if (xmlHttp.getResponseHeader(content_type) === applction_json) {
                let data = JSON.parse(xmlHttp.responseText);
                if (data.code === 0) {
                    example6Data.fruits = data.data;
                    console.log("/menu/all" + JSON.stringify(data.data))
                    return;
                } else {
                    errmessga(data.message);
                }


            } else {
                console.log(xmlHttp.responseText);
                errmessga('服务器正在抢修中!!');
            }

        }
    }
    xmlHttp.open("POST", servicePate + '/menu/listKey', true);
    xmlHttp.setRequestHeader('content-type', 'application/json');
    xmlHttp.setRequestHeader('access-token', getToken());
    xmlHttp.send(JSON.stringify({
        startPage: pageNub !== null ? pageNub : 1,
        pageSize: size != null ? size : 10
    }));
}


var example6 = new Vue({
    el: '#example-6',
    data() {
        return example6Data
    }
});
selectAuthPage(1, 100);