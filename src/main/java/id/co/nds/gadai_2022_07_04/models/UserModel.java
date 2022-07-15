
package id.co.nds.gadai_2022_07_04.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.apache.logging.log4j.message.Message;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.co.nds.gadai_2022_07_04.controllers.GroupController.DeletingById;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.GettingAllByCriteria;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.UpdatingById;

public class UserModel {

    // @NotNull(message = "Do not input UserId", groups = })
    @Null(message = "UserId must have", groups = { PostingNew.class })
    // @Pattern(regexp = "^{1,15}$", message = "User Id Max Length 15", groups =
    // {
    // PostingNew.class })
    private String userId;

    // @Pattern(regexp = "^[a-zA-z0-9]{1,30}$", message = "UserName Max Length
    // 30", groups = {
    // PostingNew.class, UpdatingById.class })
    @NotBlank(message = "UserName must Input, cant be Null", groups = {
            PostingNew.class, UpdatingById.class })
    private String userName;

    @NotBlank(message = "User Phone must Input, cant be Null")
    @Pattern(regexp = "^[0,+62][0-9]{9,12}$", message = "User Phone pattern input is invalid ,Must be  min 9 and max 12 numeric , and Start with 0 , +62", groups = {
            PostingNew.class, GettingAllByCriteria.class, UpdatingById.class })
    private String userHp;

    // @Pattern(regexp = "^[a-zA-z0-9]{0,50}$", message = "User Description Max
    // length 50", groups = {
    // PostingNew.class, UpdatingById.class })
    private String userDecs;

    @NotBlank(message = "Transaction User must be Input, can't be null", groups = {
            PostingNew.class })
    // @Pattern(regexp = "^[0-9]{0,17}$", message = "User Transaction max Length
    // 17", groups = {
    // PostingNew.class, UpdatingById.class })
    private String userTxnLimit;

    // @NotBlank(message = "Date Must Be Input", groups = { PostingNew.class })
    // @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    // @JsonFormat(pattern = "yyyy/MM/dd")
    @Pattern(regexp = "^[0-9]{4}-(02-(0[1-9]|[12][0-9])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))$", message = "Input Date correctly with yyyy/MM/DD format", groups = {
            PostingNew.class})

    private String userRegDate;

    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp deletedDate;
    private String actorId;
    private String recStatus;

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserHp() { return userHp; }

    public void setUserHp(String userHp) { this.userHp = userHp; }

    public String getUserDecs() { return userDecs; }

    public void setUserDecs(String userDecs) { this.userDecs = userDecs; }

    public String getUserTxnLimit() { return userTxnLimit; }

    public void setUserTxnLimit(String userTxnLimit) {
        this.userTxnLimit = userTxnLimit;
    }

    public String getUserRegDate() { return userRegDate; }

    public void setUserRegDate(String userRegDate) {
        this.userRegDate = userRegDate;
    }

    public Timestamp getCreatedDate() { return createdDate; }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() { return updatedDate; }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getDeletedDate() { return deletedDate; }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getActorId() { return actorId; }

    public void setActorId(String actorId) { this.actorId = actorId; }

    public String getRecStatus() { return recStatus; }

    public void setRecStatus(String recStatus) { this.recStatus = recStatus; }

}
