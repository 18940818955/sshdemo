<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/header.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/common/top.jspf" %>

		<div class="container">
<div class="jumbotron">
			<h1>抱歉</h1>
			<p>session已经过期，请返回主页</p>
			<p>
				<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/index" role="button">返回主页</a>
			</p>
	</div>
		</div>

<%@ include file="/common/footer.jspf"%>
</body>
</html>