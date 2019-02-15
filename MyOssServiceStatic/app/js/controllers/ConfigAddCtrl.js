(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('ConfigAddCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', ConfigAddCtrl]);

  function ConfigAddCtrl($scope, $log, MyDataService, DialogService) {
    $log.debug('ConfigAddCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('ConfigAddCtrl destroy...');
    });

    $scope.model = {
      tbOssConfig: {}
    };

    $scope.closeMe = function() {
      DialogService.hideCustom();
    };

    $scope.add = function() {
      DialogService.showWait('信息保存中，请稍候...');
      MyDataService.send('/TbOssConfig/add', $scope.model, function(data) {
        DialogService.hideWait();
        if (data.success) {
          $scope.model.tbOssConfig = {};
        }
        DialogService.showAlert(data.message);
      });
    };
  }
})();
