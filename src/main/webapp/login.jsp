<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>
<%@ include file="/common/header.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

			<div class="col-xs-12 col-sm-offset-4 col-sm-4 ">
				<form class="form-signin" action="j_spring_security_check" method="post">
					<h2 class="form-signin-heading">用户登录</h2>
					<s:if test="%{#parameters.error != null}">
						<p>请确认您的密码</p>
					</s:if>
					<s:if test="%{#parameters.logout != null}">
						<p>请重新登录</p>
					</s:if>
					<div class="form-group">
						<label for="loginName" class="sr-only">用户名</label> <input
							type="text" id="loginName" class="form-control" name="j_username"
							placeholder="用户名" required autofocus>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="sr-only">Password</label> <input
							type="password" id="inputPassword" name="j_password"
							class="form-control" placeholder="密码" required>
					</div>
					<s:if test='#loginError!=null'>
						<span><s:property value="#loginError" /></span>
					</s:if>
					<div class="checkbox">
						<label> <input type="checkbox" value="remember-me">
							记住密码
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
				</form>
			</div>
	<%@ include file="/common/footer.jspf"%>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>