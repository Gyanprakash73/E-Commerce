package com.gyan.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultArray {
    @SerializedName("ID")
    @Expose
    String ID;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("products")
    @Expose
    List<Products> products = null;

    public ResultArray() {
    }

    public ResultArray(String ID, String name, List<Products> products) {
        this.ID = ID;
        this.name = name;
        this.products = products;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<Products> getProducts() {
        return products;
    }
}
