/**
 * 用于栏目公用js处理的脚本
 *
 */
var imenu = new Object();
$(function(){
    imenu.clickMenu();
})

imenu.clickMenu = function(){
    $(document).on('click','[data-page-click="menu-item"]',function(){
//        console.log("menuClick");
//        var parent = 
        $(this).parents('[data-cont="menu-cont"]').find('[data-page-click="menu-item"]').each(function(index,ele){
            $(ele).removeClass("activ");
        })
        $(this).addClass("activ");
//        console.log(parent);
    })
}

$(document).ready(function() {
	/***************************栏目拖动的js开始*****************************/
	// console.log("一级栏目加载完成");
	// 一级栏目的拖动
	$("#menu-cont").sortable({
		//            约束其运动方向(只能y轴运动)
		axis: "y",
		//设置把手
		handle: ".menu-move-handle",
		revert: true,
		//            约束其运动范围(只能在父元素中运动)
		//            containment: "parent",
		start: function(event, ui) {
			// console.
			ui.item.addClass("move-box-start");

			// console.log(event);
			// console.log(ui);
			// ui.item.removeClass("add_transition");
			// $(this).addClass("sortable_start");
			// $("#sortable>.li").addClass("li_sor");
		},
		stop: function(event, ui) {
			ui.item.removeClass("move-box-start");
			// console.log($(this))
			var len = $(this).children('dl.menu').length;
			var ele;
			var menu;
			for (var i = 0; i < len; i++) {
				ele = $(this).children('dl').eq(i).children('dt').children('span').children('span.hide_datas');
				menu = iutil.getDatas(ele, {
					dataName: 'para-_id',
					objName: '_id'
				}, {
					dataName: 'para-name',
					objName: 'name'
				})
				menu.order = i + 1;
				// console.log('顺序修改完成' + i);
				// console.log(menu);
				$.ajax({
					url: 'menu/order',
					type: 'post',
					data: {
						_id: menu._id,
						order: menu.order
					},
					success: function(data) {
						// console.log('顺序修改完成' + i);
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
	// 二级栏目的拖动
	$(".menu-items-count").sortable({
		//            约束其运动方向(只能y轴运动)
		axis: "y",
		handle: ".item-move-handle",
		revert: true,
		// containment: ".column_items",
		//            约束其运动范围(只能在父元素中运动)
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
			var menu;
			for (var i = 0; i < len; i++) {
				ele = $(this).children('dd').eq(i).children('span').children('span');
				menu = iutil.getDatas(ele, {
					dataName: 'para-_id',
					objName: '_id'
				}, {
					dataName: 'para-name',
					objName: 'name'
				})
				menu.order = i + 1
					// console.log(menu);
				$.ajax({
					url: 'menu/order',
					type: 'post',
					data: {
						_id: menu._id,
						order: menu.order
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
//	$(document).on('click', '#menu_handle_contorler', function() {
//		// console.log('has click');
//		var isHdide = $(this).data('hide-type') == 0 ? true : false;
//		// console.log(isHdide);
//		if (isHdide) {
//			$(this).parents('.menu_box').first().find('i.column_icon_move').each(function(index, ele) {
//				// console.log(ele);
//				$(ele).css('display', 'inline-block')
//			});
//			$(this).data('hide-type', 1)
//		} else {
//			$(this).parents('.menu_box').first().find('i.column_icon_move').each(function(index, ele) {
//				// console.log(ele);
//				$(ele).css('display', 'none')
//			});
//			$(this).data('hide-type', 0)
//		}
//	});
});