var role = new Object();
$(function() {
	//添加角色时
	$(document).on('click', ".add_role_modal", function() {
		// 获取当前权限列表的数量,加一后赋给顺序值
		var order = $(this).parents('.role_box').first().find('dd.menu_item').length + 1;
		iutil.cleanInput('role_todo', 'role_id', 'role_order', 'role_name', 'role_remark');
		iutil.setInput({
			id: "role_todo",
			val: 1
		}, {
			id: "role_order",
			val: order
		});
		$("#au_role").modal("toggle");
	});


	//修改权限时
	$(document).on('click', ".edit_role_modal", function() {
		// 清理之前的模态框中的数据
		iutil.cleanInput('role_todo', 'role_id', 'role_order', 'role_name', 'role_remark');

		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var role = iutil.getDatas(dataSpan, {
			dataName: 'para-_id',
			objName: '_id'
		}, {
			dataName: 'para-name',
			objName: 'name'
		}, {
			dataName: 'para-order',
			objName: 'order'
		}, {
			dataName: 'para-remark',
			objName: 'remark'
		});
		// console.log(role);

		iutil.setInput({
			id: "role_todo",
			val: 2
		}, {
			id: "role_order",
			val: role.order
		}, {
			id: 'role_id',
			val: role._id
		}, {
			id: 'role_name',
			val: role.name
		}, {
			id: 'role_remark',
			val: role.remark
		});
		$("#au_role").modal("toggle");
	});

	$(document).on('click', '.remove_role_modal', function() {
		//清除之前预留的信息
		iutil.cleanInput('remove_modal_id', 'remove_modal_url', 'remove_modal_data1', 'remove_modal_data2');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var id = dataSpan.data('para-_id'); //获取id
		// var menu_id = dataSpan.data('para-mid'); //获取id
		// var rank = dataSpan.data('para-rank'); //获取等级
		iutil.setInput({
			id: "remove_modal_id",
			val: id
		}, {
			id: "remove_modal_url",
			val: '/role/remove'
		});
		$("#i_remove_modal_form_submit").attr('onclick', 'modal.remove(role.showRole)');
		$("#remove_confirm").modal("toggle");
	});

	//角色列表点击时触发的事件
	$(document).on('click', '.role_show_auths', function() {
		var _id = $(this).data('role-id');
		$("#checked_role_id_remark_data").data('role-id', _id);
		// $("#checked_role_id_remark_data").text(_id);
		// console.log(_id);
		var url = '/role/showauths' + _id;
		$.ajax({
			url: url,
			type: 'GET',
			data: {
				// _id: _id
			},
			async: false,
			//- cache: false,
			//- contentType: false,
			//- processData: false,
			success: function(data) {
				cb(data);
			},
			error: function() {
				console.log('pathExcel error2')
			}
		});

		function cb(data) {
			$('#role_auths_container_box').html(data);
		}
	});
	$(document).on('click', '#role_auths_container_box dl dd span a', function() {
		var role_id = $("#checked_role_id_remark_data").data('role-id');
		var auth_id = $(this).data('role_id');
		var hasChecked = $(this).data('has_checked');
		//如果此选项是被选中的(原来有相应的权限)
		if (hasChecked && hasChecked == true) {
			//删除页面的样式
			$(this).removeClass('auth_checked');
			//在后台删除相应的数据
			role.removeAuth(role_id, auth_id);
			//前台的data备注框设置成false,说明此权限已经不存在了
			$(this).data('has_checked', false)
				// $(this).data('has_checked')="true"
		} else {
			//添加被选中的样式
			$(this).addClass('auth_checked');
			//添加此权限到数据库
			role.addAuth(role_id, auth_id);
			//设置页面data的备选项
			$(this).data('has_checked', true);
		}
		// console.log($(this).attr('id'));
		// console.log($(this).attr('class'));
	});
});

role.showRole = function(data) {
	$("#main").html(data);
}
role.auRoles = function() {
	//获取执行什么操作
	var todo = $("#role_todo").val();
	var url = todo == 1 ? '/role/add' : '/role/update';

	//获取栏目的id
	// var menu_id = $("#role_menu_id").val();
	var _id = $("#role_id").val();

	//获取封装好的menu对象
	var role = iutil.getObj({
		id: 'role_order',
		name: 'order'
	}, {
		id: 'role_name',
		name: 'name'
	}, {
		id: 'role_remark',
		name: 'remark'
	});

	//如果是修改,则设置其权限的id
	if (todo == 2) {
		role._id = _id;
	}
	// console.log(role);
	// console.log(menu_id);

	$.ajax({
		url: url,
		type: 'POST',
		data: {
			role: role
		},
		async: false,
		success: function(data) {
			cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});

	function cb(data) {
		if (data.code) {

		} else {
			$("#main").html(data);
		}
	}
}
role.addMenu = function(item_id) {
	var role_id = $("#checked_role_id_remark_data").data('role-id');
	$.ajax({
		url: 'role/addmenu',
		type: 'POST',
		data: {
			role_id: role_id,
			menu_id: item_id
		},
		async: false,
		success: function(data) {
			// console.log(data);
			// cb(data);
			// console.log('add menu success');
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}
role.removeMenu = function(item_id) {
	var role_id = $("#checked_role_id_remark_data").data('role-id');
	$.ajax({
		url: 'role/removemenu',
		type: 'POST',
		data: {
			role_id: role_id,
			menu_id: item_id
		},
		async: false,
		success: function(data) {
			// cb(data);
			// console.log('remove menu success');
			// console.log(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}
role.addAuth = function(role_id, auth_id) {
	$.ajax({
		url: 'role/addauth',
		type: 'POST',
		data: {
			role_id: role_id,
			auth_id: auth_id
		},
		async: false,
		success: function(data) {
			// cb(data);
			// console.log('remove menu success');
			// console.log(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}
role.removeAuth = function(role_id, auth_id) {
	$.ajax({
		url: 'role/removeauth',
		type: 'POST',
		data: {
			role_id: role_id,
			auth_id: auth_id
		},
		async: false,
		success: function(data) {
			// cb(data);
			// console.log('remove menu success');
			// console.log(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});


}