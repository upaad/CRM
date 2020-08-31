$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    getSelectData();
});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_departments').bootstrapTable({
            url: '/emp/select',                 //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 15, 20],              //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            contentType: 'application/json',
            dataType: 'json',
            responseHandler: function (res) {
                return {
                    "total":res.total,
                    "rows":res.rows
                }
            },
            columns: [{
                checkbox: true
            }, {
                field: 'empid',
                title: '员工编号',
                width: 100,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'username',
                title: '用户登录名',
                width: 150,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'password',
                title: '密码',
                width: 150,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'tel',
                title: '联系电话',
                width: 150,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'name',
                title: '用户姓名',
                width: 150,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'email',
                title: '邮箱',
                width: 200,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'roleid',
                title: '用户类型',
                width: 100,
                align: 'center',
                valign: 'middle',
                formatter:  function(value,row,index) {
                    if(value == undefined) {
                        return "<span style='color:red;'>该角色已删除</span>";
                    } else {
                        return  value;
                    }
                }
            }],
        onLoadSuccess: function (res) {//可不写
            //加载成功时
        }, onLoadError: function (statusCode) {
            return "加载失败了"
        }, formatLoadingMessage: function () {
            //正在加载
            return "拼命加载中...";
        }, formatNoMatches: function () {
            //没有匹配的结果
            return '无符合条件的记录';
        }
    });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var id = $("#txt_search_empid").val();
        if ($("#txt_search_empid").val() == '') {
            id = 0;
        }
        var bol = $('#tb_departments').bootstrapTable('getOptions').pageNumber;
        var p = 1;
        if (bol){
            p = bol;
        }
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            size: params.limit,   //页面大小
            page: p,  //页码
            empid: id,
            username: $("#txt_search_username").val()
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function () {
    var user = sessionStorage.getItem("role");
    var oInit = new Object();
    if ("admin" == user) {
        $("#btn_reset").show();
    }else {
        $("#btn_reset").hide();
    }
    oInit.Init = function () {
        //初始化页面上面的按钮事件
        $("#btn_add").click(function () {
            checkForm();
            closeCheck();
            $("#myModalLabel").text("新增");
            $("#myModal").find(".form-control").val("");
            $("#txt_empid").removeAttr("readonly","readonly");
            $("#txt_empid").attr("type","hidden");
            $("#txt_id").attr("hidden","hidden");
            $('#myModal').modal();
        });

        $("#btn_edit").click(function () {
            checkForm2();
            closeCheck();
            var arrselections = $("#tb_departments").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                toastr.warning('只能选择一行进行编辑');
                return;
            }
            if (arrselections.length <= 0) {
                toastr.warning('请选择有效数据');
                return;
            }
            $("#myModalLabel").text("编辑");
            $("#txt_empid").val(arrselections[0].empid);
            $("#txt_username").val(arrselections[0].username);
            $("#txt_password").val(arrselections[0].password);
            $("#txt_tel").val(arrselections[0].tel);
            $("#txt_name").val(arrselections[0].name);
            $("#txt_email").val(arrselections[0].email);
            $("#txt_roleid").val(arrselections[0].roleid);
            $("#txt_empid").attr("readonly","readonly");
            $("#txt_id").removeAttr("hidden","hidden");
            $("#txt_empid").attr("type","text");
            $('#myModal').modal();
            // $("#myModal").modal({backdrop: 'static', keyboard: false});
        });

        $("#btn_delete").click(function () {
            var arrselections = $("#tb_departments").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                toastr.warning('请选择有效数据');
                return;
            }
            if (arrselections.length > 1) {
                toastr.warning('暂时没实现多条数据删除');
                return;
            }
           Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: "get",
                    url: "/emp/delete",
                    data: { "empid": arrselections[0].empid },
                    success: function (data, status) {
                        if (status == "success") {
                            if (data.status == "unath" ) {
                                toastr.warning('你没有权限');
                                return
                            }else if (data.status == "error")  {
                                toastr.warning('该员工下存在客户，请先转移再删除!!!');
                            } else if (data.status == "success") {
                                toastr.success('删除成功');
                                $("#tb_departments").bootstrapTable('refresh');
                            }
                        }
                    },
                    error: function () {
                        toastr.error('Error');
                    },
                    complete: function () {
                    }
                });
           });
        });

        $("#btn_submit").click(function () {
            $("#tbform").data("bootstrapValidator").validate();
            console.log("--->" + $("#myModalLabel").text());
            if(!$('#tbform').data('bootstrapValidator').isValid()){
                console.log("--->"+ $('#tbform').data('bootstrapValidator').isValid());
                toastr.error('信息错误，修改失败');
                return ;
            } else {
                send();
            }

        });

        $("#btn_reset").click(function () {
            Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: "get",
                    url: "/emp/resetpwd",
                    headers: {'Content-Type': 'application/json;charset=utf8'},
                    dataType: "json",
                    data: {},
                    success: function (data, status) {
                        if (status == "success") {
                            toastr.success('重置密码成功');
                        }
                    },
                    error: function () {
                        toastr.error('Error');
                    },
                    complete: function () {}
                });
            });
        });

        $("#btn_query").click(function () {
           $("#tb_departments").bootstrapTable('refresh');
        });
    };
    return oInit;
};

