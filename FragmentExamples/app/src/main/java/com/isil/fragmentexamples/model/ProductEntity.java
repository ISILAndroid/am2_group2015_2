package com.isil.fragmentexamples.model;

import java.io.Serializable;

/**
 * Created by emedinaa on 09/09/15.
 */
public class ProductEntity implements Serializable {
    private int id;
    private String name;
    private int photo;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
