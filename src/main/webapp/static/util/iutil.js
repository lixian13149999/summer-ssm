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
//传入值的结构{"id":"#menu_name","name":"name"},{"id":"#menu_id","name":"id"}
iutil.parseValue = function () {

	var data = new Object();

	if(v.isNull(arguments)||arguments.length<1){
		return undefined;
	}else{
		for (var i = 0; i < arguments.length; i++) {
			var item = arguments[i];
			var ivalue = $(item.id).val();
			data[item.name] = ivalue;
		};
		return data;
	}
}

iutil.parseData = function () {
	var ele;

	var data = new Object();
	if(v.isNull(arguments)||arguments.length<1){
		return undefined;
	}else{
		for (var i = 0; i < arguments.length; i++) {
			if (i == 0) {
				ele = arguments[i];
			}else{
				var item = arguments[i];

				// var iname = item.objName;
				// var ivalue = $(ele).data(item.dataName);
				data[item.objName] = $(ele).data(item.dataName);
			}
		};
		return data;
	}
}
