package com.nghiavuquansu.model;

import java.util.ArrayList;

public class JsonLyDo {
    private int idLyDo;
    private String moTa;
    private String danhSach;
    private ArrayList<JsonPhanLoaiLyDo> phanLoaiLyDos;

    public JsonLyDo(int idLyDo, String moTa, String danhSach, ArrayList<JsonPhanLoaiLyDo> phanLoaiLyDos) {
        super();
        this.idLyDo = idLyDo;
        this.moTa = moTa;
        this.danhSach = danhSach;
        this.phanLoaiLyDos = phanLoaiLyDos;
    }

    public int getIdLyDo() {
        return idLyDo;
    }

    public void setIdLyDo(int idLyDo) {
        this.idLyDo = idLyDo;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(String danhSach) {
        this.danhSach = danhSach;
    }

    public ArrayList<JsonPhanLoaiLyDo> getPhanLoaiLyDos() {
        return phanLoaiLyDos;
    }

    public void setPhanLoaiLyDos(ArrayList<JsonPhanLoaiLyDo> phanLoaiLyDos) {
        this.phanLoaiLyDos = phanLoaiLyDos;
    }
}
