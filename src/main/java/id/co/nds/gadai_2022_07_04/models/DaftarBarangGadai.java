
package id.co.nds.gadai_2022_07_04.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.PostingNew;

public class DaftarBarangGadai {

	private Integer NoUrut;

	@NotBlank(message = "Nama Barang is Required", groups = {
			PostingNew.class })
	private String namaBarang;

	@NotBlank(message = "Description is Required", groups = {
			PostingNew.class })
	private String Kondisi;

	@NotNull(message = "Jumlah is Required")
	private Double jlh;
	
	@NotNull(message = "Harga satuan is Required", groups = {
			PostingNew.class })
	private Double hargaPerSatuan;

	public Integer getNoUrut() { return NoUrut; }

	public void setNoUrut(Integer noUrut) { NoUrut = noUrut; }

	public String getNamaBarang() { return namaBarang; }

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getKondisi() { return Kondisi; }

	public void setKondisi(String kondisi) { Kondisi = kondisi; }

	public Double getJlh() { return jlh; }

	public void setJlh(Double jlh) { this.jlh = jlh; }

	public Double getHargaPerSatuan() { return hargaPerSatuan; }

	public void setHargaPerSatuan(Double hargaPerSatuan) {
		this.hargaPerSatuan = hargaPerSatuan;
	}
}
