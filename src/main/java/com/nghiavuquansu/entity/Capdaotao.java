package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the capdaotao database table.
 * 
 */
@Entity
@NamedQuery(name="Capdaotao.findAll", query="SELECT c FROM Capdaotao c")
public class Capdaotao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcapdaotao;

	private String in;

	private String mota;

	//bi-directional many-to-one association to Congdan
	@OneToMany(mappedBy="capdaotao")
	private List<Congdan> congdans;

	public Capdaotao() {
	}

	public int getIdcapdaotao() {
		return this.idcapdaotao;
	}

	public void setIdcapdaotao(int idcapdaotao) {
		this.idcapdaotao = idcapdaotao;
	}

	public String getIn() {
		return this.in;
	}

	public void setIn(String in) {
		this.in = in;
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
		congdan.setCapdaotao(this);

		return congdan;
	}

	public Congdan removeCongdan(Congdan congdan) {
		getCongdans().remove(congdan);
		congdan.setCapdaotao(null);

		return congdan;
	}

}