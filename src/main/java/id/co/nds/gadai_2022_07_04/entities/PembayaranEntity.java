package id.co.nds.gadai_2022_07_04.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tx_pembayaran_h")
public class PembayaranEntity {
    @Id
    @GenericGenerator(name = "no_pembayaran_seq",
    strategy = "id.co.nds.gadai_2022_07_04.generators.PembayaranNoGenerator")
    
    @GeneratedValue(generator = "no_pembayaran_seq")
    
    @Column(name="no_pembayaran")
    private String noPembayaran;

    @Column(name="no_transaksi")
    private String noTransaksi;

    @Column(name="total_tagihan_cicilan")
    private Double totalTagihanCicilan;

    @Column(name="total_tagihan_denda")
    private Double totalTagihanDenda;

    @Column(name="biaya_adm_tutup")
    private Double biayaAdmTutup;

    @Column(name="total_tagihan")
    private Double totalTagihan;

    @Column(name="pembulatan")
    private Double pembulatan;

    @Column(name="jumlah_pembayaran")
    private Double jumlahPembayaran;

    @Column(name="metode_bayar")
    private String metodeBayar;

    public String getNoPembayaran() {
        return noPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Double getTotalTagihanCicilan() {
        return totalTagihanCicilan;
    }

    public void setTotalTagihanCicilan(Double totalTagihanCicilan) {
        this.totalTagihanCicilan = totalTagihanCicilan;
    }

    public Double getTotalTagihanDenda() {
        return totalTagihanDenda;
    }

    public void setTotalTagihanDenda(Double totalTagihanDenda) {
        this.totalTagihanDenda = totalTagihanDenda;
    }

    public Double getBiayaAdmTutup() {
        return biayaAdmTutup;
    }

    public void setBiayaAdmTutup(Double biayaAdmTutup) {
        this.biayaAdmTutup = biayaAdmTutup;
    }

    public Double getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(Double totalTagihan) {
        this.totalTagihan = totalTagihan;
    }

    public Double getPembulatan() {
        return pembulatan;
    }

    public void setPembulatan(Double pembulatan) {
        this.pembulatan = pembulatan;
    }

    public Double getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(Double jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }
}
