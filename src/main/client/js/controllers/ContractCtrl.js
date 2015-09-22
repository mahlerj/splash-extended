'use strict';

angular
.module('logicline.controllers')
.controller('ContractCtrl', [
'$state',
'$scope',
'apiContractService',

function($state, $scope, apiContractService) {
  $scope.referrer = $state.current.name === 'contract_list_admin'? 'vrm_edit_admin': 'vrm_edit';
  apiContractService.getList($state.params.userId).then(function(response) {
    if (response === false) {
      $scope.contracts = [];
      return;
      }
    $scope.contracts = response instanceof Array? response: [response];
  });
}]);