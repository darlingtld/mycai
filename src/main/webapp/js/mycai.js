/**
 * Created by darlingtld on 2015/5/14.
 */
var mycaiModule = angular.module('MycaiModule', ['ngRoute']);
var user;
var wechatId;
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
    //curStatus = pageStatus.first_open;
    getUserInfo();
});

function getUserInfo() {
    var code = getURLParameter('code');
    console.log("code:" + code);
    $.ajax({
        type: 'get',
        url: app + "/user/code/" + code,
        success: function (data) {
            //alert(data);
            user = data;
            wechatId = data.openid;
            console.log(user);

        }
    });
}

mycaiModule.controller('navController', function ($scope, $http, $routeParams) {
    var url = app + '/nav/' + $routeParams.nav + '/20';
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
        fillSpinner($scope.products);
    });
    if (curStatus != pageStatus.first_open) {
        setTimeout("$('#main_nav').click()", 300);
        init();
    } else {
        curStatus = pageStatus.view_product;
    }
});

mycaiModule.controller('productController', function ($scope, $http, $routeParams) {
    var url = app + '/product/' + $routeParams.type + '/' + $routeParams.category;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
        fillSpinner($scope.products);
    });
    if (curStatus != pageStatus.first_open) {
        setTimeout("$('#main_nav').click()", 300);
        init();
    } else {
        curStatus = pageStatus.view_product;
    }
});

mycaiModule.controller('checkoutController', function ($scope, $location, $document, $routeParams) {
    var from = $routeParams.from;
    if (bill.totalAmount == 0) {
        alert('您还未购买任何物品');
        curStatus = pageStatus.first_open;
        init();
        $location.path('/');
    } else {
        $scope.bill = bill;

        $('a.next').text('确认订单');
        $('a.next').attr('href', '#/confirm');
        curStatus = pageStatus.checkout;
    }
    if (from == 'menu') {
        setTimeout("$('#main_nav').click()", 300);
    }
});


mycaiModule.controller('confirmController', function ($scope, $location) {
        if (bill.totalAmount == 0) {
            alert('您还未购买任何物品');
            curStatus = pageStatus.first_open;
            init();
            $location.path('/');
        } else {
            $('.datetime').mobiscroll().datetime({
                theme: 'sense-ui',     // Specify theme like: theme: 'ios' or omit setting to use default
                mode: 'scroller',       // Specify scroller mode like: mode: 'mixed' or omit setting to use default
                lang: 'zh',       // Specify language like: lang: 'pl' or omit setting to use default
                minDate: new Date(),  // More info about minDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-minDate
                maxDate: new Date(2020, 1, 1, 1, 1),   // More info about maxDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-maxDate
                stepMinute: 10  // More info about stepMinute: http://docs.mobiscroll.com/2-14-0/datetime#!opt-stepMinute
            });

            $scope.bill = bill;

            var sendTs = DateAdd("d ", 1, new Date());
            sendTs.setHours(7);
            sendTs.setMinutes(0);
            sendTs.setSeconds(0);
            $scope.send = {
                ts: sendTs.Format("yyyy-MM-dd hh:mm:ss")
            }

            $('.checkout').html('<div><a class="next">提交</a>');
            $('a.next').css('margin-left', '45%');

            //$('a.next').bind('click', function () {
            $('div.checkout').on('click', 'a.next', function () {

                var order = {
                    userId: user.nickname,
                    wechatId: wechatId,
                    bill: JSON.stringify(bill),
                    orderTs: new Date().Format("yyyy-MM-dd hh:mm:ss"),
                    deliveryTs: $('#delivery_ts').val(),
                    shopInfo: $('#shop_info').val(),
                    consignee: $('#consignee').val(),
                    consigneeContact: $('#consignee_contact').val()
                };

                if (validateOrder(order)) {
                    $.ajax({
                        type: "post",
                        url: app + "/order/submit",
                        contentType: "application/json",
                        data: JSON.stringify(order),
                        success: function (data) {
                            alert('提交订单成功！');
                            clearBill();
                            curStatus = pageStatus.first_open;
                            init();
                            window.location = app + '/index.html?wechatId=' + wechatId + '#/order/history';
                            //$location.path('/order/history');
                        },
                        error: function (data) {
                            alert(data.status);
                        }
                    });
                    curStatus = pageStatus.confirm;
                }
            })

        }
    }
);

mycaiModule.controller('orderController', function ($http, $scope) {
    if (wechatId == undefined) {
        wechatId = getURLParameter('wechatId');
    }
    var url = app + '/order/get/' + wechatId;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.orders = data;
    });

    if (curStatus != pageStatus.first_open) {
        setTimeout("$('#main_nav').click()", 300);
    } else {
        curStatus = pageStatus.view_product;
    }
});

mycaiModule.controller('orderDetailController', function ($http, $scope, $routeParams) {
    var url = app + '/order/detail/' + $routeParams.id;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.orderDetail = data;
        $scope.items = JSON.parse($scope.orderDetail.bill).items;
        $scope.total = JSON.parse($scope.orderDetail.bill);
    });

});

