<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/header.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<%-- <%@ include file="/common/top.jspf"%>
	<div class="container">
		<div class="jumbotron">
			<h1>您好</h1>
			<p></p>
			<p>
				<a class="btn btn-primary btn-lg" href="product_list" role="button">查看产品</a>
			</p>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%> --%>
	
	<jsp:forward page="product_list" />
</body>
</html>