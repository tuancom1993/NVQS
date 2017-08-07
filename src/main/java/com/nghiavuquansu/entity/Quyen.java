package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the quyen database table.
 * 
 */
@Entity
@Table(name="quyen")
@NamedQuery(name="Quyen.findAll", query="SELECT q FROM Quyen q")
public class Quyen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_quyen")
	private int idQuyen;

	@Column(name="mo_ta")
	private String moTa;

	public Quyen() {
	}

	public int getIdQuyen() {
		return this.idQuyen;
	}

	public void setIdQuyen(int idQuyen) {
		this.idQuyen = idQuyen;
	}

	public String getMoTa() {
		return this.moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

}