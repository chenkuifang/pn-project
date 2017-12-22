layui.use(['layer', 'jquery', 'table', 'form'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        form = layui.form,
        table = layui.table,
        $ = layui.jquery;

    // 数据渲染(templet遵守laytpl 模板规则 )
    var tableIns = table.render({
        elem: '#dataTable',
        url: '/order/listPage',
        cols: [[
            {type: 'checkbox', widht: '5%'}
            , {field: 'goodsNum', title: '商品编码', width: '10%'}
            , {field: 'goodsName', title: '商品名称', width: '35%'}
            , {field: 'salePrice', title: '销售价格', width: '10%'}
            , {field: 'discountPrice', title: '折后价格', width: '10%'}
            , {field: 'stock', title: '库存', width: '5%'}
            , {field: 'saleCount', title: '销量', width: '5%'}
            , {field: 'status', title: '状态', width: '5%', templet: '<div>{{statusFormat(d.status)}}</div>'}
            , {title: '操作', templet: '#operationTemplet', width: '15%'}
        ]],
        page: true
    });

    // 添加
    $(".add").click(function () {
        //当前层索引
        var index = layui.layer.open({
            title: "添加商品信息",
            type: 2,
            content: "/goods/add"
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
            title: "编辑商品信息",
            type: 2,
            content: "/goods/edit/" + id
        });

        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    // 搜索
    $("body").on("click", ".searchBtn", function () {
        var orderSid = $("#orderSid").val();
        var goodsName = $("#goodsName").val();
        var buyerName = $("#buyerName").val();
        tableIns.reload({
            where: {
                orderSid : orderSid,
                buyerName : buyerName,
                goodsName: goodsName
            }
        });
    });

    // 单个删除操作
    $("body").on("click", ".remove", function () {
        var id = $(this).attr("data-id");
        layer.confirm('确定删除吗?', {icon: 3, title: '提示信息'}, function () {
            $.ajax({
                type: "post",
                url: "/goods/remove",
                data: {id: id},
                success: function (result) {
                    if (result["code"] === g.successCode) {
                        window.location.reload();
                        layer.closeAll();
                    } else {
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
            ids.push(item.id);
        });
        //layer.alert(JSON.stringify(checkStatus.data));

        if (ids == null || ids.length == 0) {
            return;
        }

        layer.confirm('确定删除吗?', {icon: 3, title: g.title}, function () {
            $.ajax({
                type: "post",
                url: "/goods/removeBatch",
                async: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(ids),
                traditional: true, //防止深度序列化
                success: function (result) {
                    if (result["code"] == g.successCode) {
                        layer.closeAll();
                        window.location.reload();
                    } else {
                        layer.msg(result["msg"]);
                    }
                }
            });
        });

    });

    //监听提交
    form.on('submit(saveSubmit)', function (data) {
        $.ajax({
            type: "post",
            url: "/goods/save",
            data: data.field,//表单数据
            success: function (result) {
                if (result["code"] == g.successCode) {
                    window.parent.location.reload();
                    layer.closeAll;
                } else {
                    layer.alert(result["msg"]);
                    return false;
                }
            }
        });
        return false;
    });

});

// 状态转换
function statusFormat(o) {
    var value = "未知";
    if (o == 0) {
        value = "下架";
    } else if (o == 1) {
        value = "正常";
    }
    return value;
}