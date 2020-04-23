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
    role: {
        name: ''
    }
};
var example3 = new Vue({
    el: '#example-3',
    data: example3Data
});
var example5 = new Vue({
    el: '#example-5',
    data: example5Data
});
var syalert = function () {
    return {
        syopen: function (id, data) {
            if (data !== null) {
                example3Data.role.fid = data.fid;
                example3Data.role.name = data.name;
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
                if (data.code === 0) {
                    pageDate.list = data.data.data;
                    pageButton.all = parseInt(data.data.total / size) === data.data.total / size ? data.data.total / size : parseInt(data.data.total / size) + 1;
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
    xmlHttp.open("POST", servicePate + '/role/list', true);
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
        httpClient('POST', '/role/delete',
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
        httpClient('POST', '/role/updata',
            {
                fid: example3Data.role.fid,
                name: example3Data.role.name
            },
            function (data) {
                selectPage(pageButton.cur, pageSize);
            },
            function (err) {
                console.log(JSON.stringify(err));
            })
    }
    if (id === 'alert5') {
        httpClient('POST', '/role/add',
            {
                name: example5Data.role.name
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

let all = {
    name: 'all',
    children: {
        A: {
            name: 'A',
            children: {
                a1: {
                    name: 'a1',
                    children: {
                        a11: {
                            name: 'a11',
                            children: null
                        },
                        a12: {
                            name: 'a12',
                            children: null
                        }
                    }
                },
                a2: {
                    name: 'a2',
                    children: {
                        b21: {
                            name: 'b21',
                            children: null
                        }
                    }
                }
            }
        },
        B: {
            name: 'B',
            children: {
                b1: {
                    name: 'b1',
                    children: {
                        b11: {
                            name: 'b11',
                            children: null
                        },
                        b12: {
                            name: 'b12',
                            children: null
                        }
                    }
                },
                b2: {
                    name: 'b2',
                    children: {
                        b21: {
                            name: 'b21',
                            children: null
                        }
                    }
                }
            }
        }
    }
}


const example6 = new Vue({
    el: "#example-6",
    components: {
        tree: {
            template: '\n' +
                '<div>\n' +
                '    <ul>\n' +
                '        <li   >\n' +
                '            <span @click="isshow()">{{treelist.name}}</span>\n' +
                '                <tree  v-for="item in treelist.children"   \n' +
                '                    v-if="isFolder"\n' +
                '                    v-show="open" \n' +
                '                    :treelist="item" \n' +
                '                    :keys="item"\n' +
                '                ></tree>\n' +
                '        </li>\n' +
                '    </ul>\n' +
                '</div>\n' +
                '\n',
            name: 'tree',
            props: ['treelist'],
            data() {
                return {
                    open: false
                }
            }, computed: {
                isFolder: function () {
                    return this.treelist.children
                }
            }
            , methods: {
                isshow() {
                    if (this.isFolder) {
                        this.open = !this.open
                    }
                }
            }
        }

    },
    data: {
        treeList: all
    }
})

