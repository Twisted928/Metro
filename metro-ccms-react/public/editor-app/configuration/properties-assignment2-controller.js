/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl2 = [
  '$scope',
  '$modal',
  function ($scope, $modal) {
    // Config for the modal window
    var opts = {
      template: 'editor-app/configuration/properties/assignment2-popup.html?version=' + Date.now(),
      scope: $scope,
    };

    // Open the dialog
    $modal(opts);
  },
];

var KisBpmAssignmentPopupCtrl2 = [
  '$scope',
  '$http',
  function ($scope, $http) {
    // Put json representing assignment on scope
    if (
      $scope.property.value !== undefined &&
      $scope.property.value !== null &&
      $scope.property.value.assignment !== undefined &&
      $scope.property.value.assignment !== null
    ) {
      $scope.assignment = $scope.property.value.assignment;
    } else {
      $scope.assignment = {};
    }

    if (
      $scope.assignment.candidateUsers == undefined ||
      $scope.assignment.candidateUsers.length == 0
    ) {
      $scope.assignment.candidateUsers = [{ value: '' }];
    }

    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function (index) {
      $scope.assignment.candidateUsers.splice(index + 1, 0, {
        value: 'value ' + userValueIndex++,
      });
    };

    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function (index) {
      $scope.assignment.candidateUsers.splice(index, 1);
    };

    if (
      $scope.assignment.candidateGroups == undefined ||
      $scope.assignment.candidateGroups.length == 0
    ) {
      $scope.assignment.candidateGroups = [{ value: '' }];
    }
    $scope.assignment.groups = [];

    //后台获取角色信息
    $http({
      method: 'GET',
      url: CONTEXT_PATH + '/activiti/model/getRole',
    })
      .success(function (data, status, headers, config) {
        let arr = [];
        let candidateGroups = $scope.assignment.candidateGroups || {};
        data.forEach((element) => {
          let a = candidateGroups.filter((d) => d.value == element.roleId);
          arr.push({
            name: element.roleName,
            value: element.roleId,
            remark: element.remark,
            check: a.length > 0,
          });
        });
        $scope.assignment.groups = arr;
      })
      .error(function (data, status, headers, config) {
        console.log('Error loading model with id ' + modelId + ' ' + data);
      });

    $scope.addCandidateGroupValue = function (index) {
      $scope.assignment.candidateGroups.splice(index + 1, 0, {
        value: $scope.assignment.groups[index].value,
      });
      $scope.assignment.groups[index].enable = false;
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function (index) {
      $scope.assignment.candidateGroups.splice(index, 1);
    };

    $scope.save = function () {
      $scope.property.value = {};
      handleAssignmentCheck($scope);
      $scope.property.value.assignment = $scope.assignment;

      $scope.updatePropertyInModel($scope.property);
      $scope.close();
    };

    // Close button handler
    $scope.close = function () {
      handleAssignmentInput($scope);
      $scope.property.mode = 'read';
      $scope.$hide();
    };

    var handleAssignmentCheck = function ($scope) {
      if ($scope.assignment.groups) {
        let arr = [];
        $scope.assignment.groups.forEach((element) => {
          if (element.check) {
            arr.push({ value: element.value });
          }
        });

        $scope.assignment.candidateGroups = arr;
      }
    };

    var handleAssignmentInput = function ($scope) {
      if ($scope.assignment.candidateUsers) {
        var emptyUsers = true;
        var toRemoveIndexes = [];
        for (var i = 0; i < $scope.assignment.candidateUsers.length; i++) {
          if ($scope.assignment.candidateUsers[i].value != '') {
            emptyUsers = false;
          } else {
            toRemoveIndexes[toRemoveIndexes.length] = i;
          }
        }

        for (var i = 0; i < toRemoveIndexes.length; i++) {
          $scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
        }

        if (emptyUsers) {
          $scope.assignment.candidateUsers = undefined;
        }
      }

      if ($scope.assignment.candidateGroups) {
        var emptyGroups = true;
        var toRemoveIndexes = [];
        for (var i = 0; i < $scope.assignment.candidateGroups.length; i++) {
          if ($scope.assignment.candidateGroups[i].value != '') {
            emptyGroups = false;
          } else {
            toRemoveIndexes[toRemoveIndexes.length] = i;
          }
        }

        for (var i = 0; i < toRemoveIndexes.length; i++) {
          $scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
        }

        if (emptyGroups) {
          $scope.assignment.candidateGroups = undefined;
        }
      }
    };
  },
];
