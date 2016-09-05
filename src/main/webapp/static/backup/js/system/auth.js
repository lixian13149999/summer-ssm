// add_auth_modal
var auth = new Object();

$(function() {
	//添加权限时
	$(document).on('click', ".add_auth_modal", function() {
		// 获取当前权限列表的数量,加一后赋给顺序值
		var order = $(this).parents('.auth_box').first().find('dd.menu_item').length + 1;
		// console.log($(this).parents('.auth_box').first().find('dd.menu_item'));
		var menuId = $("#au_auth_hide_datas").data('para-mid');
		if (menuId == '' || menuId == undefined) {
			$("#error_modal_msg_lable").html('请先选择对应的栏目');
			$("#modal_danger_msg").modal("toggle");

		} else {
			// console.log(order);
			// var order = 
			iutil.cleanInput('auth_menu_id', 'auth_todo', 'auth_id', 'auth_uuname', 'auth_order', 'auth_name', 'auth_icon', 'auth_url', 'auth_remark');
			iutil.setInput({
				id: "auth_todo",
				val: 1
			}, {
				id: "auth_order",
				val: order
			}, {
				id: 'auth_menu_id',
				val: menuId
			}, {
				id: 'auth_icon',
				val: 'icon-class-'
			});
			$("#au_auth").modal("toggle");
		}
	});

	//修改权限时
	$(document).on('click', ".edit_auth_modal", function() {
		// // 获取当前权限列表的数量,加一后赋给顺序值
		// var order = $(this).parents('.auth_box').first().find('dd.menu_item').length + 1;
		// // console.log($(this).parents('.auth_box').first().find('dd.menu_item'));
		// var menuId = $("#au_auth_hide_datas").data('para-mid');
		// // console.log(order);
		// // var order =
		// 清理之前的模态框中的数据
		iutil.cleanInput('auth_menu_id', 'auth_todo', 'auth_id', 'auth_uuname', 'auth_order', 'auth_name', 'auth_icon', 'auth_url', 'auth_remark');

		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var auth = iutil.getDatas(dataSpan, {
			dataName: 'para-mid',
			objName: 'menu'
		}, {
			dataName: 'para-_id',
			objName: '_id'
		}, {
			dataName: 'para-uuname',
			objName: 'uuname'
		}, {
			dataName: 'para-name',
			objName: 'name'
		}, {
			dataName: 'para-icon',
			objName: 'icon'
		}, {
			dataName: 'para-order',
			objName: 'order'
		}, {
			dataName: 'para-url',
			objName: 'url'
		}, {
			dataName: 'para-remark',
			objName: 'remark'
		});

		iutil.setInput({
			id: "auth_todo",
			val: 2
		}, {
			id: "auth_order",
			val: auth.order
		}, {
			id: 'auth_menu_id',
			val: auth.menu
		}, {
			id: 'auth_icon',
			val: auth.icon
		}, {
			id: 'auth_id',
			val: auth._id
		}, {
			id: 'auth_uuname',
			val: auth.uuname
		}, {
			id: 'auth_name',
			val: auth.name
		}, {
			id: 'auth_url',
			val: auth.url
		}, {
			id: 'auth_remark',
			val: auth.remark
		});
		$("#au_auth").modal("toggle");
	});
	$(document).on('click', '.remove_auth_modal', function() {
		//清除之前预留的信息
		iutil.cleanInput('remove_modal_id', 'remove_modal_url', 'remove_modal_data1', 'remove_modal_data2');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var id = dataSpan.data('para-_id'); //获取id
		var menu_id = dataSpan.data('para-mid'); //获取id
		// var rank = dataSpan.data('para-rank'); //获取等级
		iutil.setInput({
			id: "remove_modal_id",
			val: id
		}, {
			id: "remove_modal_url",
			val: '/auth/remove'
		}, {
			id: "remove_modal_data1",
			val: menu_id
		});
		$("#i_remove_modal_form_submit").attr('onclick', 'modal.remove(auth.showAuth)');
		$("#remove_confirm").modal("toggle");
	});
});

// //传入参数的描述参数
// 	var id; //id值(如果传参数,id是必传值)
// 	var name; //name值,封装到对象中的属性值
// 	var labelType; //标签类型
// 	var dataType; //数据类型

auth.auAuths = function() {
	//获取执行什么操作
	var todo = $("#auth_todo").val();
	var url = todo == 1 ? '/auth/add' : '/auth/update';

	//获取栏目的id
	var menu_id = $("#auth_menu_id").val();
	var _id = $("#auth_id").val();

	//获取封装好的menu对象
	var auth = iutil.getObj({
		id: 'auth_menu_id',
		name: 'menu'
	}, {
		id: 'auth_uuname',
		name: 'uuname'
	}, {
		id: 'auth_order',
		name: 'order'
	}, {
		id: 'auth_name',
		name: 'name'
	}, {
		id: 'auth_icon',
		name: 'icon'
	}, {
		id: 'auth_url',
		name: 'url'
	}, {
		id: 'auth_remark',
		name: 'remark'
	});

	//如果是修改,则设置其权限的id
	if (todo == 2) {
		auth._id = _id;
	}
	// console.log(auth);
	// console.log(menu_id);

	$.ajax({
		url: url,
		type: 'POST',
		data: {
			auth: auth,
			menu_id: menu_id
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
			$("#auth_au_container_box").html(data);
		}
	}
}
auth.getAuths = function(menuId) {
	var url = '/auth/list' + menuId;
	$("#au_auth_hide_datas").data('para-mid', menuId);
	// console.log($(this).prevAll('.hide_datas').first());
	// console.log($("#au_auth_hide_datas").data('para-mid'))
	$.ajax({
		url: url,
		type: 'GET',
		data: {

		},
		async: false,
		success: function(data) {
			// cb(data);
			$("#auth_au_container_box").html(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}
auth.showAuth = function(data) {
	$("#auth_au_container_box").html(data);
}


auth.getIAuths = function(id, ele_box) {
	//- console.log(ele_box);
	//- var url = '/auth/list'+id
	$.ajax({
		url: '/auth/list',
		type: 'POST',
		data: {
			_id: id
		},
		async: false,
		success: function(data) {
			//- console.log(data);
			cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});

	function cb(data) {
		$(ele_box).html(data);
	}
}

// menu.hasChild = function(menuId) {
// 	var hasChild = true;
// 	$.ajax({
// 		url: '/menu/haschild',
// 		type: 'POST',
// 		data: {
// 			menu_id: menuId
// 		},
// 		async: false,
// 		//- cache: false,
// 		//- contentType: false,
// 		//- processData: false,
// 		success: function(data) {

// 			cb(data);
// 		},
// 		error: function() {
// 			console.log('pathExcel error2')
// 		}
// 	});

// 	function cb(data) {
// 		if (data.code == 200 && data.data == 0) {
// 			hasChild = false;
// 		}
// 	}
// 	return hasChild;
// }
// menu.showMenus = function(data) {
// 	$("#inner_box").html(data);
// 	// console.log(data);
// }