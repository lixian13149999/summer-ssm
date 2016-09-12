/**
 * 头部代码处理的文件
 *
 */
var itop = new Object();
//获取微信二维码的代码块
itop.getWechatBarcode = function() {
	var wechatData = $("#top_icon_wechat_barcode");

	var url = wechatData.data('para-barcode');
//    console.log("进入获取二维码方法");
	return '<div class="wechat-2d-barcode" style="background-image: url('+url+');"></div>'
}

//获取用户简介的代码块
itop.getUserIntd = function() {
	var user_datas = $('#top_user_info_hide_datas');

	var _id = user_datas.data('para-_id'); //用户的id
	var username = user_datas.data('para-username'); //用户名
	var realname = user_datas.data('para-realname'); //用户的真实姓名
	var photo = user_datas.data('para-photo'); //用户头像
	var position = user_datas.data('para-position'); //职位
	var email = user_datas.data('para-email'); //邮箱
	var phone = user_datas.data('para-phone'); //电话
	var depart_remark = user_datas.data('para-depart_remark'); //部门

	return '<div class="user-intd-box">' +
		'<div class="intd-top">' +
		'<div style="background-image: url(' + photo + ');" class="intd-photo"></div>' +
		'<div class="intd-info">' +
		'<p class="intd-info-msg">' + depart_remark + '</p>' +
		'<p class="intd-info-msg">' + realname + '</p>' +
		'<p class="intd-info-msg">' + email + '</p>' +
		'</div>' +
		'<div class="intd-top-icon"><a id="user_shezhi_button" class="inner-icon-tiny"><i class="icon hover-default icon-tool"></i></a></div>' +
		'</div>' +
		'<div class="intd-body"></div>' +
		'<div class="intd-foot">' +
		'<a class="intd-foot-msg hover-default">发起申请</a>' +
		'<a class="intd-foot-icon" href="/logout">' +
		'<i class="icon hover-default icon-exit"></i>'
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