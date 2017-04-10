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
<body  ng-controller="[[defaultCtr]]" ng-cloak>
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
                    <form class="form-inline" method="POST" action="<%=path%>[[searchUrl]]" id="findForm" name="findForm" >
                        <table class="table table-striped" >
[[searchBody]]
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
[[listTitle]]
                            <th width="5%" >操作</th>
                        </tr>
                    </thead>
                    <tbody >
                        <tr ng-repeat="item in dataList" >
[[listBody]]
                            <td>
                                <a href="#editModal"  ng-click="events.update(item.id)" role="button" class="btn  btn-mini btn-info" data-toggle="modal" title="编辑内容" ><i class="icon-edit"></i>修改</a>
                                <button class="btn btn-mini btn-danger" ng-click="events.delete(item.id)" ><i class="icon-remove"></i>删除</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="tfoot" >
                    <tjglTag:Page  url="[[searchUrl]]"/>
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
[[editBody]]
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
    require([ "[[defaultJS]]"]);
</script>
</html>