package com.xfinity.simpsonsviewer.multipleappsharedcodebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Model {

    @SerializedName("Text")
    private String Text;

    @SerializedName("Icon")
    private HashMap<String,String> Icon = new HashMap<>();

    @SerializedName("URL")
    private String Url;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public HashMap<String, String> getIcon() {
        return Icon;
    }

    public void setIcon(HashMap<String, String> icon) {
        Icon = icon;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


}
