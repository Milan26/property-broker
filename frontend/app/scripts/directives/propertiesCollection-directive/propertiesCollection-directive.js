'use strict';

angular.module('propertyBrokerApp.directives')
    .directive('propertiesCollection', function () {
        return {
            templateUrl: 'scripts/directives/propertiesCollection-directive/propertiesCollection-directive.html',
            restrict: 'E',
            scope: {
                data: '='
            },
            controller: ['$scope', '$filter', function ($scope, $filter) {

                $scope.$watch('data', function (newValue) {
                    $scope.data = newValue;
                }, false);

                $scope.filteredProperties = [];
                $scope.currentPage = 1;
                $scope.itemsPerPage = 5;
                $scope.maxSize = 10;

                $scope.$watch('query', function (query) {
                    $scope.filteredData = $filter('filter')($scope.data, query);
                    $scope.totalItems = $scope.filteredData.length;
                    $scope.pageChanged();
                }, false);

                $scope.setPage = function (pageNo) {
                    $scope.currentPage = pageNo;
                };

                $scope.pageChanged = function() {
                    var begin = ($scope.currentPage - 1) * $scope.itemsPerPage;
                    var end = begin + $scope.itemsPerPage;
                    $scope.filteredProperties = $scope.filteredData.slice(begin, end);
                };


                $scope.propertySelected  = function(selected){
                    console.log(selected);
                    $scope.marker = {
                        id: 0,
                        show:true,
                        coords: {latitude: selected.coords.x, longitude: selected.coords.y },
                        options:{
                            icon:{url:"http://maps.google.com/mapfiles/ms/icons/yellow-dot.png"}
                        },
                        selected:selected
                    };
                };


            }]
        };
    });
