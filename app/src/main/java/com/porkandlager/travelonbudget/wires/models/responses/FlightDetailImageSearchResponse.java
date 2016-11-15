package com.porkandlager.travelonbudget.wires.models.responses;

import com.google.gson.annotations.SerializedName;
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution;

import java.util.List;

/**
 * Created by tista on 11/15/16.
 */

public class FlightDetailImageSearchResponse {

    private int status;
    private String message;

    @SerializedName("data")
    private List<PhotoWithAttribution> photos;

    public List<PhotoWithAttribution> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoWithAttribution> photos) {
        this.photos = photos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
