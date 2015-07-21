/**
 * Created by darlingtld on 2015/7/4 0004.
 */
var delivModule = angular.module('DelivModule', []);
var isTest = false;
var user;
var wechatId;
var confirmCode;
var orderStatus = {
    NOT_DELIVERED: "未配送",
    IN_DELIVERY: "配送中",
    DELIVERED_NOT_PAID: "已配送（未付款）",
    DELIVERED_PAID: "已配送（已付款）"
}

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
var app = '/mycai';
var bill = {
    items: [],
    totalAmount: 0,
    totalPrice: 0
}

delivModule.config(function () {
        confirmCode = getURLParameter('confirm_code');
        //var html = '';
        //html += '<button class="btn btn-info btn-block" ng-click="confirm($event)">' + orderStatus.NOT_DELIVERED + '</button><br>'
        //html += '<button class="btn btn-success btn-block" ng-click="confirm($event)">' + orderStatus.IN_DELIVERY + '</button><br>'
        //html += '<button class="btn btn-danger btn-block" ng-click="confirm($event)">' + orderStatus.DELIVERED_NOT_PAID + '</button><br>'
        //html += '<button class="btn btn-primary btn-block" ng-click="confirm($event)">' + orderStatus.DELIVERED_PAID + '</button><br>'
        //$('#order-status').html(html);
    }
);


delivModule.controller('mainController', function ($scope, $http) {
    var url = app + '/order/confirm_code/' + confirmCode;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.order = data;
        if ($scope.order.status == orderStatus.DELIVERED_PAID) {
            alert('该订单已配送（已付款）！');
            $('body').html('订单已确认！');
        }
        $scope.items = JSON.parse($scope.order.bill).items;
        for (var i = 0; i < $scope.items.length; i++) {
            var amount = $scope.items[i].amount;
            var price = $scope.items[i].productPrice;
            $scope.items[i].totalPrice = (amount * price).toFixed(2);
            $scope.items[i].actAmount = amount;
        }
        $scope.totalPrice = JSON.parse($scope.order.bill).totalPrice;
    });

    $scope.updateTotalPrice = function () {
        this.item.totalPrice = (this.item.actAmount * this.item.productPrice).toFixed(2);
        modifyBill($scope.order, this.item);
        setTimeout('refreshCheckoutUI()', 200);
    }

    function modifyBill(order, item) {
        var totalPrice = 0;
        var bill = JSON.parse(order.bill);
        for (var i = 0; i < bill.items.length; i++) {
            if (item.id == bill.items[i].id) {
                bill.items[i].amount = item.actAmount;
            }
            totalPrice += item.actAmount * item.productPrice;

        }
        bill.totalPrice = totalPrice.toFixed(2);
        order.confirmBill = JSON.stringify(bill);
        order.totalPrice = totalPrice.toFixed(2);
        order.confirmTs = new Date().Format("yyyy-MM-dd hh:mm:ss");
    }

    $scope.confirm = function (target) {
        $scope.order.status = target.target.innerText;
        console.log($scope.order);

        $http.post(app + "/order/update", JSON.stringify($scope.order)).
            success(function (data, status, headers, config) {
                alert('确认成功！');
                $('body').html('订单已确认！');
            }).error(function () {
                alert('确认失败!');
            });
    }
});

function getURLParameter(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function refreshCheckoutUI() {
    var priceList = $('span.actTotalPrice');
    var totalPrice = 0;
    try {
        for (var i = 0; i < priceList.length; i++) {
            totalPrice += parseFloat(priceList[i].innerText);
        }
    } catch (err) {
        console.log(err);
        return;
    }
    $('#totalPrice').text(totalPrice.toFixed(2));
}

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