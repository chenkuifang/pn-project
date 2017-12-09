
// ztree 设置
var tree;
var setting = {
	check : {
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		},// 勾选checkbox对于父子节点的关联关系
		chkStyle : "checkbox",
		enable : true
	},
	// 数据
	data : {
		simpleData : {
			enable : true
		}
	}
};

// 初始化数据
function init(roleId) {
	$.ajax({
		type : "post",
		url : "/menu/listMenu/" + roleId,
		async : false,
		success : function(result) {
			var data = result.data;
			tree = $.fn.zTree.init($("#treeDemo"), setting, data);
			tree.expandAll(true);// 全部展开
		}
	});
}

$(document).ready(function() {
	var action = $("#action").val();
	var roleId = $("#roleId").val();

	init(roleId);
	// 监听提交
	$("body").on("click", ".addSubmit", function() {
		var nodes = tree.getCheckedNodes(true);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			// 获取选中节点的值
			ids.push(nodes[i].id);
		}

		if(g.isEmpty(roleId)) {
		    layer.alert("角色编号不能为空");
		    return;
		}

		if(ids.length == 0) {
		    layer.alert("请选择菜单");
		    return;
		}
		// 总数据
		var data = {
			action : action,
		    roleId : roleId,
		    ids : ids
		};

		$.ajax({
            type:"post",
            contentType:"application/json; charset=utf-8",
            url:"/role/saveRoleMenu",
            async : false,
            data: JSON.stringify(data),
            success:function(result){
                 if(result["code"]==g.successCode) {
                	 layer.alert("成功");
                 }
                 if(result["code"]==g.failCode) {
                	 layer.alert("失败");
                 }
            }
		});
	});

});
