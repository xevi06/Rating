
package com.example.daomodule.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Xevi on 30/01/2015.
 */
public class RestaurantORMDao {
    @DatabaseField(generatedId = true);
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int rate;
    @DatabaseField
    private String type;

    RestaurantORMDao(){}
    public int getId(){
        return id;
    }
    //getters
    public String getName(){
        return name;
    }

    public int getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setType(String type) {
        this.type = type;
    }

}
