var rowCongDanClickedXoa_Current;
$(document).ready(function() {
    clearClassMenuActive();
    $(".menu li a.quatuoi").addClass('menu-active');

    $.datetimepicker.setLocale('vi');
    $('.from-date').datetimepicker({
        timepicker: false,
        dayOfWeekStart: 1,
        format: 'd/m/Y',
        formatDate: 'Y/m/d',
        minDate: '1870/01/02', // yesterday is minimum date
        maxDate: '2100/01/02' // and tommorow is maximum date calendar
    });

    var dttable = $(".table-datatable").DataTable({
        "language": {
            "decimal": "",
            "emptyTable": "Không tìm thấy kết quả (không có dữ liệu)",
            "info": "Hiển thị _START_ đến _END_ của _TOTAL_ kết quả",
            "infoEmpty": "Hiển thị 0 đến 0 của 0 kết quả",
            "infoFiltered": "(filtered from _MAX_ total entries)",
            "infoPostFix": "",
            "thousands": ",",
            "lengthMenu": "Hiển thị _MENU_ kết quả",
            "loadingRecords": "Loading...",
            "processing": "Processing...",
            "search": "Tìm Kiếm:",
            "zeroRecords": "Không tìm thấy kết quả",
            "paginate": {
                "first": "Trang đầu",
                "last": "Trang cuối",
                "next": "Tiếp",
                "previous": "Trước"
            },
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            }
        },
        "columnDefs": [
            { "width": "100px", "targets": 1 }
        ],
    });




    $(".btn-huybo").click(function(event) {
        hideModal(1);
        rowCongDanClickedXoa_Current = null;
    });

    $(".btn-xacnhanxoa").click(function(event) {
        var Congdan = new Object();
        Congdan["idCongDan"] = parseInt($(this).attr('data-idcongdan'));
        var jsonCongDan = JSON.stringify(Congdan);
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: 'POST',
            url: '/quanlycongdan/xoacongdan',
            data: jsonCongDan,
            success: function(data) {
                if (data == "OK") {
                    $(".mess").text('Xóa công dân thành công').show(100);
                    hideModal(600);
                    dttable.row(rowCongDanClickedXoa_Current).remove().draw();
                } else if (data == "NOK") {
                    $(".mess").text('Xóa công dân thất bại. Vui lòng thử lại..!').show(1);
                }
            },
            err: function() {
                $(".mess").text('Xóa công dân thất bại. Vui lòng thử lại..!').show(1);
            }
        });
    });

    $(".btn-xemdstungay").click(function(event) {
        var dateFrom = $(".from-date").val();
        // window.location.href = "/danhsachcongdan/danhsachquatuoinghiavu/"+dateFrom;
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: 'POST',
            url: '/setdatecalculate',
            data: dateFrom,
            success: function(data) {
                if (data == "OK") {
                    console.log("Luu ngay thanh cong");
                    window.location.href = "/danhsachcongdan/danhsachquatuoinghiavu";
                } else if (data == "NOK") {
                    console.log("Luu ngay khong thanh cong");
                }
            },
            err: function() {
                console.log("Luu ngay khong thanh cong");
            }
        });
    });
});

$(".xoa-congdan").on('click', function(event) {
    var idcongdan = $(this).attr('data-idcongdan');
    var hoten = $(this).attr('data-hoten');
    $(".modal .modal-hoten").text(hoten);
    $(".modal .btn-xacnhanxoa").attr('data-idcongdan', idcongdan);
    showModal();
    rowCongDanClickedXoa_Current = $(this).closest('tr');
});

function removeRow(datatable, el) {
    $(datatable).row($(el).closest('tr')).remove().draw();
}


function showModal() {
    $(".cover").fadeIn(300);
    $(".modal").slideDown(400);
    $(".mess").text('');
}

function hideModal(delayTime) {
    $(".cover").delay(delayTime).fadeOut(300);
    $(".modal").delay(delayTime).slideUp(300);
}