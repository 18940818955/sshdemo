<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<div class="panel-heading">修改订单页面 ,订单号:<s:property value="#request.orderId"/></div>
			<div class="panel-body">
				<form action="order_add" method="post" id="addOrder">
					<input type="hidden" name="orderId"
						value="<s:property value='#request.orderId'/>" />
					<table
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th width="10%">产品</th>
								<th width="10%">单价</th>
								<th width="10%">订货数量</th>
								<th width="10%">总价</th>
								<th width="15%">实际发货数量</th>
								<th width="15%">实际收货数量</th>
								<th width="30%">其他说明</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList">
								<s:if test="amount>0">
									<tr>
										<s:if test="addFlg!=1">
											<input type="hidden" name="id"
												value="<s:property value="id" />" />
										</s:if>
										<s:else>
											<input type="hidden" name="id" />
										</s:else>
										<td><input type="hidden" name="productName"
											value="<s:property value="productName" />" /> <s:property
												value="productName" /></td>
										<td><input type="hidden" readonly name="price"
											value="<s:property value="price" />" /> <s:property
												value="price" /></td>
										<td>
											<sec:authorize access="hasRole('ROLE_CUSTOMER')">
												<s:if
													test='#request.status.id==3'>
													<input type="text" name="amount" maxlength="4"
														onInput="amountInput(this)"
														value="<s:property value="amount" />" />
												</s:if> <s:else>
													<input type="hidden" name="amount" maxlength="4"
														onInput="amountInput(this)"
														value="<s:property value="amount" />" />
													<s:property value="amount" />
												</s:else>
											</sec:authorize>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<input type="hidden" name="amount" maxlength="4"
														onInput="amountInput(this)"
														value="<s:property value="amount" />" />
													<s:property value="amount" />
											</sec:authorize>
											</td>
										<td><s:property value="price*amount" /></td>
										<td>
										
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<s:if test='#request.status.id==1'>
													<input type="text" name="realAmount"
														<s:if test='realAmount==0.0 || realAmount==""'>
															value="<s:property value="amount" />"
														</s:if>
													<s:else>value="<s:property value="realAmount" />"</s:else>>
												</s:if>
												<s:else>
													<s:if test='realAmount==0.0 || realAmount==""'>
														<s:property value="amount" />
														<input type="hidden" name="realAmount"
															value="<s:property value="amount" />">
													</s:if>
													<s:else>
														<s:property value="realAmount" />
														<input type="hidden" name="realAmount"
															value="<s:property value="realAmount" />">
													</s:else>
												</s:else>
											</sec:authorize> 
											
											<sec:authorize access="hasRole('ROLE_CUSTOMER')">

												<s:if test='realAmount==0.0 || realAmount==""'>
													<s:property value="amount" />
													<input type="hidden" name="realAmount"
														value="<s:property value="amount" />">
												</s:if>
												<s:else>
													<s:property value="realAmount" />
													<input type="hidden" name="realAmount"
														value="<s:property value="realAmount" />">
												</s:else>

											</sec:authorize></td>
										<td><sec:authorize access="hasRole('ROLE_CUSTOMER')">
												<s:if test='#request.status.id==2'>
													<input type="text" name="receiveAmount"
														value="<s:property value='receiveAmount' />">
												</s:if>
												<s:else>
													<input type="hidden" name="receiveAmount"
														value="<s:property value="receiveAmount" />">
													<s:property value='receiveAmount' />
												</s:else>
											</sec:authorize> 
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<input type="hidden" name="receiveAmount"
														value="<s:property value="receiveAmount" />">
												<s:property value='receiveAmount' />
											</sec:authorize></td>
										<td><sec:authorize access="hasRole('ROLE_CUSTOMER')">
												<s:if test='#request.status.id==2'>
													<input type="text" name="reason"
														value="<s:property value='reason' />">
												</s:if>
												<s:else>
													<input type="hidden" name="reason"
														value="<s:property value="reason" />">
													<s:property value='reason' />
												</s:else>
											</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
												<input type="hidden" name="reason"
														value="<s:property value="reason" />">
												<s:property value='reason' />
											</sec:authorize></td>
									</tr>
								</s:if>
							</s:iterator>
						</tbody>
					</table>
					<div class="bootstrapDate pull-right "
						style="height: 37px; margin-right: 10px;">
						<div class="form-group">
							<div class="input-group date form_date col-md-5  pull-right"
								data-date="" data-date-format="yyyy-mm-dd"
								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd"
								style="width: 192px;">
								<input class="form-control" size="16" type="text"
									name="deliverTime" readonly
									value="<s:date name='#request.deliverTime' format='yyyy-MM-dd'/>">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<label for="dtp_input2" class="col-md-3 control-label pull-right"
								style="padding-top: 7px; width: 123px;">期待到货日期</label> <input
								type="hidden" id="dtp_input2" value="" /><br />
						</div>
					</div>
				</form>
			</div>
			<div class="panel-footer clearfix">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<s:if test="#request.status.id==1">
						<button class="btn btn-info pull-right" onclick="acceptOrder(this)" data-loading-text="请稍等" autocomplete="off">接受订单</button>
						<button class="btn btn-danger pull-right"
							onclick="regectOrder(this)" data-loading-text="请稍等" autocomplete="off">驳回</button>
					</s:if>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_CUSTOMER')">
					<s:if test="#request.status.id==2">
						<button class="btn btn-info pull-right" 
							onclick="confirmOrder(this);" data-loading-text="请稍等" autocomplete="off">确认收货</button>
					</s:if>
					<s:if test="#request.status.id==1||#request.status.id==3">
						<button class="btn btn-danger pull-right"
							onclick="cancelOrder(this)" data-loading-text="请稍等" autocomplete="off">取消订单</button>
					</s:if>
					<s:if test="#request.status.id==3">
						<button class="btn btn-info pull-right" onclick="addOrder(this)" data-loading-text="请稍等" autocomplete="off">重新下单</button>
					</s:if>
				</sec:authorize>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script type="text/javascript">
	
		var orderId = $("input[name='orderId']").val();
		function addOrder(o) {
			$(o).button('loading');
			var check = addValidateAll();
			if (check) {
				$("#addOrder").attr("action","order_reorder").submit();
			}
		}
		function amountInput(o) {
			$(o).parent().next().children().eq(0).text(
					$(o).parent().prev().children().eq(0).val() * $(o).val());
		}
		function cancelOrder(o) {
			$(o).button('loading');
			$.ajax({
				type : "POST",
				url : 'order_editAjax',
				data : {
					str : orderId
				},
				success : function(msg) {
					if (msg == "OK") {

						window.location.href = "order_cancel?str=" + orderId;
					} else {
						alert("已经发货，该订单不允许修改。");
					}
				}
			});
		}
		function confirmOrder(o) {//确认收货
			$(o).button('loading');
			if (confirm("确认收货？")) {
				//alert($("#addOrder").serialize());
				$("#addOrder").attr("action", "order_confirmOrder").submit();
				//		window.location.href = "order_confirmOrder?orderId=<s:property value='#request.orderId'/>&";
			}
		}
		function acceptOrder(o) {//接受订单
			$(o).button('loading');
			var check = validateAll();
			if (check) {
				$("#addOrder").attr("action", "order_accept").submit();
			}
		}
		function regectOrder(o) {//驳回订单
			$(o).button('loading');
			window.location.href = "order_regectOrder?id=<s:property value='#request.orderId'/>";
		}
		function addValidateAll() {
			var flag = 0;
			var flag1 = 0;
			var num = 0;
			$("input[name='amount']")
					.each(
							function(index, Obj) {
								if ($(Obj).val() == "" || $(Obj).val() == null
										|| $(Obj).val() == 0) {
									flag += 1;
								}
								if ($(Obj).val() != ""
										&& $(Obj).val() != undefined
										&& $(Obj).val() != null
										&& $(Obj)
												.val()
												.match(
														/^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/) == null) {
									flag1 += 1;
									$(Obj).css('border-color', 'red');
								} else {
									$(Obj).removeAttr("style");
								}
								num = index;
							});
			if (flag == num + 1) {
				alert("必须输入一个产品的数量");
				return false;
			}
			if (flag1 != 0) {
				alert("产品数量不能为非数字");
				return false;
			}
			return true;

		};
		function validateAll() {
			var flag = 0;
			var flag1 = 0;
			var num = 0;
			$("input[name='realAmount']")
					.each(
							function(index, Obj) {
								if ($(Obj).val() == "" || $(Obj).val() == null
										|| $(Obj).val() == 0) {
									flag += 1;
									return;
								}
								if ($(Obj).val() != ""
										&& $(Obj).val() != undefined
										&& $(Obj).val() != null
										&& $(Obj)
												.val()
												.match(
														/^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/) == null) {
									flag1 += 1;
									$(Obj).css('border-color', 'red');
								} else {
									$(Obj).removeAttr("style");
								}
								num = index;
							});
			if (flag == num + 1) {
				alert("必须输入一个产品的数量");
				return false;
			}
			if (flag1 != 0) {
				alert("产品数量不能为非数字");
				return false;
			}
			return true;

		};
	</script>
</body>

</html>