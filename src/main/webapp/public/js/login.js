var baseLogin = new Object();

$(document).ready(function() {
    $(document).on("focus", "[data-login-input='border']", function() {
        $(this).parent('.input-box').addClass('input_focus');
    });
    $(document).on("blur", "[data-login-input='border']", function() {
        $(this).parent('.input-box').removeClass('input_focus');
    });
    
    ivalid.base('base_login_form',baseLogin.submit,baseLogin.showError,baseLogin.valSuccess);
    
//    $(document).on('change','.error',function(){
//        console.log("aaa");
//    });
});

baseLogin.submit = function(){
    console.log("abcc");
}

baseLogin.showError = function(error,ele){
    var errorMsg = $(error).text();
    if(errorMsg!=""&&errorMsg!=undefined&&errorMsg!="undefined"){
        imessenger.error(errorMsg);
    }
}
baseLogin.valSuccess = function(){
    Messenger().hideAll();
}