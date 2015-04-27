'use strict';

/**
 * Services: API User
  */
angular.module('apcoa.services')
.factory('apiUserService', [
'Restangular',
'apiBaseResponses',

function(Restangular, apiBaseResponses) {
  var user = {
    clogin: function(credentials) {
      return Restangular
              .all('user/clogin')
              .post(credentials)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    login: function(credentials) {
      return Restangular
              .all('user/login')
              .post(credentials)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    passwordReset: function(userId) {
      return Restangular
              .one('user/edit/password', userId)
              .customPUT({})
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    passwordVerify: function(userId) {
      return Restangular
              .one('user/edit/password', userId)
              //.put({"oldPassword":"1234","newPassword":"5678"})
              .customPUT(credentials)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    passwordNew: function(credentials, userId) {
      return Restangular
              .one('user/edit/password', userId)
              //.put({newPassword":"5678"})
              .customPUT(credentials)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    getInformation: function(userId) {
      if (userId) {
        return Restangular
                .one('user/edit', userId)
                .get()
                .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
      } else {
        return Restangular
                .one('user/edit')
                .get()
                .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
      }
    },
    putInformation: function(userInformation) {
      return Restangular
              .one('user/edit', userInformation.userIdFk)
              .customPUT(userInformation)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    postInformation: function(userInformation) {
      return Restangular
              .one('user/create')
              .customPOST(userInformation)
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    getList: function() {
      return Restangular
              .one('user/edit')
              .get()
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    },
    getSearchedListByCustomerId: function(customerId) {
      return Restangular
              .one('user/search', customerId)
              .get()
              .then(apiBaseResponses.onSuccess, apiBaseResponses.onError);
    }
  };

  return user;
}]);