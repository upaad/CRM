$(function () {
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();
	checkForm();
	getSelectData();
});

/*各组件根据打开窗口大小  初始化响应一次*/
baseOnWindowSizeToChange();

/*窗口宽度高度发生变化时，触发各组件响应函数*/
$(window).resize(
	function() {
		baseOnWindowSizeToChange();
	}
);

/*窗口宽度高度发生变化时，各组件应当的响应*/
function baseOnWindowSizeToChange() {
	//获取浏览器高度
	var h = $(window).height();
	//获取浏览器宽度
	var w = $(window).width();

	// //获取底部单个选项宽度大小并赋给上拉表单宽度
	// var dropdownWidth = $(".bottomSelect").width();
	// $(".dropdownSelect").css("width", dropdownWidth);
	
	/*主标题在窗口大于500时显示，小于500时隐藏*/
	if(h < 500) {
		$("#title").fadeOut(700); //id为title的元素0.7秒内消失
	} else {
		$("#title").fadeIn(1200); //id为title的元素1秒内显示
	}
	
	/*输入框组在浏览器高度小于600时，高度变紧凑，大于600还原*/
	if(h < 600) {
		$(".form-group").css("margin-bottom","0"); 
	} else {
		$(".form-group").css("margin-bottom","15"); 
	}
	
	/* 当浏览器宽度小于1100时：
	 * 标题大小 根据浏览器宽度大小变换而变换
	 * 登录 注册只显示图标
	 * 
	 * 大于1100时还原
	 * */
	if(w < 1100) {
		$("#title").css("font-size", w / 10);
		$("#loginText").text(" ");
		$("#registText").text(" ");
	} else {
		$("#title").css("font-size", "140px");
		$("#loginText").text("登录");
		$("#registText").text("注册");
	}
	if(w < 400) {
		$(".modal-dialog").css("width","90%");
	}else{
		$(".modal-dialog").css("width","350px");
	}
}

var ButtonInit = function () {
	var oInit = new Object();
	oInit.Init = function () {
		//初始化页面上面的按钮事件
		$("#btn_reg").click(function () {
			checkForm();
			$('#register').find(".form-control").val("");
			$('#register').modal();
			closeCheck();
		});

		$("#btn_login").click(function () {
			$("#login").find(".form-control").val("");
			$('#login').modal();
		});

		$("#btn_subReg").click(function () {
			var postdata = {};
			postdata.username = $("#txt_username").val();
			postdata.password = $("#pwd").val();
			postdata.roleid = $("#selectpickers").val();
			console.log("--->"+ $('#regform').data('bootstrapValidator').isValid());
			if(!$('#regform').data('bootstrapValidator').isValid()){
				return ;
			}
			$.ajax({
				type: "post",
				url: "/user/register",
				headers:{'Content-Type':'application/json;charset=utf8'},
				dataType:"json",
				data: JSON.stringify(postdata),
				success: function (data, status) {
					if (status == "success") {
						if (data.status == "success") {
							console.log("---->注册成功");
							$('#register').modal('hide');
						}
					}
				},
				error: function () {},
				complete: function () {}
			});
		});

		$("#btn_subLog").click(function () {
			$.ajax({
				url:"user/login",
				dataType:'json',
				data: {"username":$("#username").val(), "password":$("#password").val()},
				type: 'GET',
				success:function(res){
					if (res.status){
						sessionStorage.setItem("role",res.role);
						sessionStorage.setItem("userid",res.userid);
						if (res.name == null) {
							sessionStorage.setItem("name","请尽快填写姓名");
						}  else {
							sessionStorage.setItem("name",res.name);
						}
						location.href="http://localhost:8080/main.html"
					} else {
						toastr.error('用户名或密码错误');
					}
				},error:function(){alert("错误");}
			});
		});
	};
	return oInit;
};

/* 检查注册名是否合法 */
function checkForm() {
	$("#regform").bootstrapValidator({
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
			regpwd: {
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
			pwdcheck: {
				validators: {
					notEmpty: {
						message: '密码不能为空'
					},
					stringLength: {//检测长度
						min: 1,
						max: 6,
						message: '长度必须在1-6之间'
					},
					identical: {//与指定控件内容比较是否相同，比如两次密码不一致
						field: 'regpwd',//指定控件name
						message: '输入的密码不一致'
					}
				}
			}
		}
	})
}

function closeCheck() {
	$('#register').on('hide.bs.modal', function () {
		if ($("#regform").data('bootstrapValidator') != null){
			console.log("关闭清空验证");
			$("#regform").data('bootstrapValidator').destroy();
		}
	});
}

//获取下拉框内容
function getSelectData(){
	$.ajax({
		url:"role/selectbyrole",
		dataType:'json',
		data:{},
		type: 'GET',
		success:function(res){
			$("#selectpickers").empty();
			$.each(res,function(i,o){
				console.log("身份--->o.rolename");
				var opt="";
				opt+="<option style='color: #2F3E44' value=\""+ o.roleid + "\">"+ o.rolename +"</option>";
				$("#selectpickers").append(opt);
			});
			$("#selectpickers").selectpicker('refresh');//动态加载
			// $("#selectpickers").selectpicker('render');
		},error:function(){}
	});
}