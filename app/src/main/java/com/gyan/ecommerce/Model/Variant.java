package com.gyan.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {
    @SerializedName("ID")
    @Expose
    String ID;
    @SerializedName("weight")
    @Expose
    String weight;
    @SerializedName("quantity")
    @Expose
    String quantity;
    @SerializedName("discount")
    @Expose
    String discount;
    @SerializedName("final_price")
    @Expose
    String final_price;
    @SerializedName("actual_price")
    @Expose
    String actual_price;
    @SerializedName("product_id")
    @Expose
    String product_id;

    public Variant() {
    }

    public Variant(String ID, String weight, String quantity, String discount, String final_price, String actual_price, String product_id) {
        this.ID = ID;
        this.weight = weight;
        this.quantity = quantity;
        this.discount = discount;
        this.final_price = final_price;
        this.actual_price = actual_price;
        this.product_id = product_id;
    }

    public String getID() {
        return ID;
    }

    public String getWeight() {
        return weight;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public String getFinal_price() {
        return final_price;
    }

    public String getActual_price() {
        return actual_price;
    }

    public String getProduct_id() {
        return product_id;
    }
}
