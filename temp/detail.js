// 渲染detail页面数据
(function() {
    var sentData = window.location.search.split("?name=")[1];
    window.DOMContentLoaded = function () {
        getDetail(sentData);
    }
    getDetail(sentData);
    //获取个人信息的函数
    function getDetail(thisID) {
        $.ajax({
            type:"POST",
            url:"./MyController",
            dataType:"json",
            contentType:"application/x-www-form-urlencoded;charset=utf-8",
            data:{
                gid: thisID,
                action: "getDetail"
            },
            error:function () {
                alert("信息获取失败，请重新操作一遍或刷新页面");
            },
            success:function (data) {
                $("#poSition").html(data.poSition)
                $("#teachersName").html(data.teachersName)
                $("#offAdd").html(data.offAdd)
                $("#qqNumber").html(data.qqNumber)
                $("#telNumber").html(data.telNumber)
                $("#offNumber").html(data.offNumber) 
            }
        });
    }
})();
