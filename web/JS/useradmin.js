$(function () {
    var sign = 1; //1添加2修改
    //用户列表
    $('#users_table').datagrid({
        title: "用户列表",
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        fit: true,
        toolbar: $('#users-tb'),
        pagination: true,
        columns:[[
            {field:"id",title:"读者编号",width:1},
            {field:"name",title:"姓名",width:1},
            {field:"sex",title:"性别",width:1},
            {field:"surplus_book",title:"能借书数",width:1},
            {field:"loan_book",title:"借书数",width:1}
        ]],
        url:"./servlet2"
    });
    $('#add_user_win').dialog({
        height:510,
        width:510,
        modal:true,
        buttons:[{
            text:'保存',
            handler:function(){
                //此处应添加验证代码
                if (sign == 1) {  //分别是添加和修改
                    var user_post_data = $("#add_user_win_form").serialize()+"&action=addUser&form=userAdmin";
                }else {
                    var user_post_data = $("#add_user_win_form").serialize()+"&action=modifyUser&form=userAdmin";
                }

                $.post("./servlet2", user_post_data,submitUser,'json');
            }
        },{
            text:'取消',
            handler:function () {
                $('#add_user_win').dialog('close');

            }
        }]
    });

    $('#add_user_win').dialog('close');

    //新增用户按钮
    $('#new-user-win').bind('click', function(){
        sign =1;
        $("#add_user_win_form input[name='reader_id' ] ").attr("disabled",false);
        $("#add_user_win").dialog({title:'添加用户'});
        $("#add_user_win_form input").val("");
        $("#add_user_win").dialog("open")
    });
    //修改按钮
    $('#edit-user').bind('click',function () {
        sign=2;
        var select_data = $('#users_table').datagrid('getSelections');
        if (select_data.length) {
            $("#add_user_win_form input[name='reader_id']").val(select_data[0].id);
            $("#add_user_win_form input[name='reader_name']").val(select_data[0].name);
            $("#add_user_win_form input[name='reader_sex']").val(select_data[0].sex);
            $("#add_user_win_form input[name='reader_can_borrow']").val(select_data[0].surplus_book);
            $("#add_user_win_form input[name='reader_borrows']").val(select_data[0].loan_book);
            $("#add_user_win_form input[name='reader_id' ] ").attr("disabled",true);
            $('#add_user_win').dialog({title:'用户信息修改'});
            $('#add_user_win').dialog('open');

        }else {
            $.messager.confirm('提示','请选中某一行再进行修改');
        }
    });

    //刷新按钮
    $('#reload-user').bind('click',function () {
        $('#users_table').datagrid('load');
    });

    $('#password-clean').bind('click',function () {
        //添加重置密码操作
        var psf_clean_data = "action=cleanPassword&form=userAdmin";
        $.post("./servlet2", psf_clean_data,submitUser,'json');
    });
    $('#search-user').searchbox({
        searcher:search
    })

});

function submitUser(data) {
    $.messager.confirm('消息',data.msg);
}

function search(value, name) {
    //输入值，选择值
    var search_data = "info="+name+"&content="+value;
    $.post("./searchServlet",search_data,function writeData(datas,status) {
        if (datas.length<3){
            datas  = '{"total":1,"rows":[{"id":"查询无结果","title":"","author":"","publisher":"","category":"","setTime":"","totalBook":"","surplus":""}]}'
        }
        var json = $.parseJSON(datas); //json字符串转成json对象
        $('#users_table').datagrid('loadData',json);  //这个方法只接受json对象
    },"text");
}