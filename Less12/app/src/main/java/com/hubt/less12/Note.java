package com.hubt.less12;

public class Note {
    private int id;
    private String tieuDe;
    private String noiDung;
    private String ngayTao;

    public Note() {}

    public Note(int id, String tieuDe, String noiDung, String ngayTao) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }

    public Note(String tieuDe, String noiDung, String ngayTao) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public String getNgayTao() { return ngayTao; }
    public void setNgayTao(String ngayTao) { this.ngayTao = ngayTao; }

    @Override
    public String toString() {
        return tieuDe + " (" + ngayTao + ")";
    }
}
