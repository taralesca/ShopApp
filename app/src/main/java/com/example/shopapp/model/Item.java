package com.example.shopapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Item {
    private int userId;
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "price")
    private Double price;
//    private String image;

    public Item() {
    }

    public Item(int userId, String title, String description, Double price) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
//        this.image = image;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    //    public String getImage() {
//        return image;
//    }

//    public void setImage(String image) {
//        this.image = image;
//    }

    public Item fromJSON(JSONObject data) throws JSONException {
        this.id = data.getInt("id");
        this.title = data.getString("title");
        this.description = data.getString("description");
        this.userId = data.getInt("userId");
        this.price = data.getDouble("price");
//        this.image = data.getString("image");
        return this;
    }
}
