/**
 * 	定义bodyTab模块(请不要更新layui的版本,因为layui的模块可能有些版本不兼容)特别是modules/layer不能换
 *  1.如果使用element.js的压缩版会引起左边菜菜单有时候失效，但如果引入未压缩版就没问题。why?
 *  @author: QuiFar 
*/
var tabFilter,menu=[],liIndex,curNav,delMenu;
layui.define(["element","jquery"],function(exports){
	var element = layui.element(),
		$ = layui.jquery,
		layId,
		Tab = function(){
			this.tabConfig = {
				closed : true,
				openTabNum : 10,
				tabFilter : "bodyTab"
			}
		};
		
	// 加载数据
    $.get(g.rootPath + "/user/listMenu",function(data){
		if( data != undefined) {
			if($(".navBar").html() == '') {
				var _this = this;
				//读取数据
				var navInfo = navBar(data);
				$(".navBar").html(navInfo).height($(window).height()-230);
				element.init();  //初始化页面元素
				$(window).resize(function(){
					$(".navBar").height($(window).height()-230);
				})
			}
		}
	},"json").then(function(){
		// 加载数据完成后绑定点击事件
		$(".layui-nav .layui-nav-item a").on("click",function(){
			addTab($(this));
			$(this).parent("li").siblings().removeClass("layui-nav-itemed");
		})
	} );
		
	//参数设置
	Tab.prototype.set = function(option) {
		var _this = this;
		$.extend(true, _this.tabConfig, option);
		return _this;
	};

	//通过title获取lay-id
	Tab.prototype.getLayId = function(title){
		$(".layui-tab-title.top_tab li").each(function(){
			if($(this).find("cite").text() == title){
				layId = $(this).attr("lay-id");
			}
		})
		return layId;
	}
	//通过title判断tab是否存在
	Tab.prototype.hasTab = function(title){
		var tabIndex = -1;
		$(".layui-tab-title.top_tab li").each(function(){
			if($(this).find("cite").text() == title){
				tabIndex = 1;
			}
		})
		return tabIndex;
	}

	//左侧导航tab操作
	var tabIdIndex = 0;
	Tab.prototype.tabAdd = function(_this){
		if(window.sessionStorage.getItem("menu")){
			menu = JSON.parse(window.sessionStorage.getItem("menu"));
		}
		var that = this;
		var closed = that.tabConfig.closed,
			openTabNum = that.tabConfig.openTabNum;
			tabFilter = that.tabConfig.tabFilter;
		// $(".layui-nav .layui-nav-item a").on("click",function(){
			if(_this.find("i.iconfont,i.layui-icon").attr("data-icon") != undefined){
				var title = '';
				if(that.hasTab(_this.find("cite").text()) == -1 && _this.siblings("dl.layui-nav-child").length == 0){
					if($(".layui-tab-title.top_tab li").length == openTabNum){
						layer.msg('只能同时打开'+openTabNum+'个选项卡哦。不然系统会卡的！');
						return;
					}
					tabIdIndex++;
					if(_this.find("i.iconfont").attr("data-icon") != undefined){
						title += '<i class="iconfont '+_this.find("i.iconfont").attr("data-icon")+'"></i>';
					}else{
						title += '<i class="layui-icon">'+_this.find("i.layui-icon").attr("data-icon")+'</i>';
					}
					title += '<cite>'+_this.find("cite").text()+'</cite>';
					title += '<i class="layui-icon layui-unselect layui-tab-close" data-id="'+tabIdIndex+'">&#x1006;</i>';
					// 添加tab
					element.tabAdd(tabFilter, {
				        title : title,
				        content :"<iframe src='"+_this.attr("data-url")+"' data-id='"+tabIdIndex+"'></frame>",
				        id : new Date().getTime()
				    })

					//当前窗口内容
					var curmenu = {
						"icon" : _this.find("i.iconfont").attr("data-icon")!=undefined ? _this.find("i.iconfont").attr("data-icon") : _this.find("i.layui-icon").attr("data-icon"),
						"title" : _this.find("cite").text(),
						"href" : _this.attr("data-url"),
						"layId" : new Date().getTime()
					}
					menu.push(curmenu);
					window.sessionStorage.setItem("menu",JSON.stringify(menu)); //打开的窗口
					window.sessionStorage.setItem("curmenu",JSON.stringify(curmenu));  //当前的窗口
					element.tabChange(tabFilter, that.getLayId(_this.find("cite").text()));
				}else{
					//当前窗口内容
					var curmenu = {
						"icon" : _this.find("i.iconfont").attr("data-icon")!=undefined ? _this.find("i.iconfont").attr("data-icon") : _this.find("i.layui-icon").attr("data-icon"),
						"title" : _this.find("cite").text(),
						"href" : _this.attr("data-url"),
						"layId" : new Date().getTime()
					}
					window.sessionStorage.setItem("curmenu",JSON.stringify(curmenu));  //当前的窗口
					element.tabChange(tabFilter, that.getLayId(_this.find("cite").text()));
				}
			}
		// })
	}
	// 删除tab 触发
	$("body").on("click",".top_tab li",function(){
		
		//切换后获取当前窗口的内容
		var curmenu = '';
		var menu = JSON.parse(window.sessionStorage.getItem("menu"));
		curmenu = menu[$(this).index()-1];
		if($(this).index() == 0){
			window.sessionStorage.setItem("curmenu",'');
		}else{
			window.sessionStorage.setItem("curmenu",JSON.stringify(curmenu));
			if(window.sessionStorage.getItem("curmenu") == "undefined"){
				//如果删除的不是当前选中的tab,则将curmenu设置成当前选中的tab
				if(curNav != JSON.stringify(delMenu)){
					window.sessionStorage.setItem("curmenu",curNav);
				}else{
					window.sessionStorage.setItem("curmenu",JSON.stringify(menu[liIndex-1]));
				}
			}
		}
		element.tabChange(tabFilter,$(this).attr("lay-id")).init();
	})

	//删除tab
	$("body").on("click",".top_tab li i.layui-tab-close",function(){
		//删除tab后重置session中的menu和curmenu
		liIndex = $(this).parent("li").index();
		var menu = JSON.parse(window.sessionStorage.getItem("menu"));
		//获取被删除元素
		delMenu = menu[liIndex-1];
		var curmenu = window.sessionStorage.getItem("curmenu")=="undefined" ? "undefined" : window.sessionStorage.getItem("curmenu")=="" ? '' : JSON.parse(window.sessionStorage.getItem("curmenu"));
		if(JSON.stringify(curmenu) != JSON.stringify(menu[liIndex-1])){  //如果删除的不是当前选中的tab
			// window.sessionStorage.setItem("curmenu",JSON.stringify(curmenu));
			curNav = JSON.stringify(curmenu);
		}else{
			if($(this).parent("li").length > liIndex){
				window.sessionStorage.setItem("curmenu",curmenu);
				curNav = curmenu;
			}else{
				window.sessionStorage.setItem("curmenu",JSON.stringify(menu[liIndex-1]));
				curNav = JSON.stringify(menu[liIndex-1]);
			}
		}
		menu.splice((liIndex-1), 1);
		window.sessionStorage.setItem("menu",JSON.stringify(menu));
		element.tabDelete("bodyTab",$(this).parent("li").attr("lay-id")).init();
	})

	var bodyTab = new Tab();
	exports("bodyTab",function(option){
		return bodyTab.set(option);
	});
});

