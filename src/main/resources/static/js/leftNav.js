/**
 * 左边导航的显示格式HTML
 * @param data json格式数据
 * @returns
 */
function navBar(data){
	var ulHtml = '<ul class="layui-nav layui-nav-tree">';
	var childData = data;
	
	for(var i=0,len1=data.length; i<len1; i++){
		var flag = 0;
		ulHtml += '<li class="layui-nav-item">';
		//根目录
		if(data[i].parentId == 0){
			for(var j=0,len2=childData.length;j<len2;j++){
				if(data[i].menuId != childData[j].parentId) {
					continue;
				}
				
				flag += 1;
				if(flag == 1){
					ulHtml += '<a href="javascript:;">';
					//图标
					if(data[i].icon != undefined && data[i].icon != ''){
						if(data[i].icon.indexOf("icon-") != -1){
							ulHtml += '<i class="iconfont '+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
						}else{
							ulHtml += '<i class="layui-icon" data-icon="'+data[i].icon+'">'+data[i].icon+'</i>';
						}
					}
					ulHtml += '<cite>'+data[i].name+'</cite>';
					ulHtml += '<span class="layui-nav-more"></span>';
					ulHtml += '</a>'
					ulHtml += '<dl class="layui-nav-child">';
				}
				
				ulHtml += '<dd><a href="javascript:;" data-url="'+childData[j].url+'">';
				//子集图标
				if(childData[j].icon != undefined && childData[j].icon != ''){
					if(childData[j].icon.indexOf("icon-") != -1){
						ulHtml += '<i class="iconfont '+childData[j].icon+'" data-icon="'+childData[j].icon+'"></i>';
					}else{
						ulHtml += '<i class="layui-icon" data-icon="'+childData[j].icon+'">'+data[i].children[j].icon+'</i>';
					}
				}
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
			if(data[i].icon != undefined && data[i].icon != ''){
				if(data[i].icon.indexOf("icon-") != -1){
					ulHtml += '<i class="iconfont '+data[i].icon+'" data-icon="'+data[i].icon+'"></i>';
				}else{
					ulHtml += '<i class="layui-icon" data-icon="'+data[i].icon+'">'+data[i].icon+'</i>';
				}
			}
			ulHtml += '<cite>'+data[i].name+'</cite></a>';
		}
		
		ulHtml += '</li>'
	}
	ulHtml += '</ul>';
	return ulHtml;
}
