(function() {
	var search = document.getElementById('search');
	var college = document.getElementById('college');
	var xy = document.getElementsByClassName('xy');
	var everyCollege = college.querySelectorAll("li>ul");
	var len = xy.length;
	var xy_name = document.getElementsByClassName('xy-name');
	var query_res = document.getElementsByClassName('query-res');
	var lists = college.getElementsByTagName('ul');
	var suggest = document.getElementById('suggest');
	var nth = document.getElementById('nothing');
	var classToCollege = {
	    "computer": "信息院",
	    "math": "数学院",
	    "electrical": "电气院",
	    "law": "法学院",
	    "materialElectirc": "物电院",
	    "art": "文学院",
	    "business": "工管院",
	    "manager": "经管院",
	    "hicivilde": "土木院",
	    "foreign": "外国院",
	    "newsMovie": "新影院",
	    "ylsy": "岳麓书院",
	    "mechanics": "机械院",
	    "material": "材料院",
	    "buidding": "建筑院",
	    "chemistry": "化工院",
	    "environnment": "环境院",
	    "biology": "生物",
	    "design": "设计院",
	    "robot": "机器人学院",
	    "marxist": "马克思主义学院",
	    "economic-trade": "经贸院",
	    "jintong": "金统院",
	    "edcucation": "教育学院",
	    "sport": "体育学院"
	};
	(function(){
	    for(var i=0;i<len;i++){
	        xy[i].onclick = function(){
	            var curList = this.parentNode;
	            var listContent = curList.getElementsByTagName('ul')[0];
	            if(listContent.classList.contains("show")){
	                listContent.classList.remove("show");
	                listContent.classList.add("hide");
	                this.style.backgroundImage = 'url(./images/fold.png)';
	            }else{
	                listContent.classList.remove("hide");
	                listContent.classList.add("show");
	                this.style.backgroundImage = 'url(./images/unfold.png)';
	            };
	        };
	    }
	})();
	document.addEventListener("click",function() {
	    document.getElementById("suggest").innerHTML="";
	});
	(function() {
	    var clickArea = college.querySelectorAll("#college>li");
	    for(var i=0,len=clickArea.length;i<len;i++) {
	        (function(i){
	            clickArea[i].open=false;
	            clickArea[i].onclick = function(e) {
	                e=e||window.e;
	                if(e.stopPropagation) {
	                    e.stopPropagation();
	                }else {
	                    e.cancelBubble=true;
	                }
	                var aimArea = clickArea[i].getElementsByTagName("ul");
	                var getId = aimArea[0].className.split("show")[0];
	                getEveryAll(getId);
	                clickArea[i].onclick = null;
	            }
	        })(i);
	    }

	    search.onchange = function(){
	        if(search.value!="") {
	            var val = search.value;
	            var en = encodeURI(val);
	            var oUl = document.getElementById("suggest");
	            getSearchData(en);
	            oUl.innerHTML = "";
	        }
	    };
	    function getEveryAll(thisID) {
	        //console.log(thisID);
	        $.ajax({
	            type:"POST",
	            url:"./MyController",
	            dataType:"json",
	            contentType:"application/x-www-form-urlencoded;charset=utf-8",
	            data:{
	                gid: thisID,
	                action: "getEveryAll"
	            },
	            error:function () {
	                alert("暂无教师信息，可以尝试刷新后再次操作");
	            },
	            success:function (data) {
	                var frag = document.createDocumentFragment();
	                var oUl = document.getElementsByClassName(thisID)[0];
	                for(var i=0,len=data.hide.length;i<len;i++) {
	                    var item = document.createElement("li");
	                    item.innerHTML = data.hide[i];
	                    (function(i){
	                        var temp=data.hide[i];
	                        item.onclick=function(e) {
	                            if(e.stopPropagation) {
	                                e.stopPropagation();
	                            }else {
	                                e.cancelBubble = true;
	                            }
	                            var sentData = encodeURI(temp);
	                            window.location.href="detail.html?name="+sentData;
	                        }
	                    })(i);
	                    frag.appendChild(item);   
	                }
	                oUl.appendChild(frag);
	            }
	        });
	    }

	    function getSearchData(thisID) {
	        $.ajax({
	            type:"POST",
	            url:"./MyController",
	            dataType:"json",
	            contentType:"application/x-www-form-urlencoded;charset=utf-8",
	            data:{
	                gid: thisID,
	                action: "getSearchData"
	            },
	            error:function () {
	                alert("搜索不到对应的信息，请尝试更换关键词");
	            },
	            success:function (data) {
	                var frag = document.createDocumentFragment();
	                var suggestArea = document.getElementById("suggest");
	                var num=0;
	                for(var key in data) {
	                    var item = document.createElement("li");
	                    (function(key) {
	                        item.innerHTML = key+"<span>"+classToCollege[data[key]]+"</span>";
	                        item.onclick = function() {
	                            var sentData = encodeURI(key);
	                            window.location.href="detail.html?name="+sentData;
	                            item.onclick=null;
	                        };
	                        num++;
	                    })(key);
	                    frag.appendChild(item);
	                }
	                suggestArea.appendChild(frag);
	            }
	        });
	    }
	})();
})();
