var userid = sessionStorage.getItem("userid");
$(function () {
    initData();
    initEdit();
    initCusNum();
});

function initEdit() {
    //editables on first profile page
    $.fn.editable.defaults.mode = 'inline';
    $.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>'+
        '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';


    //text editable
    // $('#username')
    //     .editable({
    //         type: 'text',
    //         name: 'username'
    //     });
    $('#password')
        .editable({
            type: 'text',
            name: 'password',
            emptytext: "请输入密码",
            value: $("#password").html(),
            validate: function (value) {
                if (!$.trim(value)) {
                    return '不能为空';
                }
            },
            url: function (params) {
                var obt = {"empid":userid,"password":params.value};
                console.log("empid--->"+userid+"password--->"+params.value);
                $.ajax({
                    type: 'POST',
                    url: "/emp/update",
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify(obt),
                    dataType: 'JSON',
                    success: function (data, textStatus, jqXHR) {
                        toastr.success("修改成功");
                    },
                    error: function () { toastr.error("修改失败");}
                });
            }
        });
    // $('#tel')
    //     .editable({
    //         type: 'text',
    //         name: 'tel'
    //     });
    //
    // $('#email')
    //     .editable({
    //         type: 'text',
    //         name: 'email'
    //     });
}

function initData() {
    $.get(
        "emp/selectone",
        {"empid":userid},
        function (res) {
            $("#empid").html(res.empid);
            $("#username").html(res.username);
            $("#password").html(res.password);
            $("#tel").html(res.tel);
            $("#title_name").html(res.name);
            $("#email").html(res.email);
            $("#rolename").html(res.roleName);
        },"json"
    )
}

function initCusNum() {
    $.get(
        "customer/selNum",
        {"empid":userid},
        function (res) {
            $("#cusnum").html(res);
        },"json"
    )
    
}


