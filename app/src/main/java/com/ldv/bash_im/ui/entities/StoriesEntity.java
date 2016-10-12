package com.ldv.bash_im.ui.entities;


import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.query.Select;

import java.util.List;

public class StoriesEntity extends SugarRecord {

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

  public StoriesEntity(){

  }


    public static  List<StoriesEntity> selectAll(){
        List<StoriesEntity> allStories = StoriesEntity.listAll(StoriesEntity.class);
        return allStories;
    }
}

