'use strict';

/**
 * Services: API VRM
 */
angular.module('apcoa.services')
.factory('apiVRMService', [
'Restangular',
'apiBaseResponses',

function(Restangular, apiBaseResponses) {
  var vrm = {
    /**
     * Get VRM's
     * @param  {string} contractInfoKey
     * @return {object}
     */
    getVRMs: function(contractInfoKey) {
      return Restangular
              .one('vrm/edit', contractInfoKey)
              .get()
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    /**
     * Create VRM's
     * @param  {string} contractInfoKey
     * @param  {array}  vrms
     * @return {object}
     */
    createVRMs: function(contractInfoKey, vrms) {
      return Restangular
              .one('vrm/edit/create', contractInfoKey)
              .customPOST(vrms)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    /**
     * Update VRM's
     * @param  {string} contractInfoKey
     * @param  {array}  vrms
     * @return {object}
     */
    updateVRMs: function(contractInfoKey, vrms) {
      return Restangular
              .one('vrm/edit', contractInfoKey)
              .customPUT(vrms)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    /**
     * Put information
     * @param  {object} userInformation
     * @return {object}
     */
    putInformation: function(userInformation) {
      return Restangular
              .one('user/edit')
              .customPUT(userInformation)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    }
  };
  return vrm;
}]);