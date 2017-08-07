package com.nghiavuquansu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the cong_dan database table.
 * 
 */
@Entity
@Table(name = "cong_dan")
@NamedQuery(name = "CongDan.findAll", query = "SELECT c FROM CongDan c")
public class CongDan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cong_dan")
    private int idCongDan;

    @Column(name = "created_by")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ghi_chu_ca_nhan")
    private String ghiChuCaNhan;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ho_ten_cha")
    private String hoTenCha;

    @Column(name = "ho_ten_me")
    private String hoTenMe;

    @Column(name = "id_phan_loai_ly_do")
    private int idPhanLoaiLyDo;

    @Column(name = "ly_do_cu_the")
    private String lyDoCuThe;

    @Column(name = "nganh_hoc")
    private String nganhHoc;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "nien_khoa")
    private String nienKhoa;

    @Column(name = "phan_loai_suc_khoe")
    private String phanLoaiSucKhoe;

    private String phuong;

    @Column(name = "ten_truong")
    private String tenTruong;

    @Column(name = "tinh_trang_suc_khoe")
    private String tinhTrangSucKhoe;

    @Column(name = "to_dan_pho")
    private String toDanPho;

    @Column(name = "trinh_do_hoc_van")
    private String trinhDoHocVan;

    @Column(name = "updated_by")
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    // bi-directional many-to-one association to CapDaoTao
    @ManyToOne
    @JoinColumn(name = "id_cap_dao_tao")
    private CapDaoTao capDaoTao;

    // bi-directional many-to-one association to LyDo
    @ManyToOne
    @JoinColumn(name = "id_ly_do")
    private LyDo lyDo;

    @Transient
    private int index;

    public CongDan() {
    }

    public int getIdCongDan() {
        return this.idCongDan;
    }

    public void setIdCongDan(int idCongDan) {
        this.idCongDan = idCongDan;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChuCaNhan() {
        return this.ghiChuCaNhan;
    }

    public void setGhiChuCaNhan(String ghiChuCaNhan) {
        this.ghiChuCaNhan = ghiChuCaNhan;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHoTenCha() {
        return this.hoTenCha;
    }

    public void setHoTenCha(String hoTenCha) {
        this.hoTenCha = hoTenCha;
    }

    public String getHoTenMe() {
        return this.hoTenMe;
    }

    public void setHoTenMe(String hoTenMe) {
        this.hoTenMe = hoTenMe;
    }

    public int getIdPhanLoaiLyDo() {
        return idPhanLoaiLyDo;
    }

    public void setIdPhanLoaiLyDo(int idPhanLoaiLyDo) {
        this.idPhanLoaiLyDo = idPhanLoaiLyDo;
    }

    public String getLyDoCuThe() {
        return this.lyDoCuThe;
    }

    public void setLyDoCuThe(String lyDoCuThe) {
        this.lyDoCuThe = lyDoCuThe;
    }

    public String getNganhHoc() {
        return this.nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public Date getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNienKhoa() {
        return this.nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public String getPhanLoaiSucKhoe() {
        return this.phanLoaiSucKhoe;
    }

    public void setPhanLoaiSucKhoe(String phanLoaiSucKhoe) {
        this.phanLoaiSucKhoe = phanLoaiSucKhoe;
    }

    public String getPhuong() {
        return this.phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getTenTruong() {
        return this.tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    public String getTinhTrangSucKhoe() {
        return this.tinhTrangSucKhoe;
    }

    public void setTinhTrangSucKhoe(String tinhTrangSucKhoe) {
        this.tinhTrangSucKhoe = tinhTrangSucKhoe;
    }

    public String getToDanPho() {
        return this.toDanPho;
    }

    public void setToDanPho(String toDanPho) {
        this.toDanPho = toDanPho;
    }

    public String getTrinhDoHocVan() {
        return this.trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public CapDaoTao getCapDaoTao() {
        return this.capDaoTao;
    }

    public void setCapDaoTao(CapDaoTao capDaoTao) {
        this.capDaoTao = capDaoTao;
    }

    public LyDo getLyDo() {
        return this.lyDo;
    }

    public void setLyDo(LyDo lyDo) {
        this.lyDo = lyDo;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}