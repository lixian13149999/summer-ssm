$(document).ready(function() {
	// 权限列表的拖动
	$(".auth_au_order_box").sortable({
		//约束其运动方向(只能y轴运动)
		axis: "y",
		handle: ".column_icon_move",
		revert: true,
		// containment: ".column_items",
		// 约束其运动范围(只能在父元素中运动)
		// containment: "parent",
		start: function(event, ui) {
			// ui.item.addClass("sortable_start");

			// ui.item.removeClass("add_transition");
			// $(this).addClass("sortable_start");
			// $("#sortable>.li").addClass("li_sor");
		},
		stop: function(event, ui) {
			var len = $(this).children('dd.menu_item').length;
			var ele;
			var auth;
			for (var i = 0; i < len; i++) {
				ele = $(this).children('dd.menu_item').eq(i).children('span').children('span');
				auth = iutil.getDatas(ele, {
					dataName: 'para-_id',
					objName: '_id'
				}, {
					dataName: 'para-name',
					objName: 'name'
				}, {
					dataName: 'para-mid',
					objName: 'menu'
				})
				auth.order = i + 1
					// console.log(auth);
				$.ajax({
					url: 'auth/order',
					type: 'post',
					data: {
						_id: auth._id,
						order: auth.order,
						menu: auth.menu
					},
					success: function(data) {
						// $("#column_list").html(data);
						// console.log(data);
					},
					err: function(jqXHR, textStatus, errorThrown) {
						console.log('error ' + textStatus + " " + errorThrown);
					}
				})
			};
		}
	});

	$(document).on('click', '#auth_handle_contorler', function() {
		// console.log('has click');
		var isHdide = $(this).data('hide-type') == 0 ? true : false;
		// console.log(isHdide);
		if (isHdide) {
			$(this).parents('.auth_box').first().find('i.column_icon_move').each(function(index, ele) {
				// console.log(ele);
				$(ele).css('display', 'inline-block')
			});
			$(this).data('hide-type', 1)
		} else {
			$(this).parents('.auth_box').first().find('i.column_icon_move').each(function(index, ele) {
				// console.log(ele);
				$(ele).css('display', 'none')
			});
			$(this).data('hide-type', 0)
		}
	});
});