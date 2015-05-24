<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
					产品列表
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="pull-right" style="margin-top: -7px;">
							<a href="javascript:editProductUI();" class="btn btn-success">增加产品</a>
							<%@include file="/views/product/editUI.jsp"%>
						</div>
					</sec:authorize>
				</div>
				<div class="panel-body ">
					<s:if test='recordList.size()!=0'>
						<div class="table-responsive">
							<table
								class="table table-hover table-bordered table-striped table-condensed ">
								<thead>
									<tr class="">
										<th class="">产品号</th>
										<th class="">产品名称</th>
										<th class="">单价</th>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<th class="">操作</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="recordList">
										<tr class="">
											<td class=""><s:property value="id" /></td>
											<td class=""><s:property value="productName" /></td>
											<td class=""><s:property value="price" /></td>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<td class=""><a
													href="javascript:editProductUI(<s:property value="id" />)"
													class="btn btn-sm btn-info" data-toggle="tooltip"
													data-placement="top" title="修改"><span
														class="glyphicon glyphicon-cog"></span></a> 
														<a href="product_delete?param=<s:property value="id" />"
															onclick="return confirm('确定删除${obj.productName}吗？')"
															class="btn btn-sm btn-danger" data-toggle="tooltip"
															data-placement="top" title="删除"><span
															class="glyphicon glyphicon-remove"></span></a>
												</td>
											</sec:authorize>
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
				<form action="product_list" method="post" id="page_form">
					<input type='hidden' name='pageNum' value='${pageNum}'> <input
						type='hidden' name='pageSize' value='${pageSize}'> <input
						type='hidden' name='pageJumpType' value='5'>
				</form>
			</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script>
		function editProductUI(param) {
			if (param != null) {
				$.ajax({
					type : "POST",
					url : 'product_editAjax?param=' + param,
					data : '',
					success : function(msg) {
						var obj = $.parseJSON(msg);
						$("#id").val(obj.id);
						$("#productName").attr("readonly", "readonly");
						$("#productName").val(obj.productName);
						$("#price").val(obj.price);
						$("#style").attr("readonly", "readonly");
						$("#style").val(obj.style);
						$("#unit").attr("readonly", "readonly");
						$("#unit").val(obj.unit);
						$('#editProductUI').modal('show');
					}
				});
			} else {
				$("#id").val("");
				$("#productName").val("");
				$("#price").val("");
				$("#style").val("");
				$("#unit").val("");
				$('#editProductUI').modal('show');
			}
		}
	</script>
</body>
</html>