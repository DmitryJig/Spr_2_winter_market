(function () {

    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            // .when('/order_pay/:orderId', {
            //     templateUrl: 'order_pay/order_pay.html',
            //     controller: 'orderPayController'
            // })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.winterMarketUser) {
            try {
                let jwt = $localStorage.winterMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.winterMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
        }
        if (!$localStorage.winterMarketGuestCartId){
            $http.get('http://localhost:5555/cart/api/v1/cart/generate_uuid')
                .then(function successCallback(response){
                    $localStorage.winterMarketGuestCartId = response.data.value;
            });
        }
    }
})();
angular.module('market').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
        const contextPath = 'http://localhost:5555';

        $scope.tryToAuth = function () {
            $http.post(contextPath + '/auth/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                        $location.path('/');
                        $scope.mergeCart();
                    }
                }, function errorCallback(response) {

                });
        };

        $scope.tryToLogout = function () {
            $scope.clearUser();
            $scope.user = null;
            $location.path('/');
        }

        $scope.clearUser = function () {
            delete $localStorage.winterMarketUser;
            $http.defaults.headerc.common.Authorization = '';
        };

        $scope.isUserLoggedIn = function () {
            if ($localStorage.winterMarketUser) {
                return true;
            } else {
                return false;
            }
        }

    $scope.mergeCart = function () {
        $http.put('http://localhost:5555/cart/' + 'api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/merge')
            .then(function (response){
                console.log('carts merged')
            })
    }

        // $scope.submitCreateProduct = function () {
        //     $http.post(contextPath + '/core/api/v1/products', $scope.newProduct).then(function (response) {
        //         $scope.loadProducts();
        //     })
        // }
    }
)
;