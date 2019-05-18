$(function () {
    $("#mywest").tree({
        url:"./config/userInfo.json",
        onClick:function (value) {
            console.log(value);
            if($("#mycenter").tabs('exists',value.text)){
                //存在
                $("#mycenter").tabs("select",value.text);
            }else{
                //不存在创建
                $("#mycenter").tabs('add',{
                    title:value.text,
                    closable:true,
                    href:value.attributes.url//只能加载body里内容
                });
            }

        }
    });
    $("#mycenter").tabs({
        fit:true

    })
});