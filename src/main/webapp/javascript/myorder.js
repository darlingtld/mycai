/**
 * Created by darlingtld on 2015/7/4 0004.
 */
var mycaiModule = angular.module('MycaiModule', ['ngRoute']);
var isTest = false;
var app = '/mycai';
var user;
var wechatId;

if (isTest) {
    user = {
        nickname: 'lingda',
        openid: 'o5Irvt5957jQ4xmdHmDp59epk0UU',
        headimgurl: 'http://wx.qlogo.cn/mmopen/0pygn8iaZdEeVBqUntWJB9rzhkKIyKnQFzIqswrYFrhHefEXiaCOhJnBqIicxMRd0IeOHe9ffAtKTvXzOfokp9UhS2BlYXh5PxO/0',
        consignee: '灵达',
        consignee_contact: '13402188638',
        shop_info: '新中源大楼'
    }
    wechatId = 'o5Irvt5957jQ4xmdHmDp59epk0UU';
}

mycaiModule.config(function () {

});


mycaiModule.controller('routerController', function ($http, $scope, $location) {
    getUserInfo();
    if (user == undefined || user == null) {
        var code = getURLParameter('code');
        $http.get(app + "/user/code/" + code).success(function (data, status, headers, config) {
            user = data;
            wechatId = user.openid;
            $('img.user-icon').attr('src', user.headimgurl);
            setLocalStorage('wechatId', wechatId);
        });
    }
    sleep(2000)
    $location.path('/order/history');

});

mycaiModule.controller('orderController', function ($http, $scope) {
    if (user == undefined || user == null) {
        var code = getURLParameter('code');
        $http.get(app + "/user/code/" + code).success(function (data, status, headers, config) {
            user = data;
            wechatId = user.openid;
            $('img.user-icon').attr('src', user.headimgurl);
            setLocalStorage('wechatId', wechatId);
            $http.get(url).success(function (data, status, headers, config) {
                $scope.orders = data;
            });
        });
    }
    if (wechatId == undefined) {
        wechatId = getLocalStorage('wechatId');
    }
    if ($scope.orders == undefined) {
        var url = app + '/order/get/' + wechatId;
        $http.get(url).success(function (data, status, headers, config) {
            $scope.orders = data;
        });
    }
    //})

    //sleep(2000)
    //if (user == undefined || user == null) {
    //    var code = getURLParameter('code');
    //    $http.get(app + "/user/code/" + code).success(function (data, status, headers, config) {
    //        user = data;
    //        wechatId = user.openid;
    //        $('img.user-icon').attr('src', user.headimgurl);
    //        var url = app + '/order/get/' + wechatId;
    //        $http.get(url).success(function (data, status, headers, config) {
    //            $scope.orders = data;
    //        });
    //    });
    //}
});

mycaiModule.controller('orderDetailController', function ($http, $scope, $routeParams) {
    var url = app + '/order/detail/' + $routeParams.id;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.orderDetail = data;
        $scope.items = JSON.parse($scope.orderDetail.bill).items;
        $scope.total = JSON.parse($scope.orderDetail.bill);
        $scope.total.price = parseFloat($scope.total.price).toFixed(2);
        if (JSON.parse($scope.orderDetail.confirmBill) != null) {
            $scope.items = JSON.parse($scope.orderDetail.confirmBill).items;
            $scope.total = JSON.parse($scope.orderDetail.bill);
            $scope.actTotal = JSON.parse($scope.orderDetail.confirmBill)
        }
    });

});

mycaiModule.directive('confirmCode', function () {
    return {
        restrict: 'AE',
        scope: {},
        link: function (scope, element, attr) {
            var value = attr.confirmCode;
            element.bind('click', function () {
                alert("确认码 ：\r\n" + value);
            })
        }
    }
});

mycaiModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/order/history', {
            controller: 'orderController',
            templateUrl: 'orderHistory.html'
        })
        .when('/order/details/:id', {
            controller: 'orderDetailController',
            templateUrl: 'orderDetails.html'
        })
        .when('/router', {
            controller: 'routerController',
            template: ''
        })
        .otherwise({
            redirectTo: '/order/history'
        });
}]);

mycaiModule.filter('part', function () {
    return function (input, which) {
        if (!angular.isString(input)) {
            return input;
        }
        return input.split(' ')[which];
    };
});

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function getURLParameter(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function setLocalStorage(key, value) {
    if (typeof(Storage) != "undefined") {
        localStorage.setItem(key, value);
        //console.log('[' + key + ']:[' + value + ']');
    } else {
        console.log("local storage is not supported!")
    }
}

function getLocalStorage(key) {
    if (typeof(Storage) != "undefined") {
        return localStorage.getItem(key);
    } else {
        console.log("local storage is not supported!");
    }
}

function sleep(d) {
    for (var t = Date.now(); Date.now() - t <= d;);
}

function getUserInfo(callback) {
    var code = getURLParameter('code');
    $.ajax({
        type: 'get',
        url: app + "/user/code/" + code,
        success: function (data) {
            user = data;
            wechatId = user.openid;
            $('img.user-icon').attr('src', user.headimgurl);
            setLocalStorage('wechatId', wechatId);
            if (callback && typeof(callback) === "function") {
                callback();
            }
        }
    });
}
