var menu = new Object();

$(function() {
	//添加一级栏目时
	$(document).on('click', ".add_menu_modal", function() {
		// 获取到当前一级栏目的数量,并加一后赋值给order(当做顺序的值)
		var order = $(this).parents('.menu_box').first().children('dl.menu').length + 1;
		// console.log(order);
		// var order = 
		iutil.cleanInput('menu_id', 'menu_todo', 'menu_rank', 'menu_parent', 'menu_order', 'menu_name', 'menu_icon', 'menu_url');
		iutil.setInput({
			id: "menu_todo",
			val: 1
		}, {
			id: "menu_rank",
			val: 1
		}, {
			id: "menu_icon",
			val: "icon-class-"
		}, {
			id: "menu_order",
			val: order
		});
		$("#au_menu").modal("toggle");
	});
	//添加二级栏目时
	$(document).on('click', '.add_menu_modal_child', function() {
		var order = $(this).parents('dl.menu').first().find('dd.menu_item').length + 1;
		// console.log($(this).parents('dl.menu').first().find('dd.menu_item'));
		iutil.cleanInput('menu_id', 'menu_todo', 'menu_rank', 'menu_parent', 'menu_order', 'menu_name', 'menu_icon', 'menu_url');
		var dataSpan = $(this).prevAll('span').first();
		var parentId = dataSpan.data('para-_id');
		iutil.setInput({
			id: "menu_todo",
			val: 1
		}, {
			id: "menu_rank",
			val: 2
		}, {
			id: "menu_icon",
			val: "icon-class-"
		}, {
			id: "menu_order",
			val: order
		}, {
			id: "menu_parent",
			val: parentId
		});
		$("#au_menu").modal("toggle");
	});

	//删除二级栏目时
	$(document).on('click', '.remove_menu_modal_child', function() {
		//清除之前预留的信息
		iutil.cleanInput('remove_modal_id', 'remove_modal_url', 'remove_modal_data1', 'remove_modal_data2');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var id = dataSpan.data('para-_id'); //获取id
		var pid = dataSpan.data('para-pid'); //获取id
		var rank = dataSpan.data('para-rank'); //获取等级
		iutil.setInput({
			id: "remove_modal_id",
			val: id
		}, {
			id: "remove_modal_url",
			val: '/menu/remove'
		}, {
			id: "remove_modal_data1",
			val: rank
		}, {
			id: "remove_modal_data2",
			val: pid
		});
		$("#i_remove_modal_form_submit").attr('onclick', 'modal.remove(menu.showMenus)');
		$("#remove_confirm").modal("toggle");
	});
	//删除一级栏目时
	$(document).on('click', '.remove_menu_modal', function() {
		iutil.cleanInput('remove_modal_id', 'remove_modal_url', 'remove_modal_data1', 'remove_modal_data2');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var id = dataSpan.data('para-_id'); //获取id
		// console.log(id);
		// console.log(menu.hasChild(id));
		if (menu.hasChild(id)) {

			$("#modal_danger_msg").modal("toggle");
		} else {
			var rank = dataSpan.data('para-rank'); //获取等级
			iutil.setInput({
				id: "remove_modal_id",
				val: id
			}, {
				id: "remove_modal_url",
				val: '/menu/remove'
			}, {
				id: "remove_modal_data1",
				val: rank
			});
			$("#i_remove_modal_form_submit").attr('onclick', 'modal.remove(menu.showMenus)');
			$("#remove_confirm").modal("toggle");
		}
	});
	//编辑一级栏目时
	$(document).on('click', '.edit_menu_modal', function() {
		// 获取一级栏目的相关信息
		iutil.cleanInput('menu_id', 'menu_todo', 'menu_rank', 'menu_parent', 'menu_order', 'menu_name', 'menu_icon', 'menu_url');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		//获取栏目相关的数据
		var menu = iutil.getDatas(dataSpan, {
			dataName: 'para-_id',
			objName: '_id'
		}, {
			dataName: 'para-rank',
			objName: 'rank'
		}, {
			dataName: 'para-order',
			objName: 'order'
		}, {
			dataName: 'para-name',
			objName: 'name'
		}, {
			dataName: 'para-icon',
			objName: 'icon'
		}, {
			dataName: 'para-url',
			objName: 'url'
		});
		//把栏目相关的数据放到模态框中
		iutil.setInput({
			id: "menu_id",
			val: menu._id
		}, {
			id: "menu_todo",
			val: 2
		}, {
			id: "menu_rank",
			val: menu.rank
		}, {
			id: "menu_parent",
			val: ''
		}, {
			id: "menu_order",
			val: menu.order
		}, {
			id: "menu_name",
			val: menu.name
		}, {
			id: "menu_icon",
			val: menu.icon
		}, {
			id: "menu_url",
			val: menu.url
		});
		//打开模态框
		$("#au_menu").modal("toggle");
	});
	//编辑二级栏目时
	$(document).on('click', '.edit_menu_modal_child', function() {
		// 获取一级栏目的相关信息
		iutil.cleanInput('menu_id', 'menu_todo', 'menu_rank', 'menu_parent', 'menu_order', 'menu_name', 'menu_icon', 'menu_url');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		//获取栏目相关的数据
		var menu = iutil.getDatas(dataSpan, {
			dataName: 'para-pid',
			objName: 'pid'
		}, {
			dataName: 'para-_id',
			objName: '_id'
		}, {
			dataName: 'para-rank',
			objName: 'rank'
		}, {
			dataName: 'para-order',
			objName: 'order'
		}, {
			dataName: 'para-name',
			objName: 'name'
		}, {
			dataName: 'para-icon',
			objName: 'icon'
		}, {
			dataName: 'para-url',
			objName: 'url'
		});
		//把栏目相关的数据放到模态框中
		iutil.setInput({
			id: "menu_id",
			val: menu._id
		}, {
			id: "menu_todo",
			val: 2
		}, {
			id: "menu_rank",
			val: menu.rank
		}, {
			id: "menu_parent",
			val: menu.pid
		}, {
			id: "menu_order",
			val: menu.order
		}, {
			id: "menu_name",
			val: menu.name
		}, {
			id: "menu_icon",
			val: menu.icon
		}, {
			id: "menu_url",
			val: menu.url
		});
		//打开模态框
		$("#au_menu").modal("toggle");
	});

});

