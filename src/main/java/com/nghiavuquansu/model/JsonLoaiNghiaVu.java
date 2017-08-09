package com.nghiavuquansu.model;

import java.util.ArrayList;

public class JsonLoaiNghiaVu {
    private int idLoaiNghiaVu;
    private String moTa;
    private ArrayList<JsonLyDo> lyDos;

    public JsonLoaiNghiaVu(int idLoaiNghiaVu, String moTa, ArrayList<JsonLyDo> lyDos) {
        super();
        this.idLoaiNghiaVu = idLoaiNghiaVu;
        this.moTa = moTa;
        this.lyDos = lyDos;
    }

    public int getIdLoaiNghiaVu() {
        return idLoaiNghiaVu;
    }

    public void setIdLoaiNghiaVu(int idLoaiNghiaVu) {
        this.idLoaiNghiaVu = idLoaiNghiaVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public ArrayList<JsonLyDo> getLyDos() {
        return lyDos;
    }

    public void setLyDos(ArrayList<JsonLyDo> lyDos) {
        this.lyDos = lyDos;
    }
}
