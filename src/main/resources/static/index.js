angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/winter/api/v1';

    $scope.tryToAuth = function (){
        $http.post('http://localhost:8189/winter/auth', $scope.user)
            .then(function successCallback(response){
                if (response.data.token){
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback (response){

            });
    };

    $scope.tryToLogout = function (){
        $scope.clearUser();
        $scope.user = null;
    }

    $scope.clearUser = function (){
        delete $localStorage.winterMarketUser;
        $http.defaults.headerc.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function (){
        if ($localStorage.winterMarketUser){
            return true;
        } else {
            return false;
        }
    }

    $scope.checkAuth = function (){
        $http.get('http://localhost:8189/winter/auth_check').then(function (response){
            alert(response.data.value);
        })
    }

    if($localStorage.winterMarketUser){
        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date() / 1000);
            if (currentTime > payload.exp){
                console.log("Token is expired!!!");
                delete $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e){
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
    }

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

    $scope.removeFromCart = function (productId){
        $http.get(contextPath + '/cart/remove/' + productId)
            .then(function (response){
                $scope.loadCart();
            })
    }

    $scope.createOrder = function (){
        $http.post(contextPath + '/orders', $scope.orderData).then(function (response){
            $scope.loadOrders();
            $scope.loadCart();
        })
    }

    $scope.loadOrders = function (){
        $http.get(contextPath + '/orders').then(function (response){
            $scope.Orders = response.data;
        })
    }

    $scope.loadProducts();
    $scope.loadCart();
});