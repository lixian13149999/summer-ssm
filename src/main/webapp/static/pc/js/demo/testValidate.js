function formAction() {
    console.log("formAction");
}
//        $().ready(function() {
//            console.log("页面校验执行了初始化");
//            $("#signupForm").validate();
//        });
$(function () {
    var testValidate = $("#signupForm").validate({
        debug: true, //调试模式取消submit的默认提交功能   
        //errorClass: "label.error", //默认为错误的样式类为：error   
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false,
        submitHandler: function (form) { //表单提交句柄,为一回调函数，带一个参数：form   
            // alert("提交表单");   
            console.log(form);
            form.submit(); //提交表单   
        },
        errorPlacement: function (error, element) {
            console.log(error);
            console.log(element);
        },
        rules: {
            username: {
                required: true,
                remote: {
                    url: "userIsExist", //后台处理程序
                    type: "get", //数据发送方式
                    dataType: "json", //接受数据格式   
                    data: { //要传递的数据
                        username: function () {
                            return $("#username").val();
                        }
                    }
                }
            },
            password: {
                required: true,
                rangelength: [3, 10]
            }
        },
        messages: {
            username: {
                required: "必填",
                remote: "ajax验证出现问题"
            },
            password: {
                required: "不能为空",
                rangelength: "长度限制"
            }
        }
    });
});