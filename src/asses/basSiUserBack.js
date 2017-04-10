/**
 * Created by Hocean on 2017/2/6.
 */
!define(["jquery", "angular", "jquery.form", "bootstrap", "erp/common/common", "html5Validate", "jquery-ui", "erp/bas/basConsoleUtil"], function ($) {
    $(document).ready(function () {


    });

    angular.module('basSiUserFromSysUser', []).controller("basSiUserFromSysUserCtr", function ($scope, $http) {

        $scope.selects = {  //保留值
            items: [], //[{label: "未分组", value: "", tag: "test"}],
            selectedValue: ""
        };
        $scope.selectsSearch = {  //搜索
            items: [{label: "全部", value: "", tag: "test"}],
            selectedValue: ""
        };
        $scope.groups = [];  //只是显示

        $scope.userSiGroup = {  //提交的数据
            userId: "",
            basSiUserId: "",
            siId: "",
            groups: []
        };

        $scope.tabs = [];
        $scope.tab = {  //表格
            office_id : "",
            login_name : "",
            name : "",
            si_id : "",
            group_name : "",
            id : ""
        };

        $scope.dataList = [];

        var init = function () {
            // var c = angular.copy($scope.selects);
            // $scope.groups.push(c);

            var url = "sysGroup/sysGroup";
            $.ajax({
                async: false,
                type: "GET",
                url: $('#path').val() + "/tbi/" + url,
                data: "",
                success: function (retData) {
                    for (var p in retData) {
                        if (retData[p]) {
                            var map = retData[p];
                            $scope.selects.items.push({label: map.groupName, value: map.id, tag: "test"});
                            $scope.selectsSearch.items.push({label: map.groupName, value: map.id, tag: "test"});
                        }
                    }
                }
            });

        };

        $scope.events = {
            alerts: function (msg) {
                alert(msg);
            },
            update: function (id) {
                var urlSelectOne = "basesiuser/selectbasesiuser";
                var urlUpdateOne = "basesiuser/editbasesiusers";
                editData(id,urlSelectOne,urlUpdateOne)
                alert(id);
            },
            delete: function (id) {
                if(window.confirm('确认删除该记录?')==true){
                    //location.href='<%=path%>/tbi/operatingWarning/deleteBasSiUser?id=item.siId';
                    var url = '/tbi/basesiuser/deletebasesiuser?id=';
                    location.href=$('#path').val()  + url + id;
                }
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
                    // for (var p in data) {
                    //     if (data[p]) {
                    //         var d = data[p];
                    //         var tab = angular.copy($scope.tab);
                    //         tab.office_id = d.office_id;
                    //         tab.login_name  = d.login_name;
                    //         tab.name  = d.name;
                    //         tab.si_id  = d.si_id;
                    //         tab.group_name  = d.group_name;
                    //         tab.id = d.id;
                    //         $scope.tabs.push(tab);
                    //     }
                    // }

                });
            },
            searchUserGroup: function () {
                var url = "sysUser/list";
                $http({
                    method: 'POST',
                    url: $('#path').val() + "/tbi/" + url,
                    data: "data=" + JSON.stringify($scope.userSiGroup),  // pass in data as strings
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                }).success(function (data) {
                    for (var p in data) {
                        if (data[p]) {
                            var d = data[p];
                            var tab = angular.copy($scope.tab);
                            tab.office_id = d.office_id;
                            tab.login_name  = d.login_name;
                            tab.name  = d.name;
                            tab.si_id  = d.si_id;
                            tab.group_name  = d.group_name;
                            tab.id = d.id;
                            $scope.tabs.push(tab);
                        }
                    }

                });

            },
            addUserGroup: function () {
                var c = angular.copy($scope.selects);
                $scope.groups.push(c);
            },
            removeUserGroup: function () {
                //$scope.groups[0].selectedValue = "2";
                $scope.groups.splice($scope.userSiGroup.groups.length - 1, 1);
            },
            editUserGroup: function (index) {
                var url = "sysUser/selectSysUser?id=" + index;
                $http({
                    method: 'GET',
                    url: $('#path').val() + "/tbi/" + url,
                    data: "",  // pass in data as strings
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                }).success(function (data) {
                    $scope.userSiGroup.userId = data.id;
                    $scope.userSiGroup.basSiUserId = data.basSiUserId;
                    $scope.userSiGroup.siId = data.siId;
                    $scope.groups = [];
                    if(data.groups.length > 0){
                        for (var p in data.groups) {
                            if (data.groups[p]) {
                                var c = angular.copy($scope.selects);
                                c.selectedValue = data.groups[p].groupId; //不知道为什么不管用
                                $scope.groups.push(c);

                            }
                        }
                        setTimeout(function () {
                            var list = $("[id='itemSelect']");
                            list.each(function(index,element){
                                // this.val();
                                this.options[data.groups[index].groupId -  1].selected = true; //保持选中状态
                            });
                        }, 200)
                    }else{
                        var c = angular.copy($scope.selects);
                        $scope.groups.push(c);
                    }

                    //$scope.$apply();
                });


            },
            submitSiGroup: function () {

                $scope.userSiGroup.groups = [];
                for (var p in $scope.groups) {
                    var ind = $scope.groups[p].selectedValue;
                    $scope.userSiGroup.groups.push(ind);
                }
                // alert($.param($scope.userSiGroup));

                var url = "basesiuser/addbasesiuser";
                $http({
                    method: 'POST',
                    url: $('#path').val() + "/tbi/" + url,
                    data: "data=" + JSON.stringify($scope.userSiGroup),  // pass in data as strings
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                }).success(function (data) {
                    if (data.success) {
                        $scope.message = data.message;
                    } else {

                    }
                });
            }
        }

        init();
    });
    angular.bootstrap(document, ['basSiUserFromSysUser']);


});
