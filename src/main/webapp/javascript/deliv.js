/**
 * Created by darlingtld on 2015/7/4 0004.
 */
var delivModule = angular.module('DelivModule', []);
var isTest = true;
var user;
var wechatId;
var confirmCode;

if (isTest) {
    user = {nickname: 'lingda'};
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
    }
);


delivModule.controller('mainController', function ($scope, $http) {
    var url = app + '/order/confirm_code/' + confirmCode;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.order = data;
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

    $scope.confirm = function () {
        $scope.order.status = '已收货';
        console.log($scope.order);

        //$http.post(app + "/order/submit", JSON.stringify(order)).
        //    success(function (data, status, headers, config) {
        //        alert('确认成功！');
        //    }).error(function () {
        //        alert('确认失败!');
        //    });
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
            console.log(totalPrice);
        }
    } catch (err) {
        console.log(err);
        return;
    }
    $('#totalPrice').text(totalPrice);
}

