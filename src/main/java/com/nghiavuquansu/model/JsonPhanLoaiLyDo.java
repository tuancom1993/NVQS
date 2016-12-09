package com.nghiavuquansu.model;

public class JsonPhanLoaiLyDo {
	private int idPhanLoaiLyDo;
	private String moTa;
	public JsonPhanLoaiLyDo(int idPhanLoaiLyDo, String moTa) {
		super();
		this.idPhanLoaiLyDo = idPhanLoaiLyDo;
		this.moTa = moTa;
	}
	public int getIdPhanLoaiLyDo() {
		return idPhanLoaiLyDo;
	}
	public void setIdPhanLoaiLyDo(int idPhanLoaiLyDo) {
		this.idPhanLoaiLyDo = idPhanLoaiLyDo;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	
}
