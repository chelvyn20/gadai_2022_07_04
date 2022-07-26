
package id.co.nds.gadai_2022_07_04.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import id.co.nds.gadai_2022_07_04.keys.KeysGadai;

@Entity
@Table(name = "tx_transaksi_barang")
@IdClass(KeysGadai.class)
public class TransaksiBarangEntity {

	@Id
	// @GenericGenerator(name = "transaksi_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.TransaksiIdGenerator")
	// @GeneratedValue(generator = "transaksi_id_seq")
	// @JoinColumn(name = "no_transaksi")
	private String noTransaksi;

	@Id
	// @GenericGenerator(name = "noUrut_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.TransaksiNoUrutIdGenerator")
	// @GeneratedValue(generator = "noUrut_id_seq")
	private Integer noUrut;
	// @EmbeddedId
	// private KeysGadai keysGadai;

	@Column(name = "nama_barang")
	private String namaBarang;

	@Column(name = "kondisi")
	private String kondisi;

	@Column(name = "jumlah")
	private Integer jumlah;

	@Column(name = "harga_per_satuan")
	private Double hargaPerSatuan;

	

	public TransaksiBarangEntity() {}

	public String getNamaBarang() { return namaBarang; }

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getKondisi() { return kondisi; }

	public void setKondisi(String kondisi) { this.kondisi = kondisi; }

	public Integer getJumlah() { return jumlah; }

	public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }

	public Double getHargaPerSatuan() { return hargaPerSatuan; }

	public void setHargaPerSatuan(Double hargaPerSatuan) {
		this.hargaPerSatuan = hargaPerSatuan;
	}

	// public KeysGadai getKeysGadai() { return keysGadai; }

	// public void setKeysGadai(KeysGadai keysGadai) { this.keysGadai = keysGadai; }

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public Integer getNoUrut() { return noUrut; }

	public void setNoUrut(Integer noUrut) { this.noUrut = noUrut; }

	
	

}
