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
		<div class="panel panel-default">
			<div class="panel-heading">修改密码</div>
			<div class="panel-body ">
				<form class="form-horizontal" id="changePassword" method="post"
					action="user_changePassword">

					<input type="hidden" name="id" id="id"
						value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.id}">
					<input type="hidden" id="pwd"
						value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.password}">
					<div class="form-group">
						<label for="oldPassword"
							class="col-sm-2 col-sm-offset-1 control-label">原密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="oldPassword"
								placeholder="" name="oldPassword">
						</div>
					</div>
					<div class="form-group">
						<label for="newPassword"
							class="col-sm-2 col-sm-offset-1 control-label">新密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="newPassword"
								placeholder="" name="newPassword">
						</div>
					</div>
					<div class="form-group">
						<label for="password"
							class="col-sm-2 col-sm-offset-1 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password"
								placeholder="" name="password">
						</div>
					</div>
				</form>
			</div>
			<div class="panel-footer clearfix">
				<div class="pull-right" id="footer">
					<a href="<%=request.getContextPath()%>/user_info"
						class="btn btn-default" data-dismiss="modal">返回</a>
					<button type="button" onclick="changePwd();"
						class="btn btn-primary" data-loading-text="请稍等" autocomplete="off"
						id="changePwd">确定</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script type="text/javascript">
		function changePwd() {
			if (checkAll()) {
				$("#changePwd").button('loading');
				$("#changePassword").submit();
			}
		}
		function checkAll() {
			if ($('#oldPassword').val() == '') {
				tishi($('#oldPassword'), '原密码不能为空');
				return false;
			} else if ($('#oldPassword').val() != $('#pwd').val()) {
				tishi($('#oldPassword'), '密码错误');
				return false;
			} else {
				tishi($('#oldPassword'));
			}
			if ($('#newPassword').val() == '') {
				tishi($('#newPassword'), '新密码不能为空');
				return false;
			} else {
				tishi($('#newPassword'));
			}
			if ($('#password').val() == '') {
				tishi($('#password'), '确认密码不能为空');
				return false;
			} else if ($('#password').val() != $('#newPassword').val()) {
				tishi($('#password'), '两次密码不相同');
				return false;
			} else {
				tishi($('#password'));
			}
			return true;
		}
	</script>
</body>
</html>