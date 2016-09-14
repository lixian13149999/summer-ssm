/**
 * 登陆页面的js文件
 */
var sign = new Object();

$(function(){
	vd.init("signin_form",2,1);
	vd.init("signup_form",2,1);
});

//执行登陆操作
sign.signin = function() {
	var user = iform.parse("signin_form");
    //console.log(user);
	$.ajax({
		url: "signin",
		type: 'POST',
		dataType:"json",
		data: user,
		async: false,
		success: function(data) {
			console.log(data);
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
sign.autoSignin = function () {
	// var userObj = jQuery.parseJSON(user);

	var userName = $("#signupUserName").val();
	var pwd = $("#pwd").val();

	$.ajax({
		url: "signin",
		type: 'POST',
		dataType:"json",
		data: {
			userName:userName,
			pwd:pwd
		},
		async: false,
		success: function(data) {
			console.log(data);
			sign.signinSuccessBack(data);
			//cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
}

//执行登录有正常返回值的时候所做的操作
sign.signinSuccessBack = function (data) {
	//如果返回值为操作成功
	if(iutil.isSuccess(data)){
		//跳转到首页
		window.location.href=ctx;
	}else{
		imessenger.error("登录失败");
	}
}

//注册的相关处理
sign.signup = function() {
//	var user = iform.parse("signup_form");
	var userName = $("#signupUserName").val();
	var pwd = $("#pwd").val();
	
	$.ajax({
		url: "signup",
		type: 'POST',
		dataType:"json",
		data: {
			userName:userName,
			pwd:pwd
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
	function cb (data) {
		if(iutil.isSuccess(data)){
			var user = data.data;
			if(!v.isNull(user)){
				imessenger.success("注册成功,正在自动登录....");
				sign.autoSignin();
			}
		}
	}
}