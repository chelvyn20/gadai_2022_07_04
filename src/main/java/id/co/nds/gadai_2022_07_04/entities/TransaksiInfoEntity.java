package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "tx_transaksi_cicilan_tetap")
public class TransaksiInfoEntity {
	@Id
	// @GenericGenerator(name = "transaksi_id_seq", strategy =
	// "id.co.nds.gadai_2022_07_04.generators.TransaksiIdGenerator")
	// @GeneratedValue(generator = "transaksi_id_seq")
	@Column(name = "no_transaksi")
	private String noTransaksi;

	@Column(name = "total_nilai_tak")
	private Double totalNilaiTak;

	@Column(name = "nilai_pencairan_pel")
	private Double nilaiPencairanPel;

	@Column(name = "diskon_adm_buka")
	private Double diskonAdmBuka;

	@Column(name = "tx_ltv")
	private Double txLtv;

	@Column(name = "max_nilai_pinj")
	private Double maxNilaiPinj;

	@Column(name = "biaya_adm_buka")
	private Double biayaAdmBuka;

	@Column(name = "biaya_adm_buka_ak")
	private Double biayaAdmBukaAk;

	@Column(name = "total_nilai_pinj")
	private Double totalNilaiPinj;

	@Column(name = "tanggal_tx")
	private Date tanggal_tx;

	@Column(name = "tanggal_jatuh_tempo")
	private Date tanggalJatuhTempo;

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

	@JoinColumn(name = "customer_id", referencedColumnName = "custId")
	@Column(name = "customer_id")
	private String customerId;

	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	@Column(name = "product_id")
	private String productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "status_tx")
	private String statusTx;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public Double getTotalNilaiTak() { return totalNilaiTak; }

	public void setTotalNilaiTak(Double totalNilaiTak) {
		this.totalNilaiTak = totalNilaiTak;
	}

	public Double getNilaiPencairanPel() { return nilaiPencairanPel; }

	public void setNilaiPencairanPel(Double nilaiPencairanPel) {
		this.nilaiPencairanPel = nilaiPencairanPel;
	}

	public Double getDiskonAdmBuka() { return diskonAdmBuka; }

	public void setDiskonAdmBuka(Double diskonAdmBuka) {
		this.diskonAdmBuka = diskonAdmBuka;
	}

	public Double getTxLtv() { return txLtv; }

	public void setTxLtv(Double txLtv) { this.txLtv = txLtv; }

	public Double getMaxNilaiPinj() { return maxNilaiPinj; }

	public void setMaxNilaiPinj(Double maxNilaiPinj) {
		this.maxNilaiPinj = maxNilaiPinj;
	}

	public Double getBiayaAdmBuka() { return biayaAdmBuka; }

	public void setBiayaAdmBuka(Double biayaAdmBuka) {
		this.biayaAdmBuka = biayaAdmBuka;
	}

	public Double getBiayaAdmBukaAk() { return biayaAdmBukaAk; }

	public void setBiayaAdmBukaAk(Double biayaAdmBukaAk) {
		this.biayaAdmBukaAk = biayaAdmBukaAk;
	}

	public Double getTotalNilaiPinj() { return totalNilaiPinj; }

	public void setTotalNilaiPinj(Double totalNilaiPinj) {
		this.totalNilaiPinj = totalNilaiPinj;
	}

	public Date getTanggal_tx() { return tanggal_tx; }

	public void setTanggal_tx(Date tanggal_tx) { this.tanggal_tx = tanggal_tx; }

	public Date getTanggalJatuhTempo() { return tanggalJatuhTempo; }

	public void setTanggalJatuhTempo(Date tanggalJatuhTempo) {
		this.tanggalJatuhTempo = tanggalJatuhTempo;
	}

	public Double getTxBiayaJasaPeny() { return txBiayaJasaPeny; }

	public void setTxBiayaJasaPeny(Double txBiayaJasaPeny) {
		this.txBiayaJasaPeny = txBiayaJasaPeny;
	}

	public Double getTxBiayaJasaPenyPer() { return txBiayaJasaPenyPer; }

	public void setTxBiayaJasaPenyPer(Double txBiayaJasaPenyPer) {
		this.txBiayaJasaPenyPer = txBiayaJasaPenyPer;
	}

	public Double getTotalBiayaJasaPeny() { return totalBiayaJasaPeny; }

	public void setTotalBiayaJasaPeny(Double totalBiayaJasaPeny) {
		this.totalBiayaJasaPeny = totalBiayaJasaPeny;
	}

	public Double getTxBiayaAdmTutup() { return txBiayaAdmTutup; }

	public void setTxBiayaAdmTutup(Double txBiayaAdmTutup) {
		this.txBiayaAdmTutup = txBiayaAdmTutup;
	}

	public Double getTotalPengem() { return totalPengem; }

	public void setTotalPengem(Double totalPengem) {
		this.totalPengem = totalPengem;
	}

	public String getCustomerId() { return customerId; }

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProductId() { return productId; }

	public void setProductId(String productId) { this.productId = productId; }

	public String getProductName() { return productName; }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Timestamp getCreatedDate() { return createdDate; }

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatusTx() { return statusTx; }

	public void setStatusTx(String statusTx) { this.statusTx = statusTx; }
}
