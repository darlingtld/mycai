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

var pageStatus = {
    first_open: 0,
    view_product: 1,
    checkout: 2,
    confirm: 3,
    submit: 4
};
var curStatus = pageStatus.first_open;

mycaiModule.controller('mainController', function ($scope) {
    curStatus = pageStatus.first_open;
});

mycaiModule.controller('navController', function ($scope, $http, $routeParams) {
    var url = app + '/nav/' + $routeParams.nav + '/20';
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
    });
    if (curStatus != pageStatus.first_open) {
        setTimeout("$('#main_nav').click()", 300);
    } else {
        curStatus = pageStatus.view_product;
    }
});

mycaiModule.controller('productController', function ($scope, $http, $routeParams) {
    var url = app + '/product/' + $routeParams.type + '/' + $routeParams.category;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
    });
    if (curStatus != pageStatus.first_open) {
        setTimeout("$('#main_nav').click()", 300);
    } else {
        curStatus = pageStatus.view_product;
    }
});

mycaiModule.controller('checkoutController', function ($scope, $location) {
    if (bill.totalAmount == 0) {
        alert('您还未购买任何物品');
        curStatus = pageStatus.first_open;
        $location.path('/');
    } else {
        $scope.bill = bill;

        //console.log($('input[spinner-instance]'));
        //for (var i = 0; i < $('.product_amount').length; i++) {
        //    var amount = $('.product_amount')[i].innerText;
        //    console.log(amount);
        //    $('input[spinner-instance]')[i].val(amount);
        //
        //}

        $('a.next').text('确认订单');
        $('a.next').attr('href', '#/confirm');
        curStatus = pageStatus.checkout;
    }
});

mycaiModule.controller('confirmController', function ($scope) {
    $('.datetime').mobiscroll().datetime({
        theme: 'sense-ui',     // Specify theme like: theme: 'ios' or omit setting to use default
        mode: 'scroller',       // Specify scroller mode like: mode: 'mixed' or omit setting to use default
        lang: 'zh',       // Specify language like: lang: 'pl' or omit setting to use default
        minDate: new Date(),  // More info about minDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-minDate
        maxDate: new Date(2020, 1, 1, 1, 1),   // More info about maxDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-maxDate
        stepMinute: 10  // More info about stepMinute: http://docs.mobiscroll.com/2-14-0/datetime#!opt-stepMinute
    });

    $scope.bill = bill;

    $('.checkout').html('<div><a class="next" href="#/submit">提交</a>');
    $('a.next').css('margin-left', '45%');

    $('a.next').bind('click', function () {
        var order = {
            userId: 'lingda',
            bill: JSON.stringify(bill),
            deliveryTs: $('#delivery_ts').val(),
            shopInfo: $('#shop_info').val(),
            consignee: $('#consignee').val(),
            consigneeContact: $('#consignee_contact').val()
        };

        $.ajax({
            type: "post",
            url: app + "/order/submit",
            contentType: "application/json",
            data: JSON.stringify(order),
            success: function (data) {
                alert('提交订单成功！');
            },
            error: function (data) {
                alert(data.status);
            }
        });
        //console.log(order);

        curStatus = pageStatus.confirm;
    })

});

mycaiModule.controller('orderController', function ($http, $scope) {
    var url = app + '/order/get/' + 'lingda';
    $http.get(url).success(function (data, status, headers, config) {
        $scope.orders = data;
    });
    if (curStatus != pageStatus.first_open) {
        setTimeout("$('#main_nav').click()", 300);
    } else {
        curStatus = pageStatus.view_product;
    }
});

mycaiModule.controller('submitController', function ($scope) {
    $('footer.bg-dark').hide();
    curStatus = pageStatus.submit;
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
        .when('/product/:type/:category', {
            controller: 'productController',
            templateUrl: 'product.html'
        })
        .when('/checkout', {
            controller: 'checkoutController',
            templateUrl: 'checkout.html'
        })
        .when('/confirm', {
            controller: 'confirmController',
            templateUrl: 'confirm.html'
        })
        .when('/submit', {
            controller: 'submitController',
            templateUrl: 'success.html'
        })
        .when('/nav/:nav', {
            controller: 'navController',
            templateUrl: 'product.html'
        })
        .when('/order/history', {
            controller: 'orderController',
            templateUrl: 'orderHistory.html'
        })

        .otherwise({
            redirectTo: '/nav/zuixincaipin'
        });
}]);

function init() {
    $('footer.bg-dark').show();
    $('.checkout').html('<div><a class="basket"><i class="icon-basket-loaded i-lg"></i></a></div><div>物件数：<span id="totalAmount">0</span>件 </div> <div>总价：<span id="totalPrice">0</span>元</div><div><a class="next" href="#/checkout">下一步</a></div>');
}


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
    var productDescription = $(ele.find('.product_description')[0]).data('product_description');
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
                description: productDescription,
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

