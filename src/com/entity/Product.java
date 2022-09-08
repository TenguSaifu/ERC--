package com.entity;

public class Product {

    public Product(int id, String type, String code, String name, String unit, String classification,
                   String norm, String cost_price, String purchase_price, String unit_price, String review) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.classification = classification;
        this.norm = norm;
        this.cost_price = cost_price;
        this.purchase_price = purchase_price;
        this.unit_price = unit_price;
        this.review = review;
    }

    private int id;
    private String type;
    private String code;
    private String name;
    private String unit;
    private String classification;
    private String norm;
    private String cost_price;
    private String purchase_price;
    private String unit_price;
    private String review;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", classification='" + classification + '\'' +
                ", norm='" + norm + '\'' +
                ", cost_price='" + cost_price + '\'' +
                ", purchase_price='" + purchase_price + '\'' +
                ", unit_price='" + unit_price + '\'' +
                ", review='" + review + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(String purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
