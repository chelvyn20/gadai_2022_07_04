package id.co.nds.gadai_2022_07_04.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;


import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.DeletingById;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.GettingAllByCriteria;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.RequestMethodById;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.UpdatingById;


public class CustomerModel extends RecordModel {
    @Null(message = "Customer id must be null", groups = { PostingNew.class})
    @NotNull (message = "Customer Id cannot be null", groups= {UpdatingById.class, DeletingById.class})
    @PositiveOrZero (message = "Customer Id must not be less than 0", groups = {GettingAllByCriteria.class, RequestMethodById.class})
    private String custId;   
    
    @NotBlank(message = "Customer name cannot be blank", groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s][a-zA-Z]+)*$", message = "Customer namee pattern must be words only", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    private String custName; 

    @NotNull (message = "KTP cannot be null", groups= {PostingNew.class})
    @PositiveOrZero (message = "KTP must not be positive number", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    private String custKtp; 

    @Pattern(regexp = "^(\\+62|0)[0-9]{9,12}$", message = "Call Numbers must start with 0 or + 62 and followed by 9-12 digits number", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class}) 
    private String custHp;
    
    @Pattern(regexp = "^[P,p,W,w]$", message = "Customer Gender pattern must be 'P' or 'W'", groups = {
        GettingAllByCriteria.class, UpdatingById.class, PostingNew.class })
    private String custJk;

    private String custJenisUsahaId;


    private Double custLimitTxn;
    
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustKtp() {
        return custKtp;
    }
    public void setCustKtp(String custKtp) {
        this.custKtp = custKtp;
    }
    public String getCustHp() {
        return custHp;
    }
    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }
    public String getCustJk() {
        return custJk;
    }
    public void setCustJk(String custJk) {
        this.custJk = custJk;
    }
    public String getCustJenisUsahaId() {
        return custJenisUsahaId;
    }
    public void setCustJenisUsahaId(String custJenisUsahaId) {
        this.custJenisUsahaId = custJenisUsahaId;
    }
    public Double getCustLimitTxn() {
        return custLimitTxn;
    }
    public void setCustLimitTxn(Double custLimitTxn) {
        this.custLimitTxn = custLimitTxn;
    }
}
