package com.nghiavuquansu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the congdan database table.
 * 
 */
@Entity
@NamedQuery(name="Congdan.findAll", query="SELECT c FROM Congdan c")
public class Congdan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcongdan;

	private String diachi;

	private String ghichu;

	private String ghichucanhan;

	private String hoten;

	private String hotencha;

	private String hotenme;

	private int idphanloailydo;

	private String lydocuthe;

	private String nganhhoc;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date ngaysinh;

	private String nienkhoa;

	private String phanloaisuckhoe;

	private String phuong;

	private String tentruong;

	private String tinhtrangsuckhoe;

	private String todanpho;

	private String trinhdohocvan;

	//bi-directional many-to-one association to Lydo
	@ManyToOne
	@JoinColumn(name="idlydo")
	private Lydo lydo;

	//bi-directional many-to-one association to Capdaotao
	@ManyToOne
	@JoinColumn(name="idcapdaotao")
	private Capdaotao capdaotao;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
    private String updatedBy;
	
	
	@Column(name="created_date")
    private Date createdDate;
	
	
    @Column(name="updated_date")
    private Date updatedDate;

	public Congdan() {
	}

	public int getIdcongdan() {
		return this.idcongdan;
	}

	public void setIdcongdan(int idcongdan) {
		this.idcongdan = idcongdan;
	}

	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getGhichu() {
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public String getGhichucanhan() {
		return this.ghichucanhan;
	}

	public void setGhichucanhan(String ghichucanhan) {
		this.ghichucanhan = ghichucanhan;
	}

	public String getHoten() {
		return this.hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getHotencha() {
		return this.hotencha;
	}

	public void setHotencha(String hotencha) {
		this.hotencha = hotencha;
	}

	public String getHotenme() {
		return this.hotenme;
	}

	public void setHotenme(String hotenme) {
		this.hotenme = hotenme;
	}

	public int getIdphanloailydo() {
		return this.idphanloailydo;
	}

	public void setIdphanloailydo(int idphanloailydo) {
		this.idphanloailydo = idphanloailydo;
	}

	public String getLydocuthe() {
		return this.lydocuthe;
	}

	public void setLydocuthe(String lydocuthe) {
		this.lydocuthe = lydocuthe;
	}

	public String getNganhhoc() {
		return this.nganhhoc;
	}

	public void setNganhhoc(String nganhhoc) {
		this.nganhhoc = nganhhoc;
	}

	public Date getNgaysinh() {
		return this.ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getNienkhoa() {
		return this.nienkhoa;
	}

	public void setNienkhoa(String nienkhoa) {
		this.nienkhoa = nienkhoa;
	}

	public String getPhanloaisuckhoe() {
		return this.phanloaisuckhoe;
	}

	public void setPhanloaisuckhoe(String phanloaisuckhoe) {
		this.phanloaisuckhoe = phanloaisuckhoe;
	}

	public String getPhuong() {
		return this.phuong;
	}

	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}

	public String getTentruong() {
		return this.tentruong;
	}

	public void setTentruong(String tentruong) {
		this.tentruong = tentruong;
	}

	public String getTinhtrangsuckhoe() {
		return this.tinhtrangsuckhoe;
	}

	public void setTinhtrangsuckhoe(String tinhtrangsuckhoe) {
		this.tinhtrangsuckhoe = tinhtrangsuckhoe;
	}

	public String getTodanpho() {
		return this.todanpho;
	}

	public void setTodanpho(String todanpho) {
		this.todanpho = todanpho;
	}

	public String getTrinhdohocvan() {
		return this.trinhdohocvan;
	}

	public void setTrinhdohocvan(String trinhdohocvan) {
		this.trinhdohocvan = trinhdohocvan;
	}

	public Lydo getLydo() {
		return this.lydo;
	}

	public void setLydo(Lydo lydo) {
		this.lydo = lydo;
	}

	public Capdaotao getCapdaotao() {
		return this.capdaotao;
	}

	public void setCapdaotao(Capdaotao capdaotao) {
		this.capdaotao = capdaotao;
	}

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}