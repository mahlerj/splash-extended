'use strict';

angular.module('logicline.controllers')
.controller('UserCtrl', [
'$scope',
'apiUserService',

function($scope, apiUserService) {
  $scope.isOwnData = $scope.$state.current.name === 'customer_edit';
  $scope.referrer = $scope.isOwnData ? 'customer_search_view':'dashboard';

  apiUserService.getInformation($scope.$state.params.userId || '').then(function(response) {
    $scope.userInformation = response;
  });

  $scope.updateUser = function(isValid, userInformation) {
    if (isValid) {
      apiUserService.putInformation(userInformation).then(function(response) {
        $scope.updated = true;
      });
    };
  };

  $scope.resetPassword = function(userId) {
    apiUserService.passwordReset(userId).then(function(res) {
      $scope.res = res;
      if (!res.isError) {
        $scope.isReseted = true;       
      }
    });
  };
}]);