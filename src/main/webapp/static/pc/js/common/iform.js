/**
 * 用于表单部分公用js的处理
 */
var iform = new Object();

iform.listenerFormControl = function(){
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

iform.parse = function(formId){
    var backData = new Object();
    $("#"+formId+" input[name]").each(function(index,ele){
        var name = $(ele).attr('name');
        var val = $(ele).val();
        
        var fun = 'backData.'+name+' = val';
        eval(fun);
    });
    return backData;
}