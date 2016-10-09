/**
 * 登陆页面的js文件
 */
var sign = new Object();

$(function() {
	vd.init("signin_form", 2, 1);
	vd.init("signup_form", 2, 1);
	vd.init("verify_email_form", 2, 1);


	// console.log(document.getElementById("userName"));
	// document.getElementById("userName").focus();
});

//执行登陆操作
sign.signin = function() {
	var user = iform.parse("signin_form");
	//console.log(user);
	var url = ctx + "signin";
	//	console.log(url);
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		data: user,
		async: false,
		success: function(data) {
			//console.log(data);
			sign.signinSuccessBack(data);
			//cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
	//console.log("signin");
}

//注册成功后自动登陆的操作
sign.autoSignin = function() {
	// var userObj = jQuery.parseJSON(user);

	var userName = $("#signupUserName").val();
	var pwd = $("#pwd").val();

	var url = ctx + "signin";
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		data: {
			userName: userName,
			pwd: pwd
		},
		async: false,
		success: function(data) {
			//console.log(data);
			sign.signinSuccessBack(data);
			//cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}

//执行登录有正常返回值的时候所做的操作
sign.signinSuccessBack = function(data) {
	//如果返回值为操作成功
	if (iutil.isSuccess(data)) {
		//跳转到首页
		window.location.href = ctx;
//		window.location.href = "/";
	} else {
		imessenger.error("登录失败");
	}
}

//注册的相关处理
sign.signup = function() {
	//	var user = iform.parse("signup_form");
	var userName = $("#signupUserName").val();
	var pwd = $("#pwd").val();

	var url = ctx + "signup";

	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		data: {
			userName: userName,
			pwd: pwd
		},
		async: false,
		success: function(data) {
			// console.log(data);
			cb(data);
		},
		error: function() {
			console.log('注册时,服务器出错');
		}
	});

	function cb(data) {
		if (iutil.isSuccess(data)) {
			var user = data.data;
			if (!v.isNull(user)) {
				imessenger.success("注册成功,正在自动登录....");
				sign.autoSignin();
			}
		}
	}
}

//像用户发送用于绑定邮箱的邮件的方法
sign.sendVerifyEmail = function() {
//	console.log("进入发送邮件的方法");
	var verifyUserIsExist = sign.checkVerifyUserIsExist();
	//sign.checkVerifyUserIsExist
	//检查当前是否有登录用户
	if (verifyUserIsExist) {
		imessenger.success("用户检测成功,将要发送验证信息到您的邮箱");
		//如果不为空,验证用户是否存在
		var url = ctx + "mail/sendVerifyEmail";
		var userId = $("#user_id").val();
		var emailAddr = $("#email").val();

		$.ajax({
			url: url,
			type: 'POST',
			dataType: "json",
			data: {
				"userId": userId,
				"emailAddr":emailAddr
			},
			async: false,
			success: function(data) {
				//console.log(data);
				cb(data);
			},
			error: function() {
				console.log('验证用户时,服务器出错');
			}
		});

		function cb(data) {
			//验证返回值是否正确
			if (iutil.isSuccess(data)) {
				//如果验证值正确
				if (data.data) {
					verifyUserIsExist = true;
				}
			} else {
				imessenger.error("验证用户信息出错");
			}
		}
	} else {
		imessenger.error("用户不存在或验证出错,请重新登录");
	}
}

sign.checkVerifyUserIsExist = function() {
//	console.log("进入检查用户的方法");
	var verifyUserIsExist = false;
	//获取用户id
	var userId = $("#user_id").val();
	//检查用户id是否为空
	if (v.isNull(userId)) {
		//如果为空,则直接返回错误提示
		imessenger.error("获取登录用户出错,请重新登录");
	} else {
		//如果不为空,验证用户是否存在
		var url = ctx + "user/userIsExist";
		$.ajax({
			url: url,
			type: 'GET',
			dataType: "json",
			data: {
				id: userId
			},
			async: false,
			success: function(data) {
				//console.log(data);
				cb(data);
			},
			error: function() {
				console.log('验证用户时,服务器出错');
			}
		});

		function cb(data) {
			//验证返回值是否正确
			if (iutil.isSuccess(data)) {
				//如果验证值正确
				if (data.data) {
					verifyUserIsExist = true;
				}
			} else {
				imessenger.error("验证用户信息出错");
			}
		}
	}
	return verifyUserIsExist;
}

sign.checkBundleType = function(){
	var bundleOk = $("#user_bundle_ok").val();

	if(bundleOk=="true"){

	}else if(bundleOk=="false"){
		imessenger.error("绑定邮箱出错,请重新绑定");
	}
	//console.log(bundleOk);
}
