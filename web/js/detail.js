(function(document) {
    var sentData = window.location.search.split("?")[1].split("&")[0].split("=")[1],
        teachersName = window.location.search.split("?")[1].split("&")[1].split("=")[1],
        college = window.location.search.split("?")[1].split("&")[2].split("=")[1],
        doc = document,
        tel;
    window.onload = function() {
        getDetail(sentData);
        var ele = {
                teacherName: doc.getElementById("teachersName"),
                oCopyBtn: doc.getElementById("copy"),
                oCall: doc.getElementById("call"),
                aDetailInfos: doc.querySelectorAll(".detail-info p"),
                aInfos: doc.querySelectorAll(".detail-info p span:nth-child(2)"),
                oOffAdd: doc.getElementById("offAdd"),
                oOffNumber: doc.getElementById("offNumber"),
                oOffCollege: doc.getElementById("offCollege"),
                oPrompt: doc.getElementsByClassName("prompt")[0],
                clearColor: function(obj,attr) {
                    for(var i=0,len=obj.length;i<len;i++) {
                        obj[i].style.cssText="backgroundColor:attr;color:#000";
                    }
                },
                selectInfo: function(element) {
                    var text = document.getElementById(element);
                    if (document.body.createTextRange) {
                        var range = document.body.createTextRange();
                        range.moveToElementText(text);
                        range.select();
                    } else if (window.getSelection) {
                        var selection = window.getSelection();
                        var range = document.createRange();
                        range.selectNodeContents(text);
                        selection.removeAllRanges();
                        selection.addRange(range);
                    } else {
                        alert("none");
                    }
                    document.execCommand("Copy");
                    ele.oPrompt.style.opacity = "1";
                    setTimeout(function() {
                        ele.oPrompt.style.opacity = "0";
                    },5000)
                }
            }; 
        ele.teacherName.innerHTML = decodeURI(teachersName);
        ele.oOffCollege.innerHTML = decodeURI(college);
        for(var j=0,len=ele.aDetailInfos.length;j<len;j++) {
            (function(j) {
                ele.aDetailInfos[j].onclick = function() {
                    switch(j) {
                        case 0:
                            ele.selectInfo("offCollege");
                            break;
                        case 1:
                            ele.selectInfo("offAdd");
                            break;
                        case 2:
                            ele.selectInfo("offNumber");
                            break;
                    }
                }
            })(j);
        }
        document.addEventListener("click",function() {
            ele.clearColor(ele.aDetailInfos,"#fff");
        })
        function phone(tel) {
            this.location.href="tel://"+tel;
        }
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
                    alert("信息获取失败，请刷新页面后重试");
                },
                success:function (data) {
                    $("#offAdd").html(data.offAdd)
                    $("#offNumber").html(data.offNumber)
                    tel=data.offNumber;
                    ele.oCall.onclick = function() {
                        phone(tel);
                    }
                }
            });
        }
    };
})(document);
