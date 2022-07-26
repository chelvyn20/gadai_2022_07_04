package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import id.co.nds.gadai_2022_07_04.key.CicilanKey;

@Entity
@Table(name = "tx_cicilan")
@IdClass(CicilanKey.class)
public class CicilanEntity {
    
    @Id
    @Column(name="no_transaksi")
    private String noTransaksi;

    @Id
    @Column(name="cicilan_ke")
    private Integer cicilanKe;

    @Column(name="tx_pokok")
    private Double txPokok;

    @Column(name="tx_bunga")
    private Double txBunga;

    @Column(name="tx_status")
    private String statusTransaksi;

    @Column(name="tanggal_aktif")
    private LocalDateTime tglAktif;

    @Column(name="tanggal_jatuh_tempo")
    private LocalDateTime tglJatuhTempo;

    @Column(name="tanggal_bayar")
    private LocalDateTime tglBayar;

    @Column(name="created_date")
    private Timestamp createdDate;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Integer getCicilanKe() {
        return cicilanKe;
    }

    public void setCicilanKe(Integer cicilanKe) {
        this.cicilanKe = cicilanKe;
    }

    public Double getTxPokok() {
        return txPokok;
    }

    public void setTxPokok(Double txPokok) {
        this.txPokok = txPokok;
    }

    public Double getTxBunga() {
        return txBunga;
    }

    public void setTxBunga(Double txBunga) {
        this.txBunga = txBunga;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public LocalDateTime getTglAktif() {
        return tglAktif;
    }

    public void setTglAktif(LocalDateTime tglAktif) {
        this.tglAktif = tglAktif;
    }

    public LocalDateTime getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(LocalDateTime tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public LocalDateTime getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(LocalDateTime tglBayar) {
        this.tglBayar = tglBayar;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
}
