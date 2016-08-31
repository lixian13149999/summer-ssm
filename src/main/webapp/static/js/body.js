//点击左侧菜单顶部，页面样式变化
$(document).on("click","[data-sidebar-fold='flex']",function(){
    if($(".iconright",$(this)).hasClass("invisible")){
        change();
    }else if($(".iconleft",$(this)).hasClass("invisible")){
        $(".iconleft",$(this)).removeClass("invisible").siblings("i").addClass("invisible");
        $(".viewFramework-sidebar-mini").removeClass("viewFramework-sidebar-mini").addClass("viewFramework-sidebar-full");
        $(document).on("mouseover","[data-sidebar-tips='tips']",function(){
            $(".sidebar-tips").css("display","none");
        })
    }
})

//点击一级菜单，页面样式变化
$(document).on("click","[data-sider-title='collapse']",function(){
    if($(".iconup",$(this)).hasClass("hide")){
        $(".iconup",$(this)).removeClass("hide").siblings("i").addClass("hide");
        $(this).addClass("change");
    }else if($(".icon",$(this)).hasClass("hide")){
        $(".icon",$(this)).removeClass("hide").siblings("i").addClass("hide");
        $(this).removeClass("change");
    }
})

//点击二级菜单，页面样式变化
$(document).on("click","[data-sidebar-item='item']",function(){
    $(".product-navbar-container").css("width","0");
    $(".viewFramework-product-body").css({left:"0"});
    $(".product-navbar-container").animate({width:$(".viewFramework-product-navbar").width()-20});
    $(".viewFramework-product-body").animate({left:$(".viewFramework-product-navbar").width()-20});
    $("[data-sidebar-item='item']").removeClass("on");
    $(this).addClass("on").removeClass("over");
    $(".viewFramework-product .iconright").addClass("conceal");
    change();
    $(".viewFramework-product .iconleft").removeClass("conceal").css("left","0").animate({left:$(".viewFramework-product-navbar").width()-35});
})

//二级菜单移入移出样式变化
$(document).on("mouseover","[data-sidebar-item='item']",function(){
    if($(this).hasClass("on")){
        return false;
    }
    else{
        $(this).addClass("over");
    }
})

$(document).on("mouseout","[data-sidebar-item='item']",function(){
   $(this).removeClass("over");
})

//点击三级菜单内部图标，页面样式变化
$(document).on("click","[data-product-iconleft='iconleft']",function(){
    $(".product-navbar-container").animate({width:"0"});
    $(".viewFramework-product-body").animate({left:"0"});
    $(".viewFramework-product .iconright").removeClass("conceal").css("left",$(".viewFramework-product-navbar").width()-20+"px").animate({left:"0"});
    $(this).addClass("conceal");    
})

$(document).on("click","[data-product-iconright='iconright']",function(){
    $(".product-navbar-container").animate({width:$(".viewFramework-product-navbar").width()-20});
    $(".viewFramework-product-body").animate({left:$(".viewFramework-product-navbar").width()-20});
    $(this).addClass("conceal");
    $(".viewFramework-product .iconleft").removeClass("conceal").css("left","-15px").animate({left:$(".viewFramework-product-navbar").width()-35+"px"});
})

//三级菜单展开图标初始化及页面跟随样式变化
$(function(){
    var height = $(window).height()/2 +"px";
    $(".viewFramework-product .iconleft").css("top",height);
    $(".viewFramework-product .iconright").css("top",height);
})

var height=null;
$(window).resize(function(){
    height = $(window).height()/2;
    $(".viewFramework-product .iconleft").css("top",height);
    $(".viewFramework-product .iconright").css("top",height);
})

//手风琴菜单收缩时样式变化
var change = function(){
    $(".sidebar-fold .iconright").removeClass("invisible").siblings("i").addClass("invisible");
    $(".viewFramework-sidebar-full").removeClass("viewFramework-sidebar-full").addClass("viewFramework-sidebar-mini");
    //手风琴菜单小提示样式变化
    $(document).on("mouseover","[data-sidebar-tips='tips']",function(){
        var content=$(this).nextAll("span").html();
        var top=($(this).offset().top)-($("#view_top").height())+"px";
        $(".sidebar-tips").css({"display":"block","top":top}).html(content);
    })
    $(document).on("mouseout","[data-sidebar-tips='tips']",function(){
        $(".sidebar-tips").css("display","none");
    })
}
//三级菜单内部列表样式变化
$(document).on("click","[data-list-content='content']",function(){
    $(this).addClass("listchange").siblings().removeClass("listchange");
})

$(document).on("mouseover","[data-list-content='content']",function(){
    if($(this).hasClass("listchange")){
        return false;
    }else{
        $(this).addClass("listover");
    }
})

$(document).on("mouseout","[data-list-content='content']",function(){
   $(this).removeClass("listover");
})