/**
 * 左边导航内容拼接
 * @param result
 * @returns
 */
function navBar(result){
	var ulHtml = '<ul class="layui-nav layui-nav-tree">';
	var data = result.data;
	var childData = data;
	for(var i=0,len1=data.length; i<len1; i++){
		var flag = 0; 
		
		//根目录
		if(data[i].parentId == 0){
			// 如果想不展开 把 layui-nav-itemed 删除即可
			ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
			
			for(var j=0,len2=childData.length;j<len2;j++){
				if(data[i].menuId != childData[j].parentId) {
					continue;
				}
				
				flag += 1;
				//如果是根目录，并且有子目录则拼接 nav-more 样式
				if(flag == 1){
					ulHtml += '<a href="javascript:;">';
					//图标
					ulHtml += '<i class="iconfont '+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
					ulHtml += '<cite>'+data[i].name+'</cite>';
					ulHtml += '<span class="layui-nav-more"></span>';
					ulHtml += '</a>'
					ulHtml += '<dl class="layui-nav-child">';
				}
				
				ulHtml += '<dd><a href="javascript:;" data-url="'+childData[j].url+'">';
				//子集图标
				ulHtml += '<i class="iconfont '+childData[j].icon+'" data-icon="'+childData[j].icon+'"></i>';
				ulHtml += '<cite>'+childData[j].name+'</cite></a></dd>';
			}
			ulHtml += "</dl>"
		}else{ 
			//去除子集作为根目录展示(随便一个>1的数)
			flag = 99;
		}
		//无子集的根目录
		if(flag == 0){
			ulHtml += '<a href="javascript:;" data-url="'+data[i].url+'">';
			ulHtml += '<i class="iconfont '+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
			ulHtml += '<cite>'+data[i].name+'</cite></a>';
		}
		
		ulHtml += '</li>'
	}
	ulHtml += '</ul>';
	return ulHtml;
}

