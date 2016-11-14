/**
 * 总体的调用主控制器,
 *
 */
var imain = new Object();

$(function(){
    //加入头部弹出框的监听方法(微信/个人信息)
    ipopover.listenerPromptCont();
    //加入beaut输入框的监听方法(输入框中属性提示上移效果)
    iform.listenerFormControl();
    
    imain.changePageTitleStyle();

    //监听加载页面的方法(通过栏目的点击,加载一个新的页面)
    imain.openPage();
})

imain.changePageTitleStyle = function(){
    $(document).on('click','.page-second-title-content .tab-navs .tab-nav',function(){
        $('.page-second-title-content .tab-navs .tab-nav').each(function(index,ele){
            $(ele).removeClass('checked');
        });
        $(this).addClass('checked');
    });
}

imain.openPage = function () {
    $(document).on('click','[data-menu-side="item"]',function () {
        var href = $(this).data("body-mapping");

        $.ajax({
            url: ctx + href,
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

        console.log(href);
        console.log("打开页面");
    })
}

imain.testAddMenuList = function () {
    $.ajax({
        url: ctx + "menu?b",
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
