var demo = new Object();

$(function(){
    demo.addHtml('/static/include/top.htm','top');
})

demo.addHtml = function(sourceUrl, targetId) {
    $.ajax({
        url: sourceUrl,
        type: 'GET',
        data: {
            // _id: user_id
        },
        async: false,
        //- cache: false,
        //- contentType: false,
        //- processData: false,
        success: function(data) {
            //				 console.log(data);
            $("#" + targetId).html(data);
            //				cb(data);
        },
        error: function() {
            console.log('加载页面出错');
        }
    });
}