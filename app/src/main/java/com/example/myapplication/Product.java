package com.example.myapplication;

public class Product {

    int id;
    String name;
    double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.getDefault(), "%s - $ %.2f", name, price);
    }
}