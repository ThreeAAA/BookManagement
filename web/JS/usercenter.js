$(function () {
    $("#pg").propertygrid({
        url: './config/usercenter.json',
        showGroup: false,
        width:500,
        tool:"#add"
    });

    $("#okok").bind("click",function () {
            $.messager.confirm(
                "确认窗口",
                "确认修改吗？",
                function (r) {
                    if (r){
                        //此处修改操作，更新数据库，重新请求修改后的数据，最后显示出来
                        alert("修改成功");
                    }
                }
            )
        })
});
