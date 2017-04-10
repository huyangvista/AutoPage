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
                            [[searchBody]]
<%--                        <tr >
                                <td >
                                    SI工作号:
                                </td>
                                <td />
                                <td >
                                    用户编号:
                                </td>
                                <td >
                                    <input type="text" name="userId"    id="userIdFind" value="${pageTool.model.userId}" >
                                </td>
                                <td >
                                    创建时间:

                                </td>
                                <td >
                                    <input type="text" data-type="date" name="createDateFrom"   id="createDateFrom" value="${pageTool.filterMap.createDateFrom}">&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text" data-type="date" name="createDateTo"   id="createDateTo" value="${pageTool.filterMap.createDateTo}">
                                </td>
                            </tr>
                            <tr >
                                <td >
                                    创建人:
                                </td>
                                <td >
                                    <input type="text" name="createBy"    id="createByFind" value="${pageTool.model.createBy}" >
                                </td>
                                <td >
                                    修改时间:
                                </td>
                                <td >
                                    <input type="text" data-type="date" name="updateDateFrom"   id="updateDateFrom" value="${pageTool.filterMap.updateDateFrom}">
                                    &nbsp;&nbsp;至&nbsp;&nbsp;
                                    <input type="text" data-type="date" name="updateDateTo"   id="updateDateTo" value="${pageTool.filterMap.updateDateTo}">
                                </td>
                                <td >
                                    修改人:
                                </td>
                                <td >
                                    <input type="text" name="updateBy"    id="updateByFind" value="${pageTool.model.updateBy}" >
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
                            </tr>--%>
                        </table>
                    </form>
                </div>
                <!--列表-->
                <table class="table table-striped table-bordered table-hover" >
                    <thead >
                    <tr >
                        [[listTitle]]
                       <%-- <th>SI工作号</th>
                        <th>用户编号</th>
                        <th>创建时间</th>
                        <th>创建人</th>
                        <th>修改时间</th>
                        <th>修改人</th>
                        <th width="5%" >操作</th>--%>
                    </tr>
                    </thead>
                    <tbody >
                    <tr ng-repeat="item in dataList" >
                        [[listBody]]
                        <%--<td>{{item.siId}}</td>
                        <td>{{item.userId}}</td>
                        <td>{{item.createDate}}</td>
                        <td>{{item.createBy}}</td>
                        <td>{{item.updateDate}}</td>
                        <td>{{item.updateBy}}</td>
                        <td>
                            <a href="#editModal"  ng-click="events.update(item.id)" role="button" class="btn  btn-mini btn-info" data-toggle="modal" title="编辑内容" ><i class="icon-edit"></i>修改</a>
                            <button class="btn btn-mini btn-danger" ng-click="events.delete(item.id)" ><i class="icon-remove"></i>删除</button>

                            &lt;%&ndash;  <a href="#editModal" onclick="editData(item.siId,'operatingWarning/selectBasSiUser','operatingWarning/editBasSiUser')" role="button" class="btn  btn-mini btn-info" data-toggle="modal" title="编辑内容" ><i class="icon-edit"></i>修改</a>
                              <button class="btn btn-mini btn-danger" onclick="if(window.confirm('确认删除该记录?')==true)location.href='<%=path%>/tbi/operatingWarning/deleteBasSiUser?id=item.siId'"><i class="icon-remove"></i>删除</button>
        &ndash;%&gt;
                        </td>--%>
                    </tr>

                    </tbody>
                </table>
                <div class="tfoot" >
                    <tjglTag:Page  url="/tbi/operatingWarning/basSiUser"/>
                </div>
                <!--表单-->
                <div id="editModal" class="modal  fade" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true" >
                    <div class="modal-dialog"> </div>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">管理记录</h3>
                    </div>
                    <form action="editBasSiUser" id="modelForm" method="post" class="form-inline" >
                        <%--<input type="hidden"  name="id"  id="editMember.id" class="form-control" >--%>
                        <div class="modal-body" >
                            [[editBody]]

<%--                            <div class="row" >
                                <div class="col-md-4" >
                                    <label class="control-label"   >SI工作号</label>
                                    <input type="text" maxlength="64" name="userId"  id="editMember.userId"  class="form-control-order form-control" placeholder="用户编号" required="required" >
                                </div>
                                <div class="col-md-4" >
                                    <label class="control-label"   >用户编号</label>
                                    <input type="text" maxlength="64" name="siId"  id="editMember.siId"  class="form-control-order form-control" placeholder="用户编号" required="required" >
                                </div>
                                <div class="col-md-4" >
                                    <label class="control-label"   >创建时间</label>
                                    <input type="text" data-type="date" name="createDate"  id="editMember.createDate"  class="form-control-order form-control" placeholder="创建时间"  >
                                </div>
                            </div>
                            <div class="row" >
                                <div class="col-md-4" >
                                    <label class="control-label"   >创建人</label>
                                    <input type="text" maxlength="50" name="createBy"  id="editMember.createBy"  class="form-control-order form-control" placeholder="创建人"  >
                                </div>
                                <div class="col-md-4" >
                                    <label class="control-label"   >修改时间</label>
                                    <input type="text" data-type="date" name="updateDate"  id="editMember.updateDate"  class="form-control-order form-control" placeholder="修改时间"  >
                                </div>
                                <div class="col-md-4" >
                                    <label class="control-label"   >修改人</label>
                                    <input type="text" maxlength="50" name="updateBy"  id="editMember.updateBy"  class="form-control-order form-control" placeholder="修改人"  >
                                </div>
                            </div>--%>
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