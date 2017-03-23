var listLoaiNghiaVu;
var listLyDo_Current;

$(document).ready(function() {
	clearClassMenuActive();
	$(".menu li a.trangchu").addClass('menu-active');

	var dttable = $(".table-datatable").DataTable({
		"language": {
            "decimal":        "",
		    "emptyTable":     "Không tìm thấy kết quả (không có dữ liệu)",
		    "info":           "Hiển thị _START_ đến _END_ của _TOTAL_ kết quả",
		    "infoEmpty":      "Hiển thị 0 đến 0 của 0 kết quả",
		    "infoFiltered":   "(filtered from _MAX_ total entries)",
		    "infoPostFix":    "",
		    "thousands":      ",",
		    "lengthMenu":     "Hiển thị _MENU_ kết quả",
		    "loadingRecords": "Loading...",
		    "processing":     "Processing...",
		    "search":         "Tìm Kiếm:",
		    "zeroRecords":    "Không tìm thấy kết quả",
		    "paginate": {
		        "first":      "Trang đầu",
		        "last":       "Trang cuối",
		        "next":       "Tiếp",
		        "previous":   "Trước"
		    },
		    "aria": {
		        "sortAscending":  ": activate to sort column ascending",
		        "sortDescending": ": activate to sort column descending"
		    }
    	},
        "columnDefs": [
		    { "width": "100px", "targets": 1 }, {"width": "100px", "targets": 2}
		],
		});

		// $('.table-datatable').dataTable( {
		//   	"autoWidth": false
		// } );

		// $(window).on('resize', function () {
  //  			 dttable.columns.adjust().draw(); 
  // 		});

		// $(".wrap-article").on('resize', function () {
  //  			 dttable.columns.adjust().draw(); 
  // 		});
  		

  		// $(".icon-hidden").click(function myLoop(){
  		// 	setTimeout(
			 //  function() 
			 //  {
			 //  	if( $(".wrap-menu ").width() == 0 || $(".wrap-menu ").width() == 230 ){
  		// 			dttable.columns.adjust().draw(); 
  		// 			return;
  		// 		} else {
  		// 			myLoop();
  		// 		}
			 //    //dttable.columns.adjust().draw(); 
			 //  }, 100);
  		// });

	var rowCongDanClickedXoa_Current;
	$(".xoa-congdan").click(function(event) {
		var idcongdan = $(this).attr('data-idcongdan');
		var hoten = $(this).attr('data-hoten');
		$(".modal .modal-hoten").text(hoten);
		$(".modal .btn-xacnhanxoa").attr('data-idcongdan', idcongdan);
		showModal();
		rowCongDanClickedXoa_Current = $(this).closest('tr');
	});

	$(".btn-huybo").click(function(event) {
		hideModal(1);
		rowCongDanClickedXoa_Current = null;
	});

	$(".btn-xacnhanxoa").click(function(event) {
		var Congdan = new Object();
		Congdan["idcongdan"] = parseInt($(this).attr('data-idcongdan'));
		var jsonCongDan = JSON.stringify(Congdan);
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			type: 'POST',
			url: '/quanlycongdan/xoacongdan',
			data: jsonCongDan,
			success : function(data) {
				if(data == "OK") {
					$(".mess").text('Xóa công dân thành công').show(100);
					hideModal(600);
					dttable.row(rowCongDanClickedXoa_Current).remove().draw();
				}
				else if (data == "NOK") {
					$(".mess").text('Xóa công dân thất bại. Vui lòng thử lại..!').show(1);
				}
			},
			err : function(){
				$(".mess").text('Xóa công dân thất bại. Vui lòng thử lại..!').show(1);
			}
		});
	});

	/************************************LIST LOAI NGHIA VU*************************************/
	listLoaiNghiaVu = JSON.parse(loadAjaxLoaiNghiaVu());

	$(".btn-xemds").click(function(event) {
		var idLydo = $("#se-ld").val();
		window.location.href = "/danhsachcongdan/danhsach?id="+idLydo;
	});
});

function removeRow(datatable, el){
	$(datatable).row($(el).closest('tr')).remove().draw();
}


function showModal(){
	$(".cover").fadeIn(300);
	$(".modal").slideDown(400);
	$(".mess").text('');
}

function hideModal(delayTime){
	$(".cover").delay(delayTime).fadeOut(300);
	$(".modal").delay(delayTime).slideUp(300);
}

$(document).on('change', '#se-lnv', function(event) {
	$("#se-ld").html("");
	$("#se-plld").html("");
	var idLoaiNghiaVu = $(this).val();
	$("#p-lnv").text($("#se-lnv option:selected").text());
	$(listLoaiNghiaVu).each(function(index, loaiNghiaVu){
		if(idLoaiNghiaVu == loaiNghiaVu.idLoaiNghiaVu){
			var listLyDo = loaiNghiaVu.lyDos;
			listLyDo_Current = listLyDo;
			$(listLyDo).each(function(index_lydo, lyDo){
				$("#se-ld").append("<option value='"+  lyDo.idLyDo +"'> "+ lyDo.moTa +" </option>");	
			});
			$("#se-ld").val($("#se-ld option:first").val()).trigger('change');
		}
	});
});

$(document).on('change', '#se-ld', function(event) {
	$("#se-plld").html("");
	var idLyDo = $(this).val();
	$("#p-ld").text($("#se-ld option:selected").text());
	$(listLyDo_Current).each(function(index, lyDo){
		if (idLyDo == lyDo.idLyDo){
			var listPhanLoaiLyDo = lyDo.phanLoaiLyDos;
			$(listPhanLoaiLyDo).each(function(index_PLLD,phanLoaiLyDo){
				$("#se-plld").append("<option value='"+  phanLoaiLyDo.idPhanLoaiLyDo +"'> "+ phanLoaiLyDo.moTa +" </option>");
			});
		}
	});
	$("#p-ld").text($("#se-ld option:selected").text());
	$("#se-plld").val($("#se-plld option:first").val()).trigger('change');
});

function loadAjaxLoaiNghiaVu(){
	var json;
	$.ajaxSetup({async: false});
	$.get("/getlistjsonloainghiavu", function(data, status){
        json = data;
    });
    return json;
}
