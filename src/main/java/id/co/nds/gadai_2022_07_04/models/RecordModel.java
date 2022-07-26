package id.co.nds.gadai_2022_07_04.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.GettingAllByCriteria;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.RequestMethodById;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.UpdatingById;

public class RecordModel {
    @PositiveOrZero(message = "Actor Id must not be less than 0", groups = { PostingNew.class,
            RequestMethodById.class })
    private Integer actorId;

    @Pattern(regexp = "^[A,a,N,n]$", message = "Product recStatus pattern must be words only", groups = {
            GettingAllByCriteria.class, UpdatingById.class })
    private String recStatus;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
