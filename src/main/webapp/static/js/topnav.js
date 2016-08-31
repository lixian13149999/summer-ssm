$(function(){
    $(document).on("click","#search_box",function(){
        if($("#search_input").css("display")=="none"){
        /*如果为none则显示block*/
            $("#search_input").css("display","block");
            $(this).css("background","#0087b4");
            $(this).css("color","#000");
        }else if($("#search_input").css("display")=="block"){
            $("#search_input").css("display","none");
            $(this).css("background","#09c");
            $(this).css("color","#fff");
        }
        console.log("aa");
    });
//    /***********************以上是搜索框的显示与隐藏**********************************/
    $(document).on("mouseover","#mobileapp_box",function(){
      if($("#downloadapp").css("display")=="none"){
         $("#downloadapp").css("display","block");
          console.log("bb");
      }
//        else{
//         $("#downloadapp").css("display","none");
//      } 
    });
    $(document).on("mouseout","#mobileapp_box",function(){
      if($("#downloadapp").css("display")=="block"){
         $("#downloadapp").css("display","none");
          console.log("bb");
      }
//        else{
//         $("#downloadapp").css("display","block");
//      } 
    });
//    /***************************以上是手机app下载的显示与隐藏**************************/
    $(document).on("click","#infor_box",function(){
        if($("#infor_item").css("display")=="none"){
            $("#infor_item").css("display","block");
        }else{
            $("#infor_item").css("display","none");
        }
    
    });
//    /************************以上是站内信息的显示与隐藏**********************************/
//    $(document).on("click","#order_btn",function(){
//        if($("#order_down").css("display")=="none"){
//            $("#order_down").css("display","block");
//            $(this).css("background","#fff");
//            $(this).css("color","#000");
//            $("#sanjiao").removeClass("xiaosanjiaodown");
//            $("#sanjiao").addClass("xiaosanjiao");
//            $("#sanjiao").html("&#xe609;");
//        }else{
//            $("#order_down").css("display","none");
//            $(this).css("background","#09c");
//            $(this).css("color","#fff");
//            $("#sanjiao").removeClass("xiaosanjiao");
//            $("#sanjiao").addClass("xiaosanjiaodown");
//            $("#sanjiao").html("&#xe600;");                              
//        }
//    });
///****************************以上是工单与服务的显示与隐藏***********************************/
//    $(document).on("click","#helpdocument_btn",function(){
//        if($("#helpdocument_down").css("display")=="none"){
//            $("#helpdocument_down").css("display","block");
//            $(this).css("background","#fff");
//            $(this).css("color","#000");
//            $("#sanjiao").removeClass("xiaosanjiaodown");
//            $("#sanjiao").addClass("xiaosanjiao");
//            $("#sanjiao").html("&#xe609;");
//            console.log("ab");
//        }else{
//            $("#helpdocument_down").css("display","none");
//            $(this).css("background","#09c");
//            $(this).css("color","#fff");
//            $("#sanjiao").removeClass("xiaosanjiao");
//            $("#sanjiao").addClass("xiaosanjiaodown");
//            $("#sanjiao").html("&#xe600;");
//        
//        }    
//    });
//    $(document).on("blur","#helpdocument_btn",function(){
////        $(this).unbind("click");
//          $("#helpdocument_down").css("display","none");
//           console.log("cc");
//    });
////    $("#helpdocument_btn").blur(function(){
////        $("#helpdocument_down").css("display","none");
////        console.log("ceshi");
////    });
//    /****************************以上是帮助与文档的显示与隐藏*********************************/
//    $(document).on("click","#userinfor_btn",function(){
//            if($("#user_down").css("display")=="none"){
//                $("#user_down").css("display","block");
//                $(this).css("background","#fff");
//                $(this).css("color","#000");
//                $("#sanjiao").removeClass("xiaosanjiaodown");
//                $("#sanjiao").addClass("xiaosanjiao");
//                $("#sanjiao").html("&#xe609;");
//            }else{
//                $("#user_down").css("display","none");
//                $(this).css("background","#09c");
//                $(this).css("color","#fff");
//                $("#sanjiao").removeClass("xiaosanjiao");
//                $("#sanjiao").addClass("xiaosanjiaodown");
//                $("#sanjiao").html("&#xe609;");
//            }
//    });
    /******************************测试**********************************************************/
    $(document).on("click","[data-toggle-show='order_item']",function(){
       
        var ele=$(this).parent().find("[data-fresh-target='show']"); //取到要显示的div
   
        if(ele.css("display")=="none"){
                //让其他同名的先不显示
                $("[data-fresh-target='show']").css("display","none");
                $("[data-toggle-show='order_item']").css("background","#09c");
                $("[data-toggle-show='order_item']").css("color","#fff");
                $("#sanjiao").removeClass("xiaosanjiao");
                $("#sanjiao").addClass("xiaosanjiaodown");
                $("#sanjiao").html("&#xe609;");
                //显示当前的同时对其他的背景色和按钮颜色进行改变
                ele.css("display","block");
                $(this).css("background","#fff");
                $(this).css("color","#000");
                $("#sanjiao").removeClass("xiaosanjiaodown");
                $("#sanjiao").addClass("xiaosanjiao");
                $("#sanjiao").html("&#xe609;");
            }else {
                $("[data-fresh-target='show']").css("display","none");
                console.log("testtest");
                $("[data-toggle-show='order_item']").css("background","#09c");
                $("[data-toggle-show='order_item']").css("color","#fff");
                $("#sanjiao").removeClass("xiaosanjiao");
                $("#sanjiao").addClass("xiaosanjiaodown");
                $("#sanjiao").html("&#xe609;");
            }
        
        
    });
    /******************************通过mouseover来改变背景色******************************************/
    $(document).on("mouseover","[data-refresh='color']",function(){
        $(this).css("background","#0087b4");
    });
    $(document).on("mouseout","[data-refresh='color']",function(){
        $(this).css("background","#09c");
    });
    /*******************************二维码的显示与隐藏*************************************************/
    
    $(document).on("mouseover","[data-show='app']",function(){
        $(this).css("display","block");
    });
    $(document).on("mouseout","[data-show='app']",function(){
        $(this).css("display","none");
    });
})