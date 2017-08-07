package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the phan_loai_ly_do database table.
 * 
 */
@Entity
@Table(name="phan_loai_ly_do")
@NamedQuery(name="PhanLoaiLyDo.findAll", query="SELECT p FROM PhanLoaiLyDo p")
public class PhanLoaiLyDo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_phan_loai_ly_do")
	private int idPhanLoaiLyDo;

	@Column(name="mo_ta")
	private String moTa;

	//bi-directional many-to-one association to LyDo
	@ManyToOne
	@JoinColumn(name="id_ly_do")
	private LyDo lyDo;

	public PhanLoaiLyDo() {
	}

	public int getIdPhanLoaiLyDo() {
		return this.idPhanLoaiLyDo;
	}

	public void setIdPhanLoaiLyDo(int idPhanLoaiLyDo) {
		this.idPhanLoaiLyDo = idPhanLoaiLyDo;
	}

	public String getMoTa() {
		return this.moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public LyDo getLyDo() {
		return this.lyDo;
	}

	public void setLyDo(LyDo lyDo) {
		this.lyDo = lyDo;
	}

}