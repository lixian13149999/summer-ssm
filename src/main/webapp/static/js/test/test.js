var test = new Object();

$(function(){
    var topViewContent = $("#view_top");
    var topViewUrl = "/pages/pc/include/top.html";
    test.pushHtml(topViewContent,topViewUrl);
    
    var bodyViewContent = $("#view_body");
    var bodyViewUrl = "/pages/pc/include/body.html";
    test.pushHtml(bodyViewContent,bodyViewUrl);
});

test.pushHtml = function(ele,url){
    $.ajax({
        url: url,
        type: 'GET',
        data: {
            // _id: user_id
        },
        async: false,
        //- cache: false,
        //- contentType: false,
        //- processData: false,
        success: function (data) {
//            console.log(data);
            $(ele).html(data);
            //				cb(data);
        },
        error: function () {
            console.log('加载页面出错');
        }
    });
}