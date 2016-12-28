var ipower = new Object();

$(function() {
	ipower.showPowerByMenu();

	ipower.openAddPowerModel();

	ipower.openUpdataPowerModel();
});

//展示权限列表的方法
ipower.showPowerByMenu = function() {
	$(document).on('click', '[data-proj-click="menu"]', function() {
		// console.log("abc");

		var menuDatas = $(this).next('span').children('span');
		var menuId = menuDatas.data('para-id');
		var menuName = menuDatas.data('para-name');

		//设置栏目id到对应的数据存储位置
		ipower.setMenuIdForAdd(menuId, menuName);

		console.log(menuId);

		$.ajax({
			url: 'power/list',
			type: 'GET',
			data: {
				menuId: menuId
			},
			success: function(data) {
				cb(data);
			},
			err: function(jqXHR, textStatus, errorThrown) {
				console.log('error ' + textStatus + " " + errorThrown);
			}
		});

		function cb(data) {
			if (iutil.isSuccess(data)) {
				$("#power-cont").html(data.data);
				console.log($("#power_data_menu_id").data("para-id"));
			}
			// $("#power-cont").
			// console.log(data);
			// $('#addMenuModal').modal('toggle');
		}
	})
}

//设置栏目id到对应的数据存储位置
ipower.setMenuIdForAdd = function(menuId, menuName) {
	if (menuId == undefined || menuId.length < 1) {

	} else {
		$("#power_data_menu_id").data("para-id", menuId);
		$("#power_data_menu_id").data("para-name", menuName);
	}
}

// .menu-modal-input.page-form
// 	label.input-tips(for="power_id") 栏目ID
// 	input#power_menu_id.form-control(type="text" name="menuId" readonly)
// 	label.error-msg-label(for="menu_id")
// .menu-modal-input.page-form
// 	label.input-tips(for="power_will_todo") 执行的操作
// 	input#power_will_todo.form-control(type="text" name="willTodo" readonly)
// 	label.error-msg-label(for="power_will_todo")
// .menu-modal-input.page-form
// 	label.input-tips(for="power_sort") 权限顺序
// 	input#power_sort.form-control(type="text" name="sort" readonly)
// 	label.error-msg-label(for="power_sort")
// .menu-modal-input.page-form
// 	label.input-tips(for="menu_name") 所属栏目
// 	input#power_menu_name.form-control(type="text" name="menuName" readonly)
// 	label.error-msg-label(for="menu_name")
// .menu-modal-input.page-form
// 	label.input-tips(for="power_name") 权限名称
// 	input#power_name.form-control(type="text" name="name")
// 	label.error-msg-label(for="power_name")
// .menu-modal-input.page-form
// 	label.input-tips(for="power_icon") 权限图标
// 	input#power_icon.form-control(type="text" name="icon")
// 	label.error-msg-label(for="power_icon")
// .menu-modal-input.page-form
// 	label.input-tips(for="power_permission") 权限标识
// 	input#power_permission.form-control(type="text" name="permission")
// 	label.error-msg-label(for="power_permission")
// .menu-modal-input.page-form.input-textarea
// 	label.input-tips(for="power_description") 权限简介
// 	textarea#power_description.form-control(type="text" name="description")
// 	label.error-msg-label(for="power_description")
// .menu-modal-input.page-form.input-radio
// 	label.input-tips 是否显示
// 	label(for="show")
// 		input(name="isShow" type="radio" value="1")
// 		| 是
// 	label(for="hide")
// 		input(name="isShow" type="radio" value="2")
// 		| 否
// 	label.error-msg-label
// .button-box
// 	button.button.button-primary.modal-button 提交
// 	button.button.modal-button(data-dismiss="modal") 关闭
//打开权限添加的模态框
ipower.openAddPowerModel = function() {
	ipower.clearPowerModal();

	$(document).on('click', '#power_tools_add', function() {
		var menuId = $("#power_data_menu_id").data("para-id");
		var menuName = $("#power_data_menu_id").data("para-name");
		var sort = $('#power-cont .au-power-box').size() + 1;

		if (menuId != undefined && menuId.length > 1) {
			$("#power_menu_id").val(menuId);
			$("#power_will_todo").val(1);
			$("#power_sort").val(sort);
			$("#power_menu_name").val(menuName);
			$('#addPowerModal').modal('toggle');
		} else {
			imessenger.error("未选定栏目,请先选定栏目");
		}
	})
}

ipower.openUpdataPowerModel = function() {
	ipower.clearPowerModal();


}

/**
 * 用于模态框的初始化
 */
ipower.clearPowerModal = function() {
	$('#addPowerModal .page-form').removeClass('has-error'); //添加模态框错误样式（红色边框）清除
	$('#addPowerModal .form-control').val(''); //添加模态框的输入框内容清空
	$('#addPowerModal .error-msg-label').html(''); //添加模态框错误提示内容清空
	$('#addPowerModal .input-radio :radio').removeAttr('checked');
	$('#addPowerModal .input-radio input:eq(0)').prop('checked', true); //添加模态框初始化显示为是
}