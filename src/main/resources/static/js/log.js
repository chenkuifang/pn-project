var $; // 该jquery 同时提供给base.js 使用，所以引入base.js必须要在menu.js后面
layui.use(['form', 'layer', 'jquery', 'table','laydate'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        form = layui.form,
        table = layui.table,
        laydate = layui.laydate;
        $ = layui.jquery;

    // 数据渲染(templet遵守laytpl 模板规则 )
    var tableIns = table.render({
        elem: '#dataTable',
        url: '/log/listPage',
        cols: [[
            {field: 'userId', title: '操作用户ID', width: '10%'}
            , {field: 'userName', title: '操作用户名称', width: '10%'}
            , {field: 'id', title: 'ip', width: '10%'}
            , {field: 'operation', title: '操作类型', width: '10%'}
            , {field: 'method', title: '操作方法', width: '10%'}
            , {field: 'params', title: '方法参数', width: '30%'}
            , {field: 'createTime', title: '创建时间', width: '20%', templet: '<div>{{g.dateTimeFormat(d.createTime)}}</div>'}
        ]],
        page: true
    });

    //开始时间选择器
    laydate.render({
      elem: '#startTime'
      ,type: 'datetime'
      ,value: g.getSubDateTime(7)
    });

    //结束时间选择器
    laydate.render({
      elem: '#endTime'
      ,type: 'datetime'
      ,value: g.getAddDateTime(7)
    });

    // 搜索
    $("body").on("click", ".searchBtn", function () {
        var operation = $("#operation").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        tableIns.reload({
            where: {
                operation: operation,
                startTime : startTime,
                endTime : endTime
            }
        });
    });

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