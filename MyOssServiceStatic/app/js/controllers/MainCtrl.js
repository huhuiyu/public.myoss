(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('MainCtrl', ['$scope', '$log', '$location', '$timeout', 'MyDataService', 'DialogService', 'MyUtilService', MainCtrl]);

  function MainCtrl($scope, $log, $location, $timeout, MyDataService, DialogService, MyUtilService) {
    $log.debug('MainCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('MainCtrl destroy...');
    });

    $scope.logout = function() {
      DialogService.showWait('安全退出中，请稍候...');
      MyDataService.send('/TbAdmin/logout', {}, function(data) {
        DialogService.hideWait();
        if (data.success) {
          MyUtilService.redirect('/');
          return;
        }
        DialogService.showAlert(data.message);
      });
    };

    $scope.changePwd = function() {
      DialogService.showCustom('templates/changepwd.html');
    };

    //菜单管理=====================================================================================
    var menuBasePath = 'templates/';
    $scope.menus = [{ key: 'ossinfo.html', text: 'oss文件管理' }, { key: 'config.html', text: 'oss配置管理' }];

    $scope.changePage = function(index) {
      MyUtilService.redirect('/route/page/main', { menuid: index });
    };

    $scope.menuid = 0;
    $scope.changeInc = function() {
      $scope.menuid = 0;
      if (isNaN($location.search().menuid)) {
        $scope.menuid = 0;
      } else {
        $scope.menuid = parseInt($location.search().menuid);
      }
      if ($scope.menuid < 0 || $scope.menuid >= $scope.menus.length) {
        $scope.menuid = 0;
      }
      $timeout(function() {
        $scope.menu = menuBasePath + $scope.menus[$scope.menuid].key;
      }, 1);
    };

    $scope.changeInc();
  }
})();
