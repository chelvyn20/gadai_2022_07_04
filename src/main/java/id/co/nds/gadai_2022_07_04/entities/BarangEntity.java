package id.co.nds.gadai_2022_07_04.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import id.co.nds.gadai_2022_07_04.key.BarangKey;

@Entity
@Table(name = "TX_TRANSAKSI_BARANG")
 @IdClass(BarangKey.class)
public class BarangEntity {
    @Id
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @Id
    @Column(name = "no_urut")
    private Integer noUrut;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "kondisi")
    private String kondisi;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "harga_per_satuan")
    private Double hargaPerSatuan;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Integer getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(Integer noUrut) {
        this.noUrut = noUrut;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHargaPerSatuan() {
        return hargaPerSatuan;
    }

    public void setHargaPerSatuan(Double hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }

}
