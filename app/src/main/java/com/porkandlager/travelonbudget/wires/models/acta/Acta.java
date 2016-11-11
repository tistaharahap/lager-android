package com.porkandlager.travelonbudget.wires.models.acta;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Acta<T> {

    @SerializedName("subject")
    private ActaObj subject;

    @SerializedName("action")
    private String action;

    @SerializedName("object")
    private ActaObj object;

    @SerializedName("meta")
    private T meta;

    public ActaObj getSubject() {
        return subject;
    }

    public T getMeta() {
        return meta;
    }

    public void setSubject(ActaObj subject) {
        this.subject = subject;
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
}
