package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Content {

    @SerializedName("wikipedia_url")
    private String wikipediaUrl;

    @SerializedName("location")
    private Location location;

    @SerializedName("picture")
    private String pictureUrl;

    @SerializedName("description")
    private String description;

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public Location getLocation() {
        return location;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getDescription() {
        return description;
    }

}
