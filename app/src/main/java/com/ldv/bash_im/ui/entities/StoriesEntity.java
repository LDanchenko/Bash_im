package com.ldv.bash_im.ui.entities;


import com.ldv.bash_im.ui.StoriesDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;


import java.util.List;
@Table(database = StoriesDatabase.class)
public class StoriesEntity  extends BaseModel{

    
    @PrimaryKey (autoincrement = true)
    int id;

    @Column
    public String site;

    @Column
    public String name;

    @Column
    public String desc;

    @Column
    public String link;

    @Column
    public String elementPureHtml;


    @Column
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
        List<StoriesEntity> allStories =  new Select().from(StoriesEntity.class).queryList();

        return allStories;
    }

    public static   List<StoriesEntity> selectFavorite(){

        List<StoriesEntity> allStories = SQLite.select()
                .from(StoriesEntity.class)
                .where(StoriesEntity_Table.favorite.eq(true)).queryList();
        return allStories;
    }
}

