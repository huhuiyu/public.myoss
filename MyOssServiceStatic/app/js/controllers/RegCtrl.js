(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('RegCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', 'MyUtilService', RegCtrl]);

  function RegCtrl($scope, $log, MyDataService, DialogService, MyUtilService) {
    $log.debug('RegCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('RegCtrl destroy...');
    });

    $scope.model = {
      tbAdmin: {}
    };

    $scope.other = { pwd1: '', agreement: false };

    $scope.focus = {
      username: true
    };

    $scope.back = function() {
      MyUtilService.redirect('/');
    };

    $scope.showAgreement = function() {
      DialogService.showAlert('上传文件必须遵守法律法规和阿里云的oss协议。');
    };

    $scope.reg = function() {
      if (MyUtilService.empty($scope.model.tbAdmin.username)) {
        DialogService.showAlert('用户名必须填写');
        return;
      }
      if (MyUtilService.empty($scope.model.tbAdmin.password)) {
        DialogService.showAlert('密码必须填写');
        return;
      }
      if ($scope.model.tbAdmin.password != $scope.other.pwd1) {
        DialogService.showAlert('密码必须一致');
        return;
      }
      if (!$scope.other.agreement) {
        DialogService.showAlert('必须同意协议');
        return;
      }
      $scope.model.tbAdmin.password = MyUtilService.md5($scope.model.tbAdmin.password);
      DialogService.showWait('用户注册中，请稍候...');
      MyDataService.send('/TbAdmin/reg', $scope.model, function(data) {
        DialogService.hideWait();
        $scope.model.tbAdmin = {};
        $scope.other = { pwd1: '', agreement: false };
        DialogService.showAlert(data.message, function() {
          if (data.success) {
            MyUtilService.redirect('/');
          }
        });
      });
    };
  }
})();
