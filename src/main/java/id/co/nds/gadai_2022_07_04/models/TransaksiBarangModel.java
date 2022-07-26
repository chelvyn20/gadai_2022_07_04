
package id.co.nds.gadai_2022_07_04.models;

public class TransaksiBarangModel {

	private String noTransaksi;
	private Integer noUrut;
	private String namaBarang;
	private String kondisi;
	private Integer jumlah;
	private Double hargaPersatuan;

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public Integer getNoUrut() { return noUrut; }

	public void setNoUrut(Integer noUrut) { this.noUrut = noUrut; }

	public String getNamaBarang() { return namaBarang; }

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getKondisi() { return kondisi; }

	public void setKondisi(String kondisi) { this.kondisi = kondisi; }

	public Integer getJumlah() { return jumlah; }

	public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }

	public Double getHargaPersatuan() { return hargaPersatuan; }

	public void setHargaPersatuan(Double hargaPersatuan) {
		this.hargaPersatuan = hargaPersatuan;
	}

}
