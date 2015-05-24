<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!-- Modal -->
<div class="modal fade" id="editUserUI" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="myModalLabel">
					<b>用户页面</b>
				</h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="editUser" method="post"
					action="user_edit">
					
					<input type="hidden" name="id" id="id">
					<div class="form-group">
						<label for="userid" class="col-sm-2 col-sm-offset-1 control-label">帐号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="userid"
								placeholder="Userid" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 col-sm-offset-1 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name"
								placeholder="Name" name="name">
						</div>
					</div>
					<div class="form-group">
						<label for="mobile" class="col-sm-2 col-sm-offset-1 control-label">手机</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="mobile"
								placeholder="Mobile" name="mobile">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 col-sm-offset-1 control-label">邮箱</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="email"
								placeholder="Email" name="email">
						</div>
					</div>
					<div class="form-group">
						<label for="weixinid" class="col-sm-2 col-sm-offset-1 control-label">微信号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="weixinid"
								placeholder="Weixinid" name="weixinid">
						</div>
					</div>
					<div class="form-group">
						<label for="Role" class="col-sm-2 col-sm-offset-1 control-label">权限</label>
						<div class="col-sm-6">
							<select id="roles" name="roles.id" class="form-control">
								<option value="">----请选择----</option>
								<option value="2">管理员</option>
								<option value="3">客户</option>
							</select>
						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<a href="javascript:editUser();" class="btn btn-primary">确定</a>
			</div>
		</div>
	</div>
</div>
<script>
	function checkAll(a) {
		var failsCount = 0;
		if ('all' == a || 'name' == a) {
			if ($('#name').val() == '') {
				tishi($('#name'), '姓名不能为空');
				failsCount += 1;
			} else {
				tishi($('#name'));
			}
		}
		return failsCount == 0 ? true : false;
	}
	function editUser() {
		
		if (checkAll('all')) {
			
			$("#editUser").submit();
		}
	}
</script>