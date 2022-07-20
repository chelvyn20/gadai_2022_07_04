package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ms_product")
public class ProductEntity {
    
    @Id
	@GenericGenerator(name = "product_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.ProductIdGenerator")
	@GeneratedValue(generator = "product_id_seq")    
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_type")
    private String productTipe;
    
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_ltv")
    private Double productLtv;

    @Column(name = "product_tenor")
    private Integer productJangkaWaktu;

    @Column(name = "biaya_adm_buka_type")
    private String productBiayaAdminBukaTipe;

    @Column(name = "biaya_adm_buka_val")
    private Double productBiayaAdminBuka;

    @Column(name = "biaya_adm_tutup_type")
    private String productBiayaAdminTutupTipe;

    @Column(name = "biaya_adm_tutup_val")
    private Double productBiayaAdminTutup;

    @Column(name = "biaya_js_peny_rate")
    private Double productBiayaJasaPeny;

    @Column(name = "biaya_js_peny_per")
    private Integer productBiayaJasaPenyPeriode;

    @Column(name = "biaya_denda_keterlambatan_rate")
    private Double productBiayaDenda;

    @Column(name = "biaya_denda_keterlambatan_per")
    private Integer productBiayaDendaPeriode;

    @Column(name="created_date")
    private Timestamp createdDate;
    
    @Column(name="updated_date")
    private Timestamp updatedDate;

    @Column(name="deleted_date")
    private Timestamp deletedDate;

    @Column(name="created_by")
    private String creatorId;

    @Column(name="updated_by")
    private String updaterId;

    @Column(name="deleted_by")
    private String deleterId;

    @Column(name="rec_status")
    private String recStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTipe() {
        return productTipe;
    }

    public void setProductTipe(String productTipe) {
        this.productTipe = productTipe;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getProductLtv() {
        return productLtv;
    }

    public void setProductLtv(Double productLtv) {
        this.productLtv = productLtv;
    }

    public Integer getProductJangkaWaktu() {
        return productJangkaWaktu;
    }

    public void setProductJangkaWaktu(Integer productJangkaWaktu) {
        this.productJangkaWaktu = productJangkaWaktu;
    }

    public String getProductBiayaAdminBukaTipe() {
        return productBiayaAdminBukaTipe;
    }

    public void setProductBiayaAdminBukaTipe(String productBiayaAdminBukaTipe) {
        this.productBiayaAdminBukaTipe = productBiayaAdminBukaTipe;
    }

    public Double getProductBiayaAdminBuka() {
        return productBiayaAdminBuka;
    }

    public void setProductBiayaAdminBuka(Double productBiayaAdminBuka) {
        this.productBiayaAdminBuka = productBiayaAdminBuka;
    }

    public String getProductBiayaAdminTutupTipe() {
        return productBiayaAdminTutupTipe;
    }

    public void setProductBiayaAdminTutupTipe(String productBiayaAdminTutupTipe) {
        this.productBiayaAdminTutupTipe = productBiayaAdminTutupTipe;
    }

    public Double getProductBiayaAdminTutup() {
        return productBiayaAdminTutup;
    }

    public void setProductBiayaAdminTutup(Double productBiayaAdminTutup) {
        this.productBiayaAdminTutup = productBiayaAdminTutup;
    }

    public Double getProductBiayaJasaPeny() {
        return productBiayaJasaPeny;
    }

    public void setProductBiayaJasaPeny(Double productBiayaJasaPeny) {
        this.productBiayaJasaPeny = productBiayaJasaPeny;
    }

    public Integer getProductBiayaJasaPenyPeriode() {
        return productBiayaJasaPenyPeriode;
    }

    public void setProductBiayaJasaPenyPeriode(Integer productBiayaJasaPenyPeriode) {
        this.productBiayaJasaPenyPeriode = productBiayaJasaPenyPeriode;
    }

    public Double getProductBiayaDenda() {
        return productBiayaDenda;
    }

    public void setProductBiayaDenda(Double productBiayaDenda) {
        this.productBiayaDenda = productBiayaDenda;
    }

    public Integer getProductBiayaDendaPeriode() {
        return productBiayaDendaPeriode;
    }

    public void setProductBiayaDendaPeriode(Integer productBiayaDendaPeriode) {
        this.productBiayaDendaPeriode = productBiayaDendaPeriode;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(String deleterId) {
        this.deleterId = deleterId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    
   

}
