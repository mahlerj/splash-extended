'use strict';

angular
.module('apcoa.controllers')
.controller('VRMCtrl', [
'$scope',
'apiContractService',
'apiVRMService',
'apiUserService',

function($scope, apiContractService, apiVRMService, apiUserService) {
  $scope.searchValue = null;
  $scope.vrms = [];
  $scope.vrmsToCreate = [];
  $scope.referrer = $scope.$state.current.name === 'vrm_edit_admin'? 'customer_search_view': 'contract_list';

  var oldFacilityName = '';
  var oldParkingLotCount = 0;

  apiContractService.getContractInformation($scope.$state.params.contractInfoId).then(function(response) {
    $scope.contract = response;
    oldParkingLotCount = $scope.contract.contractInfo.parkingLotCount;
    oldFacilityName = $scope.contract.contractInfo.facilityName;

    getVRMs();

    apiUserService.getInformation($scope.contract.contractInfo.userIdFk).then(function(response) {
      if (response !== false) {
        $scope.customer = response;
      }
    });
  });

  $scope.filterBySearch = function(vrm) {
    if (vrm.isDeleted) {
      return false;
    }

    if (!angular.isString($scope.searchValue) || $scope.searchValue === '') {
      return true;
    }

    var regex = new RegExp(escapeRegExp($scope.searchValue), 'i');
    return (regex.test(vrm.vrmName) || regex.test(vrm.description));
  };

  $scope.removeVRM = function(vrmId) {
    $scope.vrms.forEach(function(vrm) {
      if (vrm.vrmId === vrmId) {
        vrm.isDeleted = true;
      }
    });
    setVRMsToAdd();
  };

  $scope.clearVRM = function(index) {
    $scope.vrms[index].vrmName = '';
    $scope.vrms[index].description = '';
    $scope.vrms[index].isUpdated = true;
  };

  $scope.updateVRM = function(index) {
    $scope.vrms[index].isUpdated = true;
  };

  $scope.removeNewVRM = function(index) {
    //$scope.vrmsToCreate.splice(index, 1);
    $scope.vrmsToCreate[index] = null;
  }

  $scope.createNewVRM = function(index, vrm) {
    $scope.vrmsToCreate[index] = vrm;
  };

  $scope.updateData = function(isValid) {
      $scope.vrmEditFormSubmitted = true;
      if (!$scope.contract.contractInfo.parkingLotCount) {
        $scope.contract.contractInfo.parkingLotCount = 0;
      }

      var vrmsToCreate = getVRMsToCreate();
      var vrmsToUpdateOrDelete = getVRMsToUpdateOrDelete();

      if (isValid && isVRMsCountValid(vrmsToCreate.length)) {
        apiVRMService.updateVRMs($scope.$state.params.contractInfoId, vrmsToUpdateOrDelete).then(function(response) {
            apiVRMService.createVRMs($scope.$state.params.contractInfoId, vrmsToCreate).then(function(response) {
              if (oldParkingLotCount !== $scope.contract.contractInfo.parkingLotCount || oldFacilityName !== $scope.contract.contractInfo.facilityName) {
                apiContractService.putContractInformation($scope.$state.params.contractInfoId, $scope.contract.contractInfo);
              }
              $scope.updated = true;
              $scope.vrmsToCreate = [];
              getVRMs();
            });
          });
      };
  };

  $scope.$watch('contract.contractInfo.parkingLotCount', function(newValue) {
    // initial update. so do not need any actions
    if (angular.isUndefined(newValue) || newValue > 100) {
      return;
    }
    setVRMsToAdd();
  });

  function getVRMs() {
    apiVRMService.getVRMs($scope.$state.params.contractInfoId).then(function(response) {
      $scope.vrms = [];
      if (response !== false) {
        $scope.vrms = response;
        setVRMsToAdd();
      }
    });
  };

  function isVRMsCountValid(sizeOfNewVRMs) {
    var currentVRMs = $scope.vrms.filter(function(vrm) {
      return angular.isUndefined(vrm.isDeleted);
    });
    var vrmsPossible = getPossibleVRMCount($scope.contract.contractInfo.parkingLotCount);
    $scope.bufferMRNs = -1 *(vrmsPossible - (sizeOfNewVRMs + currentVRMs.length));
   
    if ($scope.bufferMRNs > 0) {
      $scope.updated = false;
      return false;
    }
    return true;
  };

  function getVRMsToUpdateOrDelete() {
    if (!$scope.vrms) {
      return [];
    }
    var vrmsArray = angular.copy($scope.vrms.filter(function(vrm) {
      return (vrm.isDeleted || vrm.isUpdated);
    }));

    vrmsArray.forEach(function(vrm) {
      if (vrm.isDeleted) {
        vrm.vrmName = '';
        vrm.description = '';
        delete vrm.isDeleted;
        delete vrm.isUpdated;
      }
      if (vrm.isUpdated) {
        delete vrm.isUpdated;
      }
    });
    return vrmsArray;
  };

  function getVRMsToCreate() {
    if (!$scope.vrmsToCreate) {
      return [];
    }
    return $scope.vrmsToCreate.filter(function(vrm) {
      return (vrm !== 'undefined' && vrm !== null);
    });
  };

  function setVRMsToAdd() {
    if (!$scope.contract || angular.isUndefined($scope.contract.contractInfo) || angular.isUndefined($scope.contract.contractInfo.parkingLotCount)) {
      return;
    }
    $scope.vrmsToCreate = getVRMsToCreate();
    var vrmsPossible = getPossibleVRMCount($scope.contract.contractInfo.parkingLotCount);
    var existingVRMs = $scope.vrms.filter(function(vrm) {return angular.isUndefined(vrm.isDeleted)});
    var arraylength = vrmsPossible - existingVRMs.length - $scope.vrmsToCreate.length;

    if (arraylength > 0) {
      $scope.vrmsToCreate = $scope.vrmsToCreate.concat(new Array(arraylength));
    }
  };

  /*
   * Calculate number of possible new VRM's
   */
  function getPossibleVRMCount(parkingLotCount) {
    var multiplier = 5;
    return parseInt(parkingLotCount) * multiplier;
  };

  function escapeRegExp(string) {
    return string ? string.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1"): '';
  };
}]);