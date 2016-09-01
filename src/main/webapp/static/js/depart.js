var depart = new Object();
$(function() {

	//添加部门时
	$(document).on('click', ".add_depart_modal", function() {
		// 获取当前部门列表的数量,加一后赋给顺序值
		var order = $(this).parents('.depart_box').first().find('dd.menu_item').length + 1;
		iutil.cleanInput('depart_todo', 'depart_id', 'depart_company_id', 'depart_order', 'depart_name');
		var c_id = $("#au_depart_hide_datas").data('para-cid');
		iutil.setInput({
			id: "depart_todo",
			val: 1
		}, {
			id: "depart_company_id",
			val: c_id
		}, {
			id: "depart_order",
			val: order
		});
		$("#au_depart").modal("toggle");
	});
	//修改公司时
	$(document).on('click', ".edit_depart_modal", function() {
		// 清理之前的模态框中的数据
		iutil.cleanInput('depart_todo', 'depart_id', 'depart_company_id', 'depart_order', 'depart_name');

		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var depart = iutil.getDatas(dataSpan, {
			dataName: 'para-_id',
			objName: '_id'
		}, {
			dataName: 'para-name',
			objName: 'name'
		}, {
			dataName: 'para-cid',
			objName: 'company'
		}, {
			dataName: 'para-order',
			objName: 'order'
		});
		// console.log(depart);

		iutil.setInput({
			id: "depart_todo",
			val: 2
		}, {
			id: "depart_order",
			val: depart.order
		}, {
			id: "depart_company_id",
			val: depart.company
		}, {
			id: 'depart_id',
			val: depart._id
		}, {
			id: 'depart_name',
			val: depart.name
		});
		$("#au_depart").modal("toggle");
	});


	$(document).on('click', '.remove_depart_modal', function() {
		//清除之前预留的信息
		iutil.cleanInput('remove_modal_id', 'remove_modal_url', 'remove_modal_data1', 'remove_modal_data2');
		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var id = dataSpan.data('para-_id'); //获取id
		var cid = dataSpan.data('para-cid'); //获取id
		// var menu_id = dataSpan.data('para-mid'); //获取id
		// var rank = dataSpan.data('para-rank'); //获取等级
		iutil.setInput({
			id: "remove_modal_id",
			val: id
		}, {
			id: "remove_modal_url",
			val: '/depart/remove'
		}, {
			id: "remove_modal_data1",
			val: cid
		});
		$("#i_remove_modal_form_submit").attr('onclick', 'modal.remove(depart.showDepart)');
		$("#remove_confirm").modal("toggle");
	});
});

depart.showDepart = function(data) {
	$("#company_depart_container_box").html(data);
}

// //通过公司的id获取其下的部门
// company.getDepart = function(companyId) {
// 	var url = '/depart/list' + companyId;
// 	$("#au_depart_hide_datas").data('para-cid', companyId);
// 	// console.log($(this).prevAll('.hide_datas').first());
// 	// console.log($("#au_auth_hide_datas").data('para-mid'))
// 	$.ajax({
// 		url: url,
// 		type: 'GET',
// 		data: {

// 		},
// 		async: false,
// 		success: function(data) {
// 			// cb(data);
// 			$("#company_depart_container_box").html(data);
// 		},
// 		error: function() {
// 			console.log('pathExcel error2')
// 		}
// 	});
// }
depart.auDepart = function() {
	//获取执行什么操作
	var todo = $("#depart_todo").val();
	var url = todo == 1 ? '/depart/add' : '/depart/update';

	//获取栏目的id
	// var menu_id = $("#depart_menu_id").val();
	var _id = $("#depart_id").val();

	//获取封装好的menu对象
	var depart = iutil.getObj({
		id: 'depart_order',
		name: 'order'
	}, {
		id: 'depart_name',
		name: 'name'
	}, {
		id: 'depart_company_id',
		name: 'company'
	});

	//如果是修改,则设置其公司的id
	if (todo == 2) {
		depart._id = _id;
	}
	// console.log(depart);
	// console.log(menu_id);

	$.ajax({
		url: url,
		type: 'POST',
		data: {
			depart: depart
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
			$("#company_depart_container_box").html(data);
		}
	}
}