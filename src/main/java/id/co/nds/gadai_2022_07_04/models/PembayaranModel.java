
package id.co.nds.gadai_2022_07_04.models;

import java.util.List;

public class PembayaranModel {

	private String noTransaksi;
	private String custId;
	private String custName;
	private String cicDateBegin;
	private String cicDateEnd;
	private String metodeBayar;
	private Double jumlahInputPembayaran;
	private Double diskon;
	private List<CicilanModel> SelectedNoCic;

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public String getCustId() { return custId; }

	public void setCustId(String custId) { this.custId = custId; }

	public String getCustName() { return custName; }

	public void setCustName(String custName) { this.custName = custName; }

	public String getCicDateBegin() { return cicDateBegin; }

	public void setCicDateBegin(String cicDateBegin) {
		this.cicDateBegin = cicDateBegin;
	}

	public String getCicDateEnd() { return cicDateEnd; }

	public void setCicDateEnd(String cicDateEnd) {
		this.cicDateEnd = cicDateEnd;
	}

	public String getMetodeBayar() { return metodeBayar; }

	public void setMetodeBayar(String metodeBayar) {
		this.metodeBayar = metodeBayar;
	}

	public Double getJumlahInputPembayaran() { return jumlahInputPembayaran; }

	public void setJumlahInputPembayaran(Double jumlahInputPembayaran) {
		this.jumlahInputPembayaran = jumlahInputPembayaran;
	}

	public Double getDiskon() { return diskon; }

	public void setDiskon(Double diskon) { this.diskon = diskon; }

	public List<CicilanModel> getSelectedNoCic() { return SelectedNoCic; }

	public void setSelectedNoCic(List<CicilanModel> selectedNoCic) {
		SelectedNoCic = selectedNoCic;
	}

}
