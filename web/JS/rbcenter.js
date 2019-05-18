$(function () {
    $('#books').datagrid({
        fitColumns:true,
        singleSelect:true,
        rownumbers:true,
        fit:true,
        pagination:true,
        columns:[[
            {field:"id",title:"图书编号",width:1},
            {field:"title",title:"书名",width:1},
            {field:"author",title:"作者",width:1,formatter:getname},
            {field:"publisher",title:"出版社",width:1,formatter:getname},
            {field:"category",title:"类别",width:1,formatter:getname},
            {field:"getTime",title:"借阅时间",width:1},
            {field:"returnTime",title:"应还时间",width:1}
        ]],
        url:"./servletReader", //数据来源
        toolbar:$('#tbs')
    });

    function getname(value,row,index) { //过滤空值，先就这样把
        return value ? value:value;
    }

    $('#returns').bind('click',function () {
        var selectData = $('#books').datagrid('getSelections');
        if (selectData.length) {
            $.messager.confirm(
                "确认窗口",
                "确认归还此书",
                function (r) {
                    if (r){
                        //此处添加还书相关操作
                        var postData = "book_id="+selectData[0].id;
                        postData = postData+"&action=returnBook";
                        console.log(postData);
                        $.post("./servletReader",postData,msgWin,"json");
                        alert("还书成功");
                        $('#books').datagrid('load');
                    }
                }
            )
        }else{
        $.messager.confirm('提示','请选择归还书籍');
    }

    });
    //刷新
    $('#reloads').bind('click',function () {
        $('#books').datagrid("load");
    });

});

function msgWin(data) {
    if (!data.success){
        $.messager.confirm("消息",data.msg);
    }
}