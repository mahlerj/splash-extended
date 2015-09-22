'use strict';

/**
 * Services: API Contract
 */
angular.module('logicline.services')
.factory('apiContractService', [
  'Restangular',
  'apiBaseResponses',

function(Restangular, apiBaseResponses) {
  var contract = {
    getList: function(userId) {
      if (userId) {
        return Restangular
              .one('contract/list', userId)
              .get()
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
      } else {
        return Restangular
              .one('contract/list')
              .get()
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
      }
    },
    getContractInformation: function(contractInfoKey) {
      return Restangular
              .one('contract/edit', contractInfoKey)
              .get()
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    putContractInformation: function(contractInfoKey, contractInfo) {
      return Restangular
              .one('contract/edit', contractInfoKey)
              .customPUT(contractInfo)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    }
  };

  return contract;
}]);