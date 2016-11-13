package com.porkandlager.travelonbudget.wires.models.acta;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Acta<T> {

    @SerializedName("actor")
    private ActaObj actor;

    @SerializedName("action")
    private String action;

    @SerializedName("object")
    private ActaObj object;

    @SerializedName("meta")
    private T meta;

    public T getMeta() {
        return meta;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ActaObj getObject() {
        return object;
    }

    public void setObject(ActaObj object) {
        this.object = object;
    }

    public void setMeta(T meta) {
        this.meta = meta;
    }

    public ActaObj getActor() {
        return actor;
    }

    public void setActor(ActaObj actor) {
        this.actor = actor;
    }
}
