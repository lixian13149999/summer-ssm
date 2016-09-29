var iutil = new Object();

iutil.isSuccess = function (data) {
	if(data.code==200&&data.type==1){
		return true;
	}
	return false;
}