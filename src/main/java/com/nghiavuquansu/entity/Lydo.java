package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lydo database table.
 * 
 */
@Entity
@NamedQuery(name="Lydo.findAll", query="SELECT l FROM Lydo l")
public class Lydo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idlydo;

	private String danhsach;

	private String mota;

	//bi-directional many-to-one association to Congdan
	@OneToMany(mappedBy="lydo")
	private List<Congdan> congdans;

	//bi-directional many-to-one association to Loainghiavu
	@ManyToOne
	@JoinColumn(name="idloainghiavu")
	private Loainghiavu loainghiavu;

	//bi-directional many-to-one association to Phanloailydo
	@OneToMany(mappedBy="lydo")
	private List<Phanloailydo> phanloailydos;

	public Lydo() {
	}

	public int getIdlydo() {
		return this.idlydo;
	}

	public void setIdlydo(int idlydo) {
		this.idlydo = idlydo;
	}

	public String getDanhsach() {
		return this.danhsach;
	}

	public void setDanhsach(String danhsach) {
		this.danhsach = danhsach;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public List<Congdan> getCongdans() {
		return this.congdans;
	}

	public void setCongdans(List<Congdan> congdans) {
		this.congdans = congdans;
	}

	public Congdan addCongdan(Congdan congdan) {
		getCongdans().add(congdan);
		congdan.setLydo(this);

		return congdan;
	}

	public Congdan removeCongdan(Congdan congdan) {
		getCongdans().remove(congdan);
		congdan.setLydo(null);

		return congdan;
	}

	public Loainghiavu getLoainghiavu() {
		return this.loainghiavu;
	}

	public void setLoainghiavu(Loainghiavu loainghiavu) {
		this.loainghiavu = loainghiavu;
	}

	public List<Phanloailydo> getPhanloailydos() {
		return this.phanloailydos;
	}

	public void setPhanloailydos(List<Phanloailydo> phanloailydos) {
		this.phanloailydos = phanloailydos;
	}

	public Phanloailydo addPhanloailydo(Phanloailydo phanloailydo) {
		getPhanloailydos().add(phanloailydo);
		phanloailydo.setLydo(this);

		return phanloailydo;
	}

	public Phanloailydo removePhanloailydo(Phanloailydo phanloailydo) {
		getPhanloailydos().remove(phanloailydo);
		phanloailydo.setLydo(null);

		return phanloailydo;
	}

}