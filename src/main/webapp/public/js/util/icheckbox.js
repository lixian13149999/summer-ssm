$(function(){
//    图标类型的复选框点击时效果切换
    $(document).on('click','[data-checkbox="checkbox"]',function(){
        //获取当前的选择框状态
        var checkType = $(this).data("checkbox-type");
        //如果是已选中状态
        if(checkType=="uncheck"){
            console.log(checkType);
            $(this).addClass("icon-checkbox-checked").removeClass("icon-checkbox").data("checkbox-type","checked");
        }else if(checkType="checked"){
            console.log(checkType);
            $(this).addClass("icon-checkbox").removeClass("icon-checkbox-checked").data("checkbox-type","uncheck");
        }
    });
})