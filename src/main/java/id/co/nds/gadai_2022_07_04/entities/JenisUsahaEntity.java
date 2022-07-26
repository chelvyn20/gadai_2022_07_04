package id.co.nds.gadai_2022_07_04.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_jenis_usaha")
public class JenisUsahaEntity {
    @Id
    @Column(name = "jenis_usaha_id")
    private String jenisUsahaId;

    @Column(name = "jenis_usaha_name")
    private String jenisUsahaName;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "created_by")
    private Integer creatorId;

    @Column(name = "updated_by")
    private Integer updaterId;

    @Column(name = "deleted_by")
    private Integer deleterId;

    @Column(name = "rec_status")
    private String recStatus;

    public String getJenisUsahaId() {
        return jenisUsahaId;
    }

    public void setJenisUsahaId(String jenisUsahaId) {
        this.jenisUsahaId = jenisUsahaId;
    }

    public String getJenisUsahaName() {
        return jenisUsahaName;
    }

    public void setJenisUsahaName(String jenisUsahaName) {
        this.jenisUsahaName = jenisUsahaName;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Integer getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(Integer deleterId) {
        this.deleterId = deleterId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }
}
