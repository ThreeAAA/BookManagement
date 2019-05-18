//验证id账号
$.extend($.fn.validatebox.defaults.rules, {
    idLength : {
        validator : function (value, param) {
            return value.length >= param[0] && value.length<= param[1] && /^\d+(\.\d+)?$/i.test(value);
        },
        message : '请输入长度大于{0}小于{1}的纯数字'
    },

    //验证重复输入的密码
    double_psd : {
        //本文本框内容，传入值
        validator : function (value, param) {
            return value == $(param[0]).val();
        },
        message : "密码不一致"
    },

    //验证密码格式
    ver_psd: {
        validator: function(value){ //数字字符下划线
            return /^\w+$/.test(value);
        },
        message: '请不要输入其他字符，只能输入6-20位数字、字符、下划线'
    },
    //密码长度
    ver_psd:{
        validator:function (value) {
            return value.length<=20 && value.length>=6;
        },
        message:'请处在指定长度内，只能输入6-20位数字、字符、下划线'
    }
});