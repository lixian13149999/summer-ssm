var company = new Object();
$(function() {
	//添加公司时
	$(document).on('click', ".add_company_modal", function() {
		// 获取当前公司列表的数量,加一后赋给顺序值
		var order = $(this).parents('.company_box').first().find('dd.menu_item').length + 1;
		iutil.cleanInput('company_todo', 'company_id', 'company_order', 'company_name');
		iutil.setInput({
			id: "company_todo",
			val: 1
		}, {
			id: "company_order",
			val: order
		});
		$("#au_company").modal("toggle");
	});

	//修改公司时
	$(document).on('click', ".edit_company_modal", function() {
		// 清理之前的模态框中的数据
		iutil.cleanInput('company_todo', 'company_id', 'company_order', 'company_name', 'company_remark');

		//获取数据对象(隐藏对象)
		var dataSpan = $(this).prevAll('span').first();
		var company = iutil.getDatas(dataSpan, {
			dataName: 'para-_id',
			objName: '_id'
		}, {
			dataName: 'para-name',
			objName: 'name'
		}, {
			dataName: 'para-order',
			objName: 'order'
		});
		// console.log(company);

		iutil.setInput({
			id: "company_todo",
			val: 2
		}, {
			id: "company_order",
			val: company.order
		}, {
			id: 'company_id',
			val: company._id
		}, {
			id: 'company_name',
			val: company.name
		});
		$("#au_company").modal("toggle");
	});


	$(document).on('click', '.remove_company_modal', function() {
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
			val: '/company/remove'
		});
		$("#i_remove_modal_form_submit").attr('onclick', 'modal.remove(company.showCompany)');
		$("#remove_confirm").modal("toggle");
	});

	// //添加部门时
	// $(document).on('click', ".add_depart_modal", function() {
	// 	// 获取当前部门列表的数量,加一后赋给顺序值
	// 	var order = $(this).parents('.depart_box').first().find('dd.menu_item').length + 1;
	// 	iutil.cleanInput('depart_todo', 'depart_id', 'depart_company_id', 'depart_order', 'depart_name');
	// 	var c_id = $("#au_depart_hide_datas").data('para-cid');
	// 	iutil.setInput({
	// 		id: "depart_todo",
	// 		val: 1
	// 	}, {
	// 		id: "depart_company_id",
	// 		val: c_id
	// 	}, {
	// 		id: "depart_order",
	// 		val: order
	// 	});
	// 	$("#au_depart").modal("toggle");
	// });
});
company.showCompany = function(data) {
	$("#main").html(data);
}

//通过公司的id获取其下的部门
company.getDepart = function(companyId) {
	var url = '/depart/list' + companyId;
	$("#au_depart_hide_datas").data('para-cid', companyId);
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
			$("#company_depart_container_box").html(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}
company.auCompany = function() {
	//获取执行什么操作
	var todo = $("#company_todo").val();
	var url = todo == 1 ? '/company/add' : '/company/update';

	//获取栏目的id
	// var menu_id = $("#company_menu_id").val();
	var _id = $("#company_id").val();

	//获取封装好的menu对象
	var company = iutil.getObj({
		id: 'company_order',
		name: 'order'
	}, {
		id: 'company_name',
		name: 'name'
	});

	//如果是修改,则设置其公司的id
	if (todo == 2) {
		company._id = _id;
	}
	// console.log(company);
	// console.log(menu_id);

	$.ajax({
		url: url,
		type: 'POST',
		data: {
			company: company
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