function clearBill() {
    bill = {
        items: [],
        totalAmount: 0,
        totalPrice: 0
    }
}

mycaiModule.controller('submitController', function ($scope) {
    $('footer.bg-dark').hide();
    curStatus = pageStatus.submit;
    clearBill();
});

mycaiModule.directive('spinnerInstance', function () {
    return {
        restrict: 'AE',
        scope: {},
        link: function (scope, element, attr) {
            var value = attr.spinnerInstance;
            if (value == undefined || value == "") {
                value = 0;
            }
            element.spinner({
                value: value,
            });
        }
    }
})

mycaiModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/product/:type/:category', {
            controller: 'productController',
            templateUrl: 'product.html'
        })
        .when('/checkout/:from', {
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
        .when('/order/details/:id', {
            controller: 'orderDetailController',
            templateUrl: 'orderDetails.html'
        })
        .otherwise({
            redirectTo: '/nav/zuixincaipin'
        });
}]);


function fillSpinner(products) {
    for (var i = 0; i < bill.items.length; i++) {
        var productId = bill.items[i].productId;
        for (var j = 0; j < products.length; j++) {
            if (productId == products[j].id) {
                products[j].purchaseAmount = bill.items[i].amount;
                break;
            }
        }

    }
}

function init() {
    $('footer.bg-dark').show();
    $('.checkout').html('<div><a class="basket"><i class="icon-basket-loaded i-lg"></i></a></div><div>物件数：<span id="totalAmount">0</span>件 </div> <div>总价：<span id="totalPrice">0</span>元</div><div><a class="next" href="#/checkout/footer">下一步</a></div>');
    refreshCheckoutUI(bill.totalAmount, bill.totalPrice.toFixed(2));

}


function refreshCheckoutUI(totalAmount, totalPrice) {
    $('#totalAmount').text(totalAmount);
    $('#totalPrice').text(totalPrice);
    //console.log(bill.items);
}

function isFirstBuy(items, productId) {
    for (var i = 0; i < items.length; i++) {
        if (productId == items[i].productId) {
            return false;
        }
    }
    return true;
}

function validateOrder(order) {
    if (order.deliveryTs.trim() == '') {
        alert('请选择配送时间');
        return false;
    } else if (order.shopInfo.trim() == '') {
        alert('请输入商店信息');
        return false;
    } else if (order.consignee.trim() == '') {
        alert('请输入收货人姓名');
        return false;
    } else if (order.consigneeContact.trim() == '') {
        alert('请输入收货人联系方式');
        return false;
    } else {
        return true;
    }
}

function refreshCheckoutItemUI(ele, amount, productPrice) {
    if (ele.find('.amount>span').length == 0) {
        return;
    }
    $(ele.find('.amount>span')[0]).text(amount);
    $(ele.find('.price>span')[0]).text(amount * productPrice);
}

function changeTotalCost(_this) {
    var ele = _this.parent().parent().parent();
    var amount = _this.siblings('input')[0].value;
    var productId = $(ele.find('.product_id')[0]).data('product_id');
    var productName = $(ele.find('.product_name')[0]).data('product_name');
    var productDescription = $(ele.find('.product_description')[0]).data('product_description');
    var productPrice = $(ele.find('.product_price')[0]).data('product_price');
    var productUnit = $(ele.find('.product_unit')[0]).data('product_unit');
    var picurl = $(ele.find('.product_picurl')[0]).data('product_picurl');
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
    refreshCheckoutItemUI(ele, amount, productPrice);
    //console.log(totalAmount + ":" + totalPrice.toFixed(2));
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

function getURLParameter(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function DateAdd(interval, number, date) {
    switch (interval) {
        case "y ":
        {
            date.setFullYear(date.getFullYear() + number);
            return date;
            break;
        }
        case "q ":
        {
            date.setMonth(date.getMonth() + number * 3);
            return date;
            break;
        }
        case "m ":
        {
            date.setMonth(date.getMonth() + number);
            return date;
            break;
        }
        case "w ":
        {
            date.setDate(date.getDate() + number * 7);
            return date;
            break;
        }
        case "d ":
        {
            date.setDate(date.getDate() + number);
            return date;
            break;
        }
        case "h ":
        {
            date.setHours(date.getHours() + number);
            return date;
            break;
        }
        case "m ":
        {
            date.setMinutes(date.getMinutes() + number);
            return date;
            break;
        }
        case "s ":
        {
            date.setSeconds(date.getSeconds() + number);
            return date;
            break;
        }
        default:
        {
            date.setDate(d.getDate() + number);
            return date;
            break;
        }
    }
}

//$(function () {
//    var code = getURLParameter('code');
//    console.log("code:" + code);
//    $.ajax({
//        type: 'get',
//        url: app + "/user/code/" + code,
//        success: function (data) {
//            alert(data);
//            wechatId = data.openid;
//        }
//    });
//})

