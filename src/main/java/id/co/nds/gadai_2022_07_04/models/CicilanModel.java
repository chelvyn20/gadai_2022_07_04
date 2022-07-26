package id.co.nds.gadai_2022_07_04.models;

public class CicilanModel {

	private String noTransaksi;
	private Integer cicilan_ke;
	private Double tx_pokok;
	private Double tx_bunga;
	private String tx_status;
	private String tanggalAktif;
	private String tanggalJatuhTempo;
	private String tanggalBayar;
	public String getNoTransaksi() { return noTransaksi; }
	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}
	public Integer getCicilan_ke() { return cicilan_ke; }
	public void setCicilan_ke(Integer cicilan_ke) { this.cicilan_ke = cicilan_ke; }
	public Double getTx_pokok() { return tx_pokok; }
	public void setTx_pokok(Double tx_pokok) { this.tx_pokok = tx_pokok; }
	public Double getTx_bunga() { return tx_bunga; }
	public void setTx_bunga(Double tx_bunga) { this.tx_bunga = tx_bunga; }
	public String getTx_status() { return tx_status; }
	public void setTx_status(String tx_status) { this.tx_status = tx_status; }
	public String getTanggalAktif() { return tanggalAktif; }
	public void setTanggalAktif(String tanggalAktif) {
		this.tanggalAktif = tanggalAktif;
	}
	public String getTanggalJatuhTempo() { return tanggalJatuhTempo; }
	public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
		this.tanggalJatuhTempo = tanggalJatuhTempo;
	}
	public String getTanggalBayar() { return tanggalBayar; }
	public void setTanggalBayar(String tanggalBayar) {
		this.tanggalBayar = tanggalBayar;
	}

	
}
