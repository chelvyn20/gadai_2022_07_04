package id.co.nds.gadai_2022_07_04.models;

import javax.validation.constraints.Pattern;

import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.GettingAllByCriteria;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.UpdatingById;

public class RecordModel {
    private String actorId;

    @Pattern(regexp = "^[A,a,N,n]$", message = "Product recStatus pattern must be words only", groups = {
            GettingAllByCriteria.class, UpdatingById.class })
    private String recStatus;

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
