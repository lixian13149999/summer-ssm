$(function(){
    
/*******************************获取浏览器的宽度**********************************/  
var width = $(window).width();
$(".body_main").css("width",width-190+"px");  
/*******************************菜单栏顶部点击展开收缩**********************************/  
$(document).on("click",".left_menu_flex",function(){
    if($(".left_menu_flex .to_right").css("display")=="none"){
        $(".left_menu_flex .to_right").css("display","block");
        $(".left_menu_flex .to_left").css("display","none");
        $(".content").css("display","none");
        $(".left_main").css("width","50px");
        $(".left_menu").css("width","67px");
        $(".left_menu_right").css("left","50px");
        if($(".left_menu_right").css("width") == "0px"){
            $(".body_main").css("left","60px");
            $(".body_main").css("width",width-60+"px");  
        }else if($(".left_menu_right").css("width") == "180px"){
            $(".body_main").css("left","240px");
            $(".body_main").css("width",width-240+"px");
        }
    }else if($(".left_menu_flex .to_right").css("display")=="block"){
        $(".left_menu_flex .to_right").css("display","none");
        $(".left_menu_flex .to_left").css("display","block");
        $(".content").css("display","inline-block");
        $(".left_main").css("width","180px");
        $(".left_menu").css("width","197px");
        $(".left_menu_right").css("left","180px");
        if($(".left_menu_right").css("width") == "0px"){
            $(".body_main").css("left","190px");
            $(".body_main").css("width",width-190+"px");
        }else if($(".left_menu_right").css("width") == "180px"){
            $(".body_main").css("left","370px");
            $(".body_main").css("width",width-370+"px");
        }
    }
})

/***********************一级菜单标题前图标变化**************************/ 
$(document).on("click",".panel-head",function(){
    if($(".to_up",$(this)).css("display") == "none"){
        $(".to_down",$(this)).css("display","none");
        $(".to_up",$(this)).css("display","block");
        $(this).css("background-color","#22282e");
    }else if($(".to_up",$(this)).css("display") == "block"){
                $(".to_down",$(this)).css("display","block");
                $(".to_up",$(this)).css("display","none");
                $(this).css("background-color","#37424f");
    }
})

/***********************菜单栏二级菜单点击右侧菜单栏展开**************************/ 
$(document).on("click",".collapse_option",function(){
    $(".left_menu_flex .to_right").css("display","block");
    $(".left_menu_flex .to_left").css("display","none");
    $(".content").css("display","none");
    $(".left_main").css("width","50px");
    $(".left_menu").css("width","67px");
    $(".menu_right_iconl").html("&#xe603;");
    $(".menu_right_iconl").css("top",height);
    $(".menu_right_iconr").css("display","none");
    $(".left_menu_right").css("left","50px");
    $(".left_menu_right").width("0");
    $(".left_menu_right").css("width","180px");
    $(".collapse_option").removeClass("on");
    $(this).addClass("on");
    $(".body_main").css("left","240px"); 
    $(".body_main").css("width",width-240+"px");
}) 

/***********************三级菜单栏图标点击菜单收缩**************************/ 

var height = $(window).height()/2 +　"px";
    
$(document).on("click",".menu_right_iconl",function(){
    $(".menu_right_iconl").html("");
    $(".left_menu_right").css("width","0");
    $(".menu_right_iconr").css({"display":"block","top":height});
    $(".menu_right_iconr").html("&#xe602;");
    if($(".left_main").css("width") == "50px"){
        $(".body_main").css("left","60px");
        $(".body_main").css("width",width-60+"px");
    }
    else if($(".left_main").css("width") == "180px"){
        $(".body_main").css("left","190px");
        $(".body_main").css("width",width-190+"px");
    }
    
})

$(document).on("click",".menu_right_iconr",function(){
    $(".menu_right_iconl").html("&#xe603;");
    $(".left_menu_right").css("width","180px");
    $(this).css("display","none");
    if($(".left_main").css("width") == "50px"){
        $(".body_main").css("left","240px");
        $(".body_main").css("width",width-240+"px");
    }
    else if($(".left_main").css("width") == "180px"){
        $(".body_main").css("left","370px");
        $(".body_main").css("width",width-370+"px");
    }
})

/***********************二级菜单鼠标移入移出小提示**************************/ 
$(document).on("mousemove",".left_menu .icon",function(e){
    if($(".left_menu").css("width") == "67px"){
    var parent = $(this).parent();
    var text = $(".content",$(parent)).html();
    $("#tips").css("display","none")
    var top = e.pageY - 20 + "px";
    var left = e.pageX + 10 + "px";
    $("#tips").html(text);
    $("#tips").show().css({"top":top,"left":left});}
})

$(document).on("mouseleave",".left_menu .icon",function(){
    $("#tips").css("display","none");
})

})