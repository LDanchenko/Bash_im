package com.ldv.bash_im.ui.database.entities;


import com.ldv.bash_im.ui.database.StoriesDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;


import java.util.List;
@Table(database = StoriesDatabase.class, insertConflict = ConflictAction.ABORT)
public class StoriesEntity  extends BaseModel{

    
    @PrimaryKey
    private String id;

    @Column
    private String elementPureHtml;

    @Column
    private boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElementPureHtml() {
        return elementPureHtml;
    }

    public void setElementPureHtml(String elementPureHtml) {
        this.elementPureHtml = elementPureHtml;
    }

    public StoriesEntity(){}


    public StoriesEntity (String id, String elementPureHtml, boolean favorite) {
        this.id=id;
        this.elementPureHtml=elementPureHtml;
        this.favorite=favorite;
    }

    public static List<StoriesEntity> selectAll(){

        return   new Select().from(StoriesEntity.class).queryList();

    }

    public static List<StoriesEntity> selectFavorite(){

        return SQLite.select()
                .from(StoriesEntity.class)
                .where(StoriesEntity_Table.favorite.eq(true)).queryList();

    }
/*
    public static StoriesEntity selectByLink(String link){

        StoriesEntity story = SQLite.select()
                .from(StoriesEntity.class)
                .where(StoriesEntity_Table.id.eq(link)).querySingle();
        return story;
    }
*/
}

