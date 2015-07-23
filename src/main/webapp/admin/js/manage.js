/**
 * Created by tangld on 2015/6/8.
 */
var adminModule = angular.module('AdminModule', ['ngRoute']);
var app = '/mycai';
var cdata = {
    "蔬菜水果": {
        "叶菜类": "category/yecailei",
        "根茎类": "category/genjinglei",
        "茄果类": "category/qieguolei",
        "豆类": "category/doulei",
        "葱姜蒜": "category/congjiangsuan",
        "菌类": "category/junlei",
        "特菜": "category/tecai"
    },
    "禽肉蛋类": {
        "一级白条": "category/yijibaitiao",
        "二级白条": "category/erjibaitiao",
        "冻猪肉": "category/dongzhurou",
        "羊肉": "category/yangrou",
        "牛肉": "category/niurou",
        "鲜鸡肉": "category/xianjirou",
        "冻鸡肉": "category/dongjirou",
        "鸭肉": "category/yarou",
        "禽类": "category/qinlei",
        "禽蛋类": "category/qindanlei",
        "熟食加工": "category/shushijiagong"
    },
    "水产冻货": {
        "海鲜水产": "category/haixianshuichan",
        "鱼丸火锅": "category/yuwanhuoguo",
    },
    "米面粮油": {
        "大米": "category/dami",
        "面粉面条": "category/mianfenmiantiao",
        "食用油": "category/shiyongyou",
        "杂粮": "category/zaliang",
        "面点": "category/miandian",
        "烘焙佐料": "category/hongbeizuoliao",
    },
    "调料其他": {
        "调味品": "category/tiaoweipin",
        "粉丝粉条": "category/fensifentiao",
        "豆制品": "category/douzhipin",
        "干货": "category/ganhuo",
        "调味品": "category/tiaoweipin",
        "酱油醋": "category/jiangyoucu",
        "腌菜罐头": "category/yancaiguantou"
    },
    "餐厨用品": {
        "纸品湿巾": "category/zhipinshijin",
        "餐饮用具": "category/canyinyongju",
    },
    "酒水饮料": {
        "饮料": "category/yinliao",
        "饮用水": "category/yinyongshui",
    }
}

adminModule.controller('productController', function ($scope, $http, $routeParams, $location) {
    $('li[role]').removeClass('active');
    $('li[role="manage_product"]').addClass('active');

    $scope.changedValue = function (item) {
        var productUrl = app + '/product/' + item + '/wechatid/0';
        $http.get(productUrl).success(function (data, status, headers, config) {
            $scope.products = data;
        });
    }

    objS = $('select.product-picker');
    var selectHtml = '';
    $.each(cdata, function (name, value) {
        selectHtml += '<optgroup label="' + name + '">'
        $.each(value, function (name, value) {
            selectHtml += '<option value="' + value + '">' + name + '</option>';
        });
        selectHtml += '</optgroup>';
    });
    objS.html(selectHtml);

    //var selected = $routeParams.category;
    //objS.val('category/' + selected);

    var typeUrl = app + '/product/type_map';
    $http.get(typeUrl).success(function (data, status, headers, config) {
        $scope.typeMap = data;
    });

    var productUrl = app + '/product/category/' + $routeParams.category + '/wechatid/0';
    $http.get(productUrl).success(function (data, status, headers, config) {
        $scope.products = data;
    });
    $scope.modify = function (id) {
        $('#dialog').attr('method', 'update');
        for (var i = 0; i < $scope.products.length; i++) {
            var product;
            if (id == $scope.products[i].id) {
                product = $scope.products[i];
                break;
            }
        }
        $('#pid').val(product.id);
        $('#name').val(product.name);
        $('#description').val(product.description);
        //$('#type').val(product.type);
        //$('#category').val(product.category);
        $('#price').val(product.price);
        $('#unit').val(product.unit);

    };

    $scope.create = function () {
        $('#dialog').attr('method', 'create');
    };

    $scope.export = function () {
        window.location.href = app + '/product/export/';
    };

    $scope.save = function () {
        var method = $('#dialog').attr('method');
        var product = {
            name: $('#name').val(),
            description: $('#description').val(),
            type: $('#type').val(),
            category: $('#category').val(),
            price: $('#price').val(),
            unit: $('#unit').val()
        };
        if (method == 'update') {
            product.id = $('#pid').val();
        }
        $.ajax({
            type: "post",
            url: app + "/product/" + method,
            contentType: "application/json",
            data: JSON.stringify(product),
            success: function (data) {
                alert('保存成功！');
                location.reload();
            },
            error: function (data) {
                alert('保存失败');
                location.reload();
            }
        });
    }
});

adminModule.controller('orderController', function ($scope, $http) {
    $('li[role]').removeClass('active');
    $('li[role="manage_order"]').addClass('active');
    $http.get(app + '/order/getall').success(function (data) {
        $scope.orders = data;
    });

    $scope.modify = function (id) {
        $('#myDialog').attr('method', 'update');
        for (var i = 0; i < $scope.orders.length; i++) {
            var order;
            if (id == $scope.orders[i].id) {
                order = $scope.orders[i];
                break;
            }
        }
        $('#oid').val(order.id);
        $('#userId').val(order.userId);
        $('#orderTs').val(order.orderTs);
        $('#deliveryTs').val(order.deliveryTs);
        $('#shopInfo').val(order.shopInfo);
        $('#consignee').val(order.consignee);
        $('#consigneeContact').val(order.consigneeContact);

    };

    $scope.save = function () {
        var oid = $('#oid').val();
        var order;
        for (var i = 0; i < $scope.orders.length; i++) {
            if (oid == $scope.orders[i].id) {
                order = $scope.orders[i];
                break;
            }
        }
        order.deliveryTs = $('#deliveryTs').val();
        order.shopInfo = $('#shopInfo').val();
        order.consignee = $('#consignee').val();
        order.consigneeContact = $('#consigneeContact').val();
        order.status = $('#orderStatus').val();

        //console.log(order);
        $.ajax({
            type: "post",
            url: app + "/order/update",
            contentType: "application/json",
            data: JSON.stringify(order),
            success: function (data) {
                alert('保存成功！');
                location.reload();
            },
            error: function (data) {
                alert('保存失败');
                location.reload();
            }
        });
    }

    $scope.export = function () {
        window.location.href = app + '/order/export/';
    };
});

adminModule.controller('dispatchController', function ($scope, $http) {
    $('li[role]').removeClass('active');
    $('li[role="manage_dispatch"]').addClass('active');
    $http.get(app + '/order/dispatch/list').success(function (data) {
        $scope.dispatchList = data;
    });

    $scope.export = function () {
        window.location.href = app + '/order/dispatch/export/';
    };

});

adminModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/order', {
            controller: 'orderController',
            templateUrl: 'orders.html'
        })
        .when('/product/category/:category', {
            controller: 'productController',
            templateUrl: 'product.html'
        })
        .when('/dispatch', {
            controller: 'dispatchController',
            templateUrl: 'dispatch.html'
        })
        .otherwise({
            redirectTo: '/product/category/yecailei'
        });
}]);