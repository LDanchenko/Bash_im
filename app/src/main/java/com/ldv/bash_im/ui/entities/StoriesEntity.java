package com.ldv.bash_im.ui.entities;


import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.query.Select;

import java.util.List;

public class StoriesEntity extends SugarRecord {


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

    public boolean favorite;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

  public String getSite(){
    return site;
  }

  public void setSite(String site){
    this.site=site;
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

    public boolean getFavorite() {

        return favorite;}

    public void setFavorite(boolean favorite) {this.favorite=favorite;}

  public StoriesEntity(){
  }

    public StoriesEntity (String name, String site, String desc, String link, String elementPureHtml, boolean favorite) {
        this.name = name;
        this.site = site;
        this.desc = desc;
        this.link = link;
        this.elementPureHtml=elementPureHtml;
        this.favorite=favorite;
    }




    public static  List<StoriesEntity> selectAll(){
        List<StoriesEntity> allStories = StoriesEntity.listAll(StoriesEntity.class);
        return allStories;
    }

    public static  List<StoriesEntity> selectFavorite(){

        List<StoriesEntity> allStories = StoriesEntity.find(StoriesEntity.class, "favorite = ?", "1");
        return allStories;
    }
}

