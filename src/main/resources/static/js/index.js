var $,tab,skyconsWeather;
layui.config({
	base : "js/"
}).use(['bodyTab','form','element','layer','jquery'],function(){
	var form = layui.form(),
		layer = layui.layer,
		element = layui.element();
		$ = layui.jquery;
		tab = layui.bodyTab();
		
//	//刷新后还原打开的窗口，注释该代码的原因因为刷新后还原窗口可以做到，但是因为每个窗口的数据是动态加载的，而且走的是document.ready方法，如果该文档已经加载完毕，
//	那么就不会走该方法从新获取动态数据，所以出现空白页面，所以注释掉，如果页面是静态内容的话就没问题
//	if(window.sessionStorage.getItem("menu") != null){
//		menu = JSON.parse(window.sessionStorage.getItem("menu"));
//		curmenu = window.sessionStorage.getItem("curmenu");
//		var openTitle = '';
//		var len = menu.length;
//		if(len > 0){
//			for(var i=0;i<len;i++){
//				openTitle = '';
//				if(menu[i].icon.split("-")[0] == 'icon'){
//					openTitle += '<i class="iconfont '+menu[i].icon+'"></i>';
//				}else{
//					openTitle += '<i class="layui-icon">'+menu[i].icon+'</i>';
//				}
//				openTitle += '<cite>'+menu[i].title+'</cite>';
//				openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="'+menu[i].layId+'">&#x1006;</i>';
//				element.tabAdd("bodyTab",{
//					title : openTitle,
//			        content :"<iframe src='"+menu[i].href+"' data-id='"+menu[i].layId+"'></frame>",
//			        id : menu[i].layId
//				})
//				//定位到刷新前的窗口
//				if(curmenu != "undefined"){
//					if(curmenu == '' || curmenu == "null"){  //定位到后台首页
//						element.tabChange("bodyTab",'');
//					}else if(JSON.parse(curmenu).title == menu[i].title){  //定位到刷新前的页面
//						element.tabChange("bodyTab",menu[i].layId);
//					}
//				}else{
//					element.tabChange("bodyTab",menu[menu.length-1].layId);
//				}
//			}
//		}
//	}

})

//打开新窗口
function addTab(_this) {
	tab.tabAdd(_this);
}