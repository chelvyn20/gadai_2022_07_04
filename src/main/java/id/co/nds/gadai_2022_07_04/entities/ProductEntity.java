
package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="ms_product")
public class ProductEntity {

	@Id
	@GenericGenerator(name = "product_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.ProductIdGenerator")
	@GeneratedValue(generator = "product_id_seq")
	@Column(name = "product_id")
	private String productId;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_desc")
	private String productDesc;

	@Column(name = "product_ltv")
	private Double productLtv;

	@Column(name = "product_tenor")
	private Integer productTenor;

	@Column(name = "biaya_adm_buka_type")
	private String biayaAdmBukaType;

	@Column(name = "biaya_adm_buka_val")
	private Double biayaAdmBukaVal;

	@Column(name = "biaya_adm_tutup_type")
	private String biayaAdmTutupType;

	@Column(name = "biaya_adm_tutup_val")
	private Double biayaAdmTutupVal;

	@Column(name = "biaya_js_peny_rate")
	private Double biayaJsPenyRate;

	@Column(name = "biaya_js_peny_per")
	private Integer biayaJsPenyPer;

	@Column(name = "biaya_denda_keterlambatan_rate")
	private Double bDKeterlambatanRate;

	@Column(name = "biaya_denda_keterlambatan_per")
	private Integer BDKeterlambatanPer;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "deleted_date")
	private Timestamp deletedDate;

	@Column(name = "deleted_by")
	private String deletedBy;

	@Column(name = "rec_status")
	private String recStatus;

	public String getProductId() { return productId; }

	public void setProductId(String productId) { this.productId = productId; }

	public String getProductType() { return productType; }

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() { return productName; }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() { return productDesc; }

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Double getProductLtv() { return productLtv; }

	public void setProductLtv(Double productLtv) {
		this.productLtv = productLtv;
	}

	public Integer getProductTenor() { return productTenor; }

	public void setProductTenor(Integer productTenor) {
		this.productTenor = productTenor;
	}

	public String getBiayaAdmBukaType() { return biayaAdmBukaType; }

	public void setBiayaAdmBukaType(String biayaAdmBukaType) {
		this.biayaAdmBukaType = biayaAdmBukaType;
	}

	public Double getBiayaAdmBukaVal() { return biayaAdmBukaVal; }

	public void setBiayaAdmBukaVal(Double biayaAdmBukaVal) {
		this.biayaAdmBukaVal = biayaAdmBukaVal;
	}

	public String getBiayaAdmTutupType() { return biayaAdmTutupType; }

	public void setBiayaAdmTutupType(String biayaAdmTutupType) {
		this.biayaAdmTutupType = biayaAdmTutupType;
	}

	public Double getBiayaAdmTutupVal() { return biayaAdmTutupVal; }

	public void setBiayaAdmTutupVal(Double biayaAdmTutupVal) {
		this.biayaAdmTutupVal = biayaAdmTutupVal;
	}

	public Double getBiayaJsPenyRate() { return biayaJsPenyRate; }

	public void setBiayaJsPenyRate(Double biayaJsPenyRate) {
		this.biayaJsPenyRate = biayaJsPenyRate;
	}

	public Integer getBiayaJsPenyPer() { return biayaJsPenyPer; }

	public void setBiayaJsPenyPer(Integer biayaJsPenyPer) {
		this.biayaJsPenyPer = biayaJsPenyPer;
	}

	public Double getbDKeterlambatanRate() { return bDKeterlambatanRate; }

	public void setbDKeterlambatanRate(Double bDKeterlambatanRate) {
		this.bDKeterlambatanRate = bDKeterlambatanRate;
	}

	public Integer getBDKeterlambatanPer() { return BDKeterlambatanPer; }

	public void setBDKeterlambatanPer(Integer bDKeterlambatanPer) {
		BDKeterlambatanPer = bDKeterlambatanPer;
	}

	public Timestamp getCreatedDate() { return createdDate; }

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() { return createdBy; }

	public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

	public Timestamp getUpdatedDate() { return updatedDate; }

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() { return updatedBy; }

	public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

	public Timestamp getDeletedDate() { return deletedDate; }

	public void setDeletedDate(Timestamp deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getDeletedBy() { return deletedBy; }

	public void setDeletedBy(String deletedBy) { this.deletedBy = deletedBy; }

	public String getRecStatus() { return recStatus; }

	public void setRecStatus(String recStatus) { this.recStatus = recStatus; }

    public void getBiayaJsPenyRate(Double productBiayaJasaPeny) {}

}
