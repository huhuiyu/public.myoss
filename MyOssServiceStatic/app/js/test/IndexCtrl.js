(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('TestIndexCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', 'MyUtilService', 'ToolService', TestIndexCtrl]);

  function TestIndexCtrl($scope, $log, MyDataService, DialogService, MyUtilService, ToolService) {
    $log.debug('TestIndexCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('TestIndexCtrl destroy...');
    });

    $scope.icons = ['&#xe645;', '&#xe662;', '&#xe62b;', '&#xe628;', '&#xe845;'];

    $scope.auth = function() {
      DialogService.showWait('授权测试中...');
      MyDataService.send('/auth/getAdmin', {}, function(data) {
        DialogService.hideWait();
        $log.debug(data);
        $scope.data = MyUtilService.trustAsHtml(MyUtilService.formatJson(data, true));
        $log.debug(ToolService.getServerToken());
      });
    };
  }
})();
