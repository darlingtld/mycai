/**
 * Created by tangld on 2015/6/8.
 */
var adminModule = angular.module('AdminModule', ['ngRoute']);
var app = '/mycai';


adminModule.controller('productController', function ($scope, $http) {
    $('li[role]').removeClass('active');
    $('li[role="manage_product"]').addClass('active');

    var url = app + '/product/all';
    $http.get(url).success(function (data, status, headers, config) {
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
        $('#type').val(product.type);
        $('#category').val(product.category);
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

adminModule.controller('orderController', function ($http) {
    $('li[role]').removeClass('active');
    $('li[role="manage_order"]').addClass('active');
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