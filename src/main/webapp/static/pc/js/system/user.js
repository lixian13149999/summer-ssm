
$(function(){
    user.navChange();//点击顶部分页菜单
    user.listMove();//移动按钮的显示隐藏、列表拖拽排序
})

var user = new Object();


//点击顶部分页菜单
user.navChange = function(){
        $(document).on("click","#commission-nav-box li",function(){
        $(this).addClass("active").siblings().removeClass("active");
    })
}
//移动按钮的显示隐藏、列表拖拽排序
user.listMove = function(){
    $(document).on('click','[data-move-icon="move"]',function(){
        if($('.move').hasClass('hide')){
            $('.move').removeClass('hide');
            $('.each-list').removeClass('nomal');
            $('#sortable').sortable({
                handle:'.icon-grip'
            });
        }else{
            $('.move').addClass('hide');
            $('.each-list').addClass('nomal');
        }
    })
}







                       
