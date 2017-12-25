/* Copyright (C) 2017
 * 这是一个基础的js文件，所以页面都引入
 *提供一些公用的方法
 * filename : default.js
 * version  : 1.0
 * author   : QuiFar
 * date     : 2017-11-22
 */

var g = {};

/* 公共 */
g = {
	// js 常量
    title: 'phonepn管理系统',
    successCode : '100',
    failCode : '101',
    is_debug: true,

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

    // 根据时间戳返回日期：2017-12-15
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

    // 获取当前+o天的时间
    getAddDateTime : function (o) {
        if(g.isEmpty(o) || !g.isNumber(o)){
            return new Date();
        }

        var d = new Date();
        d.setDate(d.getDate() + o);//获取AddDayCount天后的日期
        return g.dateTimeFormat(d);
     },

    // 获取当前 -o天的时间
    getSubDateTime : function (o) {
        if(g.isEmpty(o) || !g.isNumber(o)){
             return new Date();
        }

        var d = new Date();
        d.setDate(d.getDate() - o);//获取AddDayCount天后的日期
        return g.dateTimeFormat(d);
    },

    // 获取当前+o天的日期
    getAddDate : function (o) {
        if(g.isEmpty(o) || !g.isNumber(o)){
            return new Date();
        }

        var d = new Date();
        d.setDate(d.getDate() + o);//获取AddDayCount天后的日期
        return g.dateFormat(d);
     },

    // 获取当前 -o天的日期
    getSubDate : function (o) {
        if(g.isEmpty(o) || !g.isNumber(o)){
             return new Date();
        }

        var d = new Date();
        d.setDate(d.getDate() - o);//获取AddDayCount天后的日期
        return g.dateFormat(d);
    },

    // 获取当天最后时间
    getTodayEndTime : function() {
        var result = g.dateFormat(new Date().getTime());
        return result+' 23:59:59';
    },

    // 获取当天开始时间
    getTodayStartTime : function() {
        var result = g.dateFormat(new Date().getTime());
        return result+' 00:00:00';
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
    
    isNumber: function (value) {
    	var patrn = /^[0-9]*$/;
        if (patrn.exec(value) == null || value == "") {
            return false
        } else {
            return true
        }
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
                //do something
            	ret = text;
            },
            error: function () {
                alert(g.title + ",服务器错误");
            }
        });

        return ret;
    }
};
