angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';


    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                // если фильтр существует то ложим в параметы запроса значение поля, иначе ложим нулл
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                pageIndex: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        })
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.resetFilter = function () {
        $scope.filter = null;
        $scope.loadProducts();
    }

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + 'api/v1/cart/' + $localStorage.winterMarketGuestCartId +'/add/' + productId);
    }

    // $scope.showProductInfo = function (productId) {
    //     $http.get(contextPath + 'api/v1/products/' + productId).then(function (response) {
    //         alert(response.data.title);
    //     });
    // }
    //
    // $scope.deleteProductById = function (productId) {
    //     $http.delete(contextPath + 'api/v1/products/' + productId).then(function (response) {
    //         $scope.loadProducts();
    //     });
    // }

    $scope.loadProducts();
});