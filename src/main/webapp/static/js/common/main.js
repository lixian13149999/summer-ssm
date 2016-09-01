//获取微信二维码的代码块
function getWechatBarcode() {
	return '<div class="wechat_2d_barcode"></div>'
}

//获取用户简介的代码块
function getUserIntd() {
	var user_datas = $('#user_info_msg_hide_datas');

	var _id = user_datas.data('para-_id'); //用户的id
	var username = user_datas.data('para-username'); //用户名
	var realname = user_datas.data('para-realname'); //用户的真实姓名
	var photo = user_datas.data('para-photo'); //用户头像
	var position = user_datas.data('para-position'); //职位
	var email = user_datas.data('para-email'); //邮箱
	var phone = user_datas.data('para-phone'); //电话
	var depart_remark = user_datas.data('para-depart_remark'); //部门

	return '<div class="user_intd_box">' +
		'<div class="intd_top">' +
		'<div style="background-image: url(' + photo + ');" class="intd_photo"></div>' +
		'<div class="intd_info">' +
		'<p class="intd_info_msg">' + depart_remark + '</p>' +
		'<p class="intd_info_msg">' + realname + '</p>' +
		'<p class="intd_info_msg">' + email + '</p>' +
		'</div>' +
		'<div class="intd_top_icon"><a id="user_shezhi_button" class="inner_icon_tiny icon_msg"><i class="iconfont hover_default icon-class-shezhi"></i></a></div>' +
		'</div>' +
		'<div class="intd_body"></div>' +
		'<div class="intd_foot">' +
		'<a class="intd_foot_msg hover_default">发起申请</a>' +
		'<a class="intd_foot_icon" href="/logout">' +
		'<i class="iconfont hover_default icon-class-exit"></i>'
		// '<i data-para-_id=' + _id + ' class="iconfont hover_default icon-class-exit user_exit_button_tip"></i>'
	'+</a></div>' +
	'</div>'

	// return '<div class="user_intd_box">' +
	// 	'<div class="intd_top">' +
	// 	'<div style="background-image: url(' + photo + ');" class="intd_photo"></div>' +
	// 	'<div class="intd_info">' +
	// 	'<p class="intd_info_msg">产品技术部</p>' +
	// 	'<p class="intd_info_msg">' + realname + '</p>' +
	// 	'<p class="intd_info_msg">' + email + '</p>' +
	// 	'</div>' +
	// 	'<div class="intd_top_icon"><a class="inner_icon_tiny icon_msg"><i class="iconfont hover_default">&#xe608;</i></a></div>' +
	// 	'</div>' +
	// 	'<div class="intd_body"></div>' +
	// 	'<div class="intd_foot"><a class="intd_foot_msg hover_default">发起申请</a><a class="intd_foot_icon"><i class="iconfont hover_default">&#xe601;</i>'
	// '+</a></div>' +
	// '</div>';
}

$(function() {
	//栏目的点击效果的切换,被选中的栏目需要有底色
	$(document).on('click', '.menu .menu_item a', function() {
		// console.log($(this).parents(".menu_box").first());
		// console.log($(this).parents(".menu_box").first().find('.menu .menu_item'));
		$(this).parents(".menu_box").first().find('.menu .menu_item').each(function(index, ele) {
			//- console.log(ele);
			$(ele).removeClass('item_checked');
			//- removeClass()
		});
		$(this).parent('.menu_item').addClass('item_checked');
	});
	//角色的点击效果的切换,被选中的角色需要有底色
	$(document).on('click', '.menu .menu_item a', function() {
		// console.log($(this).parents(".menu_box").first());
		// console.log($(this).parents(".menu_box").first().find('.menu .menu_item'));
		$(this).parents(".role_box").first().find('.menu .menu_item').each(function(index, ele) {
			//- console.log(ele);
			$(ele).removeClass('item_checked');
			//- removeClass()
		});
		$(this).parent('.menu_item').addClass('item_checked');
	});
	//角色的点击效果的切换,被选中的角色需要有底色
	$(document).on('click', '.menu .menu_item a', function() {
		// console.log($(this).parents(".menu_box").first());
		// console.log($(this).parents(".menu_box").first().find('.menu .menu_item'));
		$(this).parents(".company_box").first().find('.menu .menu_item').each(function(index, ele) {
			//- console.log(ele);
			$(ele).removeClass('item_checked');
			//- removeClass()
		});
		$(this).parent('.menu_item').addClass('item_checked');
	});

	// 根据输入框中是否有值,来动态设定数据库中属性提示的样式
	$(document).on('blur', '.l_input_div .l_input', function() {
		var val = $(this).val();
		// console.log(val);
		if (val == '' || val == undefined || val == NaN) {
			$(this).next('.l_label').css("top", "-27px");
		} else {
			$(this).next('.l_label').css("top", "-48px");
		}
	})
	$(document).on('focus', '.l_input_div .l_input', function() {
		$(this).next('.l_label').css("top", "-48px");
	});

	// 右边栏目点击,通用方法
	$(document).on('click', '.item_change_menu', function() {
		var url = $(this).data("item-url");
		// var 
		$.ajax({
			url: url,
			type: 'GET',
			data: {},
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
	});

	$(document).on('click', '#user_shezhi_button', function() {
		$('[data-item-id="5728324192ce93e461df3831"]').first().click();
	});
});