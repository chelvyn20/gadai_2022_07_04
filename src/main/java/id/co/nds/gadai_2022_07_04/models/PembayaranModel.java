package id.co.nds.gadai_2022_07_04.models;

import java.sql.Date;
import java.util.List;

public class PembayaranModel {
    private String noTransaksi;
    private List<Integer> selectedNoCic;
    private String custId;
    private String actorId;
    private Double jumlahPembayaran;
    private Double diskon;
    private String metodeBayar;
    private Date cicDateBegin;
    private Date cicDateEnd;
    public String getNoTransaksi() {
        return noTransaksi;
    }
    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }
    public List<Integer> getSelectedNoCic() {
        return selectedNoCic;
    }
    public void setSelectedNoCic(List<Integer> selectedNoCic) {
        this.selectedNoCic = selectedNoCic;
    }
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getActorId() {
        return actorId;
    }
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
    public Double getJumlahPembayaran() {
        return jumlahPembayaran;
    }
    public void setJumlahPembayaran(Double jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }
    public Double getDiskon() {
        return diskon;
    }
    public void setDiskon(Double diskon) {
        this.diskon = diskon;
    }
    public String getMetodeBayar() {
        return metodeBayar;
    }
    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }
    public Date getCicDateBegin() {
        return cicDateBegin;
    }
    public void setCicDateBegin(Date cicDateBegin) {
        this.cicDateBegin = cicDateBegin;
    }
    public Date getCicDateEnd() {
        return cicDateEnd;
    }
    public void setCicDateEnd(Date cicDateEnd) {
        this.cicDateEnd = cicDateEnd;
    }
}
