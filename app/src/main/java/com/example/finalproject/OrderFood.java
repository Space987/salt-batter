package com.example.finalproject;

public class OrderFood {

    private int id;
    private int profile_id;
    private int food_id;
    private String name;
    private int cost;
    private int quantity;
    private int order_group;

    public int getFood_id() {
        return food_id;
    }

    public OrderFood(int id, int profile_id, int food_id, String name, int cost, int quantity, int order_group) {
        this.id = id;
        this.profile_id = profile_id;
        this.food_id = food_id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.order_group = order_group;
    }
    public OrderFood(int id, int profile_id, int food_id, String name, int cost, int quantity) {
        this.id = id;
        this.profile_id = profile_id;
        this.food_id = food_id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public int getOrder_group() {
        return order_group;
    }

    public OrderFood() {
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public int getQuantity() {
        return quantity;
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
        return "OrderFood{" +
                "id=" + id +
                ", profile_id=" + profile_id +
                ", food_id=" + food_id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", order_group=" + order_group +
                '}';
    }
}