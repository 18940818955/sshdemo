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
				订单列表
				<sec:authorize access="hasRole('ROLE_CUSTOMER')">
					<div class="pull-right" style="margin-top: -7px;">
						<a href="order_addUI" class="btn btn-success">订货</a>
					</div>
				</sec:authorize>
			</div>
			<div class="panel-body ">
					<div class="searchFrom clearfix" style="margin-left: -15px;">
						<form class="navbar-form navbar-left" role="search"
							action="order_list" method="post" id="search_form">
							<div class="input-group">
								<input type="text" class="form-control" name="id"
									placeholder="订单号">
									
								<div class="input-group-btn">
									<button type="button" id="select_val"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown"
										aria-expanded="false">
										<div id="selectValue" style="display: inline;">
											<s:if test='#request.productName!=null'>
												<s:property value="#request.productName"/>
											</s:if>
											<s:else>
												产品
											</s:else>
										</div>
										<span class="caret"></span>
									</button>
									<input type="hidden" name="str" id="search_select" value="<s:property value='#request.productName'/>"/>
									<ul class="dropdown-menu dropdown-menu-right" role="menu">
										<li><a href="javascript:void(0);"
											onclick="changeSelect(null,'产品')">
												产品
											</a></li>
										<s:iterator value="resultList">
											<li><a href="javascript:void(0);"
												onclick="changeSelect('<s:property value="productName"/>','<s:property value="productName"/>')"><s:property
														value="productName" /></a></li>
										</s:iterator>
									</ul>
								</div>
								<div class="input-group-btn">
									<button type="button" id="status_val"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown"
										aria-expanded="false">
										<div id="statusValue" style="display: inline;">
											<s:if test='#request.statusid!=null'>
												<s:property value="#request.statusName"/>
											</s:if>
											<s:else>
												订单状态
											</s:else>
											
										
										</div>
										<span class="caret"></span>
									</button>
									<input type="hidden" name="statusid" id="status_select" value="<s:property value='#request.statusId'/>"/>
									<ul class="dropdown-menu dropdown-menu-right" role="menu">
										<li><a href="javascript:void(0);"
											onclick="changeStatus(null,'订单状态')">订单状态</a></li>
										<s:iterator value="statusList">
											<li><a href="javascript:void(0);"
												onclick="changeStatus('<s:property value="id"/>','<s:property value="name"/>')"><s:property
														value="name" /></a></li>
										</s:iterator>
									</ul>
								</div>
							</div>
							<input class="btn btn-warning" type="submit" value="搜索">
						</form>
					</div>
				<s:if test='recordList.size()!=0'>

					<table
						class="table table-hover table-bordered table-striped table-condensed ">
						<thead>
							<tr class="">
								<th class="" style="width: 6%;">订单号</th>
								<th class="">产品</th>
								<th class="" style="width: 8%;">下单日期</th>
								<th class="" style="width: 9%;">期待到货时间</th>
								<th class="" style="width: 7%;">订单金额</th>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th class="" style="width: 7%;">下单客户</th>
								</sec:authorize>
								<th class="" style="width: 7%;">订单状态</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="recordList">
								<tr class="">
									<td class="">
											<a href="order_editUI?str=<s:property value="id" />">
												<s:property value="id" />
											</a>
									</td>
									<td class=""><s:property value="productName" /></td>
									<td class=""><s:date format="yyyy-MM-dd" name="createTime" /></td>
									<td class=""><s:date format="yyyy-MM-dd"
											name="deliverTime" /></td>
									<td class=""><s:property value="totalPrice" /></td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<td class=""><s:property value="createUser.name" /></td>
									</sec:authorize>
									<td class=""><s:property value="status.name" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
				<s:else>没有数据</s:else>
			</div>
			<s:if test="%{(recordCount>=10)}">
				<%@ include file="/common/pagination.jspf"%></s:if>
			<form action="order_list" method="post" id="page_form">
				<input type='hidden' name='pageNum' value='${pageNum}'> <input
					type='hidden' name='pageSize' value='${pageSize}'> <input
					type='hidden' name='pageJumpType' value='5'>
			</form>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script>
	function changeStatusOrder(param,id){
		window.location.href="order_changeStatus?status.id="+param+"&id="+id;
	}
		function changeStatus(key, value) {
			$("#status_select").val(key);
			$("#statusValue").text(value);
		}
		function changeSelect(key, value) {
			$("#search_select").val(key);
			$("#selectValue").text(value);
		}
		function cancelOrder(param, o) {
			if (confirm('确定取消订单吗？')) {
				$(o).button('loading');
				window.location.href = "order_delete?str=" + param;
			}
		}
		function editOrderUIAndDeliver(str) {
			var roleName = '<s:property value="#session.SPRING_SECURITY_CONTEXT.authentication.authorities"/>';
			console.log(roleName.indexOf('ROLE_ADMIN'));
			if (roleName.indexOf('ROLE_ADMIN') != -1) {
				$.ajax({
					type : "POST",
					url : 'order_editAjax',
					data : {
						str : str
					},
					success : function(msg) {
						if (msg == "OK") {

							window.location.href = "order_editUI?str=" + str;
						} else {
							alert("已经发货，该订单不允许修改。");
						}
					}
				});
			}

		}
	</script>
</body>
</html>