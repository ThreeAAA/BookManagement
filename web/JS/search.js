$(function () {
    $('#boos_table').datagrid({
        // title:"书籍列表",
        fitColumns:true,
        singleSelect:true,
        rownumbers:true,
        fit:true,
        toolbar:$('#tb'),
        pagination:true,
        columns:[[
            {field:"id",title:"图书编号",width:1},
            {field:"title",title:"书名",width:1},
            {field:"author",title:"作者",width:1,formatter:getname},
            {field:"publisher",title:"出版社",width:1,formatter:getname},
            {field:"category",title:"类别",width:1,formatter:getname},
            {field:"setTime",title:"入馆时间",width:1},
            {field:"totalBook",title:"馆藏数",width:1},
            {field:"surplus",title:"可借数",width:1}
        ]],

        url:"./servlet" //数据来源
    });

    function getname(value,row,index) { //过滤空值，先就这样把
        return value ? value:value;
    }

    //借阅书籍
    $('#add').bind('click',function () {
        var selectData = $('#boos_table').datagrid('getSelections');
        if (selectData.length){
            $.messager.confirm(
                "确认窗口",
                "确认借阅此书",
                function (r) {  //传入按钮值true或false
                    if (r){ //此处添加借阅相关操作
                        var postData = "book_id="+selectData[0].id+"&"+/reader_id=\d+/.exec(document.cookie)[0];
                        postData = postData+"&action=borrow";
                        console.log(postData);
                        $.post("./servletReader",postData,msgWin,"json");
                    }
                }
            )
        } else{
            $.messager.confirm('提示','请选择借阅书籍');
        }
    });

    //刷新
    $('#reload').bind('click',function () {
        $('#boos_table').datagrid("load");
    });
    //搜索
    $('#search').searchbox({
        searcher:search
    });

});

function msgWin(data) {
    $.messager.confirm("消息",data.msg);
}
//向服务器发送搜索图书的请求
function search(value, name) {
    //输入值，选择值
    var search_data = "info="+name+"&content="+value;
    $.post("./searchServlet",search_data,function writeData(datas,status) {
        if (datas.length<3){
            datas  = '{"total":1,"rows":[{"id":"查询无结果","title":"","author":"","publisher":"","category":"","setTime":"","totalBook":"","surplus":""}]}'
        }
        var json = $.parseJSON(datas); //json字符串转成json对象
        $('#boos_table').datagrid('loadData',json);  //这个方法只接受json对象
    },"text");
}