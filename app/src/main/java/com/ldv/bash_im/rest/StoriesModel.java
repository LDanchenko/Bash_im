
package com.ldv.bash_im.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StoriesModel {


    @SerializedName("site")
    private String site;

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("link")
    private String link;

    @SerializedName("elementPureHtml")
    private String elementPureHtml;

    public StoriesModel(){}

    public String getSite() {
        return site;
    }


    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public String getElementPureHtml() {
        return elementPureHtml;
    }


    public void setElementPureHtml(String elementPureHtml) {
        this.elementPureHtml = elementPureHtml;
    }

}