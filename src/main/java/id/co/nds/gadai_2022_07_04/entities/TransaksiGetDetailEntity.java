
package id.co.nds.gadai_2022_07_04.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tx_transaksi_cicilan_tetap")
public class TransaksiGetDetailEntity {

	@Id
	@Column(name = "no_transaksi")
	private String noTransaksi;

	@Column(name = "customer_id")
	private String custId;

	@Column(name = "customer_name")
	private String custName;

	@Column(name = "tanggal_tx")
	private Date tanggalTx;

	@Column(name = "product_id")
	private String productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_desc")
	private String productDesc;

	@OneToMany(targetEntity = TransaksiBarangEntity.class, mappedBy = "noTransaksi")
	List<TransaksiBarangEntity> DaftarBarangGadai;

	public String getCustId() { return custId; }

	public void setCustId(String custId) { this.custId = custId; }

	public String getCustName() { return custName; }

	public void setCustName(String custName) { this.custName = custName; }

	public Date getTanggalTx() { return tanggalTx; }

	public void setTanggalTx(Date tanggalTx) { this.tanggalTx = tanggalTx; }

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public String getProductId() { return productId; }

	public void setProductId(String productId) { this.productId = productId; }

	public String getProductName() { return productName; }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() { return productDesc; }

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public List<TransaksiBarangEntity> getDaftarBarangGadai() {
		return DaftarBarangGadai;
	}

	public void setDaftarBarangGadai(
		List<TransaksiBarangEntity> daftarBarangGadai) {
		DaftarBarangGadai = daftarBarangGadai;
	}
}