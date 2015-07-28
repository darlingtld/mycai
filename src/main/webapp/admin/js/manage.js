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
        "猪肉": "category/zhurou",
        "羊肉": "category/yangrou",
        "牛肉": "category/niurou",
        "鸡肉": "category/jirou",
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

var tdata = {
    "蔬菜水果": "shucaishuiguo",
    "禽肉蛋类": "qinroudanlei",
    "水产冻货": "shuichandonghuo",
    "米面粮油": "mimianliangyou",
    "调料其他": "tiaoliaoqita",
    "餐厨用品": "canchuyongpin",
    "酒水饮料": "jiushuiyinliao"
}

var isFirst = true;

adminModule.controller('productController', function ($scope, $http, $routeParams, $location) {
    $('li[role]').removeClass('active');
    $('li[role="manage_product"]').addClass('active');

    $scope.changedValue = function (item) {
        var productUrl = app + '/product/' + item + '/wechatid/0';
        $http.get(productUrl).success(function (data, status, headers, config) {
            $scope.products = data;
            procfilter();
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

    //console.log(isFirst)
    if (isFirst == true) {
        setTimeout("objS.val('category/yecailei')", 1000);
        isFirst = false;
    }

    var typeUrl = app + '/product/type_map';
    $http.get(typeUrl).success(function (data, status, headers, config) {
        $scope.typeMap = data;
    });

    var productUrl = app + '/product/category/' + $routeParams.category + '/wechatid/0';

    function getProcurement4Product(procurement, id) {
        for (var i = 0; i < procurement.length; i++) {
            if (procurement[i].productId == id) {
                return procurement[i];
            }
        }
        return null;
    }

    $http.get(productUrl).success(function (data, status, headers, config) {
        $scope.products = data;
        procfilter();

    });

    function procfilter() {
        var procurementUrl = app + '/product/procurement/all';
        $http.get(procurementUrl).success(function (data, status, headers, config) {
                $scope.procurement = data;
                for (var i = 0; i < $scope.products.length; i++) {
                    var procurement = getProcurement4Product($scope.procurement, $scope.products[i].id);
                    if (procurement == null) {
                        $scope.products[i].procindex = 1.0;
                        $scope.products[i].procprice = $scope.products[i].price;
                    } else {
                        $scope.products[i].procindex = procurement.procindex;
                        $scope.products[i].procprice = procurement.procprice;
                    }
                    $scope.products[i].price = ($scope.products[i].procindex * $scope.products[i].procprice).toFixed(2);
                }
            }
        );
    }


    $scope.updateProduct = function () {
        this.product.price = (this.product.procindex * this.product.procprice).toFixed(2);
        $http.post(app + '/product/update', JSON.stringify(this.product)).success(function () {

        })
        console.log(this.product)
    }

    //var procurementUrl = app + '/product/procurement/all';
    //$http.get(procurementUrl).success(function (data, status, headers, config) {
    //    $scope.procurement = data;
    //});
    $scope.modify = function (id) {
        $('#myDialog').attr('method', 'update');
        $('#myDialog .title').text('修改菜品');
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
        $('#type').attr('value', '');
        $('#category').attr('value', '');
        $('#price').val(product.price);
        $('#unit').val(product.unit);

    };

    $scope.create = function () {
        $('#myDialog').attr('method', 'create');
        $('#myDialog .title').text('新增菜品');
        $('#pid').val('');
        $('#name').val('');
        $('#description').val('');
        $('#type').val('');
        $('#category').val('');
        $('#price').val('');
        $('#unit').val('');
    };

    $scope.export = function () {
        window.location.href = app + '/product/export/';
    };

    $scope.save = function () {
        var method = $('#myDialog').attr('method');
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

    $scope.delete = function (orderId) {
        if (confirm("确认删除该订单")) {
            $http.post(app + '/order/delete/' + orderId, {}).success(function (data) {
                alert("删除成功！");
                location.reload();
            }).error(function (data, status, headers, configs) {
                alert(decodeURI(headers().message));
                console.log(headers)
            });
        }
    }
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

adminModule.filter('translate', function () {
    return function (text, type) {
        if (!angular.isString(text)) {
            return text;
        }
        var translatedText;

        if (type == 't') {
            for (var key in tdata) {
                if (tdata[key].toUpperCase().indexOf(text) != -1) {
                    //console.log(key + " " + vkey);
                    translatedText = key;
                    return translatedText;
                }
            }
        } else {
            for (var key in cdata) {
                for (var vkey in cdata[key]) {
                    if (cdata[key][vkey].toUpperCase().indexOf(text) != -1) {
                        //console.log(key + " " + vkey);
                        translatedText = vkey;
                        return translatedText;
                    }
                }
            }
        }
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