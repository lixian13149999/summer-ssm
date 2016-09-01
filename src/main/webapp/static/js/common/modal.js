var modal = new Object();
// input.form-control#remove_modal_id(type="text",placeholder="对象的id")
// input.form-control#remove_modal_url(type="text",placeholder="进行操作的地址")
// input.form-control#remove_modal_data1(type="text",placeholder="备用字段1")
// input.form-control#remove_modal_data2(type="text",placeholder="备用字段2")

//获取封装好的menu对象
// var menu = iutil.getObj({
// 	id: 'menu_rank',
// 	name: 'rank'
// }, {
// 	id: 'menu_order',
// 	name: 'order'
// }, {
// 	id: 'menu_name',
// 	name: 'name'
// }, {
// 	id: 'menu_icon',
// 	name: 'icon'
// }, {
// 	id: 'menu_url',
// 	name: 'url'
// });
modal.remove = function(next) {
	var url = $("#remove_modal_url").val();
	//获取封装好的menu对象
	var data = iutil.getObj({
		id: 'remove_modal_id',
		name: '_id'
	}, {
		id: 'remove_modal_data1',
		name: 'data1'
	}, {
		id: 'remove_modal_data2',
		name: 'data2'
	});
	// console.log(data);
	$.ajax({
		url: url,
		type: 'POST',
		data: {
			data: data
		},
		async: false,
		//- cache: false,
		//- contentType: false,
		//- processData: false,
		success: function(data) {
			// cb(data);
			next(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}