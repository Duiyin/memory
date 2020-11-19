
var countdown=60;

$(function() {
	$('#getVerifyBtn').click(function() {
		getvCode();
	});
});

getvCode = function() {
	var email = $("#email").val();
	if(isEmail(email)) {
		$("#getVerifyBtn").html("发送中...");
		$.ajax({
			type: "POST",
			url: "/email/verify",
			data: "email="+email,
			dataType: "json",
			success: function(txt){
				$("#getVerifyBtn").html(txt);
				setVerifyBtnDown();
			},
			error: function(){
				$("#getVerifyBtn").html("发送失败");
			}
		});
	}else{
		$("#getVerifyBtn").html("邮箱格式有误");
		setTimeout(function() {
			resetSendBtn();
    	},1000);
	}
}

isEmail = function(mail) {
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(filter.test(mail)){
		return true;
	}else{
		return false;
	}
}

setVerifyBtnDown = function(){
	if(countdown == 0){
		$("#getVerifyBtn").attr('disabled',false);
		$("#getVerifyBtn").html("点击获取激活码");
		countdown=60;
		return;
	}else{
		$("#getVerifyBtn").attr('disabled',true);
		$('#getVerifyBtn').html("重新发送(" + countdown + ")");
		countdown--;
	}
	setTimeout(function(){
		setVerifyBtnDown();
	},1000);
}

resetSendBtn = function(){
	$("#getVerifyBtn").html("点击获取激活码");
}