package com.nghiavuquansu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cap_dao_tao database table.
 * 
 */
@Entity
@Table(name="cap_dao_tao")
@NamedQuery(name="CapDaoTao.findAll", query="SELECT c FROM CapDaoTao c")
public class CapDaoTao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cap_dao_tao")
	private int idCapDaoTao;

	private String in;

	@Column(name="mo_ta")
	private String moTa;

	//bi-directional many-to-one association to CongDan
	@OneToMany(mappedBy="capDaoTao")
	private List<CongDan> congDans;

	public CapDaoTao() {
	}

	public int getIdCapDaoTao() {
		return this.idCapDaoTao;
	}

	public void setIdCapDaoTao(int idCapDaoTao) {
		this.idCapDaoTao = idCapDaoTao;
	}

	public String getIn() {
		return this.in;
	}

	public void setIn(String in) {
		this.in = in;
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
		congDan.setCapDaoTao(this);

		return congDan;
	}

	public CongDan removeCongDan(CongDan congDan) {
		getCongDans().remove(congDan);
		congDan.setCapDaoTao(null);

		return congDan;
	}

}