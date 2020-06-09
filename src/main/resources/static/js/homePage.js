

var vue1data={
    userInfo:''
}


new Vue({
    el: '#vue1',
    data: vue1data
})
httpClient("POST",'/login/userInfo',{},
    function (data) {
    vue1data.userInfo=data;
    },
    function (err) {
        errmessga(err);
    });