var signin_up = new Object();


$(function() {
	$(document).on('blur', '#username_in', function() {
		signin_up.checkUserNameIn($(this));
	})
	$(document).on('blur', '#pwd_in', function() {
		signin_up.checkPwdIn($(this));
	})
	$(document).on('blur', '#username', function() {
		signin_up.checkUserName($(this));
	})
	$(document).on('blur', '#pwd', function() {
		signin_up.checkPwd($(this));
	})
	$(document).on('blur', '#pwd2', function() {
		signin_up.checkPwd2($(this));
	})
	$(document).on('blur', '#verifyemail', function() {
		signin_up.checkEmail($(this));
	})
});

//注册
signin_up.signup = function() {
	var ele_username = $("#username");
	var ele_pwd = $("#pwd");
	var ele_pwd2 = $("#pwd2");
	if (signin_up.checkUserName(ele_username) && signin_up.checkPwd(ele_pwd) && signin_up.checkPwd2(ele_pwd2)) {
		$("#signin_form").submit();
	} else {
		return 0;
	}
}

//登陆
signin_up.signin = function() {
	var user = new Object();
	var ele_username = $("#username_in");
	var ele_pwd = $("#pwd_in");
	user.username = ele_username.val();
	user.pwd = ele_pwd.val();
	// console.log(user.username);
	// console.log(user.pwd);
	// var ele_pwd2 = $("#pwd2");
	//如果验证通过
	if (signin_up.checkUserNameIn(ele_username) && signin_up.checkPwdIn(ele_pwd)) {
		//如果用户不存在
		if (!signin_up.isExist(user.username)) {
			// console.log('用户名或密码错误');
			v.toError(ele_pwd, '用户名或密码错误');
			// return 0;
			// isPass = false;
		} else {
			$.ajax({
				url: '/signin',
				type: 'POST',
				data: {
					user: user
				},
				async: false,
				//- cache: false,
				//- contentType: false,
				//- processData: false,
				success: function(data) {
					// console.log(data);
					console.log(data);
					if (data.code == 200 && data.data == 1) {
						window.location.href = "/";
						// cb(data);
					} else if (data.code == 500 && data.data == 0) {
						// signin_up.signinError(data.msg);
						// v.toError(ele, '密码不能为空');
						cb();
					} else if (data.code == 500 && data.data == 10) {
						window.location.href = "/mail/toverify" + data.user_id;
					}
				},
				error: function() {
					console.log('pathExcel error2')
				}
			});

			function cb() {
				v.toError(ele_pwd, '用户名或密码错误');
			}
		}
	} else {
		return 0;
	}
}
var second = 120;
signin_up.toLock = function() {
	second = 120;
	// console.log('执行锁定');
	$('#verifyemail').attr("disabled", true);
	$('#verifybtn').attr("disabled", true);
	$("#verifymsg").text("验证信息已经发送到您邮箱, 请登录邮箱, 进行验证")

	var count = setInterval(function() {
		if (second >= 0) {
			// clock();
			$("#verifybtn").text(second-- + "秒后...");
		} else {
			window.clearInterval(count);
			$('#verifyemail').attr("disabled", false);
			$('#verifybtn').attr("disabled", false);
			$("#verifymsg").text("请输入邮箱");
			$("#verifybtn").text("重新发送");
		}
	}, 1000);
}

