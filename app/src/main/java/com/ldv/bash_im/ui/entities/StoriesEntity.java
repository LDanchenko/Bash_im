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

  public StoriesEntity(){
  }



    public static  List<StoriesEntity> selectAll(){
        List<StoriesEntity> allStories = StoriesEntity.listAll(StoriesEntity.class);
        return allStories;
    }
}

