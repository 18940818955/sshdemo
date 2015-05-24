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
				产品列表
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="pull-right" style="margin-top: -7px;">
						<a href="javascript:editProductUI();" class="btn btn-success">增加产品</a>
						<%@include file="/views/product/editUI.jsp"%>
					</div>
				</sec:authorize>
			</div>
			<div class="panel-body ">
				<s:if test='resultList.size()!=0'>
					<div class="table-responsive">
						<form action="product_addModel" method="post" id="modelForm">
							<table
								class="table table-hover table-bordered table-striped table-condensed ">
								<thead>
									<tr class="">
										<th class="">选择</th>
										<th class="">产品名称</th>
										<th class="">单价</th>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<th class="">操作</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="resultList" status="status">
										<tr class="">
											<td class=""><input type="checkbox" name="ids" 
											<s:iterator value="users" var="userTemp">
												<s:if test='#session.SPRING_SECURITY_CONTEXT.authentication.principal.id==#userTemp.id'>
													checked="checked"
												</s:if>
											</s:iterator>
												value="<s:property value="id"/>" /></td>

											<td class=""><s:property value="productName" /></td>
											<td class=""><s:property value="price" /></td>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<td class=""><a
													href="javascript:editProductUI(<s:property value="id" />)"
													class="btn btn-sm btn-info" data-toggle="tooltip"
													data-placement="top" title="修改"><span
														class="glyphicon glyphicon-cog"></span></a> <a
													href="product_delete?param=<s:property value="id" />"
													onclick="return confirm('确定删除${obj.productName}吗？')"
													class="btn btn-sm btn-danger" data-toggle="tooltip"
													data-placement="top" title="删除"><span
														class="glyphicon glyphicon-remove"></span></a></td>
											</sec:authorize>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</form>
					</div>
				</s:if>
				<s:else>没有数据</s:else>
			</div>
			<div class="panel-footer clearfix">
				<div class="pull-right" id="saveModel">
					<button type="button" onclick="saveModel()" class="btn btn-primary"
						data-loading-text="请稍等" autocomplete="off" id="saveModel">确定</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script type="text/javascript">
		function saveModel() {
			$("#modelForm").submit();
		}
	</script>
</body>
</html>