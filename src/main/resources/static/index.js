angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/winter/api/v1';

    $scope.loadProducts = function (){
        $http({
           url: contextPath + '/products',
           method: 'GET'
        }).then(function (response){
                $scope.Products = response.data;
            })
    }

    $scope.showProductInfo = function (productId){
        $http.get(contextPath + '/products/' + productId).then(function (response){
            alert(response.data.title);
        });
    }

    $scope.deleteProductById = function (productId){
        $http.delete(contextPath + '/products/' + productId).then(function (response){
            $scope.loadProducts();
        });
    }

    $scope.addToCart = function (productId){
        $http.get(contextPath + '/cart/add/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.decrementInCart = function (productId){
        $http.get(contextPath + '/cart/decrement/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.loadCart = function (){
        $http.get(contextPath + '/cart').then(function (response){
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function (){
        $http.get(contextPath + '/cart/clear').then(function (response){
            $scope.loadCart();
        })
    }

    $scope.clearCartLine = function (productId){
        $http.get(contextPath + '/cart/clear/' + productId)
            .then(function (response){
                $scope.loadCart();
            })
    }

    $scope.loadProducts();
    $scope.loadCart();
});