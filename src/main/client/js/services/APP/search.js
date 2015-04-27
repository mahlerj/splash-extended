'use strict';

angular.module('apcoa.services').factory('SearchService', [
    'apiUserService',
    'apiContractService',

function(apiUserService, apiContractService) {
    var searchResult = {},
        resultsList = [];

     function searchData(customerId, contractId) {
        return apiUserService.getSearchedListByCustomerId(customerId).then(function(response) {
            if (response.isError) {
                return response;
            }
            setResultsList(response);
            return resultsList;
        });
        /*return apiContractService.getList().then(function(response) {
            //resultsList = response;
            return resultsList;
        });*/
    };

    function chooseResult(resultId) {
        /*return apiUserService.getList().then(function(response) {
            searchResult = {
                contractInfo: {
                    contractId: resultId
                },
                customerInfo: {
                    customerId: response.customerId
                }
            };
            return searchResult;
        });*/

        // created mock respone as we are using only Customer object 
        for (var i = 0; i < resultsList.length; i++) {
             if (resultsList[i].userIdFk === resultId) {
                searchResult = {
                    contractInfo: {},
                    customerInfo: resultsList[i]
                }
                return searchResult;
            }
        }
    };

    function setResultsList(dataOject) {
        resultsList = [];
        for (var key in dataOject) {
            if (!dataOject.hasOwnProperty(key)) {
                continue;
            }

            resultsList.push({
                userIdFk   : key,
                customerId : dataOject[key]
            });
        }
    };

    function getResultsList() {
        return resultsList;
    };

    function getResult() {
        return searchResult;
    };

    function reset() {
        searchResult = {};
        resultsList = [];
    };

    return {
        searchData     : searchData,
        chooseResult   : chooseResult,
        getResultsList : getResultsList,
        getResult      : getResult,
        dataReset      : reset
    }
}]);