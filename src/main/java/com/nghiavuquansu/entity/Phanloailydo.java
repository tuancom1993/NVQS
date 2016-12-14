package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the phanloailydo database table.
 * 
 */
@Entity
@NamedQuery(name="Phanloailydo.findAll", query="SELECT p FROM Phanloailydo p")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="idphanloailydo")
public class Phanloailydo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idphanloailydo;

	private String mota;

	//bi-directional many-to-one association to Lydo
	@ManyToOne
	@JoinColumn(name="idlydo")
	private Lydo lydo;

	public Phanloailydo() {
	}

	public int getIdphanloailydo() {
		return this.idphanloailydo;
	}

	public void setIdphanloailydo(int idphanloailydo) {
		this.idphanloailydo = idphanloailydo;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Lydo getLydo() {
		return this.lydo;
	}

	public void setLydo(Lydo lydo) {
		this.lydo = lydo;
	}

}