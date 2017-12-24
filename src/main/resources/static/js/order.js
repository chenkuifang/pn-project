var $;
layui.use(['layer', 'jquery', 'table', 'form', 'laydate'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        form = layui.form,
        table = layui.table,
        laydate = layui.laydate;
    $ = layui.jquery;

    // 数据渲染(templet遵守laytpl 模板规则 )
    var tableIns = table.render({
        elem: '#dataTable',
        url: '/order/listPage',
        cols: [[
            {type: 'checkbox', widht: '5%'}
            , {field: 'orderSid', title: '订单号', width: '10%'}
            , {field: 'goodsName', title: '商品名称', width: '25%'}
            , {field: 'price', title: '拍下价格', width: '10%'}
            , {field: 'payPrice', title: '支付价格', width: '10%'}
            , {field: 'amount', title: '数量', width: '5%'}
            , {field: 'buyerName', title: '收货人', width: '5%'}
            , {
                field: 'createTime',
                title: '创建时间',
                width: '15%',
                templet: '<div>{{g.dateTimeFormat(d.createTime)}}</div>'
            }
            , {field: 'status', title: '状态', width: '5%', templet: '<div>{{statusFormat(d.status)}}</div>'}
            , {title: '操作', templet: '#operationTemplet', width: '10%'}
        ]],
        page: true
    });

    //开始时间选择器
    laydate.render({
        elem: '#startTime'
        , type: 'datetime'
        , value: g.getSubDateTime(7)
    });

    //结束时间选择器
    laydate.render({
        elem: '#endTime'
        , type: 'datetime'
        , value: g.getAddDateTime(7)
    });

    // 创建订单
    $(".add").click(function () {
        $.ajax({
            type: "post",
            url: "/order/createOrder",
            success: function (result) {
                if (result["code"] === g.successCode) {
                    layer.msg("创建订单成功");
                } else {
                    layer.msg(result["msg"]);
                }
            }
        });
    });

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
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        tableIns.reload({
            where: {
                orderSid: orderSid,
                buyerName: buyerName,
                goodsName: goodsName,
                startTime: startTime,
                endTime: endTime
            }
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
    if (o === 0) {
        value = "未付款";
    } else if (o == 1) {
        value = "已付款";
    } else if (o == 2) {
        value = "已发货";
    } else if (o == 3) {
        value = "已完成";
    } else if (o == 4) {
        value = "取消订单";
    }

    return value;
}