var input = new Object();

//方法引用自iverify.js
//验证用户名是否为邮箱
input.checkUserName = function(){
    var UserName = $("#username").val();
    var isnull = v.isNull(UserName);
    var isemail = v.isEmail(UserName);
    return !isnull&&isemail ? true : false ;
}
//验证密码是否大于6位
input.checkPassword = function(){
    var PassWord = $("#password").val();
    var isnull = v.isNull(PassWord);
    var istooshort = v.shortThan(PassWord,6);
    return isnull||istooshort ? false : true;
}
//验证密码是否一致
input.checkEqual = function(){
    var PassWord = $("#password").val();
    var PassWordAgain = $("#password-again").val();
    var isequal = v.isSame(PassWord,PassWordAgain);
    return isequal;
}
//判断输入框是否都满足条件
input.verfyDo = function(a,b,c){
    if(a&b&c){
        $('.mobile-button').removeAttr('disabled');
    }else{
        $('.mobile-button').attr('disabled',true);
    }
}