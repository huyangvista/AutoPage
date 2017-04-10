<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>basInsurance</title>
    <%@include file="../common/css.jsp"%>
    <%@include file="/WEB-INF/views/erp/common/common.jsp"%>
    <%@include file="/WEB-INF/views/erp/common/config.jsp"%>
</head>
<body  ng-controller="basSiUserFromSysUserCtr" ng-cloak>
<input type="hidden" id="path" value="<%=path%>">
<div class="main-content" >
    <div class="page-content" >
        <div class="row-fluid" >
            <div class="span12" >
                <!--查询-->
                <div class="block" >
                    <div class="block-header" >
                        <div >
                            <i class="icon-edit"></i>BasSiUser
                        </div>
                    </div>
                    <form class="form-inline" method="POST" action="<%=path%>/tbi/basesiuser/basesiuser" id="findForm" name="findForm" >
                        <table class="table table-striped" >
                            <tr >
                                <td>
                                    id
                                </td>
                                <td>
                                    <input type="text" name="id" id="id" value="${pageTool.model.id}">
                                </td>

                                <td>
                                    si_id
                                </td>
                                <td>
                                    <input type="text" name="si_id" id="si_id" value="${pageTool.model.si_id}">
                                </td>

                                <td>
                                    user_id
                                </td>
                                <td>
                                    <input type="text" name="user_id" id="user_id" value="${pageTool.model.user_id}">
                                </td>
                            </tr>
                            <tr >
                                <td>
                                    create_date
                                </td>
                                <td>
                                    <input type="text" name="create_date" id="create_date" value="${pageTool.model.create_date}">
                                </td>

                                <td>
                                    create_by
                                </td>
                                <td>
                                    <input type="text" name="create_by" id="create_by" value="${pageTool.model.create_by}">
                                </td>

                                <td>
                                    update_date
                                </td>
                                <td>
                                    <input type="text" name="update_date" id="update_date" value="${pageTool.model.update_date}">
                                </td>
                            </tr>
                            <tr >
                                <td>
                                    update_by
                                </td>
                                <td>
                                    <input type="text" name="update_by" id="update_by" value="${pageTool.model.update_by}">
                                </td>
                            </tr>

                            <tr >
                                <td colspan="6" align="right" >
                                    <button  type="button" id="findBtn" class="btn btn-purple btn-small" ng-click="events.search()">
                                        <i class="icon-search icon-on-right bigger-110"></i>
                                        查找
                                    </button>
                                    <a  role="button" class="btn btn-purple btn-small" data-toggle="modal"  ng-click="events.search()" title="新增"><i class="icon-user"></i>新增</a>

                                    <a href="#editModal" role="button" class="btn btn-purple btn-small" data-toggle="modal" onclick="addModelClk('operatingWarning/addBasSiUser')" title="新增"><i class="icon-user"></i>新增</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <!--列表-->
                <table class="table table-striped table-bordered table-hover" >
                    <thead >
                        <tr >
                            <th>id</th>
                            <th>si_id</th>
                            <th>user_id</th>
                            <th>create_date</th>
                            <th>create_by</th>
                            <th>update_date</th>
                            <th>update_by</th>

                            <th width="5%" >操作</th>
                        </tr>
                    </thead>
                    <tbody >
                        <tr ng-repeat="item in dataList" >
                            <td>{{item.id}}</td>
                            <td>{{item.si_id}}</td>
                            <td>{{item.user_id}}</td>
                            <td>{{item.create_date}}</td>
                            <td>{{item.create_by}}</td>
                            <td>{{item.update_date}}</td>
                            <td>{{item.update_by}}</td>

                            <td>
                                <a href="#editModal"  ng-click="events.update(item.id)" role="button" class="btn  btn-mini btn-info" data-toggle="modal" title="编辑内容" ><i class="icon-edit"></i>修改</a>
                                <button class="btn btn-mini btn-danger" ng-click="events.delete(item.id)" ><i class="icon-remove"></i>删除</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="tfoot" >
                    <tjglTag:Page  url="/tbi/basesiuser/basesiuser"/>
                </div>
                <!--表单-->
                <div id="editModal" class="modal  fade" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true" >
                    <div class="modal-dialog"> </div>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">编辑</h3>
                    </div>
                    <form action="editBasSiUser" id="modelForm" method="post" class="form-inline" >
                        <%--<input type="hidden"  name="id"  id="editMember.id" class="form-control" >--%>
                        <div class="modal-body" >
<div class="row" >

                                <div class="col-md-4" >
                                    <label class="control-label"   >id</label>
                                    <input type="text"  name="id"  id="editMember.id"  class="form-control-order form-control" placeholder="id" required="required" >
                                </div>

                                <div class="col-md-4" >
                                    <label class="control-label"   >si_id</label>
                                    <input type="text"  name="si_id"  id="editMember.si_id"  class="form-control-order form-control" placeholder="si_id" required="required" >
                                </div>

                                <div class="col-md-4" >
                                    <label class="control-label"   >user_id</label>
                                    <input type="text"  name="user_id"  id="editMember.user_id"  class="form-control-order form-control" placeholder="user_id" required="required" >
                                </div>
</div>
<div class="row" >

                                <div class="col-md-4" >
                                    <label class="control-label"   >create_date</label>
                                    <input type="text"  name="create_date"  id="editMember.create_date"  class="form-control-order form-control" placeholder="create_date" required="required" >
                                </div>

                                <div class="col-md-4" >
                                    <label class="control-label"   >create_by</label>
                                    <input type="text"  name="create_by"  id="editMember.create_by"  class="form-control-order form-control" placeholder="create_by" required="required" >
                                </div>

                                <div class="col-md-4" >
                                    <label class="control-label"   >update_date</label>
                                    <input type="text"  name="update_date"  id="editMember.update_date"  class="form-control-order form-control" placeholder="update_date" required="required" >
                                </div>
</div>
<div class="row" >

                                <div class="col-md-4" >
                                    <label class="control-label"   >update_by</label>
                                    <input type="text"  name="update_by"  id="editMember.update_by"  class="form-control-order form-control" placeholder="update_by" required="required" >
                                </div>
<div class="row" >

                            <div class="modal-footer" >
                                <button type="submit" id="editConfirm" class="btn btn-small btn-primary" >确定</button>
                                <button type="button" id="editReturn" class="btn btn-small btn-primary" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    require([ "erp/bas/basSiUser"]);
</script>
</html>
