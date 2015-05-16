/**
 * Created by darlingtld on 2015/5/14.
 */
var mycaiModule = angular.module('MycaiModule', ['ngRoute']);

mycaiModule.controller('latestFoodController', function ($scope) {

});

mycaiModule.controller('yecaileiController', function ($scope, $http) {
    $http.get('/shucaishuiguo/yecailei').success(function (data, status, headers, config) {
        $scope.products = data;
    });
});

mycaiModule.controller('yijibaitiaoController', function ($scope) {

});

mycaiModule.controller('haixianshuichanController', function ($scope) {

});

mycaiModule.controller('damiController', function ($scope) {

});

mycaiModule.controller('tiaoweipinController', function ($scope) {

});

mycaiModule.controller('zhipinshijingController', function ($scope) {

});

mycaiModule.controller('yinliaoController', function ($scope) {

});

mycaiModule.controller('checkoutController', function ($scope) {

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
        .when('/shucaishuiguo/yecailei', {
            controller: 'yecaileiController',
            templateUrl: 'yecailei.html'
        })
        .when('/qinroudanlei/yijibaitiao', {
            controller: 'yijibaitiaoController',
            templateUrl: 'yijibaitiao.html'
        })
        .when('/shuichandonghuo/haixianshuichan', {
            controller: 'haixianshuichanController',
            templateUrl: 'haixianshuichan.html'
        })
        .when('/mimianliangyou/dami', {
            controller: 'damiController',
            templateUrl: 'dami.html'
        })
        .when('/tiaoliaoqita/tiaoweipin', {
            controller: 'tiaoweipinController',
            templateUrl: 'tiaoweipin.html'
        })
        .when('/canchuyongpin/zhipinshijing', {
            controller: 'zhipinshijingController',
            templateUrl: 'zhipinshijin.html'
        })
        .when('/jiushuiyinliao/yinliao', {
            controller: 'yinliaoController',
            templateUrl: 'yinliao.html'
        })
        .when('/checkout', {
            controller: 'checkoutController',
            templateUrl: 'checkout.html'
        })
        .otherwise({
            redirectTo: '/shucaishuiguo/yecailei'
        });
}]);

