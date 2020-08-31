$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    getSelectData();
    getSelectData2();
});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_rp').bootstrapTable({
            url: '/rolepermission/select',                 //请求后台的URL（*）
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
                field: 'rpid',
                title: '角色权限编号',
                width: 150,
                align: 'center',
                valign: 'middle'
            }, {
                field: 'roleid',
                title: '角色编号',
                width: 150,
                align: 'center',
                valign: 'middle',
                formatter:  function(value,row,index) {
                    if(value == undefined) {
                        return "<span style='color:red;'>该角色已删除</span>";
                    } else {
                        return  value;
                    }
                }
            }, {
                field: 'roleName',
                title: '角色',
                width: 150,
                align: 'center',
                valign: 'middle',
                formatter:  function(value,row,index) {
                    if(value == undefined) {
                        return "<span style='color:red;'>该角色已删除</span>";
                    } else {
                        return  value;
                    }
                }
            }, {
                field: 'permissionid',
                title: '权限编号',
                width: 150,
                align: 'center',
                valign: 'middle',
                formatter:  function(value,row,index) {
                    if(value == undefined) {
                        return "<span style='color:red;'>该权限已删除</span>";
                    } else {
                        return  value;
                    }
                }
            }, {
                field: 'pname',
                title: '权限',
                width: 150,
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
        var bol = $('#tb_rp').bootstrapTable('getOptions').pageNumber;
        var p = 1;
        if (bol){
            p = bol;
        }
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            size: params.limit,   //页面大小
            page: p,  //页码
            roleid: $("#txt_search_roleid").val(),
            permissionid: $("#txt_search_permid").val()
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function () {
    var oInit = new Object();
    oInit.Init = function () {
        //初始化页面上面的按钮事件
        $("#btn_add").click(function () {
            $("#myModalLabel").text("新增");
            $("#rpModal").find(".form-control").val("");
            $('#rpModal').modal();
        });

        $("#btn_edit").click(function () {
            var arrselections = $("#tb_rp").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                toastr.warning('只能选择一行进行编辑');
                return;
            }
            if (arrselections.length <= 0) {
                toastr.warning('请选择有效数据');
                return;
            }
            $("#myModalLabel").text("编辑");
            $("#txt_rpid").val(arrselections[0].rpid);
            $("#txt_roleid").val(arrselections[0].roleid);
            $("#txt_permid").val(arrselections[0].permissionid);
            $('#rpModal').modal();
        });

        $("#btn_delete").click(function () {
            var arrselections = $("#tb_rp").bootstrapTable('getSelections');
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
                    url: "/rolepermission/delete",
                    data: { "rpid": arrselections[0].rpid },
                    success: function (data, status) {
                        if (status == "success") {
                            if (data.status == "unath" ) {
                                toastr.warning('你没有权限');
                                return
                            }
                            toastr.success('删除成功');
                            $("#tb_rp").bootstrapTable('refresh');
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
            // $("#rpform").data("bootstrapValidator").validate();
            // console.log("--->" + $("#myModalLabel").text());
            // if(!$('#rpform').data('bootstrapValidator').isValid()){
            //     console.log("--->"+ $('#rpform').data('bootstrapValidator').isValid());
            //     toastr.error('数据格式不对，操作失败！');
            //     return ;
            // }
            send();
        });

        $("#btn_query").click(function () {
            $("#tb_rp").bootstrapTable('refresh');
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

// function checkForm() {
//     $("#rpform").bootstrapValidator({
//         live : 'enabled',
//         feedbackIcons : {
//             valid : "glyphicon glyphicon-ok",
//             invalid : "glyphicon glyphicon-remove",
//             validating : "glyphicon glyphicon-refresh"
//         },
//         fields : {
//             txt_roleid: {
//                 validators: {
//                     notEmpty: {
//                         message: '角色编号不能为空'
//                     },
//                     stringLength: {//检测长度
//                         min: 1,
//                         max: 11,
//                         message: '长度必须在1-11之间'
//                     },
//                     remote: {//发起Ajax请求。
//                         url: 'role/selectbyid',//验证地址
//                         data:{},
//                         message: '角色编号不存在',//提示消息
//                         delay :  1000,//设置1秒发起名字验证
//                         type: 'GET', //请求方式
//                         dataType: 'json'
//                     }
//                 }
//             },
//             txt_permid: {
//                 validators: {
//                     notEmpty: {
//                         message: '权限编号不能为空'
//                     },
//                     stringLength: {//检测长度
//                         min: 1,
//                         max: 20,
//                         message: '长度必须在1-20之间'
//                     },
//                     remote: {//发起Ajax请求。
//                         url: 'permission/selectbyid',//验证地址
//                         data:{},
//                         message: '权限编号不存在',//提示消息
//                         delay :  1000,//设置1秒发起名字验证
//                         type: 'GET', //请求方式
//                         dataType: 'json'
//                     }
//                 }
//             }
//         }
//     })
// }

function closeCheck() {
    $('#rpModal').on('hide.bs.modal', function () {
        if ($("#rpform").data('bootstrapValidator') != null){
            console.log("关闭清空验证");
            $("#rpform").data('bootstrapValidator').destroy();
        }
    })
}

function send() {
    var opt = "";
    var postdata = {};
    postdata.rpid = $("#txt_rpid").val();
    postdata.roleid = $("#txt_roleid").val();
    postdata.permissionid = $("#txt_permid").val();
    if ( $("#myModalLabel").text() === "新增") {
        opt = "insert"
    } else if ( $("#myModalLabel").text() === "编辑") {
        opt = "update"
    };
    $.ajax({
        type: "post",
        url: "/rolepermission/" + opt,
        headers:{'Content-Type':'application/json;charset=utf8'},
        dataType:"json",
        data: JSON.stringify(postdata),
        success: function (data, status) {
            if (status == "success") {
                if (data.status == "unath" ) {
                    toastr.warning('你没有权限');
                    return
                }else if (data.status == "success") {
                    toastr.success('提交数据成功');
                    $('#rpModal').modal('hide');
                    $("#tb_rp").bootstrapTable('refresh');
                } else if (data.status == "error") {
                    toastr.warning('已存在该条数据');
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

function getSelectData2(){
    $.ajax({
        url:"permission/selectbyperm",
        dataType:'json',
        data:{},
        type: 'GET',
        success:function(res){
            $("#txt_permid").empty();
            $.each(res,function(i,o){
                var opt="";
                opt+="<option style='color: #2F3E44' value=\""+ o.permissionid + "\">"+ o.pname +"</option>";
                $("#txt_permid").append(opt);
            });
            $("#txt_permid").selectpicker('refresh');
        },error:function(){}
    });
}


