$(document).ready(function() {
	$(".btn-goToDMK").click(function(){
		window.location.href = "/thongtincanhan/matkhau";
	});

	$(".btn-edit-account").click(function(){
		showModalEditAccount();
	});

	$(".btn-huybo").click(function(){
		hideModal();
	});

	$(".btn-xacnhan").click(function(){
		if($(".modal").hasClass("isRestorePassword") == false){
			$(".form-suathongtinuser").submit();
		} else {
			var link = "/quanlytaikhoan/datlaimatkhau?usn=" + $(".input-username").val();
			console.log("Link: "+link);
			$.ajaxSetup({async: false});
			$.get(link, function(data, status){
		        if(data == "OK"){
		        	console.log("change successfull");
		        	$(".success-message").text("Đặt lại mật khẩu thành công.").delay(2000).hide(200);
		        } else {
		        	console.log("change fail");
		        	$(".error-message").text("Đặt lại mật khẩu thất bại.").delay(2000).hide(200);
		        }
		        hideModal();
		    });	
		}
		
	});

	$(".btn-restore-password").click(function(){
		showModalRestorePassword();
	})

	function showModalEditAccount(){
		$(".modal").removeClass("isRestorePassword");
		$(".title-modal").text("Sửa Thông Tin Tài Khoản");
		$(".arlet-content").text("Bạn có chắc muốn sửa thông tin tài khoản?");
		$(".cover").fadeIn(300);
		$(".modal").slideDown(400);
		$(".mess").text('');
	}

	function showModalRestorePassword(){
		$(".modal").addClass("isRestorePassword");
		$(".title-modal").text("Đặt Lại Mật Khẩu");
		$(".arlet-content").text("Bạn có chắc muốn đặt lại mật khẩu (phuongthachthang)?");
		$(".cover").fadeIn(300);
		$(".modal").slideDown(400);
		$(".mess").text('');
	}

	function hideModal(delayTime){
		$(".cover").delay(delayTime).fadeOut(300);
		$(".modal").delay(delayTime).slideUp(300);
	}
});