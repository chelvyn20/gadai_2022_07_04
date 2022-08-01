
package id.co.nds.gadai_2022_07_04.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tx_transaksi_cicilan_tetap")
public class InformasiTransaksiEntity {
	
	@Id
	@Column(name = "no_transaksi")
	private String noTransaksi;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "tanggal_tx")
	private Date tanggalTx;


	@Column(name = "total_nilai_pinj")
	private Double totalNilaiPinj;

	@Column(name = "product_tenor")
	private Integer productTenor;

	@Column(name = "tanggal_jatuh_tempo")
	private Date tglJatuhTempo;

	@Column(name = "product_id")
	private String productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_desc")
	private String productDesc;

	

    public InformasiTransaksiEntity orElse(Object object) { return null; }

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public String getCustomerId() { return customerId; }

	public void setCustomerId(String customerId) { this.customerId = customerId; }

	public String getCustomerName() { return customerName; }

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getTanggalTx() { return tanggalTx; }

	public void setTanggalTx(Date tanggalTx) { this.tanggalTx = tanggalTx; }

	public Double getTotalNilaiPinj() { return totalNilaiPinj; }

	public void setTotalNilaiPinj(Double totalNilaiPinj) {
		this.totalNilaiPinj = totalNilaiPinj;
	}

	public Integer getProductTenor() { return productTenor; }

	public void setProductTenor(Integer productTenor) {
		this.productTenor = productTenor;
	}

	public Date getTglJatuhTempo() { return tglJatuhTempo; }

	public void setTglJatuhTempo(Date tglJatuhTempo) {
		this.tglJatuhTempo = tglJatuhTempo;
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





}
