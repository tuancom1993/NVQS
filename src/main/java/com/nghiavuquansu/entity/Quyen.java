package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the quyen database table.
 * 
 */
@Entity
@NamedQuery(name="Quyen.findAll", query="SELECT q FROM Quyen q")
public class Quyen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idquyen;

	private String mota;

	public Quyen() {
	}

	public int getIdquyen() {
		return this.idquyen;
	}

	public void setIdquyen(int idquyen) {
		this.idquyen = idquyen;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

}