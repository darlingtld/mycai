/**
 * Created by darlingtld on 2015/7/4 0004.
 */
(function () {
    $('.menu-mask').on('touchstart click', function () {
        $(this).hide();
    })
    //global variables
    var $category = $(".js-category");
    var $navga = $('.menu');
    var $curCat1, $curCat2;
    var ccat1, ccat2;
    $(".js-content").css("height", ($(window).height() - $(".js-reminder").height() - $(".js-menu-bar").height() - $(".js-footer").height()) + "px");
    $(".js-search-content").css("height", $(window).height() - 90);

    if (PlusMinus.isLowerAndriod3()) {
        PlusMinus.touchScroll("subCategoryBlock");
        PlusMinus.touchScroll("mainListBlock");
        PlusMinus.touchScrollY("ma-menu-bar");
    }

    function geneLevel2DOM(data, ccat1) {
        var level2HTML = "";
        _.each(_.pairs(data[ccat1]), function (item) {
            level2HTML += "<li class='cp'><a href='#/product/category/" + item[1] + "'>" + item[0] + "</a></li>";
        });
        $category.html("").append($(level2HTML));

        //bind category event
        _.each($category.find("li"), function (item) {
            $item = $(item);
            $item.click(function () {
                resetCategoryBgColor($curCat2);
                $this = $curCat2 = $(this);
                ccat2 = $this.text().trim();
                $this.addClass("active");
            });
        });

        $firstCategory = $($category.find("li")[0]);
        if ($firstCategory) {
            $curCat2 = $firstCategory;
            $firstCategory.click();
        }
    }

    function resetCategoryBgColor($link) {
        if (!$link) return;
        $link.removeClass("active");
    }

    window.data = {
        "蔬菜水果": {
            "叶菜类": "yecailei",
            "根茎类": "genjinglei",
            "茄果类": "qieguolei",
            "豆类": "doulei",
            "葱姜蒜": "congjiangsuan",
            "菌类": "junlei",
            "特菜": "tecai"
        },
        "禽肉蛋类": {
            "一级白条": "yijibaitiao",
            "二级白条": "erjibaitiao",
            "冻猪肉": "dongzhurou",
            "羊肉": "yangrou",
            "牛肉": "niurou",
            "鲜鸡肉": "xianjirou",
            "冻鸡肉": "dongjirou",
            "鸭肉": "yarou",
            "禽类": "qinlei",
            "禽蛋类": "qindanlei",
            "熟食加工": "shushijiagong"
        },
        "水产冻货": {
            "海鲜水产": "haixianshuichan",
            "鱼丸火锅": "yuwanhuoguo",
        },
        "米面粮油": {
            "大米": "dami",
            "面粉面条": "mianfenmiantiao",
            "食用油": "shiyongyou",
            "杂粮": "zaliang",
            "面点": "miandian",
            "烘焙佐料": "hongbeizuoliao",
        },
        "调料其他": {
            "调味品": "tiaoweipin",
            "粉丝粉条": "fensifentiao",
            "豆制品": "douzhipin",
            "干货": "ganhuo",
            "调味品": "tiaoweipin",
            "酱油醋": "jiangyoucu",
            "腌菜罐头": "yancaiguantou"
        },
        "餐厨用品": {
            "纸品湿巾": "zhipinshijin",
            "餐饮用具": "canyinyongju",
        },
        "酒水饮料": {
            "饮料": "yinliao",
            "饮用水": "yinyongshui",
        }
    };
    var navHtml = '';
    window.hasFavorite = true;
    _.each(_.keys(data), function (key) {
        navHtml += "<li>" + key + "</li>";
    });

    $navga.append($(navHtml));


    _.each($navga.find("li"), function (item) {
        var $item = $(item);
        $item.click(function () {
            resetCategoryBgColor($curCat1);
            $this = $curCat1 = $(this);
            ccat1 = $this.text().trim();
            $this.addClass("active");
            geneLevel2DOM(data, ccat1);

        });
    });

    var $level1 = $($navga.find("li")[0]);
    if ($level1) {
        $curCat1 = $level1;
        $level1.click();
    }

    $('ul.menu').on('click', 'li', function () {
        restoreBuyPage();

        $category.find("li>a")[0].click();
    })
}());

