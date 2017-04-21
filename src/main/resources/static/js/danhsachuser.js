
$(document).ready(function() {
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
		    { "width": "100px", "targets": 1 }, {"width": "200px", "targets": 2}
		],
		});
});