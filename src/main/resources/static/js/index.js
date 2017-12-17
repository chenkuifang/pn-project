var $,tab,skyconsWeather;
layui.config({
	base : "js/"
}).use(['bodyTab','form','element','layer','jquery'],function(){
	var form = layui.form(),
		layer = layui.layer,
		element = layui.element();
		$ = layui.jquery;
		tab = layui.bodyTab();
		
	//刷新后还原打开的窗口
	if(window.sessionStorage.getItem("menu") != null){
		menu = JSON.parse(window.sessionStorage.getItem("menu"));
		curmenu = window.sessionStorage.getItem("curmenu");
		var openTitle = '';
		var len = menu.length;
		if(len > 0){
			for(var i=0;i<len;i++){
				openTitle = '';
				if(menu[i].icon.split("-")[0] == 'icon'){
					openTitle += '<i class="iconfont '+menu[i].icon+'"></i>';
				}else{
					openTitle += '<i class="layui-icon">'+menu[i].icon+'</i>';
				}
				openTitle += '<cite>'+menu[i].title+'</cite>';
				openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="'+menu[i].layId+'">&#x1006;</i>';
				element.tabAdd("bodyTab",{
					title : openTitle,
			        content :"<iframe src='"+menu[i].href+"' data-id='"+menu[i].layId+"'></frame>",
			        id : menu[i].layId
				})
				//定位到刷新前的窗口
				if(curmenu != "undefined"){
					if(curmenu == '' || curmenu == "null"){  //定位到后台首页
						element.tabChange("bodyTab",'');
					}else if(JSON.parse(curmenu).title == menu[i].title){  //定位到刷新前的页面
						element.tabChange("bodyTab",menu[i].layId);
					}
				}else{
					element.tabChange("bodyTab",menu[menu.length-1].layId);
				}
			}
		}
		
	}

})

//打开新窗口
function addTab(_this) {
	tab.tabAdd(_this);
}