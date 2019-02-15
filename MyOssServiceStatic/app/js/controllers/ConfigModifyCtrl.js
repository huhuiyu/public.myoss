(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('ConfigModifyCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', ConfigModifyCtrl]);

  function ConfigModifyCtrl($scope, $log, MyDataService, DialogService) {
    $log.debug('ConfigModifyCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('ConfigModifyCtrl destroy...');
    });

    var customData = DialogService.getCustomData();

    $scope.model = {
      tbOssConfig: angular.copy(customData.config)
    };

    $scope.closeMe = function() {
      DialogService.hideCustom();
    };

    $scope.modify = function() {
      DialogService.showWait('信息修改中，请稍候...');
      MyDataService.send('/TbOssConfig/update', $scope.model, function(data) {
        DialogService.hideWait();
        DialogService.showAlert(data.message);
      });
    };
  }
})();
