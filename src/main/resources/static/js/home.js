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

});