//用于获取要发送给用户的邮箱的内容
signin_up.getMail = function() {
	var ele_email = $("#verifyemail"); //获取邮箱输入框对象
	if (signin_up.checkEmail(ele_email)) { //如果检测通过
		// console.log('发送');
		var username = $("#username").val(); //用户名
		var userid = $("#user_id").val(); //用户id
		var usermail = ele_email.val(); //输入的邮箱(将要发送到的)
		var mailtype = 1; //邮件的类型 1表示用于验证的邮箱
		//先执行请求,进行拼接将要发送到用户邮箱的页面
		$.ajax({
			url: '/mail/getmail',
			type: 'GET',
			// dataType: 'json',
			data: {
				username: username,
				userid: userid,
				usermail: usermail,
				mailtype: mailtype,
				uuid: iutil.uuid()
			},
			async: false,
			success: function(data) {
				var datas = new Object();
				var o = $(data).find("options").first();
				datas.p = $(data).find("page").first().html();
				datas.usermail = o.children("usermail").text();
				datas.subject = o.children("subject").text();
				datas.text = o.children("text").text();
				signin_up.toLock();
				//调用发送方法

				signin_up.sendMail(datas);
			},
			error: function() {
				console.log('pathExcel error2')
			}
		});
	} else {
		// console.log('未发送');
		return 0;
	}
}
signin_up.sendMail = function(data) {
	$.ajax({
		url: '/mail/sendmail',
		type: 'POST',
		data: {
			data: data
		},
		async: false,
		success: function(data) {
			if (200 === data.code) {
				// console.log('sendMail success')
			} else {
				console.log('pathExcel error1')
			}
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}

signin_up.checkUserName = function(ele) {
	var isPass = true;
	var val = ele.val();
	// console.log(val.length)
	if (v.isNull(val)) { //验证是否为空
		v.toError(ele, '用户名不能为空');
		isPass = false;

	} else if (v.shortThan(val, 2)) {
		v.toError(ele, '用户名不得小于2位');
		isPass = false;
	} else if (v.longThan(val, 32)) {
		v.toError(ele, '用户名不得大于32位');
		isPass = false;
	} else if (signin_up.isExist(val)) {
		v.toError(ele, '该用户名已经被占用');
		isPass = false;
	} else {
		v.toSuccess(ele, '请输入用户名');
	}
	return isPass;
}
signin_up.checkUserNameIn = function(ele) {
	var isPass = true;
	var val = ele.val();
	// console.log(val.length)
	if (v.isNull(val)) { //验证是否为空
		v.toError(ele, '用户名不能为空');
		isPass = false;

	} else if (v.shortThan(val, 2)) {
		v.toError(ele, '用户名不得小于2位');
		isPass = false;
	} else if (v.longThan(val, 32)) {
		v.toError(ele, '用户名不得大于32位');
		isPass = false;
	} else {
		v.toSuccess(ele, '请输入用户名');
	}
	return isPass;
}

signin_up.checkPwd = function(ele) {
	var isPass = true;
	var val = ele.val();
	if (v.isNull(val)) { //验证是否为空
		v.toError(ele, '密码不能为空');
		isPass = false;

	} else if (v.shortThan(val, 4)) {
		v.toError(ele, '密码不得小于4位');
		isPass = false;
	} else if (v.longThan(val, 32)) {
		v.toError(ele, '密码不得大于32位');
		isPass = false;
	} else {
		v.toSuccess(ele, '请输入密码');
	}
	return isPass;
}
signin_up.checkPwdIn = function(ele) {
	var isPass = true;
	var val = ele.val();
	if (v.isNull(val)) { //验证是否为空
		v.toError(ele, '密码不能为空');
		isPass = false;

	} else if (v.shortThan(val, 4)) {
		v.toError(ele, '密码不得小于4位');
		isPass = false;
	} else if (v.longThan(val, 32)) {
		v.toError(ele, '密码不得大于32位');
		isPass = false;
	} else {
		v.toSuccess(ele, '请输入密码');
	}
	return isPass;
}

// signin_up.signinError = function(ele,msg){

// }

signin_up.checkPwd2 = function(ele) {
	var isPass = true;
	var val = ele.val();
	if (!v.isSame(val, $("#pwd").val())) {
		v.toError(ele, '两次密码输入不一致');
		isPass = false;
	} else {
		v.toSuccess(ele, '请再次输入密码');
	}
	return isPass;
}
signin_up.checkEmail = function(ele) {
	// console.log("进入验证");
	var isPass = true;
	var val = ele.val();
	if (v.isNull(val)) { //验证是否为空
		v.toError(ele, '邮箱不能为空');
		isPass = false;
	} else if (!v.isEmail(val)) {
		v.toError(ele, '请输入合法的邮箱');
		isPass = false;
	} else if (signin_up.mailIsExist(val)) {
		v.toError(ele, '此邮箱已经被占用,请重新输入');
		isPass = false;
	} else {
		v.toSuccess(ele, '请输入邮箱');
	}
	return isPass;
}
signin_up.mailIsExist = function(str) {
	// console.log('进入后台查看检测')
	var isExist = true;

	function cb(data) {
		// console.log(data);
		if (data.code != 200 || data.data != 1) {
			isExist = false;
			// if (data.isexist == 1) {
			// 	isExist = true;
			// } else {
			// 	isExist = false;
			// }
		} else {
			isExist = true;
		}
		return isExist;
	}
	$.ajax({
		url: '/mail/isexist',
		type: 'GET',
		data: {
			usermail: str
		},
		async: false,
		success: function(data) {
			if (data.code == 200) {
				cb(data);
			}
		},
		error: function() {
			// console.log('pathExcel error2')
			var data = new Object();
			data.code = 500;
			data.isexist = 1;
			cb(data);
		}
	});
	return isExist;
}
signin_up.isExist = function(str) {
	var isExist = false;

	function cb(data) {
		// console.log(data.code);
		//
		if (data.code == 200) {
			if (data.isexist == 1) {
				isExist = true;
			} else {
				isExist = false;
			}
		} else {
			isExist = true;
		}
		return isExist;
	}
	$.ajax({
		url: '/user/isexist',
		type: 'POST',
		data: {
			username: str
		},
		async: false,
		//- cache: false,
		//- contentType: false,
		//- processData: false,
		success: function(data) {
			// console.log(data);
			if (data.code == 200) {
				cb(data);
			}
		},
		error: function() {
			console.log('pathExcel error2')
			var data = new Object();
			data.code = 500;
			data.isexist = 1;
			cb(data);
		}
	});
	return isExist;
}