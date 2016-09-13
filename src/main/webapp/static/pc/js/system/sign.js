/**
 * 登陆页面的js文件
 */
var sign = new Object();

$(function(){
	vd.init("signin_form",2,2);
	vd.init("signup_form",2,2);
});

sign.signin = function() {
	var user = iform.parse("signin_form");
    console.log(user);
	$.ajax({
		url: "signin",
		type: 'POST',
		data: user,
		async: false,
		success: function(data) {
			console.log(data);
			//cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
	console.log("signin");
}

sign.signup = function() {
//	var user = iform.parse("signup_form");
	var userName = $("#signupUserName").val();
	var pwd = $("#pwd").val();
	
	$.ajax({
		url: "signup",
		type: 'POST',
		data: {
			userName:userName,
			pwd:pwd
		},
		async: false,
		success: function(data) {
			console.log(data);
			//cb(data);
		},
		error: function() {
			console.log('pathExcel error2')
		}
	});
	console.log("signup");
}