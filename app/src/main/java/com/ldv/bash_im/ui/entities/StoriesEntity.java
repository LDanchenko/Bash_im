package com.ldv.bash_im.ui.entities;


import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.query.Select;

import java.util.List;

public class StoriesEntity extends SugarRecord {

    public String name;

    public StoriesEntity(){

    }



    public String getName(){
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static  List<StoriesEntity> selectAll(){
        return StoriesEntity.listAll(StoriesEntity.class);
    }
}

