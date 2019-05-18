$(function () {
    var sign = 1;  //标志，用来判断是添加还是修改

    $('#boos_table1').datagrid({
        fitColumns:true,
        singleSelect:true,
        rownumbers:true,
        fit:true,
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
        url:'./servlet', //数据来源
        // url:"config/bookinfo.json", //数据来源
        toolbar:$('#tb1'),
        pagination:true
    });
    // getBookInfo();
    function getname(value,row,index) { //过滤空值，先就这样把
        return value ? value:value;
    }

    //添加图书窗口
    $('#add_book_win').dialog({
        height:510,
        width:510,
        modal:true,
        buttons:[{
            text:'保存',
            handler:function(){
                //此处应添加验证代码
                if (sign == 1) {  //分别是添加和修改
                    var book_data = $("#add_book_win_form").serialize()+"&action=add";
                }else {
                    var book_data = $("#add_book_win_form").serialize()+"&action=modify";
                }

                $.post("./servlet", book_data,submitBook,'json');
            }
        },{
            text:'取消',
            handler:function () {
                $('#add_book_win').dialog('close');

            }
        }]
    });
    $('#add_book_win').dialog('close');



    //添加按钮
    $('#add-book').bind('click',function () {
        sign = 1;
        $("#add_book_win_form input[ name='book_id' ] ").attr("disabled",false);
        $('#add_book_win').dialog({title:'添加图书'});
        $("#add_book_win_form input").val(""); //清除输入框内容
        $('#add_book_win').dialog('open');
    });
    //刷新按钮
    $('#reload-book').bind('click',function () {
        // getBookInfo();
        $('#boos_table1').datagrid("load");
    });

    //修改按钮
    $('#modify-book').bind('click',function () {
        sign = 2;
        var selections_data = $('#boos_table1').datagrid('getSelections');
        // console.log(selections_data[0]);
        if (selections_data.length){ //判断是否选中
            //把选中的行信息填入
            $("#add_book_win_form input[ name='book_id' ] ").val(selections_data[0].id);
            $("#add_book_win_form input[ name='book_name' ] ").val(selections_data[0].title);
            $("#add_book_win_form input[ name='book_author' ] ").val(selections_data[0].author);
            $("#add_book_win_form input[ name='book_publish' ] ").val(selections_data[0].publisher);
            $("#add_book_win_form input[ name='book_categoty' ] ").val(selections_data[0].category);
            $("#add_book_win_form input[ name='book_time' ] ").val(selections_data[0].setTime);
            $("#add_book_win_form input[ name='book_all_amount' ] ").val(selections_data[0].totalBook);
            $("#add_book_win_form input[ name='book_brw_amount' ] ").val(selections_data[0].surplus);
            $("#add_book_win_form input[ name='book_id' ] ").attr("disabled",true);
            $('#add_book_win').dialog({title:'修改图书'});
            $('#add_book_win').dialog('open');

        }else {
            $.messager.confirm('提示','请选中某一行再进行修改');
        }
    });

});

function submitBook(data) {
    $.messager.confirm('添加消息',data.msg);
}