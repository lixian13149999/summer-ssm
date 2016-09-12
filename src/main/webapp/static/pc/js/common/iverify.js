/**
 * 自己写的一些验证字段的文件
 * 用于对val值的验证
 */
var v = new Object();

//检测是否为空
v.isNull = function(str) {
	// str = str.trim();
	return str == '' || str == undefined || str == NaN ? true : false;
}

//检测是否有中文
v.hasChain = function(str) {
	var reg = new RegExp("[\u4e00-\u9fa5]");
	console.log(reg.test(str));
	return reg.test(str);
}

//是否大于...
v.longThan = function(str, length) {
	return str.length > length ? true : false;
}

// 是否小于...
v.shortThan = function(str, length) {
	return str.length < length ? true : false;
}

// 是否包含特殊字符
v.hasSpecialChar = function(str) {
	var reg = new RegExp("[^a-zA-Z0-9`~!@#$%^&*()\-=_+\{\};:\'\"|/*,.<>?]");
	return reg.test(str);
}

// 两个值是否相等
v.isSame = function(str1, str2) {
	return str1 == str2;
}

// 是否是数字
v.isNumber = function(str) {
	var reg = new RegExp("^[0-9]+([.]{1}[0-9]+){0,1}$");
	return reg.test(str);
}

//是否是整数
v.isWholeNumber = function(str) {
	var reg = new RegExp("^[0-9]*$");
	return reg.test(str);
}

//验证是否是手机号
v.isMobile = function(str) {
	// var reg = new RegExp("/^1\d{10}$/");
	var reg = /^1\d{10}$/;
	// console.log(reg.test(str));
	return reg.test(str);
}

//验证是否是邮箱
v.isEmail = function(str) {
	// var reg = new RegExp("^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i");
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	// console.log(reg.test(str));
	return reg.test(str);
}


//显示错误提示效果
v.toError = function(ele, msg) {
	ele.parent('div').addClass('has-error');
	ele.nextAll('span').first().text(msg);
}

//消除错误提示效果
v.toSuccess = function(ele, msg) {
	ele.parent('div').removeClass('has-error');
	ele.nextAll('span').first().text(msg);
}