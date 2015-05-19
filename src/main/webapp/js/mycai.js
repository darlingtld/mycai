/**
 * Created by darlingtld on 2015/5/14.
 */
var mycaiModule = angular.module('MycaiModule', ['ngRoute']);
var app = '/mycai';
var bill = {
    items: [],
    totalAmount: 0,
    totalPrice: 0
}

mycaiModule.controller('mainController', function ($scope) {

})
;

mycaiModule.controller('productController', function ($scope, $http, $routeParams) {
    var url = app +'/product/' + $routeParams.type + '/' + $routeParams.category;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
    });
});

mycaiModule.controller('checkoutController', function ($scope) {
    $scope.bill = bill;
});

mycaiModule.directive('spinnerInstance', function () {
    return {
        restrict: 'AE',
        scope: {},
        link: function (scope, element, attr) {
            element.spinner({});
        }
    }
})


mycaiModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when(app +'/product/:type/:category', {
            controller: 'productController',
            templateUrl: 'product.html'
        })
        .when(app +'/checkout', {
            controller: 'checkoutController',
            templateUrl: 'checkout.html'
        })
        .otherwise({
            redirectTo: app +'/product/shucaishuiguo/yecailei'
        });
}]);


function refreshCheckoutUI(totalAmount, totalPrice) {
    $('#totalAmount').text(totalAmount);
    $('#totalPrice').text(totalPrice);
    console.log(bill.items);
}
function isFirstBuy(items, productId) {
    for (var i = 0; i < items.length; i++) {
        if (productId == items[i].productId) {
            return false;
        }
    }
    return true;
}
function changeTotalCost(_this) {
    var ele = _this.parent().parent();
    var amount = _this.siblings('input')[0].value;
    var productId = $(ele.find('.product_id')[0]).data('product_id');
    var productName = $(ele.find('.product_name')[0]).data('product_name');
    var productPrice = $(ele.find('.product_price')[0]).data('product_price');
    var productUnit = $(ele.find('.product_unit')[0]).data('product_unit');
    var picurl = $(ele.find('.product_picurl')[0]).data('product_picurl');
    ;
    if (_this.hasClass('increase')) {
        bill.totalAmount++;
        bill.totalPrice += parseFloat(productPrice);
        if (isFirstBuy(bill.items, productId)) {
            bill.items.push({
                productId: productId,
                productName: productName,
                amount: amount,
                productPrice: productPrice,
                picurl: picurl,
                productUnit: productUnit
            });
        } else {
            for (var i = 0; i < bill.items.length; i++) {
                if (productId == bill.items[i].productId) {
                    bill.items[i].amount++;
                }
            }
        }
    }

    else {
        bill.totalAmount--;
        bill.totalPrice -= parseFloat(productPrice);
        for (var i = 0; i < bill.items.length; i++) {
            if (productId == bill.items[i].productId) {
                bill.items[i].amount--;
                if (bill.items[i].amount == 0) {
                    bill.items.splice(i, 1);
                }
            }
        }
    }
    refreshCheckoutUI(bill.totalAmount, bill.totalPrice.toFixed(2));
    //console.log(totalAmount + ":" + totalPrice.toFixed(2));
}

