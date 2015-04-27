'use strict';

/**
 * Services: Api base responses
 */
angular.module('apcoa.services').factory('apiBaseResponses', [
function() {
  function ApcoaAPIError(error) {
    this.error = {};
    this.status = {};
    this.statusText = {};
    this.isError = true;

    if (error) {
      this.error = error;
      this.status = error.status;
      this.statusText = error.statusText;
    } else {
      this.status = 500;
      this.statusText = "Unexpected error";
    }
  };

  var apiBaseResponses = {
    onSuccess: function(response) {
      var res = {};
      if (!angular.isUndefined(response)) {
        res = response.plain()
      }
      return res;
    },
    onError: function(error) {
      return new ApcoaAPIError(error);
    }
  };
  return apiBaseResponses;
}]);