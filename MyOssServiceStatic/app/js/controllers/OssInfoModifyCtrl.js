(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('OssInfoModifyCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', OssInfoModifyCtrl]);

  function OssInfoModifyCtrl($scope, $log, MyDataService, DialogService) {
    $log.debug('OssInfoModifyCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('OssInfoModifyCtrl destroy...');
    });

    var customData = DialogService.getCustomData();
    $scope.ocids = customData.ocids;
    if (!$scope.ocids || $scope.ocids.length == 0) {
      DialogService.showAlert('请先配置oss信息', function() {
        DialogService.hideCustom();
      });
      return;
    }

    $scope.model = {
      tbOssInfo: angular.copy(customData.ossInfo)
    };

    $scope.closeMe = function() {
      DialogService.hideCustom();
    };

    var fileEle = null;
    $scope.changeFile = function(ele) {
      if (ele.value) {
        fileEle = ele;
      } else {
        fileEle = null;
      }
    };

    $scope.modify = function() {
      $log.debug(fileEle);
      if (fileEle != null && fileEle.files[0].size > MyAppConfig.uploadLimit) {
        DialogService.showAlert('文件大小超过限制');
        return;
      }
      DialogService.showWait('信息修改中，请稍候...');

      var files = {
        file: []
      };

      if (fileEle != null) {
        files.file.push(fileEle.files[0]);
      }

      MyDataService.sendFile('/TbOssInfo/update', files, $scope.model, function(data) {
        DialogService.hideWait();
        DialogService.showAlert(data.message);
      });
    };
  }
})();
