	$(document).ready(function() {
		console.log("Path: "+window.location.pathname);

		$(".icon-hidden").click(function(event) {
			// $(this).toggleClass("menu-hiddened");
			// $(".wrap-menu").toggleClass('menu-hidden');

			if(!$(this).hasClass("menu-hiddened")){
				clearClassMenuHidden();
				$(this).addClass("menu-hiddened");
				$(".wrap-menu").addClass('menu-hidden');
				localStorage.setItem("isHidden", "true");
			} else {
				clearClassMenuHidden();
				localStorage.setItem("isHidden", "false");
			}
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

		if (typeof(Storage) !== "undefined") {
			if("true" == localStorage.getItem("isHidden")){
				clearClassMenuHidden();
				$(".icon-hidden").addClass("menu-hiddened");
				$(".wrap-menu").addClass('menu-hidden');

				$(".icon-hidden").css("transition", "left 0ms");
				$(".wrap-menu").css("transition", "all 0ms");
			} else {
				clearClassMenuHidden();
			}
		} else {
		    // Sorry! No Web Storage support..
		}


		/*****************************Menu click show danh sach cd********************************/

		$(".menu-ds ~ ul.sub-menu li a").click(function(event) {
		 	window.location.href = "/danhsachcongdan/danhsach?id="+$(this).attr('data-idlydo');
		});
	});

function clearClassMenuActive(){
	$("ul.menu .menu-active").removeClass('menu-active');
	$("ul.sub-menu .sub-menu-active").removeClass('sub-menu-active');
}

function clearClassMenuHidden(){
	$(".icon-hidden").removeClass("menu-hiddened");
	$(".wrap-menu").removeClass('menu-hidden');

	$(".icon-hidden").css("transition", "left 0.4s");
	$(".wrap-menu").css("transition", "all 0.4s");
}