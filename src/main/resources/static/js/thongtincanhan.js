$(document).ready(function() {
	$(".btn-goToDMK").click(function(){
		window.location.href = "/thongtincanhan/matkhau";
	});

	$(".btn-edit-account").click(function(){
		showModal();
	});

	$(".btn-huybo").click(function(){
		hideModal();
	});

	$(".btn-xacnhan").click(function(){
		$(".form-thongtincanhan").submit();
	});

	function showModal(){
		$(".cover").fadeIn(300);
		$(".modal").slideDown(400);
		$(".mess").text('');
	}

	function hideModal(delayTime){
		$(".cover").delay(delayTime).fadeOut(300);
		$(".modal").delay(delayTime).slideUp(300);
	}
});