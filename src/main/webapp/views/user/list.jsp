<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/header.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/common/top.jspf"%>
	<div class="container">
		<div class="col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					用户列表
					<div class="pull-right" style="margin-top: -7px;">
						<a href="javascript:editUserUI();" class="btn btn-success btn-sm">新建用户</a>
						<%@include file="/views/user/editUI.jsp"%>
					</div>
				</div>
				<div class="panel-body ">
					<s:if test='recordList.size()!=0'>
						<div class="table-responsive">
							<table
								class="table table-hover table-bordered table-striped table-condensed ">
								<thead>
									<tr class="">
										<th class="">用户ID</th>
										<th class="">姓名</th>
										<th class="">帐号</th>
										<th class="">手机</th>
										<th class="">邮箱</th>
										<th class="">微信号</th>
										<th class="">权限</th>
										<th class="">操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="recordList" var="list">
										<tr class="">
											<td class=""><s:property value="id" /></td>
											<td class=""><s:property value="name" /></td>
											<td class=""><s:property value="userid" /></td>
											<td class=""><s:property value="mobile" /></td>
											<td class=""><s:property value="email" /></td>
											<td class=""><s:property value="weixinid" /></td>
											<td class=""><s:if test='#list.roles.size()==1'>注册用户 </s:if>
												<s:else>
													<s:iterator value="#list.roles" var="item">
														<s:if test='#item.description!="注册用户"'>
															<s:property value="#item.description" />
														</s:if>
													</s:iterator>
												</s:else></td>
											<td class="">
												<a href="javascript:editUserUI(<s:property value="id" />)"
													class="btn btn-sm btn-info" data-toggle="tooltip" data-placement="top" title="修改">
													<span class="glyphicon glyphicon-cog"></span>
												</a> 
												<a href="user_delete?param=<s:property value="id" />"
													onclick="return confirm('确定删除<s:property value="name"/>吗？')"
													class="btn btn-sm btn-danger" data-toggle="tooltip" data-placement="top" title="删除">
													<span class="glyphicon glyphicon-remove"></span>
												</a> 
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</s:if>
					<s:else>没有数据</s:else>
				</div>
<s:if test="%{(recordCount>=10)}">
					<%@ include file="/common/pagination.jspf"%></s:if>
				<form action="user_list" method="post" id="page_form">
					<input type='hidden' name='pageNum' value='${pageNum}'> <input
						type='hidden' name='pageSize' value='${pageSize}'> <input
						type='hidden' name='pageJumpType' value='5'>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script>
		function editUserUI(param) {
			if (param != null) {
				$
						.ajax({
							type : "POST",
							url : 'user_editAjax?param=' + param,
							data : '',
							success : function(msg) {
								var obj = $.parseJSON(msg);
								$("#id").val(obj.id);
								$("#userid").val(obj.userid);
								$("#name").val(obj.name);
								$("#mobile").val(obj.mobile);
								$("#email").val(obj.email);
								$("#weixinid").val(obj.weixinid);
								for ( var r in obj.roles) {
									if (obj.roles[r].id != 1
											&& obj.roles[r].id != null) {
										$("#roles").val(obj.roles[r].id);
									}
								}
								$('#editUserUI').modal('show');
							}
						});
			} else {
				$("#id").val("");
				$("#userid").val("");
				$("#name").val("");
				$("#mobile").val("");
				$("#email").val("");
				$("#weixinid").val("");
				$("#roles").val("");
				$('#editUserUI').modal('show');
			}
		}
	</script>
</body>
</html>