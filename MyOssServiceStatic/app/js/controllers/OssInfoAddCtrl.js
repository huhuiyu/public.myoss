(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('OssInfoAddCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', OssInfoAddCtrl]);

  function OssInfoAddCtrl($scope, $log, MyDataService, DialogService) {
    $log.debug('OssInfoAddCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('OssInfoAddCtrl destroy...');
    });

    $scope.ocids = DialogService.getCustomData().ocids;
    if (!$scope.ocids || $scope.ocids.length == 0) {
      DialogService.showAlert('请先配置oss信息', function() {
        DialogService.hideCustom();
      });
      return;
    }

    $scope.model = {
      tbOssInfo: { ocid: $scope.ocids[0].ocid }
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

    $scope.add = function() {
      $log.debug(fileEle);
      if (fileEle == null) {
        DialogService.showAlert('请选择文件');
        return;
      }
      $log.debug(fileEle.files[0]);
      if (fileEle.files[0].size > MyAppConfig.uploadLimit) {
        DialogService.showAlert('文件大小超过限制');
        return;
      }
      DialogService.showWait('文件上传中，请稍候...');
      var files = {
        file: [fileEle.files[0]]
      };

      MyDataService.sendFile('/TbOssInfo/add', files, $scope.model, function(data) {
        DialogService.hideWait();
        DialogService.showAlert(data.message);
      });
    };
  }
})();
