package com.ldv.bash_im.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 17.10.2016.
 */

public class StoriesModel {


    @SerializedName("site")
    public String site;

    @SerializedName("name")
    public String name;

    @SerializedName("desc")
    public String desc;

    @SerializedName("link")
    public String link;

    @SerializedName("elementPureHtml")
    public String elementPureHtml;

    public String getSite(){
        return site;
    }

    public void setSite(String site){
        this.site=site;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc=desc;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link=link;
    }

    public String getElementPureHtml(){
        return elementPureHtml;
    }

    public void setElementPureHtml(String elementPureHtml){
        this.elementPureHtml=elementPureHtml;
    }


}
