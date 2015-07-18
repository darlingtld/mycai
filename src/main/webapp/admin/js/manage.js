/**
 * Created by tangld on 2015/6/8.
 */
var adminModule = angular.module('AdminModule', ['ngRoute']);
var app = '/mycai';

adminModule.controller('productController', function ($scope, $http) {
    $('li[role]').removeClass('active');
    $('li[role="manage_product"]').addClass('active');

    var typeUrl = app + '/product/type_map';
    $http.get(typeUrl).success(function (data, status, headers, config) {
        $scope.typeMap = data;
    });

    var productUrl = app + '/product/all';
    $http.get(productUrl).success(function (data, status, headers, config) {
        $scope.products = data;
    });
    $scope.modify = function (id) {
        $('#dialog').modal('show');
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
        //$("#type").find("option[value='" + product.type + "']").attr("selected", true);
        //$('#type').val(product.type);
        //$('#category').find("option[value='" + product.category + "']").attr("selected", true);
        $('#price').val(product.price);
        $('#unit').val(product.unit);

    };

    $scope.create = function () {
        $('#dialog').modal('show');
        $('#dialog').attr('method', 'create');
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
        $('#dialog').modal('show');
        $('#dialog').attr('method', 'update');
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
});

adminModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/order', {
            controller: 'orderController',
            templateUrl: 'orders.html'
        })
        .when('/product', {
            controller: 'productController',
            templateUrl: 'product.html'
        })
        .otherwise({
            redirectTo: '/product'
        });
}]);