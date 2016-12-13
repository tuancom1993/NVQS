$(document).ready(function() {
		$(".table-datatable").DataTable({
			"language": {
	            "decimal":        "",
			    "emptyTable":     "Khong tim thay ket qua (khong co du lieu)",
			    "info":           "Hien thi _START_ den _END_ cua _TOTAL_ ket qua",
			    "infoEmpty":      "Hien thi 0 den 0 cua 0 ket qua",
			    "infoFiltered":   "(filtered from _MAX_ total entries)",
			    "infoPostFix":    "",
			    "thousands":      ",",
			    "lengthMenu":     "Hien thi _MENU_ ket qua",
			    "loadingRecords": "Loading...",
			    "processing":     "Processing...",
			    "search":         "Tim Kiem:",
			    "zeroRecords":    "Khong tim thay ket qua",
			    "paginate": {
			        "first":      "Trang dau",
			        "last":       "Trang cuoi",
			        "next":       "Tiep",
			        "previous":   "Truoc"
			    },
			    "aria": {
			        "sortAscending":  ": activate to sort column ascending",
			        "sortDescending": ": activate to sort column descending"
			    }
        }
		});

	$(".xoa-congdan").click(function(event) {
		var idcongdan = $(this).attr('data-idcongdan');
		var hoten = $(this).attr('data-hoten');
		$(".modal .modal-hoten").text(hoten);
		showModal();
	});

	$(".btn-huybo").click(function(event) {
		hideModal();
	});

});

function showModal(){
	$(".cover").fadeIn(300);
	$(".modal").slideDown(400);
}

function hideModal(){
	$(".cover").fadeOut(300);
	$(".modal").slideUp(300);
}