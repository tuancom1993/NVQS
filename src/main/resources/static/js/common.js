	$(document).ready(function($) {
		console.log("Path: "+window.location.pathname);

		$(".icon-hidden").click(function(event) {
			$(this).toggleClass("menu-hiddened");
			$(".wrap-menu").toggleClass('menu-hidden');
		});

		$("section.wrap-menu-article div.wrap-menu ul.menu > li > a").click(function(event) {
			clearClassMenuActive();
			$(this).toggleClass('menu-active');
		});
		$("ul.sub-menu li a").click(function(event) {
			clearClassMenuActive();
			$(this).toggleClass('sub-menu-active');
			$(this).parent().parent().parent().find(' > a').toggleClass('menu-active');
		});


		/*****************************Menu click show danh sach cd********************************/

		$(".menu-ds ~ ul.sub-menu li a").click(function(event) {
		 	window.location.href = "/danhsachcongdan/danhsach?id="+$(this).attr('data-idlydo');
		});
	});

function clearClassMenuActive(){
	$("ul.menu .menu-active").removeClass('menu-active');
	$("ul.sub-menu .sub-menu-active").removeClass('sub-menu-active');
}