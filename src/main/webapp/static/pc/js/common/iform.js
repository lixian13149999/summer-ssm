/**
 * 用于表单部分公用js的处理
 */
var ifrom = new Object();

ifrom.listenerFormControl = function(){
    $(document).on('focus','[data-form-control="beaut-control"]',function(){
        var ele = $(this);
        ele.prev().addClass("active");
//        ele.prev().addClass("aaa");
    });
    
    $(document).on('blur','[data-form-control="beaut-control"]',function(){
        var ele = $(this);
//        console.log(v.isNull(ele.val()));
        if(v.isNull(ele.val())){
            ele.prev().removeClass("active");
        }
    });
}