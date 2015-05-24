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
				<div class="searchFrom clearfix" style="margin-left: -15px;">
					<div class="navbar-form navbar-left">
						<div class="input-group">
							<input type="text" class="form-control" name="orderNo"
								placeholder="订单号" id="orderNo">

							<div class="input-group-btn">
								<button type="button" id="select_val"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown"
									aria-expanded="false">
									<div id="selectValue" style="display: inline;">产品</div>
									<span class="caret"></span>
								</button>
								<input type="hidden" id="productInput">
								<ul class="dropdown-menu dropdown-menu-right" role="menu">
									<li><a href="javascript:void(0);"
										onclick="changeSelect(null,null)">产品</a></li>
									<s:iterator value="resultList">
										<li><a href="javascript:void(0);"
											onclick="changeSelect('<s:property value="id"/>','<s:property value="name"/>')"><s:property
													value="name" /></a></li>
									</s:iterator>
								</ul>
							</div>
						</div>
						<input class="btn btn-warning" type="submit" onclick="search()"
							value="搜索">
					</div>
				</div>
			</div>
			<div class="panel-body">

				<iframe id="reportFrame" width="900" height="400"
					src="/WebReport/ReportServer?reportlet=Order.cpt"></iframe>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script>
		function changeSelect(key, value) {
			if (key == null && value == null) {
				$("#selectValue").text("产品");
				$("#productInput").val("");
			} else {
				$("#selectValue").text(value);
				$("#productInput").val(key);

			}
		}

		function search() {

			var orderNo = $("#orderNo").val();
			var productId = $("#productInput").val();
			alert("/WebReport/ReportServer?reportlet=Order.cpt"
					+ (orderNo == null ? "" : "&orderNo=" + orderNo)
					+ (productId == null ? "" : "&productId=" + productId));
			$("#reportFrame").removeAttr("src");
			$("#reportFrame").attr(
					"src",
					"/WebReport/ReportServer?reportlet=Order.cpt"
							+ (orderNo == null ? "" : "&orderNo=" + orderNo)
							+ (productId == null ? "" : "&productId="
									+ productId));
		}
	</script>
</body>
</html>