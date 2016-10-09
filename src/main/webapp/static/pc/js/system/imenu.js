/**
 * 用于栏目公用js处理的脚本
 *
 */
var imenu = new Object();
$(function(){
    //栏目点击后添加选中效果的方法监听
    imenu.clickMenu();
    //一级栏目的拖动定义
    imenu.moveFirstMenu();
    //二级栏目的拖动定义
    imenu.moveSecondMenu();
    //权限管理的拖动定义
    imenu.movePower();
    
    //显示或隐藏拖动把手的方法
    imenu.showOrHideHandle();
    
    //显示或隐藏权限拖动把手的方法
    // imenu.showOrHidePowerHandle();
    
    //为操作按钮添加模态框
    imenu.addModal();
    
    //模态框部分内容自动填充
    imenu.autoFill();
});

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

imenu.moveFirstMenu = function(){
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
}

imenu.moveSecondMenu = function(){
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
}

imenu.movePower = function(){
    // 权限列表的拖动排序
	$(".au-role-boxes").sortable({
		//            约束其运动方向(只能y轴运动)
		axis: "y",
		handle: ".item-move-handle",
		revert: true,
		// containment: ".column_items",
		//            约束其运动范围(只能在父元素中运动)
		// containment: "parent",
		start: function(event, ui) {
            ui.item.addClass("move-box-start");
			// ui.item.addClass("sortable_start");

			// ui.item.removeClass("add_transition");
			// $(this).addClass("sortable_start");
			// $("#sortable>.li").addClass("li_sor");
		},
		stop: function(event, ui) {
            ui.item.removeClass("move-box-start");
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
}

//显示或隐藏拖动把手的方法
imenu.showOrHideHandle = function(){
    $(document).on('click','[data-flex-icon="flex"]',function(){
        var target = $('.handle',$(this).parents(".page-third-title").siblings(".menu-content"));
        if(target.hasClass('hide')){
            target.removeClass('hide');
        }else{
            target.addClass('hide');
        }
    })
}

//显示或隐藏权限拖动把手的方法
// imenu.showOrHidePowerHandle = function(){

// }


$(document).ready(function() {
	/***************************栏目拖动的js开始*****************************/
	// console.log("一级栏目加载完成");
	
	
    
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

imenu.clearMenuModal = function () {
	
}

/**
 * 用于开启添加一级栏目的模态框
 */
imenu.openAddFirstMenuModal = function(){
	//1. 获取/生成要设置的相关数据
	var addOrEdit = 1;
	var lab = 1;

	var sort = $("#menu-cont .menu-box").size()+1;
	console.log(sort);
	

	//1.1 清空栏目添加/编辑模态框中输入框中的数据
	imenu.clearMenuModal();

	//2. 填充1中获取的数据到模态框中


	//3. 打开添加栏目的模态框
	$('#addMenuModal').modal('toggle');
}

// <span data-para-place='11#{menu.place}' data-para-id='11#{menu.parentId}' data-para-sort="#{menu.sort}"></span>
imenu.openEditFirstMenuModal = function () {
	//1. 获取/定义一级栏目中相关的数据
	var dataEle = $(this).parents(".menu-tools-cont").children("span");
	var id = $(dataEle).data("para-id");
	var sort = $(dataEle).data("para-sort");
	var place = $(dataEle).data("para-place");

	//2. 清空模态框中原有的垃圾数据
	imenu.clearMenuModal();

	//3. 填充1中获取到的当前栏目的数据

	//4. 打开模态框
	$('#addMenuModal').modal('toggle');
}




//模态框的添加
imenu.addModal = function(){
	// $('#myModal').modal('toggle')
//栏目管理模态框的添加
    imenu.doAddModal('.icon-add','.au-menu-count','#addModal');
    imenu.doAddModal('.icon-edit','.au-menu-count','#addModal');
//栏目管理模态框的添加
    imenu.doAddModal('.icon-add','.au-role-count','#addModal');
    imenu.doAddModal('.icon-edit','.au-role-count','#addModal');
}

/**
 * target 表示点击的是添加按钮还是编辑按钮
 *
 */
imenu.doAddModal = function(target,parent,href){
    $(target,$(parent)).attr({href:href,'data-toggle':"modal"});
}

//模态框部分内容自动填充
imenu.autoFill = function(index){
    $(document).on('click','.icon-add',function(){
        var eleFirst = $('span:eq(0)',$(this).parents('.page-third-title'));
        var eleSecond = $('span:eq(0)',$('dd:last',$(this).parents('.menu-items-count')));
        getTarget(0).val('');
        getTarget(1).val('1');
        getTarget(3).val('');
        if($(this).hasClass('first-add')){
            getTarget(2).val('1');
            getTarget(4).val(parseInt(eleFirst.attr('data-para-place'))+1);           
        }else if($(this).hasClass('second-add')){
            getTarget(2).val('2');
            getTarget(4).val(parseInt(eleSecond.attr('data-para-place'))+1); 
        }
    })
}

var getTarget = function(index){
    return $('input:eq('+index+')',$('.modal'));
}