function restoreBuyPage() {
    $('#subCategoryBlock').show();
    $('#mainListBlock').css('width', '75%');
    init();
}

function goToCheckout() {
    $('#subCategoryBlock').hide();
    $('#mainListBlock').css('width', '100%');
    $('a.next').attr('href', '#/confirm');
    $('a.next').text('确认订单');
}

function goToConfirm() {
    $('#subCategoryBlock').hide();
    $('#mainListBlock').css('width', '100%');
    $('.checkout').html('<div><a class="next">提交</a>');
    $('a.next').css('margin-left', '45%');
}

function goToOrderHistory() {
    $('#subCategoryBlock').hide();
    $('#mainListBlock').css('width', '100%');
    $('#ma-menu-bar').hide();
    $('footer').hide();
}

function saveToLocalStorage(bill) {
    setLocalStorage('bill', JSON.stringify(bill));
}

function setLocalStorage(key, value) {
    if (typeof(Storage) != "undefined") {
        localStorage.setItem(key, value);
        console.log('[' + key + ']:[' + value + ']');
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

function clearLocalStorage() {
    if (typeof(Storage) != "undefined") {
        localStorage.removeItem('bill');
    } else {
        console.log("local storage is not supported!")
    }
}

var mycaiModule = angular.module('MycaiModule', ['ngRoute']);
var isTest = false;
var user;
var wechatId;
var code;
var orders;

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

mycaiModule.config(function () {
        if (typeof(Storage) != "undefined") {
            try {
                var ls = localStorage.getItem('bill');
                if (ls != undefined) {
                    bill = JSON.parse(ls);
                    refreshCheckoutUI(bill.totalAmount, bill.totalPrice);
                }
            }
            catch (err) {
                bill = {
                    items: [],
                    totalAmount: 0,
                    totalPrice: 0
                }
            }
        }
        else {
            console.log("local storage is not supported!")
        }
    }
)
;


mycaiModule.controller('mainController', function ($location) {
    code = getURLParameter('code');
    //if (code != null) {
    getUserInfo();
    //}
    var isOrderHistory = getURLParameter('order_history');
    if (isOrderHistory != null) {
        goToOrderHistory();
        sleep(1000);
        $location.path("/order/history");
    }
});
function sleep(d) {
    for (var t = Date.now(); Date.now() - t <= d;);
}

function getUserInfo() {
    var code = getURLParameter('code');
    $.ajax({
        type: 'get',
        url: app + "/user/code/" + code,
        success: function (data) {
            user = data;
            wechatId = user.openid;
            $('img.user-icon').attr('src', user.headimgurl);
            setLocalStorage('wechatId', wechatId);
            $.ajax({
                type: 'post',
                url: app + "/user/save_or_update",
                data: JSON.stringify(user),
                contentType: 'application/json',
                success: function (data) {
                    user = data;
                }
            });
            //$.ajax({
            //    type: 'get',
            //    url: app + '/order/get/' + user.openid,
            //    success: function (data) {
            //        orders = data;
            //    }
            //});
        }
    });
}

mycaiModule.controller('navController', function ($scope, $http, $routeParams) {
    var url = app + '/nav/' + $routeParams.nav + '/20';
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
        fillSpinner($scope.products);
    });
});

mycaiModule.controller('productController', function ($scope, $http, $routeParams) {
    var url = app + '/product/category/' + $routeParams.category + '/wechatid/' + wechatId;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.products = data;
        fillSpinner($scope.products);
    });
});

mycaiModule.controller('checkoutController', function ($scope, $location) {
    if (bill.totalAmount == 0) {
        alert('您还未购买任何物品');
        init();
        $location.path('/');
    } else {
        goToCheckout();
        $scope.bill = bill;
    }
});


