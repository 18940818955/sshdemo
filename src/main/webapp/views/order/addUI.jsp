<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/header.jspf"%>
<link
	href="${pageContext.request.contextPath}/plugins/datetimePicker/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/common/top.jspf"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">增加订单页面</div>
			<div class="panel-body">
				<form action="order_add" method="post" id="addOrder">
					<input type="hidden" name="orderId"
						value="<s:property value='#request.orderId'/>" />
					<table
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>产品</th>
								<th>单价</th>
								<th>数量</th>
								<th>总价</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList">
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
									<td><input type="text" name="amount" maxlength="4"
										onInput="amountInput(this)"
										value="<s:property value="amount" />" /></td>
									<td><span> <s:property value="price*amount" /></span></td>
								</tr>
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
				<button class="btn btn-info pull-right" onclick="addOrder(this)"  data-loading-text="请稍等" autocomplete="off">下单</button>


			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jspf"%>
	<script
		src="${pageContext.request.contextPath}/plugins/datetimePicker/bootstrap-datetimepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datetimePicker/bootstrap-datetimepicker.zh-CN.js"></script>

	<script type="text/javascript">
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		function amountInput(o) {
			$(o).parent().next().children().eq(0).text(
					$(o).parent().prev().children().eq(0).val() * $(o).val());
		}
		function addOrder(o) {
			var check = validateAll();
			if (check) {
				$(o).button('loading');
				$("#addOrder").submit();
			}
		}
		function validateAll() {
			var flag = 0;
			var flag1 = 0;
			var num=0;
			$("input[name='amount']").each(function(index, Obj) {
				if ($(Obj).val() == "" || $(Obj).val() == null||$(Obj).val()==0) {
					flag += 1;
				} 
				if ($(Obj).val()!=""&&$(Obj).val()!=undefined &&$(Obj).val()!=null&&$(Obj).val().match(/^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/) == null) {
					flag1 += 1;
					$(Obj).css('border-color','red');
				}else{
					$(Obj).removeAttr("style");
				}
					num=index;
			});
			if (flag == num+1) {
				alert("必须输入一个产品的数量");
				return false;
			}
			if(flag1!=0){
				alert("产品数量不能为非数字");
				return false;
			}
			return true;

		};
	</script>
</body>
</html>