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

var treeNodes = [ {
	"id" : 10001,
	"pId" : 0,
	"name" : "test1"
}, {
	"id" : 10003,
	"pId" : 10001,
	"name" : "test1000110001"
}, {
	"id" : 10004,
	"pId" : 10001,
	"name" : "test12"
}, {
	"id" : 10002,
	"pId" : 0,
	"name" : "123"
} ];

function loadData() {
	$.ajax({
		type : "post",
		url : "/menu/listMenu",
		async : false,
		success : function(result) {
			tree = $.fn.zTree.init($("#treeDemo"), setting, result);
			tree.expandAll(true);// 全部展开
		}
	});
}

// 添加提交
$("body").on("click", "addSubmit", function() {
	alert(1);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = [];
	for (var i = 0; i < nodes.length; i++) {
		// 获取选中节点的值
		ids.push(nodes[i].id);
	}
	alert(JSON.stringify(ids));
	// $.ajax({
	// type:"post",
	// url:"/role/save",
	// data: $("form").serialize(),//表单数据
	// success:function(result){
	// if(result["code"]==g.successCode){
	// layer.close(layer.index);
	// window.parent.location.reload();
	// }
	// if(result["code"]==g.failCode){
	// layer.msg(result["msg"]);
	// }
	// }
	// });
});

$(document).ready(function() {
	// 获取数据
	loadData();
	// 添加提交
	$("body").on("click", ".addSubmit", function() {
		var nodes = tree.getCheckedNodes(true);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			// 获取选中节点的值
			ids.push(nodes[i].id);
		}
		alert(JSON.stringify(ids));
		// $.ajax({
		// type:"post",
		// url:"/role/save",
		// data: $("form").serialize(),//表单数据
		// success:function(result){
		// if(result["code"]==g.successCode){
		// layer.close(layer.index);
		// window.parent.location.reload();
		// }
		// if(result["code"]==g.failCode){
		// layer.msg(result["msg"]);
		// }
		// }
		// });
	});

});
