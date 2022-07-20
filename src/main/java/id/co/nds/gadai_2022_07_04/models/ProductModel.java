package id.co.nds.gadai_2022_07_04.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import id.co.nds.gadai_2022_07_04.controllers.GroupController.DeletingById;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.UpdatingById;

public class ProductModel extends RecordModel{

    @Null(message = "Product id must be null", groups = { PostingNew.class})
    @NotNull (message = "Product Id cannot be null", groups= {UpdatingById.class, DeletingById.class})
    private String productId;

    @NotBlank(message = "Product Type cannot be blank", groups = {PostingNew.class})
    private String productTipe;
    
    @NotBlank(message = "Product name cannot be blank", groups = {PostingNew.class})
    private String productName;

    private String productDesc;
    @NotNull (message = "Product LTV cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private Double productLtv;
    @NotNull (message = "Product Jangka Waktu cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private Integer productJangkaWaktu;
    @NotNull (message = "Product Admin Buka Tipe cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private String productBiayaAdminBukaTipe;
    @NotNull (message = "Product Admin Buka  cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private Double productBiayaAdminBuka;
    @NotNull (message = "Product Biaya Admin Tutup Tipe cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private String productBiayaAdminTutupTipe;
    @NotNull (message = "Product Biaya Admin Tutup  cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private Double productBiayaAdminTutup;
    @NotNull (message = "Product Biaya Jasa Penyimpanan cannot be null", groups= {PostingNew.class, UpdatingById.class, DeletingById.class})

    private Double productBiayaJasaPeny;
    @NotNull (message = "Product Periode jasa penyimpanan cannot be null", groups= {UpdatingById.class, DeletingById.class})

    private Integer productBiayaJasaPenyPeriode;
    @NotNull (message = "Product Biaya Denda cannot be null", groups= {UpdatingById.class, DeletingById.class})

    private Double productBiayaDenda;
    @NotNull (message = "Product Periode Denda cannot be null", groups= {UpdatingById.class, DeletingById.class})

    private Integer productBiayaDendaPeriode;

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

    
    
}
