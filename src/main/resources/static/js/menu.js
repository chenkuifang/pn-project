var $; // 该jquery 同时提供给base.js 使用，所以引入base.js必须要在menu.js后面
layui.use(['form', 'layer', 'jquery', 'table'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        form = layui.form,
        table = layui.table;
    $ = layui.jquery;

    // 数据渲染(templet遵守laytpl 模板规则 )
    var tableIns = table.render({
        elem: '#dataTable',
        url: '/menu/listPage',
        cols: [[
            {type: 'checkbox', widht: '5%'}
            //,{field:'menuId', title: 'ID',widht:'5%'}
            , {field: 'name', title: '菜单名称', width: '10%'}
            , {field: 'url', title: '链接地址', width: '20%'}
            , {field: 'type', title: '类型', width: '5%', templet: '<div>{{typeFormat(d.type)}}</div>'}
            , {field: 'orderNum', title: '排序', width: '5%'}
            , {field: 'createTime', title: '创建时间', width: '20%', templet: '<div>{{g.dateTimeFormat(d.createTime)}}</div>'}
            , {field: 'updateTime', title: '最后修改时间', width: '20%', templet: '<div>{{g.dateTimeFormat(d.updateTime)}}</div>'}
            , {title: '操作', templet: '#operationTemplet', width: '15%'}
        ]],
        page: true
    });

    // 添加
    $(".add").click(function () {
        //当前层索引
        var index = layui.layer.open({
            title: "添加菜单信息",
            type: 2,
            content: "/menu/add"
        });

        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    })

    // 编辑操作
    $("body").on("click", ".edit", function () {
        var id = $(this).attr("data-id");
        var index = layui.layer.open({
            title: "编辑菜单信息",
            type: 2,
            content: "/menu/edit/" + id
        });

        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    // 搜索
    $("body").on("click", ".searchBtn", function () {
        var menuName = $("#menuName").val();
        var parentId = $("#parentId").val();
        tableIns.reload({
            where: {
                name: menuName,
                parentId: parentId
            }
        });
    });

    // 单个删除操作
    $("body").on("click", ".remove", function () {
        var id = $(this).attr("data-id");
        layer.confirm('确定删除吗?', {icon: 3, title: '提示信息'}, function () {
            $.ajax({
                type: "post",
                url: "/menu/remove",
                data: {menuId: id},
                success: function (result) {
                    if (result["code"] === g.successCode) {
                        window.location.reload();
                        layer.closeAll();
                    }
                    if (result["code"] === g.failCode) {
                        layer.msg(result["msg"]);
                    }
                }
            });
        });
    });

    // 批量删除
    $("body").on("click", ".removeBatch", function () {
        // 获取表格选中的所有行数据数组
        var checkStatus = table.checkStatus('dataTable');
        var data = checkStatus.data;
        var ids = [];
        $.each(data, function (i, item) {
            ids.push(item['menuId']);
        });
        //alert(JSON.stringify(checkStatus.data));

        if (ids == null || ids.length == 0) {
            return;
        }

        layer.confirm('确定删除吗?', {icon: 3, title: g.title}, function () {
            $.ajax({
                type: "post",
                url: "/menu/removeBatch",
                async: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(ids),
                traditional: true, //防止深度序列化
                success: function (result) {
                    if (result["code"] == g.successCode) {
                        window.location.reload();
                        layer.closeAll();
                    }
                    if (result["code"] == g.failCode) {
                        layer.msg(result["msg"]);
                    }
                }
            });
        });

    });

    // 添加提交
    $("body").on("click", ".addSubmit", function () {
        if (!checkData()) {
            return false;
        }
        $.ajax({
            type: "post",
            url: "/menu/save",
            data: $("form").serialize(),//表单数据
            success: function (result) {
                if (result["code"] == g.successCode) {
                    window.parent.location.reload();
                    layer.closeAll();
                }
                if (result["code"] == g.failCode) {
                    layer.msg(result["msg"]);
                }
            }
        });
    });

    function checkData() {
        var name = $("#name").val();
        var parentId = $("#parentId").val();
        var orderNum = $("#orderNum").val();

        if (g.isEmpty(name)) {
            layer.msg("名称不能为空！");
            return false;
        }

        if (g.isEmpty(parentId) || !g.isNumber(parentId)) {
            layer.msg("菜单父级输入格式不正确！");
            return false;
        }

        if (g.isEmpty(orderNum) || !g.isNumber(orderNum)) {
            layer.msg("排序输入格式不正确！");
            return false;
        }
        return true;
    }

});

// 类型格式转换
function typeFormat(o) {
    var result = '未知';
    if (o === 0) {
        result = '目录';
    } else if (o == 1) {
        result = '菜单';
    } else if (o == 2) {
        result = '按钮';
    }
    return result;
}