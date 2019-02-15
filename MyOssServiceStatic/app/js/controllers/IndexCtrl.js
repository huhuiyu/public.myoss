(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('IndexCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', 'MyUtilService', IndexCtrl]);

  function IndexCtrl($scope, $log, MyDataService, DialogService, MyUtilService) {
    $log.debug('IndexCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('IndexCtrl destroy...');
    });

    $scope.model = {
      tbAdmin: {}
    };

    $scope.focus = {
      username: true
    };

    $scope.reg = function() {
      MyUtilService.redirect('/route/page/reg');
    };

    $scope.login = function() {
      if (MyUtilService.empty($scope.model.tbAdmin.username)) {
        DialogService.showAlert('用户名必须填写');
        return;
      }
      if (MyUtilService.empty($scope.model.tbAdmin.password)) {
        DialogService.showAlert('密码必须填写');
        return;
      }
      $scope.model.tbAdmin.password = MyUtilService.md5($scope.model.tbAdmin.password);
      DialogService.showWait('用户登录中，请稍候...');
      MyDataService.send('/TbAdmin/login', $scope.model, function(data) {
        DialogService.hideWait();
        $scope.model.tbAdmin.password = '';
        DialogService.showAlert(data.message, function() {
          if (data.success) {
            MyUtilService.redirect('/route/page/main');
          }
        });
      });
    };
  }
})();
