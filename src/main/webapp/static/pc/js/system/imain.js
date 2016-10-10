/**
 * 总体的调用主控制器,
 *
 */
var imain = new Object();

$(function(){
    //加入头部弹出框的监听方法
    ipopover.listenerPromptCont();
    //加入beaut输入框的监听方法
    iform.listenerFormControl();
    
    imain.changePageTitleStyle();
})

imain.changePageTitleStyle = function(){
    $(document).on('click','.page-second-title-content .tab-navs .tab-nav',function(){
        $('.page-second-title-content .tab-navs .tab-nav').each(function(index,ele){
            $(ele).removeClass('checked');
        });
        $(this).addClass('checked');
    });
}

imain.testAddMenuList = function () {
    $.ajax({
        url: ctx + "menu/list?b",
        type: 'GET',
        dataType: "json",
        data: {},
        async: false,
        success: function(data) {
            if(iutil.isSuccess(data)){
                cb(data);
            }
            
        },
        error: function() {
            console.log('pathExcel error2')
        }
    });

    function cb (backData) {
        $("#product_main_body").html(backData.data);
    }
}