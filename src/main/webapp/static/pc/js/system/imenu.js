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
            sort = $('#menu-cont .menu-box').size()+1;

            $("#foreground_or_back").val(2);
            $("#menu_will_todo").val(1);
            $("#menu_laver").val(1);
            $("#menu_sort").val(sort);

            $('#addMenuModal').modal('toggle'); 
        }else if($(this).hasClass('second-add')){
            sort = $('.menu-item',$(this).parents('.menu-box')).size()+1;
            
            $("#foreground_or_back").val(2);
            $("#menu_will_todo").val(1);
            $("#menu_laver").val(2);
            $("#menu_sort").val(sort);

            var menuParentId = $(this).siblings("span").data("para-id");
            $("#menu_parent_id").val(menuParentId)
            
            $('#addMenuModal').modal('toggle'); 
        }else if($(this).hasClass('first-edit')){
            var menuEle = $(this).siblings("span");

            var id = $(menuEle).data('para-id');
            var parentId = $(menuEle).data('para-parent-id');
            var sort = $(menuEle).data('para-sort');

            var menuName = $(menuEle).data('para-name');
            var menuIcon = $(menuEle).data('para-icon');
            var menuPermission = $(menuEle).data('para-permission');
            var menuHref = $(menuEle).data('para-href');
            var menuDscription = $(menuEle).data('para-description');

            var isShow = $(menuEle).data('para-is-show');
            
            $("#foreground_or_back").val(2)
            $("#menu_id").val(id);
            $("#menu_will_todo").val(2);
            $("#menu_laver").val(1);
            $("#menu_parent_id").val(parentId);
            $("#menu_sort").val(sort);

            $("#menu_name").val(menuName);
            $("#menu_icon").val(menuIcon);
            $("#menu_permission").val(menuPermission);
            $("#menu_href").val(menuHref);
            $("#menu_description").val(menuDscription);

            $("input[name='isShow']").each(function (index,ele){
                if(isShow == $(ele).val()){
                    $(ele).click();
                }
            });

            $('#addMenuModal').modal('toggle'); 
        }else if($(this).hasClass('second-edit')){
            var menuEle = $(this).siblings("span");

            var id = $(menuEle).data('para-id');
            var parentId = $(menuEle).data('para-parent-id');
            var sort = $(menuEle).data('para-sort');

            var menuName = $(menuEle).data('para-name');
            var menuIcon = $(menuEle).data('para-icon');
            var menuPermission = $(menuEle).data('para-permission');
            var menuHref = $(menuEle).data('para-href');
            var menuDscription = $(menuEle).data('para-description');

            var isShow = $(menuEle).data('para-is-show');
            
            $("#foreground_or_back").val(2)
            $("#menu_id").val(id);
            $("#menu_will_todo").val(2);
            $("#menu_laver").val(2);
            $("#menu_parent_id").val(parentId);
            $("#menu_sort").val(sort);

            $("#menu_name").val(menuName);
            $("#menu_icon").val(menuIcon);
            $("#menu_permission").val(menuPermission);
            $("#menu_href").val(menuHref);
            $("#menu_description").val(menuDscription);

            $("input[name='isShow']").each(function (index,ele){
                if(isShow == $(ele).val()){
                    $(ele).click();
                }
            });
            
            $('#addMenuModal').modal('toggle');
        }
    })
}

imenu.addOrEditMenu = function () {
	// console.log("进入提交方法");

    //1. 获取并封装模态框中输入框的内容
	var menu = iutil.parseValue({
        id:"#foreground_or_back",
        name:"foregroundOrBack"
    },{
		id:"#menu_id",
		name:"id"
	},{
        id:"#menu_laver",
        name:"laver"
    },{
        id:"#menu_parent_id",
        name:"parentId"
    },{
        id:"#menu_sort",
        name:"sort"
    },{
        id:"#menu_name",
        name:"name"
    },{
        id:"#menu_icon",
        name:"icon"
    },{
        id:"#menu_permission",
        name:"permission"
    },{
        id:"#menu_href",
        name:"href"
    },{
		id:"#menu_description",
		name:"description"
	});
	
    var isShow = $("input[name='isShow']:checked").val();
    menu.isShow = isShow;

    var willTodo = $("#menu_will_todo").val();
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
			cb(data);
		},
		err: function(jqXHR, textStatus, errorThrown) {
			console.log('error ' + textStatus + " " + errorThrown);
		}
	});
    function cb (data) {
        if(iutil.isSuccess(data)){
            $("#menu-cont").html(data.data);
        }
        $('#addMenuModal').modal('toggle');
    }
}