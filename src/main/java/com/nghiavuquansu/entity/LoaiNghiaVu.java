package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the loai_nghia_vu database table.
 * 
 */
@Entity
@Table(name="loai_nghia_vu")
@NamedQuery(name="LoaiNghiaVu.findAll", query="SELECT l FROM LoaiNghiaVu l")
public class LoaiNghiaVu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_loai_nghia_vu")
	private int idLoaiNghiaVu;

	@Column(name="mo_ta")
	private String moTa;

	//bi-directional many-to-one association to LyDo
	@OneToMany(mappedBy="loaiNghiaVu")
	private List<LyDo> lyDos;

	public LoaiNghiaVu() {
	}

	public int getIdLoaiNghiaVu() {
		return this.idLoaiNghiaVu;
	}

	public void setIdLoaiNghiaVu(int idLoaiNghiaVu) {
		this.idLoaiNghiaVu = idLoaiNghiaVu;
	}

	public String getMoTa() {
		return this.moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public List<LyDo> getLyDos() {
		return this.lyDos;
	}

	public void setLyDos(List<LyDo> lyDos) {
		this.lyDos = lyDos;
	}

	public LyDo addLyDo(LyDo lyDo) {
		getLyDos().add(lyDo);
		lyDo.setLoaiNghiaVu(this);

		return lyDo;
	}

	public LyDo removeLyDo(LyDo lyDo) {
		getLyDos().remove(lyDo);
		lyDo.setLoaiNghiaVu(null);

		return lyDo;
	}

}