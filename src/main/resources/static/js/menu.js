layui.use(['layer','jquery','table'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		table = layui.table,
		$ = layui.jquery;
	
	// 数据渲染
	var tableInis = table.render({
		elem: '#dataTable',
	    url:'/menu/listData',
	    id:'menuId',
	    // 设置表头
	    cols: [[
	      {type:'checkbox',widht:'5%'}
	      //,{field:'menuId', title: 'ID',width:40}
	      ,{field:'name',title:'菜单名称',width:'10%'}
	      ,{field:'url',title:'链接地址',width:'20%'}
	      ,{field:'type',title:'类型', width:'5%'}
	      ,{field:'orderNum',title:'排序',width:'5%'}
	      ,{field:'createTime',title:'创建时间',width:'20%',templet:'<div>{{g.dateTimeFormat(1511841600000)}}</div>'}
	      ,{field:'updateTime',title: '最后修改时间',width:'20%'}
	      ,{title: '操作',templet : '#operationTemplet',width:'15%'}
	    ]],
	    page: true
	});

	//添加
	$(".add").click(function(){
		var index = layui.layer.open({
			title : "添加会员",
			type : 2,
			content : "addUser.html",
			success : function(layero, index){
				layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})

	// 编辑操作
	$("body").on("click",".edit",function(){
		layer.alert('您点击了会员编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'文章编辑'});
	})

	// 删除操作
	$("body").on("click",".remove",function(){
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			for(var i=0;i<usersData.length;i++){
				if(usersData[i].usersId == _this.attr("data-id")){
					usersData.splice(i,1);
					usersList(usersData);
				}
			}
			layer.close(index);
		});
	})

});