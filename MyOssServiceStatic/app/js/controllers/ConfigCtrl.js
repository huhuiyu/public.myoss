(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('ConfigCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', ConfigCtrl]);

  function ConfigCtrl($scope, $log, MyDataService, DialogService) {
    $log.debug('ConfigCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('ConfigCtrl destroy...');
    });

    $scope.showModify = function(config) {
      DialogService.showCustom('templates/config-modify.html', { config: config }, function() {
        $scope.query();
      });
    };

    $scope.showAdd = function(config) {
      DialogService.showCustom('templates/config-add.html', {}, function() {
        $scope.query();
      });
    };

    $scope.delete = function(config) {
      DialogService.showConfirm('是否删除' + config.bucketName + '?', function() {
        DialogService.showWait('信息查询中，请稍候');
        MyDataService.send('/TbOssConfig/delete', { tbOssConfig: config }, function(data) {
          DialogService.hideWait();
          DialogService.showAlert(data.message, function() {
            if (data.success) {
              $scope.query();
            }
          });
        });
      });
    };

    $scope.query = function() {
      DialogService.showWait('信息查询中，请稍候');
      MyDataService.send('/TbOssConfig/queryAll', {}, function(data) {
        DialogService.hideWait();
        if (data.success) {
          $scope.list = data.datas.list;
          return;
        }
        DialogService.showAlert(data.message);
      });
    };

    $scope.query();
  }
})();
