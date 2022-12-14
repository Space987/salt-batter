package com.example.finalproject;

import java.util.Arrays;

public class Food {

    private int id;
    private String name;
    private int cost;
    private String description;
    private int quantity;
    private byte[] imageData;

    public Food(int id, String name, int cost, int quantity, byte[] imageData) {
        this.id = id;
        this.name = name;

        this.cost = cost;
        this.quantity = quantity;
        this.imageData = imageData;
    }

    public Food(int id, String name, String description,int cost, int quantity, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
        this.imageData = imageData;
    }

    public Food() {
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }
}
