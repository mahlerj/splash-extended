'use strict';

angular
.module('apcoa.controllers')
.controller('SearchCtrl', [
'$rootScope',
'$scope',
'SearchService',

function($rootScope, $scope, SearchService) {
	$scope.customerId = null;
	$scope.contractId = null;
	$scope.searchResult = null;
	$scope.resultsList = null;
	$scope.isResultList = false;
	$scope.isResultChosen = false;

	if ($scope.$state.current.name !== 'customer_search') {
		$scope.resultsList = SearchService.getResultsList();
		$scope.searchResult = SearchService.getResult();
		if ($scope.resultsList.length > 0) {
			$scope.isResultList = false;
			$scope.isResultChosen = true;
		}
	} else {
		SearchService.dataReset();
		delete $rootScope.searchUserIdFk;
		delete $rootScope.isSearchActive;
	}

	$scope.searchData = function() {
		if((!angular.isString($scope.customerId) || $scope.customerId === '') && 
			(!angular.isString($scope.contractId) || $scope.contractId === '')) {
			return;
		}

		SearchService.searchData($scope.customerId, $scope.contractId).then(function(response) {
			if (response.isError) {
				return;
			}
			$scope.resultsList = [];

			if (!!response.length) {
				$scope.isResultList = true;
				$scope.resultsList = response;
			}
		});
	};

	$scope.chooseResult = function(resultId) {
		$rootScope.searchUserIdFk = resultId;
		$rootScope.isSearchActive = true;

		$scope.isResultList = false;
		$scope.isResultChosen = true;
		$scope.searchResult = SearchService.chooseResult(resultId);

		$scope.$state.go('customer_search_view');
	};

	$scope.cancelSearch = function() {
		delete $rootScope.searchUserIdFk;
		delete $rootScope.isSearchActive;		

		$scope.$state.go('dashboard');
	}

	$scope.backToResults = function() {
		$scope.searchResult = null;
		$scope.isResultList = true;
		$scope.isResultChosen = false;

		delete $rootScope.searchUserIdFk;
		delete $rootScope.isSearchActive;
	};

	//TODO: for the current implementation search by Customer ID field only
	/*$scope.searchData = function() {
		if((!angular.isString($scope.customerId) || $scope.customerId === '') && 
			(!angular.isString($scope.contractId) || $scope.contractId === '')) {
			return;
		}
		SearchService.searchContracts($scope.customerId, $scope.contractId).then(function(response) {
			$scope.isResultList = true;
			$scope.resultsList = response;
		});
	};
	$scope.chooseResult = function(resultId) {
		SearchService.chooseResult(resultId).then(function(response) {
			$scope.isResultList = false;
			$scope.isResultChosen = true;
			$scope.searchResult = response;
		});
	};*/
}]);