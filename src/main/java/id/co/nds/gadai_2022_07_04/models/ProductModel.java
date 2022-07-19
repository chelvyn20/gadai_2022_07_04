
package id.co.nds.gadai_2022_07_04.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import id.co.nds.gadai_2022_07_04.controllers.GroupController.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.UpdatingById;

public class ProductModel {

	@NotBlank(message = "ProductType Cant be Blank", groups = {
			PostingNew.class })
	@Pattern(regexp = "^(Konsinyasi Cicilan Tetap)|(Konsinyasi Cicilan Fleksibel)$", message = "Product Type is Not Found", groups = {
			PostingNew.class })
	private String productType;
	private String productid;

	@NotBlank(message = "Name Product is Required", groups = { PostingNew.class,
			UpdatingById.class })
	private String productName;
	private String productDesc;

	@NotNull(message = "Jangka Waktu is Required", groups = { PostingNew.class,
			UpdatingById.class })
	private Integer productJangkawaktu;

	@NotNull(message = "Product LTV is Required", groups = { PostingNew.class,
			UpdatingById.class })
	private Double productLTV;

	@NotNull(message = "Biaya Admin Buka is Required", groups = {
			PostingNew.class, UpdatingById.class })
	private Double productBiayaAdminBuka;

	@NotBlank(message = "Biaya Admin Buka Type is Required", groups = {
			PostingNew.class, UpdatingById.class })
	@Pattern(regexp = "^(PERSEN)|(NOMINAL)$", message = "Biaya Admin Buka Type is Not Found", groups = {
			PostingNew.class, UpdatingById.class })
	private String productBiayaAdminBukaType;

	@NotBlank(message = "Biaya Admin Tutup Type is Required", groups = {
			PostingNew.class, UpdatingById.class })
	@Pattern(regexp = "^(PERSEN)|(NOMINAL)$", message = "Biaya Admin Tutup Type is Not Found", groups = {
			PostingNew.class, UpdatingById.class })
	private String productBiayaAdminTutupType;

	@NotNull(message = "Biaya Admin Tutup is Required", groups = {
			PostingNew.class, UpdatingById.class })
	private Double productBiayaAdminTutup;

	@NotNull(message = "Biaya Jasa Peny is Required", groups = {
			PostingNew.class, UpdatingById.class })
	private Double productBiayaJasaPeny;

	@NotNull(message = "Biaya Biaya Peny Periode is Required", groups = {
			PostingNew.class, UpdatingById.class })
	private Integer productBiayaPenyPeriode;

	@NotNull(message = "Biaya Denda is Required", groups = { PostingNew.class,
			UpdatingById.class })
	private Double productBiayaDenda;

	@NotNull(message = "Biaya Denda Periode is Required", groups = {
			PostingNew.class, UpdatingById.class })
	private Integer productBiayaDendaPeriode;

	private String actorId;
	private String recStatus;

	public String getProductType() { return productType; }

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductid() { return productid; }

	public void setProductid(String productid) { this.productid = productid; }

	public String getProductName() { return productName; }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() { return productDesc; }

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductJangkawaktu() { return productJangkawaktu; }

	public void setProductJangkawaktu(Integer productJangkawaktu) {
		this.productJangkawaktu = productJangkawaktu;
	}

	public Double getProductLTV() { return productLTV; }

	public void setProductLTV(Double productLTV) {
		this.productLTV = productLTV;
	}

	public Double getProductBiayaAdminBuka() { return productBiayaAdminBuka; }

	public void setProductBiayaAdminBuka(Double productBiayaAdminBuka) {
		this.productBiayaAdminBuka = productBiayaAdminBuka;
	}

	public String getProductBiayaAdminBukaType() {
		return productBiayaAdminBukaType;
	}

	public void setProductBiayaAdminBukaType(String productBiayaAdminBukaType) {
		this.productBiayaAdminBukaType = productBiayaAdminBukaType;
	}

	public String getProductBiayaAdminTutupType() {
		return productBiayaAdminTutupType;
	}

	public void setProductBiayaAdminTutupType(
			String productBiayaAdminTutupType) {
		this.productBiayaAdminTutupType = productBiayaAdminTutupType;
	}

	public Double getProductBiayaAdminTutup() { return productBiayaAdminTutup; }

	public void setProductBiayaAdminTutup(Double productBiayaAdminTutup) {
		this.productBiayaAdminTutup = productBiayaAdminTutup;
	}

	public Double getProductBiayaJasaPeny() { return productBiayaJasaPeny; }

	public void setProductBiayaJasaPeny(Double productBiayaJasaPeny) {
		this.productBiayaJasaPeny = productBiayaJasaPeny;
	}

	public Integer getProductBiayaPenyPeriode() {
		return productBiayaPenyPeriode;
	}

	public void setProductBiayaPenyPeriode(Integer productBiayaPenyPeriode) {
		this.productBiayaPenyPeriode = productBiayaPenyPeriode;
	}

	public Double getProductBiayaDenda() { return productBiayaDenda; }

	public void setProductBiayaDenda(Double productBiayaDenda) {
		this.productBiayaDenda = productBiayaDenda;
	}

	public Integer getProductBiayaDendaPeriode() {
		return productBiayaDendaPeriode;
	}

	public void setProductBiayaDendaPeriode(Integer productBiayaDendaPeriode) {
		this.productBiayaDendaPeriode = productBiayaDendaPeriode;
	}

	public String getActorId() { return actorId; }

	public void setActorId(String actorId) { this.actorId = actorId; }

	public String getRecStatus() { return recStatus; }

	public void setRecStatus(String recStatus) { this.recStatus = recStatus; }

}
