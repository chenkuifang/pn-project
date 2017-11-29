/* Copyright (C) 2017
 * 这是一个基础的js文件，所以页面都引入
 *提供一些公用的方法
 * filename : default.js
 * version  : 1.0
 * author   : QuiFar
 * date     : 2017-11-22
 * modify   :
 */

var g = {};

/* 公共 */
g = {
    title: 'XXXXX管理系统',

    is_debug: true,

    precision_qty: 0,
    precision_money: 2,

    setCookie: function (key, value) {
        var expires = new Date();
        expires.setTime(expires.getTime() + (7 * 24 * 60 * 60 * 1000));
        document.cookie = key + '=' + value + ';expires='
            + expires.toUTCString();
    },

    getCookie: function (key) {
        var keyValue = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
        return keyValue ? keyValue[2] : null;
    },

    numberFormat: function (num) {
        if (isNaN(num) || num === null || num === '')
            return num;
        return num.replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
    },
    // 时间戳转换格式 如：2017-12-1 12：12
    dateTimeFormat: function (o) {
        var d = new Date(o);
        if (isNaN(d)) {
            o = o.substr(0, o.indexOf('.'));
            o = o.replace(/-/g, '/');
            d = new Date(o);
        }

        var month = d.getMonth() + 1;
        var day = d.getDate();
        var hour = d.getHours();
        var minute = d.getMinutes();
        var second = d.getSeconds();

        return d.getFullYear() + '-' + (('' + month).length < 2 ? '0' : '')
            + month + '-' + (('' + day).length < 2 ? '0' : '') + day + ' '
            + (('' + hour).length < 2 ? '0' : '') + hour + ':'
            + (('' + minute).length < 2 ? '0' : '') + minute + ':'
            + (('' + second).length < 2 ? '0' : '') + second;
    },

    dateFormat: function (o) {
        var d = new Date(o);
        if (isNaN(d)) {
            o = o.substr(0, o.indexOf('.'));
            o = o.replace(/-/g, '/');
            d = new Date(o);
        }

        var month = d.getMonth() + 1;
        var day = d.getDate();

        return d.getFullYear() + '-' + (('' + month).length < 2 ? '0' : '')
            + month + '-' + (('' + day).length < 2 ? '0' : '') + day;
    },

    isEmpty: function (o) {
        if (o === undefined)
            return true;
        if (o == null)
            return true;
        if ($.trim(o.toString()) == '')
            return true;
        return false;
    },

    getFloat: function (o) {
        var ret = parseFloat(o);
        return isNaN(ret) ? 0 : ret;
    },

    getQty: function (o) {
        var ret = this.getFloat(o);
        if (this.precision_qty == 0) {
            return Math.round(ret);
        } else if (this.precision_qty == 1) {
            return Math.round(ret * 10) / 10;
        } else if (this.precision_qty == 2) {
            return Math.round(ret * 100) / 100;
        } else {
            return Math.round(ret);
        }
    },

    getMoney: function (o) {
        var ret = this.getFloat(o);
        if (this.precision_money == 2) {
            return Math.round(ret * 100) / 100;
        } else if (this.precision_money == 1) {
            return Math.round(ret * 10) / 10;
        } else if (this.precision_money == 2) {
            return Math.round(ret);
        } else {
            return Math.round(ret * 100) / 100;
        }
    },

    getWidth: function () {
        // return document.body.clientWidth / window.devicePixelRatio;
        return document.body.clientWidth;
    },

    getHeight: function () {
        // return document.body.clientHeight / window.devicePixelRatio;
        return $(window).height();
    },

    ajax: function (url, url_params) {

        var ret = null;
        $.ajax({
            type: "POST",
            url: url,
            data: url_params,
            dataType: "text",
            async: false,
            cache: false,
            success: function (text) {
                var data;
                try {
                    data = $.parseJSON(text);
                } catch (e) {
                    if (epm.g.is_debug) {
                        alert(text);
                    } else {
                        $.messager.alert(epm.g.title, '服务器返回数据格式不正确', "error");
                    }
                    return;
                }

                if (data['ans'] == 'ok') {
                    ret = data;
                } else {
                    $.messager.alert(epm.g.title, data['ans'], "info");
                }
            },
            error: function () {
                $.messager.alert(epm.g.title, '服务器繁忙', "error");
            }
        });

        return ret;
    }
};

/* 公用函数 */
function g_qty(o) {
    return g.getQty(o);
}

/**
 * 返回金钱格式
 * @param o
 * @returns
 */
function g_money(o) {
    return g.getMoney(o);
}

/**
 * 根据时间戳，返回日期格式
 * 
 * @param o
 * @returns 返回格式如：2017-10-29
 */
function g_dateFormat(o) {
    if (o == null || o == '') {
        return null;
    }

    return g.dateFormat(o);
}

/**
 * 根据时间戳，返回日期格式
 * 
 * @param o
 * @returns 返回格式如：2017-10-29
 */
function g_isEmpty(o) {
    return g.isEmpty(o);
}

/* 每个页面如果存在page_init函数，都会首先执行 */
$(document).ready(function () {
    if ($.isFunction(window.page_init)) {
        page_init();
    }
});