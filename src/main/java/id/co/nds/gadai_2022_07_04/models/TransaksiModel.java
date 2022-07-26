
package id.co.nds.gadai_2022_07_04.models;

import java.util.List;

public class TransaksiModel {

	private String noTransaksi;
	private String productId;
	private String productName;
	private String trxDateBegin;
	private String trxDateEnd;
	private String StatusTrans;
	private String custKtp;
	private String custId;
	private Double nilaipencairanPelanggan;
	private Double diskonAdmBuka;
	private List<TransaksiBarangModel> daftarBarangGadai;
	private String actorId;

	public String getActorId() { return actorId; }

	public void setActorId(String actorId) { this.actorId = actorId; }

	public String getProductId() { return productId; }

	public void setProductId(String productId) { this.productId = productId; }

	public String getCustId() { return custId; }

	public void setCustId(String custId) { this.custId = custId; }

	public Double getNilaipencairanPelanggan() {
		return nilaipencairanPelanggan;
	}

	public void setNilaipencairanPelanggan(Double nilaipencairanPelanggan) {
		this.nilaipencairanPelanggan = nilaipencairanPelanggan;
	}

	public Double getDiskonAdmBuka() { return diskonAdmBuka; }

	public void setDiskonAdmBuka(Double diskonAdmBuka) {
		this.diskonAdmBuka = diskonAdmBuka;
	}

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public String getProductName() { return productName; }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTrxDateBegin() { return trxDateBegin; }

	public void setTrxDateBegin(String trxDateBegin) {
		this.trxDateBegin = trxDateBegin;
	}

	public String getTrxDateEnd() { return trxDateEnd; }

	public void setTrxDateEnd(String trxDateEnd) { this.trxDateEnd = trxDateEnd; }

	public String getStatusTrans() { return StatusTrans; }

	public void setStatusTrans(String statusTrans) { StatusTrans = statusTrans; }

	public String getCustKtp() { return custKtp; }

	public void setCustKtp(String custKtp) { this.custKtp = custKtp; }

	public List<TransaksiBarangModel> getDaftarBarangGadai() {
		return daftarBarangGadai;
	}

	public void setDaftarBarangGadai(List<TransaksiBarangModel> daftarBarangGadai) {
		this.daftarBarangGadai = daftarBarangGadai;
	}
}
