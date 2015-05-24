function tishi(id, content) {
	remove(id);
	add(id, content);

};
function add(id, content) {
	if (content == null) {// 正确
		id.parent().parent().addClass('has-success has-feedback');
		id
				.parent()
				.append(
						'<span class="glyphicon glyphicon-ok form-control-feedback" id="passwordyes" aria-hidden="true"></span>');
	} else {// 错误
		id.parent().parent().addClass('has-error has-feedback');
		id
				.parent()
				.append(
						'<span class="glyphicon glyphicon-remove form-control-feedback" id="passwordyes" aria-hidden="true"></span>');
		// id.append('<label class="control-label">'+content+'</label>');
		id
				.parent()
				.parent()
				.append(
						'<div class="alert alert-danger col-sm-3" style="margin: 0;height: 34px;padding: 6px 9px;width: 140px;" role="alert">'
								+ content + '</div>');
	}
};
function remove(id) {
	id.parent().parent().removeClass('has-feedback');
	id.parent().parent().removeClass('has-success');
	id.parent().parent().removeClass('has-error');
	id.parent().children('span').remove();
	id.parent().parent().children('div.alert').remove();
};

function trim(str) { // 删除左右两端的空格
	if (str != null &&str!="") {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	} else {
		return str;
	}
}
function ltrim(str) { // 删除左边的空格
	if (str != null &&str!="") {
		return str.replace(/(^\s*)/g, "");
	} else {
		return str;
	}
}
function rtrim(str) { // 删除右边的空格
	if (str != null &&str!="") {
		return str.replace(/(\s*$)/g, "");
	} else {
		return str;
	}
}