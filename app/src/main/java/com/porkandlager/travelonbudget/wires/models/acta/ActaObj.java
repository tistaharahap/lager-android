package com.porkandlager.travelonbudget.wires.models.acta;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class ActaObj {

    @SerializedName("id")
    private String Id;

    @SerializedName("kind")
    private String kind;

    public String getId() {
        return Id;
    }

    public String getKind() {
        return kind;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