(function ($) {
    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        };
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        };
        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                            modal.find('.cancel').click(function () { callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () { },
                    onShown: function (e) { }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            }
        }
    }();
})(jQuery);

function checkForm() {
    $("#tbform").bootstrapValidator({
        /* 生效规则
         * enabled:字段值发生变化就触发验证
         * disabled/submitted:点击提交时触发验证
         */
        live : 'enabled',
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            txt_username: {
                validators: {
                    notEmpty: {
                        message: '用户登录名不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 10,
                        message: '长度必须在1-10之间'
                    },
                    remote: {//发起Ajax请求。
                        url: 'emp/selectByUsername',//验证地址
                        data:{},
                        message: '用户登录名已存在',//提示消息
                        delay :  1000,//设置1秒发起名字验证
                        type: 'GET' //请求方式
                    }
                }
            },
            txt_password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 6,
                        message: '长度必须在1-6之间'
                    }
                }
            },
            txt_tel: {
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空'
                    },
                    regexp: {//正则验证
                        regexp: /^[0-9]{1,30}$/,
                        message: '长度必须在1-30之间'
                    }
                }
            },
            txt_name: {
                validators: {
                    notEmpty: {
                        message: '用户姓名不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 20,
                        message: '长度必须在1-20之间'
                    }
                }
            },
            txt_email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {//验证email地址
                        message: '不是正确的email地址'
                    }
                }
            }
        }
    })
}

function checkForm2() {
    $("#tbform").bootstrapValidator({
        /* 生效规则
         * enabled:字段值发生变化就触发验证
         * disabled/submitted:点击提交时触发验证
         */
        live : 'enabled',
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            txt_username: {
                validators: {
                    notEmpty: {
                        message: '用户登录名不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 10,
                        message: '长度必须在1-10之间'
                    },
                    remote: {//发起Ajax请求。
                        url: 'emp/selectByUsername2',//验证地址
                        data:{},
                        message: '用户登录名已存在',//提示消息
                        delay :  1000,//设置1秒发起名字验证
                        type: 'GET' //请求方式
                    }
                }
            },
            txt_password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 6,
                        message: '长度必须在1-6之间'
                    }
                }
            },
            txt_tel: {
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空'
                    },
                    regexp: {//正则验证
                        regexp: /^[0-9]{1,30}$/,
                        message: '长度必须在1-30之间'
                    }
                }
            },
            txt_name: {
                validators: {
                    notEmpty: {
                        message: '用户姓名不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 20,
                        message: '长度必须在1-20之间'
                    }
                }
            },
            txt_email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {//验证email地址
                        message: '不是正确的email地址'
                    }
                }
            }
        }
    })
}

function closeCheck() {
    $('#myModal').on('hide.bs.modal', function () {
        if ($("#tbform").data('bootstrapValidator') != null){
            console.log("关闭清空验证");
            $("#tbform").data('bootstrapValidator').destroy();
        }
        // $("#tbform").data('bootstrapValidator').destroy();
        // $('#tbform').data('bootstrapValidator', null);
        // $('#tbform').bootstrapValidator('resetForm');
        // $("#tbform")[0].reset();
    });
}

function send() {
    if(!$('#tbform').data('bootstrapValidator').isValid()){
        console.log("--->"+ $('#tbform').data('bootstrapValidator').isValid());
        toastr.error('信息错误，修改失败');
        // return ;
    } else {
        var postdata = {};
        var opt = "";
        postdata.empid = $("#txt_empid").val();
        postdata.username = $("#txt_username").val();
        postdata.password = $("#txt_password").val();
        postdata.tel = $("#txt_tel").val();
        postdata.name = $("#txt_name").val();
        postdata.email = $("#txt_email").val();
        postdata.roleid = $("#txt_roleid").val();
        if ($("#myModalLabel").text() === "新增") {
            opt = "insert"
        } else if ($("#myModalLabel").text() === "编辑") {
            opt = "update"
        }
        ;
        closeCheck();
        $.ajax({
            type: "post",
            url: "/emp/" + opt,
            headers: {'Content-Type': 'application/json;charset=utf8'},
            dataType: "json",
            data: JSON.stringify(postdata),
            success: function (data, status) {
                if (status == "success") {
                    if (data.status == "unath" ) {
                        toastr.warning('你没有权限');
                        return
                    }else if (data.status == "success") {
                        toastr.success('提交数据成功');
                        $('#myModal').modal('hide');
                        $("#tb_departments").bootstrapTable('refresh');
                    }
                }
            },
            error: function () {
                toastr.error('Error');
            },
            complete: function () {
            }
        });
    }
}

function getSelectData(){
    $.ajax({
        url:"role/selectbyrole",
        dataType:'json',
        data:{},
        type: 'GET',
        success:function(res){
            $("#txt_roleid").empty();
            $.each(res,function(i,o){
                var opt="";
                opt+="<option style='color: #2F3E44' value=\""+ o.roleid + "\">"+ o.rolename +"</option>";
                $("#txt_roleid").append(opt);
            });
            $("#txt_roleid").selectpicker('refresh');//动态加载
            // $("#selectpickers").selectpicker('render');
        },error:function(){}
    });
}