// //传入参数的描述参数
// 	var id; //id值(如果传参数,id是必传值)
// 	var name; //name值,封装到对象中的属性值
// 	var labelType; //标签类型
// 	var dataType; //数据类型
menu.auMenus = function() {
	//获取执行什么操作
	var todo = $("#menu_todo").val();
	var url = todo == 1 ? '/menu/add' : '/menu/update';

	var parent_id = $("#menu_parent").val();
	var menu_id = $("#menu_id").val();

	//获取封装好的menu对象
	var menu = iutil.getObj({
		id: 'menu_rank',
		name: 'rank'
	}, {
		id: 'menu_order',
		name: 'order'
	}, {
		id: 'menu_name',
		name: 'name'
	}, {
		id: 'menu_icon',
		name: 'icon'
	}, {
		id: 'menu_url',
		name: 'url'
	});

	if (todo == 2) {
		menu._id = menu_id;
	}

	$.ajax({
		url: url,
		type: 'POST',
		data: {
			menu: menu,
			parent_id: parent_id
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
		$("#main").html(data);
	}
	// console.log(menu);
	// body...
}
menu.hasChild = function(menuId) {
	var hasChild = true;
	$.ajax({
		url: '/menu/haschild',
		type: 'POST',
		data: {
			menu_id: menuId
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
		if (data.code == 200 && data.data == 0) {
			hasChild = false;
		}
	}
	return hasChild;
}
menu.showMenus = function(data) {
	$("#main").html(data);
	// console.log(data);
}