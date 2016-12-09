package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the loainghiavu database table.
 * 
 */
@Entity
@NamedQuery(name="Loainghiavu.findAll", query="SELECT l FROM Loainghiavu l")
public class Loainghiavu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idloainghiavu;

	private String mota;

	//bi-directional many-to-one association to Lydo
	@OneToMany(mappedBy="loainghiavu")
	private List<Lydo> lydos;

	public Loainghiavu() {
	}

	public int getIdloainghiavu() {
		return this.idloainghiavu;
	}

	public void setIdloainghiavu(int idloainghiavu) {
		this.idloainghiavu = idloainghiavu;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public List<Lydo> getLydos() {
		return this.lydos;
	}

	public void setLydos(List<Lydo> lydos) {
		this.lydos = lydos;
	}

	public Lydo addLydo(Lydo lydo) {
		getLydos().add(lydo);
		lydo.setLoainghiavu(this);

		return lydo;
	}

	public Lydo removeLydo(Lydo lydo) {
		getLydos().remove(lydo);
		lydo.setLoainghiavu(null);

		return lydo;
	}

}