angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

    $scope.decrementInCart = function (productId) {
        $http.get(contextPath + 'api/v1/cart/decrement/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function () {
        $http.put(contextPath + 'api/v1/cart/clear').then(function (response) {
            $scope.loadCart();
        })
    }

    $scope.removeFromCart = function (productId) {
        $http.get(contextPath + 'api/v1/cart/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            })
    }

    $scope.createOrder = function () {
        $http.post(coreContextPath + 'api/v1/orders').then(function (response) {
            alert('Заказ оформлен');
            $scope.loadOrders();
            $scope.loadCart();
        })
    }

    $scope.loadCart();
});