/**
 * Created by Hocean on 2017/2/6.
 */
!define(["jquery", "angular", "jquery.form", "bootstrap", "erp/common/common", "html5Validate", "jquery-ui", "erp/bas/basConsoleUtil"], function ($) {
    angular.module('basSiUserFromSysUser', []).controller("basSiUserFromSysUserCtr", function ($scope, $http) {
        $scope.dataList = [];
        var init = function () {

        };
        var submit = function (id) {
            var url = "basesiuser/addbasesiuser";
            $http({
                method: 'POST',
                url: $('#path').val() + "/tbi/" + url,
                data: "data=" + JSON.stringify($scope.userSiGroup),  // pass in data as strings
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
            }).success(function (data) {

            });
        };
        $scope.events = {
            alerts: function (msg) {
                alert(msg);
            },
            add: function () {

            },
            edit: function (index) {
                var url = "sysUser/selectSysUser?id=" + index;
                $http({
                    method: 'GET',
                    url: $('#path').val() + "/tbi/" + url,
                    //data: "",  // pass in data as strings
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                }).success(function (data) {

                });
            },
            delete: function (id) {
                if(window.confirm('确认删除该记录?')==true){
                    //location.href='<%=path%>/tbi/operatingWarning/deleteBasSiUser?id=item.siId';
                    var url = '/tbi/basesiuser/deletebasesiuser?id=';
                    location.href=$('#path').val()  + url + id;
                }
            },
            update: function (id) {
                var urlSelectOne = "basesiuser/selectbasesiuser";
                var urlUpdateOne = "basesiuser/editbasesiusers";
                editData(id,urlSelectOne,urlUpdateOne)
                alert(id);
            },
            search: function () {
                var url = "basesiuser/basesiuser";
                $http({
                    method: 'POST',
                    url: $('#path').val() + "/tbi/" + url,
                    //data: "data=" + JSON.stringify($scope.userSiGroup),  // pass in data as strings
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                }).success(function (data) {
                    $scope.dataList = data.list;
                });
            },
            cleanSearch: function () {

            },
            submit: function () {
                var url = "basesiuser/addbasesiuser";
                $http({
                    method: 'POST',
                    url: $('#path').val() + "/tbi/" + url,
                    data: "data=" + JSON.stringify($scope.userSiGroup),  // pass in data as strings
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                }).success(function (data) {

                });
            }
        }
        init();
    });
    angular.bootstrap(document, ['basSiUserFromSysUser']);


});
