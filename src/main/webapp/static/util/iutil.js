var iutil = new Object();

iutil.isSuccess = function (data) {
	if(data.code==200&&data.type==1){
		return true;
	}
	return false;
}

//此方法用于封装表单中的数据
//传入值不定量
//返回一个封装好的对象
iutil.parseValue = function () {
	
}