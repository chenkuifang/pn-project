layui.use(['layer','jquery','table','form'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		form = layui.form,
		table = layui.table,
		$ = layui.jquery;
	
	// 数据渲染(templet遵守laytpl 模板规则 )
	var tableIns = table.render({
		elem: '#dataTable',
	    url:'/role/listPage',
	    cols: [[
	      {type:'checkbox',widht:'5%'}
	      //,{field:'menuId', title: 'ID',widht:'5%'}
	      ,{field:'roleName',title:'角色名称',width:'10%'}
	      ,{field:'remark',title:'备注',width:'15%'}
	      ,{title:'创建人', width:'10%',templet:'<div>{{d.user.userName}}</div>'}
	      ,{field:'createTime',title:'创建时间',width:'20%',templet:'<div>{{g.dateTimeFormat(d.createTime)}}</div>'}
	      ,{field:'updateTime',title: '最后修改时间',width:'20%',templet:'<div>{{g.dateTimeFormat(d.updateTime)}}</div>'}
	      ,{field:'status',title:'状态',width:'5%',templet:'<div>{{statusFormat(d.status)}}</div>'}
	      ,{title: '操作',templet : '#operationTemplet',width:'15%'}
	    ]],
	    page: true
	});
	
	// 添加
	$(".add").click(function(){
		//当前层索引
		var index = layui.layer.open({
			title : "添加角色信息",
			type : 2,
			content : "/role/add"
		})
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})

	// 编辑操作
	$("body").on("click",".edit",function(){
		var id = $(this).attr("data-id");
		var index = layui.layer.open({
			title : "编辑角色信息",
			type : 2,
			content : "/role/edit/"+id
		})
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	});

	// 菜单设置
    $("body").on("click",".setMenu",function(){
        var id = $(this).attr("data-id");
        var index = layui.layer.open({
            title : "编辑角色菜单信息",
            type : 2,
            content : "/role/editMenu/"+id
        })
        $(window).resize(function(){
            layui.layer.full(index);
        })
        layui.layer.full(index);
    });
	
	// 搜索
	$("body").on("click",".searchBtn",function(){
		var roleName = $("#roleName").val();
		tableIns.reload({
			where : {
				roleName : roleName
			}
		});
	});

	// 单个删除操作
	$("body").on("click",".remove",function(){
		var id = $(this).attr("data-id");
		var index = layer.confirm('确定删除吗?',{icon:3, title:'提示信息'},function(index){
			$.ajax({
	             type:"post",
	             url:"/role/remove",
	             data:{roleId : id},
	             success:function(result){
	                 if(result["code"]===g.successCode){
	                     window.location.reload();
	                     layer.close(index);
	                 }
	                 if(result["code"]===g.failCode){
	                	 layer.msg(result["msg"]);
	                 }
	             }
	        });
		});
		
	});
	
	// 批量删除
	$("body").on("click",".removeBatch",function(){
		// 获取表格选中的所有行数据数组
		var checkStatus = table.checkStatus('dataTable');
		var data = checkStatus.data;
		var ids = [];
		$.each(data,function(i,item){
			ids.push(item.roleId);
		});
		//layer.alert(JSON.stringify(checkStatus.data));
		
		if(ids == null || ids.length == 0){
			return;
		}
		
		var index = layer.confirm('确定删除吗?',{icon:3, title:g.title},function(index){
			$.ajax({
	             type:"post",
	             url:"/role/removeBatch",
	             async:false,
	             contentType:"application/json; charset=utf-8",
	             data:JSON.stringify(ids),
	             traditional:true, //防止深度序列化
	             success:function(result){
	                 if(result["code"]==g.successCode){
	                     window.location.reload();
	                     layer.close(index);
	                 }
	                 if(result["code"]==g.failCode){
	                	 layer.msg(result["msg"]);
	                 }
	             }
	        });
		});
		
	});
	
	// 添加提交
	$("body").on("click",".addSubmit",function(){
		$.ajax({
             type:"post",
             url:"/role/save",
             data: $("form").serialize(),//表单数据
             success:function(result){
                 if(result["code"]==g.successCode){
                     layer.close(layer.index);
                     window.parent.location.reload();
                 }
                 if(result["code"]==g.failCode){
                	 layer.msg(result["msg"]);
                 }
             }
        });
	});

});

// 状态转换
function statusFormat(o) {
	var value = "未知";
	if(o == 0) {
		value = "暂停";
	}else if(o == 1) {
		value = "启用";
	}
	return value;
}