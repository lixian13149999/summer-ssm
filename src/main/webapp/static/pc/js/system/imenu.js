/**
 * 用于栏目公用js处理的脚本
 *
 */
var imenu = new Object();
$(function(){
    //栏目点击后添加选中效果的方法监听
    imenu.clickMenu();
    //一级栏目的拖动定义
    // imenu.moveFirstMenu();
    //二级栏目的拖动定义
    imenu.moveSecondMenu();
    //权限管理的拖动定义
    imenu.movePower();
    
    //显示或隐藏拖动把手的方法
    imenu.showOrHideHandle();
    
    //栏目添加按钮模态框
    imenu.openMenuModal();

    // 加入栏目添加/编辑的模态框表单校验
    vd.init("menu_modal_form", 1, 1);
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



imenu.moveSecondMenu = function(){
	// console.log("移动方法检测开始");
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

/**
 * 用于模态框的初始化
 */
imenu.clearMenuModal = function () {
	$('#addMenuModal .form-control').val('');
    $('#addMenuModal .error-msg-label').html('');
    $('.input-radio :radio').removeAttr('checked');
    $('.input-radio input:eq(0)').prop('checked',true);
}

/**
 * 用于获取目标span元素，得到自动填充的信息
 */
imenu.getTarget = function(ele){
    return $(ele).parents('.menu-tools-cont').children('span');
}

/**
 * 打开添加栏目的模态框
 */
// imenu.openNoDataMenuModal = function({"menu_id",value},labelRank,addOrEdit,pId,sort){
//     imenu.getInput(0).val(id);
//     imenu.getInput(2).val(labelRank);
//     imenu.getInput(1).val(addOrEdit);
//     imenu.getInput(3).val(pId);
//     imenu.getInput(4).val(sort);
//     $('#addMenuModal').modal('toggle'); 
// }
        

/**
 * 用于开启栏目管理的模态框
 */
imenu.openMenuModal = function(){
    $(document).on('click','.au-menu-count .menu-tools-box',function(){

        //1. 获取/生成要设置的相关数据
        var id;
        var addOrEdit;
        var pId;
        var labelRank;
        var sort; 

        //2 清空栏目添加/编辑模态框中输入框中的数据
        imenu.clearMenuModal();
        
        //3. 填充1中获取的数据到模态框中

        //一级栏目的添加
        if($(this).hasClass('first-add')){
            addOrEdit = 1;
            labelRank = 1;
            sort = $('#menu-cont .menu-box').size()+1;

            $("#menu_will_todo").val(addOrEdit);
            $("#menu_laver").val(labelRank);
            $("#menu_sort").val(sort);

            $('#addMenuModal').modal('toggle'); 
        }else if($(this).hasClass('second-add')){
            addOrEdit = 1;
            labelRank = 2;
            sort = $('.menu-item',$(this).parents('.menu-box')).size()+1;
            
            $("#menu_will_todo").val(addOrEdit);
            $("#menu_laver").val(labelRank);
            $("#menu_sort").val(sort);
            
            $('#addMenuModal').modal('toggle'); 
        }else if($(this).hasClass('first-edit')){
            id = imenu.getTarget(this).data('para-id');
            addOrEdit = 2;
            labelRank = 1;
            sort = imenu.getTarget(this).data('para-sort');
            
            $("#menu_id").val(id);
            $("#menu_will_todo").val(addOrEdit);
            $("#menu_laver").val(labelRank);
            $("#menu_sort").val(sort);
            
            $('#addMenuModal').modal('toggle'); 
        }else if($(this).hasClass('second-edit')){
            id = imenu.getTarget(this).data('para-id');
            addOrEdit = 2;
            labelRank = 2;            
            pId = imenu.getTarget(this).data('para-parent-id');
            sort = imenu.getTarget(this).data('para-sort');
            
            $("#menu_id").val(id);
            $("#menu_will_todo").val(addOrEdit);
            $("#menu_laver").val(labelRank);
            $("#menu_parent_id").val(pId)
            $("#menu_sort").val(sort);
            
            $('#addMenuModal').modal('toggle'); 
        }


         
    })
}

imenu.addOrEditMenu = function () {
	//1. 获取并封装模态框中输入框的内容
	var id = $("#menu_id").val();
	var willTodo = $("#menu_will_todo").val();
	var laver = $("#menu_laver").val();
	var parentId = $("#menu_parent_id").val();
	var sort = $("#menu_sort").val();

	var name = $("#menu_name").val();
	var icon = $("#menu_icon").val();
	var permission = $("#menu_permission").val();
	var href = $("#menu_href").val();
	var description = $("#menu_description").val();

	var menu = new Object();
	menu.id = id;
	menu.willTodo = willTodo;
	menu.laver = laver;
	menu.parentId = parentId;
	menu.sort = sort;

	menu.name = name;
	menu.icon = icon;
	menu.permission = permission;
	menu.href = href;
	menu.description = description;

	console.log(willTodo);
	var url;
	if(willTodo==="1"){
		url="menu/add";
	}else if(willTodo==="2"){
		url="menu/update";
	}else{
		return false;
	}

	$.ajax({
		url: url,
		type: 'POST',
		data: JSON.stringify(menu),
		success: function(data) {
			console.log(data);
			// $("#column_list").html(data);
			// console.log(data);
		},
		err: function(jqXHR, textStatus, errorThrown) {
			console.log('error ' + textStatus + " " + errorThrown);
		}
	})	
}

// <span data-para-place='11#{menu.place}' data-para-id='11#{menu.parentId}' data-para-sort="#{menu.sort}"></span>
//imenu.openEditMenuModal = function () {
//    $(document).on('click','.au-menu-count .menu-tools-box',function(){
//        //1. 获取/定义一级栏目中相关的数据
//        var dataEle = $(this).parents(".menu-tools-cont").children("span");
//        var id = $(dataEle).data("para-id");
//        var sort = $(dataEle).data("para-sort");
//        var place = $(dataEle).data("para-place");
//
//        //2. 清空模态框中原有的垃圾数据
//        imenu.clearMenuModal();
//
//        //3. 填充1中获取到的当前栏目的数据
//
//        //4. 打开模态框
//        $('#addMenuModal').modal('toggle');
//    })
//	
//}

/**
 * 用于模态框输入限制的验证
 */
// imenu.modalIverify = function(){
//     $(document).on('focus','.modal-container .form-control',function(){
//         $(this).parents('.modal-box,.modal-box-exc').next('.error-tips').html('此字段必填').css('color','#1b9af7');
//    })
//    $(document).on('blur','.modal-container .form-control',function(){
//        if(v.isNull($(this).val())){
//            $(this).parents('.modal-box,.modal-box-exc').next('.error-tips').html('此内容不能为空').css('color','#ff0000');
//        }else{
//             $(this).parents('.modal-box,.modal-box-exc').next('.error-tips').html('')
//         }
//    })
// }

/**
 * 用于模态框输入限制的验证
 */
//imenu.modalIverify = function(){
//    $("#lanmu-modal-form").validate({
//        errorClass: "label.error-tips",
//        rules:{
//            lanmuName:{
//                required: true
//            },
//            lanmuIcon:{
//                required: true
//            },
//            lanmuPower:{
//                required: true
//            },
//            lanmuAddress:{
//                required: true
//            },
//            lanmuDes:{
//                required: true
//            }
//        },
//        messages:{
//            lanmuName:{
//                required: '此内容不能为空'
//            },
//            lanmuIcon:{
//                required: '此内容不能为空'
//            },
//            lanmuPower:{
//                required: '此内容不能为空'
//            },
//            lanmuAddress:{
//                required: '此内容不能为空'
//            },
//            lanmuDes:{
//                required: '此内容不能为空'
//            }
//        }
//    })
//}






