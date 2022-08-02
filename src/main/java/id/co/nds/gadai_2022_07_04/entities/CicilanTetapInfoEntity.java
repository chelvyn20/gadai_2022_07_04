package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tx_transaksi_cicilan_tetap")
public class CicilanTetapInfoEntity {
    @Id
    @GenericGenerator(name = "transaksi_no_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.TransactionNoGenerator")
    @GeneratedValue(generator = "transaksi_no_seq")
    @Column(name = "no_transaksi")
    private String noTransaksi;

    @OneToMany(targetEntity = BarangEntity.class, mappedBy = "noTransaksi")
    private List<BarangEntity> daftarBarang;

    @Column(name = "total_nilai_tak")
    private Double totalNilaiTaksiran;

    @Column(name = "nilai_pencairan_pel")
    private Double nilaiPencairanPelanggan;

    @Column(name = "diskon_adm_buka")
    private Double diskonAdmBuka;

    @Column(name = "tx_ltv")
    private Double txLtv;

    @Column(name = "max_nilai_pinj")
    private Double maxNilaiPinj;

    @Column(name = "biaya_adm_buka")
    private Double biayaAdmBuka;

    @Column(name = "biaya_adm_buka_ak")
    private Double biayaAdmBukaAkhir;

    @Column(name = "total_nilai_pinj")
    private Double totalNilaiPinj;

    @Column(name = "tanggal_tx")
    private LocalDateTime tglTx;

    @Column(name = "tanggal_jatuh_tempo")
    private LocalDateTime tglJatuhTempo;

    @Column(name = "tx_biaya_jasa_peny")
    private Double txBiayaJasaPeny;

    @Column(name = "tx_biaya_jasa_peny_per")
    private Double txBiayaJasaPenyPer;

    @Column(name = "total_biaya_jasa_peny")
    private Double totalBiayaJasaPeny;

    @Column(name = "tx_biaya_adm_tutup")
    private Double txBiayaAdmTutup;

    @Column(name = "total_pengem")
    private Double totalPengem;

    @Column(name = "status")
    private String status;

    @Column(name = "customer_id")
    private String custId;

    @Column(name = "customer_name")
    private String custName;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @OneToMany(targetEntity = CicilanEntity.class, mappedBy = "noTransaksi")
    private List<CicilanEntity> daftarCicilan;

    @Column(name = "created_date")
    private Timestamp createdDate;

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public List<BarangEntity> getDaftarBarang() {
        return daftarBarang;
    }

    public void setDaftarBarang(List<BarangEntity> daftarBarang) {
        this.daftarBarang = daftarBarang;
    }

    public Double getTotalNilaiTaksiran() {
        return totalNilaiTaksiran;
    }

    public void setTotalNilaiTaksiran(Double totalNilaiTaksiran) {
        this.totalNilaiTaksiran = totalNilaiTaksiran;
    }

    public Double getNilaiPencairanPelanggan() {
        return nilaiPencairanPelanggan;
    }

    public void setNilaiPencairanPelanggan(Double nilaiPencairanPelanggan) {
        this.nilaiPencairanPelanggan = nilaiPencairanPelanggan;
    }

    public Double getDiskonAdmBuka() {
        return diskonAdmBuka;
    }

    public void setDiskonAdmBuka(Double diskonAdmBuka) {
        this.diskonAdmBuka = diskonAdmBuka;
    }

    public Double getTxLtv() {
        return txLtv;
    }

    public void setTxLtv(Double txLtv) {
        this.txLtv = txLtv;
    }

    public Double getMaxNilaiPinj() {
        return maxNilaiPinj;
    }

    public void setMaxNilaiPinj(Double maxNilaiPinj) {
        this.maxNilaiPinj = maxNilaiPinj;
    }

    public Double getBiayaAdmBuka() {
        return biayaAdmBuka;
    }

    public void setBiayaAdmBuka(Double biayaAdmBuka) {
        this.biayaAdmBuka = biayaAdmBuka;
    }

    public Double getBiayaAdmBukaAkhir() {
        return biayaAdmBukaAkhir;
    }

    public void setBiayaAdmBukaAkhir(Double biayaAdmBukaAkhir) {
        this.biayaAdmBukaAkhir = biayaAdmBukaAkhir;
    }

    public Double getTotalNilaiPinj() {
        return totalNilaiPinj;
    }

    public void setTotalNilaiPinj(Double totalNilaiPinj) {
        this.totalNilaiPinj = totalNilaiPinj;
    }

    public LocalDateTime getTglTx() {
        return tglTx;
    }

    public void setTglTx(LocalDateTime tglTx) {
        this.tglTx = tglTx;
    }

    public LocalDateTime getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(LocalDateTime tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public Double getTxBiayaJasaPeny() {
        return txBiayaJasaPeny;
    }

    public void setTxBiayaJasaPeny(Double txBiayaJasaPeny) {
        this.txBiayaJasaPeny = txBiayaJasaPeny;
    }

    public Double getTxBiayaJasaPenyPer() {
        return txBiayaJasaPenyPer;
    }

    public void setTxBiayaJasaPenyPer(Double txBiayaJasaPenyPer) {
        this.txBiayaJasaPenyPer = txBiayaJasaPenyPer;
    }

    public Double getTotalBiayaJasaPeny() {
        return totalBiayaJasaPeny;
    }

    public void setTotalBiayaJasaPeny(Double totalBiayaJasaPeny) {
        this.totalBiayaJasaPeny = totalBiayaJasaPeny;
    }

    public Double getTxBiayaAdmTutup() {
        return txBiayaAdmTutup;
    }

    public void setTxBiayaAdmTutup(Double txBiayaAdmTutup) {
        this.txBiayaAdmTutup = txBiayaAdmTutup;
    }

    public Double getTotalPengem() {
        return totalPengem;
    }

    public void setTotalPengem(Double totalPengem) {
        this.totalPengem = totalPengem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<CicilanEntity> getDaftarCicilan() {
        return daftarCicilan;
    }

    public void setDaftarCicilan(List<CicilanEntity> daftarCicilan) {
        this.daftarCicilan = daftarCicilan;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
