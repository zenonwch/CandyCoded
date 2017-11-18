package com.codeschool.candycoded;

public class Candy {
    private int id;
    private String name;
    private String image;
    private double price;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
