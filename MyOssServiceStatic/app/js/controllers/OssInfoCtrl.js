(function() {
  var ctrls = angular.module(MyAppConfig.controllers);
  ctrls.controller('OssInfoCtrl', ['$scope', '$log', 'MyDataService', 'DialogService', 'MyUtilService', OssInfoCtrl]);

  function OssInfoCtrl($scope, $log, MyDataService, DialogService, MyUtilService) {
    $log.debug('OssInfoCtrl init...');

    // 处理scope销毁
    $scope.$on('$destroy', function() {
      $log.debug('OssInfoCtrl destroy...');
    });

    $scope.tbOssInfo = {};
    $scope.ocids = [];

    $scope.showAdd = function() {
      DialogService.showCustom('templates/ossinfo-add.html', { ocids: $scope.ocids }, function() {
        $scope.query();
      });
    };

    $scope.showModify = function(ossInfo) {
      DialogService.showCustom('templates/ossinfo-modify.html', { ossInfo: ossInfo, ocids: $scope.ocids }, function() {
        $scope.query();
      });
    };

    //文件显示==============================================================
    $scope.showFile = function(ossInfo) {
      MyDataService.send('/TbOssInfo/getOssUrl', { tbOssInfo: { oiid: ossInfo.oiid } }, function(data) {
        if (data.success) {
          window.open(data.message, '_blank');
          return;
        }
        DialogService.showAlert(data.message);
      });
    };

    $scope.toObjUrl = function(ossInfo) {
      window.open(MyAppConfig.dataservice + '/common/getOssObjUrl?tbOssInfo.objectName=' + ossInfo.objectName + '&ts=' + new Date().getTime());
    };

    $scope.showObjUrl = function(ossInfo) {
      DialogService.showAlert(MyAppConfig.dataservice + '/common/getOssObjUrl?tbOssInfo.objectName=' + ossInfo.objectName + '&ts=' + new Date().getTime());
    };

    //删除==================================================================
    $scope.toDelete = function(ossinfo) {
      DialogService.showConfirm('是否删除:' + ossinfo.filename + '(' + ossinfo.fileinfo + ')?', function() {
        var tocid = $scope.tbOssInfo.ocid;
        $scope.tbOssInfo.oiid = ossinfo.oiid;
        $scope.tbOssInfo.ocid = ossinfo.ocid;
        DialogService.showWait('删除数据中，请稍候...');
        MyDataService.send('/TbOssInfo/delete', { tbOssInfo: $scope.tbOssInfo }, function(data) {
          DialogService.hideWait();
          DialogService.showAlert(data.message, function() {
            $scope.tbOssInfo.ocid = tocid;
            if (data.success) {
              $scope.query();
            }
          });
        });
      });
    };

    //查询==================================================================
    $scope.page = {
      pageNumber: 1,
      pageSize: 10
    };

    $scope.toPage = function(pn) {
      //不能超出范围
      if (pn <= 0 || pn > $scope.page.pageCount || pn == $scope.page.pageNumber) {
        return;
      }
      //分页查询
      $scope.page.pageNumber = pn;
      $scope.query();
    };

    $scope.query = function() {
      DialogService.showWait('信息查询中，请稍候');
      MyDataService.send('/TbOssInfo/queryAll', { page: $scope.page, tbOssInfo: $scope.tbOssInfo }, function(data) {
        DialogService.hideWait();
        if (data.success) {
          $scope.list = data.datas.list;
          $scope.page = data.datas.page;
          $scope.ocids = data.datas.ocids;
          return;
        }
        DialogService.showAlert(data.message);
      });
    };

    $scope.query();
  }
})();
