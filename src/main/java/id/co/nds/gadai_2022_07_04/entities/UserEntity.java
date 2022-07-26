
package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ms_user")
public class UserEntity {

    @Id
    @GenericGenerator(name = "user_id_req", strategy = "id.co.nds.gadai_2022_07_04.generators.UserIdGenerator")
    @GeneratedValue(generator = "user_id_req")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone")
    private String user_phone;

    @Column(name = "user_notes")
    private String userNotes;

    @Column(name = "max_limit")
    private Double maxLimit;

    @OneToMany(targetEntity = ProductEntity.class, mappedBy = "userId")
    List<ProductEntity> Products;

    @OneToMany(targetEntity = TransaksiCicilanTetapEntity.class, mappedBy = "createdId")
    List<TransaksiCicilanTetapEntity> transaksi;

    public List<ProductEntity> getProducts() { return Products; }

    public void setProducts(List<ProductEntity> products) {
        Products = products;
    }

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "updated_by")
    private String updateBy;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "rec_status")
    private String recStatus;

    public String getUserId() { return userId; }

    public void setUserId(String user_id) { this.userId = user_id; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUser_phone() { return user_phone; }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUserNotes() { return userNotes; }

    public void setUserNotes(String userNotes) { this.userNotes = userNotes; }

    public Double getMaxLimit() { return maxLimit; }

    public void setMaxLimit(Double maxLimit) { this.maxLimit = maxLimit; }

    public Date getRegisterDate() { return registerDate; }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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

    public String getUpdateBy() { return updateBy; }

    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }

    public Timestamp getDeletedDate() { return deletedDate; }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() { return deletedBy; }

    public void setDeletedBy(String deletedBy) { this.deletedBy = deletedBy; }

    public String getRecStatus() { return recStatus; }

    public void setRecStatus(String recStatus) { this.recStatus = recStatus; }

    public List<TransaksiCicilanTetapEntity> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(List<TransaksiCicilanTetapEntity> transaksi) {
        this.transaksi = transaksi;
    }

}
