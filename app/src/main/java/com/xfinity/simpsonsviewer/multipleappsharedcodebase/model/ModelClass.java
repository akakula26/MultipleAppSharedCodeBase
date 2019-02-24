package com.xfinity.simpsonsviewer.multipleappsharedcodebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelClass {

    public List<Model> getList() {
        return list;
    }

    public void setList(List<Model> list) {
        this.list = list;
    }

    @SerializedName("RelatedTopics")
    private List<Model> list;
}
