<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!-- Modal -->
<div class="modal fade" id="editProductUI" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="myModalLabel">
					<b>增加产品页面</b>
				</h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="editProduct" method="post"
					action="product_edit">
					<input type="hidden" name="id" id="id">
					<div class="form-group">
						<label for="productName" class="col-sm-2 col-sm-offset-1 control-label">产品名称</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="productName"
								placeholder="productName" name="productName">
						</div>
					</div>
					<div class="form-group">
						<label for="price" class="col-sm-2 col-sm-offset-1 control-label">单价</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="price"
								placeholder="Price" name="price">
						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<a href="javascript:editProduct();" class="btn btn-primary">确定</a>
			</div>
		</div>
	</div>
</div>
<script>
	function checkAll(a) {
		var failsCount = 0;
		if ('all' == a || 'price' == a) {
			if ($('#price').val() == '') {
				tishi($('#price'), '单价不能为空');
				failsCount += 1;
			} else if (trim($('#price').val()).match(/^([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)$/) == null) {
				tishi($('#price'), '单价不能为非数字');
				failsCount += 1;
			} else {
				tishi($('#price'));
			}
		}
		if ('all' == a || 'productName' == a) {
			if ($('#productName').val() == '') {
				tishi($('#productName'), '名称不能为空');
				failsCount += 1;
			} else {
				tishi($('#productName'));
			}
		}
		return failsCount == 0 ? true : false;
	}
	function editProduct() {
		
		if (checkAll('all')) {
			
			$("#editProduct").submit();
		}
	}
</script>