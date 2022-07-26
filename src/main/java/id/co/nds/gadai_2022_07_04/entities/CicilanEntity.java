
package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import id.co.nds.gadai_2022_07_04.keys.KeysTransaksiCicilan;

@Entity
@Table(name = "tx_cicilan")
@IdClass(KeysTransaksiCicilan.class)
public class CicilanEntity {

	// @EmbeddedId
	// private KeysTransaksiCicilan keysTransaksiCicilan;

	// public CicilanEntity() {

	// }
	@Id
	// @GenericGenerator(name = "transaksi_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.TransaksiIdGenerator")
	// @GeneratedValue(generator = "transaksi_id_seq")
	private String noTransaksi;

	private Integer cicilanKe;

	@Column(name = "tx_pokok")
	private Double txPokok;

	@Column(name = "tx_bunga")
	private Double txBunga;

	@Column(name = "tx_status")
	private String txStatus;

	@Column(name = "tanggal_aktif")
	private Date tanggalAktif;

	@Column(name = "tanggal_jatuh_tempo")
	private Date tanggalJatuhTempo;

	@Column(name = "tanggal_bayar")
	private Date tanggalBayar;

	@Column(name = "created_date")
	private Timestamp created_date;

	public Double getTxPokok() { return txPokok; }

	public void setTxPokok(Double txPokok) { this.txPokok = txPokok; }

	public Double getTxBunga() { return txBunga; }

	public void setTxBunga(Double txBunga) { this.txBunga = txBunga; }

	public String getTxStatus() { return txStatus; }

	public void setTxStatus(String txStatus) { this.txStatus = txStatus; }

	public Date getTanggalAktif() { return tanggalAktif; }

	public void setTanggalAktif(Date tanggalAktif) {
		this.tanggalAktif = tanggalAktif;
	}

	public Date getTanggalJatuhTempo() { return tanggalJatuhTempo; }

	public void setTanggalJatuhTempo(Date tanggalJatuhTempo) {
		this.tanggalJatuhTempo = tanggalJatuhTempo;
	}

	public Date getTanggalBayar() { return tanggalBayar; }

	public void setTanggalBayar(Date tanggalBayar) {
		this.tanggalBayar = tanggalBayar;
	}

	public Timestamp getCreated_date() { return created_date; }

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public Integer getCicilanKe() { return cicilanKe; }

	public void setCicilanKe(Integer cicilanKe) { this.cicilanKe = cicilanKe; }

	// public KeysTransaksiCicilan getKeysTransaksiCicilan() {
	// 	return keysTransaksiCicilan;
	// }

	// public void setKeysTransaksiCicilan(
	// 		KeysTransaksiCicilan keysTransaksiCicilan) {
	// 	this.keysTransaksiCicilan = keysTransaksiCicilan;
	// }
}
