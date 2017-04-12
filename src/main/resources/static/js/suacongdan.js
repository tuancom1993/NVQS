var listLoaiNghiaVu;
var listLyDo_Current;


$(document).ready(function(){

	$.datetimepicker.setLocale('vi');
    $('.date-picker').datetimepicker({
		timepicker:false,
		dayOfWeekStart : 1,
		format:'d/m/Y',
		formatDate:'Y/m/d',
		minDate:'1870/01/02', // yesterday is minimum date
		maxDate:'2100/01/02' // and tommorow is maximum date calendar
	});
	$(".comfirm").click(function(){
		$(".form-them-cong-dan").submit();
	});

	$(".back").click(function(event) {
		 window.history.go(-1);
	});

	listLoaiNghiaVu = JSON.parse(loadAjaxLoaiNghiaVu());

	setLoaiNghiaVuDefault();
	setLyDoDefault();
	setPhanLoaiLyDoDefault();

});

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

$(document).on('change', '#se-plld', function(event) {
	$("#p-plld").text($("#se-plld option:selected").text());
});

function loadAjaxLoaiNghiaVu(){
	var json;
	$.ajaxSetup({async: false});
	$.get("/getlistjsonloainghiavu", function(data, status){
        json = data;
    });
    return json;
}

function setLoaiNghiaVuDefault(){
	var idloainghiavu =  $("#se-ld").attr("data-idloainghiavu");
	$("#se-lnv").val(idloainghiavu);
	$("#p-lnv").text($("#se-lnv option:selected").text());
}

function setLyDoDefault(){
	var idlydo = $("#se-ld").attr("data-idlydo");
	$("#se-ld").val(idlydo);
	$("#p-ld").text($("#se-ld option:selected").text());
}

function setPhanLoaiLyDoDefault(){
	var idphanloailydo = $("#se-plld").attr("data-idphanloailydo");
	$("#se-plld").val(idphanloailydo);
	$("#p-plld").text($("#se-plld option:selected").text());
}


function includeIdLyDo(listLyDo,idlydo) {
  for(var i=0; i < listLyDo.length; i++){
    if( listLyDo[i].idLyDo == idlydo ){
      return true;
    }
  }
  return false;
}

function includeIdPhanLoaiLyDo(listPhanLoaiLyDo,idPhanLoaiLyDo) {
  for(var i=0; i < listPhanLoaiLyDo.length; i++){
    if( listPhanLoaiLyDo[i].idPhanLoaiLyDo == idPhanLoaiLyDo ){
      return true;
    }
  }
  return false;
}