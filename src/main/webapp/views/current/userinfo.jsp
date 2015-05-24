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
			<div class="panel-heading">
				个人信息
				<div class="pull-right" id="updateBtn" style="margin-top: -7px;">
					<a href="javascript:updateInfo();" class="btn btn-success">修改信息</a>
				</div>
			</div>
			<div class="panel-body ">
				<form class="form-horizontal" id="editUser" method="post"
					action="user_updateInfo">

					<input type="hidden" name="id" id="id"
						value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.id}">
					<div class="form-group">
						<label for="userid" class="col-sm-2 col-sm-offset-1 control-label">帐号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="userid"
								placeholder="Userid" name="userid"
								value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.userid}"
								readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 col-sm-offset-1 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name"
								placeholder="Name" name="name"
								value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.name}"
								readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="mobile" class="col-sm-2 col-sm-offset-1 control-label">手机</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="mobile"
								placeholder="Mobile" name="mobile"
								value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.mobile}"
								readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 col-sm-offset-1 control-label">邮箱</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="email"
								placeholder="Email" name="email"
								value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.email}"
								readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="weixinid"
							class="col-sm-2 col-sm-offset-1 control-label">微信号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="weixinid"
								placeholder="Weixinid" name="weixinid"
								value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.weixinid}"
								readonly>
						</div>
					</div>

				</form>
			</div>
			<div class="panel-footer clearfix">
				<div class="pull-right" id="changePassword" style="display: block;">
					<a href="changePwd" class="btn btn-warning">修改密码</a>
				</div>
				<div class="pull-right" id="footer" style="display: none;">
					<button type="button" onclick="cancel()" class="btn btn-default"
						data-dismiss="modal">取消</button>
					<a href="javascript:editUser();" class="btn btn-primary">确定</a>
				</div>
			</div>
		</div>
	</div>



	<%@ include file="/common/footer.jspf"%>
	<script>
		function updateInfo() {
			$("#name").removeAttr("readonly");
			$("#mobile").removeAttr("readonly");
			$("#email").removeAttr("readonly");
			$("#weixinid").removeAttr("readonly");
			$("#footer").css("display", "block");
			$("#changePassword").css("display", "none");
			$("#updateBtn").css("display", "none");
		}
		function cancel() {
			$("#name").attr("readonly", "readonly");
			$("#mobile").attr("readonly", "readonly");
			$("#email").attr("readonly", "readonly");
			$("#weixinid").attr("readonly", "readonly");
			$("#footer").css("display", "none");
			$("#changePassword").css("display", "block");
			$("#updateBtn").css("display", "block");
		}
		function editUser() {
			$("#editUser").submit();
		}
	</script>
</body>
</html>