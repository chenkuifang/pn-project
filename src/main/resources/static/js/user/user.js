var $form;
var form;
var $;
layui.use(['form','layer','table','laydate'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		table = layui.table;
	
		form = layui.form;
		$ = layui.jquery;
		$form = $('form');

        // 数据渲染(templet遵守laytpl 模板规则 )
    	var tableIns = table.render({
    		elem: '#dataTable',
    	    url:'/user/listPage',
    	    cols: [[
    	      {type:'checkbox',widht:'5%'}
    	      ,{field:'userName',title:'用户名称',width:'10%'}
    	      ,{field:'userNike',title:'昵称',width:'10%'}
    	      ,{title:'角色',width:'10%',templet:'<div>{{d.role.roleName}}</div>'}
    	      ,{field:'mobile',title:'手机号码',width:'10%'}
    	      ,{title:'创建人', width:'10%',templet:'<div>{{d.user.userName}}</div>'}
    	      ,{field:'createTime',title:'创建时间',width:'15%',templet:'<div>{{g.dateTimeFormat(d.createTime)}}</div>'}
    	      ,{field:'updateTime',title: '最后修改时间',width:'15%',templet:'<div>{{g.dateTimeFormat(d.updateTime)}}</div>'}
    	      ,{field:'status',title:'状态',width:'5%',templet:'<div>{{statusFormat(d.status)}}</div>'}
    	      ,{title: '操作',templet : '#operationTemplet',width:'10%'}
    	    ]],
    	    page: true
    	});
    	
    	// 添加
    	$(".add").click(function(){
    		//当前层索引
    		var index = layui.layer.open({
    			title : "添加用户信息",
    			type : 2,
    			content : "/user/add"
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
    			title : "编辑用户信息",
    			type : 2,
    			content : "/user/edit/"+id
    		})
    		$(window).resize(function(){
    			layui.layer.full(index);
    		})
    		layui.layer.full(index);
    	});
    	
    	// 搜索
    	$("body").on("click",".searchBtn",function(){
    		var userName = $("#userName").val();
            var roleId = $("#roleId").val();
    		tableIns.reload({
    			where : {
    				userName : userName,
					roleId :　roleId
    			}
    		});
    	});

    	// 单个删除操作
    	$("body").on("click",".remove",function(){
    		var id = $(this).attr("data-id");
    		var index = layer.confirm('确定删除吗?',{icon:3, title:'提示信息'},function(index){
    			$.ajax({
    	             type:"post",
    	             url:"/user/remove",
    	             data:{id : id},
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
    			ids.push(item.id);
    		});
    		//layer.alert(JSON.stringify(checkStatus.data));
    		
    		if(ids == null || ids.length == 0){
    			return;
    		}
    		
    		var index = layer.confirm('确定删除吗?',{icon:3, title:g.title},function(index){
    			$.ajax({
    	             type:"post",
    	             url:"/user/removeBatch",
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

    	//添加验证规则
        form.verify({
            newPwd : function(value){
                if(value.length < 6){
                    return "密码长度不能小于6位";
                }
            },
            confirmPwd : function(value){
                if(!new RegExp($("#newPwd").val()).test(value)){
                    return "两次输入密码不一致，请重新输入！";
                }
            }
        });
        
        // 添加提交
    	$("body").on("click",".addSubmit",function(){
    		$.ajax({
                 type:"post",
                 url:"/user/save",
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
        
        //监听submit事件,修改密码提交
        form.on("submit(changePwd)");

});

//状态转换
function statusFormat(o) {
	var value = "未知";
	if(o == 0) {
		value = "暂停";
	}else if(o == 1) {
		value = "启用";
	}
	return value;
}