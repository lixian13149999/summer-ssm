/**
 * 用于栏目公用js处理的脚本
 *
 */
var imenu = new Object();
$(function(){
    //栏目点击后添加选中效果的方法监听
    imenu.clickMenu();
    
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
	console.log("进入提交方法");

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
		data: {
			menu:JSON.stringify(menu)
		},
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