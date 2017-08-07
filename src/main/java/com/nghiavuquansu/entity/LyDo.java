package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ly_do database table.
 * 
 */
@Entity
@Table(name="ly_do")
@NamedQuery(name="LyDo.findAll", query="SELECT l FROM LyDo l")
public class LyDo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ly_do")
	private int idLyDo;

	@Column(name="danh_sach")
	private String danhSach;

	@Column(name="mo_ta")
	private String moTa;

	//bi-directional many-to-one association to CongDan
	@OneToMany(mappedBy="lyDo")
	private List<CongDan> congDans;

	//bi-directional many-to-one association to LoaiNghiaVu
	@ManyToOne
	@JoinColumn(name="id_loai_nghia_vu")
	private LoaiNghiaVu loaiNghiaVu;

	//bi-directional many-to-one association to PhanLoaiLyDo
	@OneToMany(mappedBy="lyDo")
	private List<PhanLoaiLyDo> phanLoaiLyDos;

	public LyDo() {
	}

	public int getIdLyDo() {
		return this.idLyDo;
	}

	public void setIdLyDo(int idLyDo) {
		this.idLyDo = idLyDo;
	}

	public String getDanhSach() {
		return this.danhSach;
	}

	public void setDanhSach(String danhSach) {
		this.danhSach = danhSach;
	}

	public String getMoTa() {
		return this.moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public List<CongDan> getCongDans() {
		return this.congDans;
	}

	public void setCongDans(List<CongDan> congDans) {
		this.congDans = congDans;
	}

	public CongDan addCongDan(CongDan congDan) {
		getCongDans().add(congDan);
		congDan.setLyDo(this);

		return congDan;
	}

	public CongDan removeCongDan(CongDan congDan) {
		getCongDans().remove(congDan);
		congDan.setLyDo(null);

		return congDan;
	}

	public LoaiNghiaVu getLoaiNghiaVu() {
		return this.loaiNghiaVu;
	}

	public void setLoaiNghiaVu(LoaiNghiaVu loaiNghiaVu) {
		this.loaiNghiaVu = loaiNghiaVu;
	}

	public List<PhanLoaiLyDo> getPhanLoaiLyDos() {
		return this.phanLoaiLyDos;
	}

	public void setPhanLoaiLyDos(List<PhanLoaiLyDo> phanLoaiLyDos) {
		this.phanLoaiLyDos = phanLoaiLyDos;
	}

	public PhanLoaiLyDo addPhanLoaiLyDo(PhanLoaiLyDo phanLoaiLyDo) {
		getPhanLoaiLyDos().add(phanLoaiLyDo);
		phanLoaiLyDo.setLyDo(this);

		return phanLoaiLyDo;
	}

	public PhanLoaiLyDo removePhanLoaiLyDo(PhanLoaiLyDo phanLoaiLyDo) {
		getPhanLoaiLyDos().remove(phanLoaiLyDo);
		phanLoaiLyDo.setLyDo(null);

		return phanLoaiLyDo;
	}

}