mycaiModule.controller('confirmController', function ($scope, $http, $location) {
        if (bill.totalAmount == 0) {
            alert('您还未购买任何物品');
            init();
            $location.path('/');
        } else {
            goToConfirm();
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

            $('#shop_info').val(user.shopInfo);
            $('#consignee').val(user.consignee);
            $('#consignee_contact').val(user.consigneeContact);

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
                    $http.post(app + "/order/submit", JSON.stringify(order)).
                        success(function (data, status, headers, config) {
                            alert('提交订单成功！');
                            clearLocalStorage();
                            setLocalStorage('shop_info', order.shopInfo);
                            setLocalStorage('consignee', order.consignee);
                            setLocalStorage('consignee_contact', order.consigneeContact);
                            clearBill();
                            init();
                            $location.path('/order/history');
                        }).error(function () {
                            alert(data.status);
                        });
                }
            })

        }
    }
);

//mycaiModule.factory('orderService', ['$http', '$q', function ($http, $q) {
//    return {
//        query: function (openid) {
//            var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行
//            $http({method: 'GET', url: app + '/order/get/' + openid}).
//                success(function (data, status, headers, config) {
//                    deferred.resolve(data);  // 声明执行成功，即http请求数据成功，可以返回数据了
//                }).
//                error(function (data, status, headers, config) {
//                    deferred.reject(data);   // 声明执行失败，即服务器返回错误
//                });
//            return deferred.promise;   // 返回承诺，这里并不是最终数据，而是访问最终数据的API
//        } // end query
//    };
//}]);


//mycaiModule.controller('orderController', ['orderService', '$scope', function (orderService, $scope) { // 注入itemService
//    goToOrderHistory();
//    var promise = orderService.query(user.openid); //获得承诺接口
//    promise.then(function (data) {  // 成功回调
//        $scope.orders = data;
//    }, function (data) {  // 错误回调
//        console.log('请求失败');
//    });
//}]);
mycaiModule.controller('orderController', function ($http, $scope) {
    goToOrderHistory();

    //var code = getURLParameter('code');
    //if (code == null) {
    //wechatId = getLocalStorage('wechatId');
    var url = app + '/order/get/' + user.openid;
    $http.get(url).success(function (data, status, headers, config) {
        $scope.orders = data;
    });
    //} else {
    //$http.get(app + "/user/code/" + code).success(function (data) {
    //    //user = data;
    //    wechatId = data.openid;
    //    $('img.user_icon').attr('src', user.headimgurl);
    //    var url = app + '/order/get/' + wechatId;
    //    $http.get(url).success(function (data, status, headers, config) {
    //        $scope.orders = data;
    //    });
    //});
    //}

    //goToOrderHistory();
    //var url = app + '/order/get/' + wechatId;
    //$http.get(url).success(function (data, status, headers, config) {
    //    $scope.orders = data;
    //});


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
            element.css('width', '20%');
            element.css('text-align', 'center');
            element.css('color', 'green');
            element.css('background-color', 'white');
            element.spinner({
                value: value,
            });
        }
    }
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
        .when('/product/category/:category', {
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
        .when('/order/details/:id', {
            controller: 'orderDetailController',
            templateUrl: 'orderDetails.html'
        })
        .otherwise({
            redirectTo: '/product/category/yecailei'
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
    $('.checkout').html('<div><span class="glyphicon glyphicon-shopping-cart"></span></div><div>物件数：<span id="totalAmount">0</span>件</div><div>总价：<span id="totalPrice">0</span>元</div><div style="text-align: center;"><a class="next btn btn-info" href="#/checkout">下一步</a></div>');
    refreshCheckoutUI(bill.totalAmount, bill.totalPrice.toFixed(2));

}


function refreshCheckoutUI(totalAmount, totalPrice) {
    $('#totalAmount').text(totalAmount);
    $('#totalPrice').text(totalPrice);
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
    saveToLocalStorage(bill);
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



