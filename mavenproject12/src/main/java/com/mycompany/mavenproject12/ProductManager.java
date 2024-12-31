package com.mycompany.mavenproject12;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hp
 */
import com.mycompany.mavenproject12.Product;
import java.util.ArrayList;

public class ProductManager {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public void updateProduct(int id, String name, String category, double price) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(name);
                product.setCategory(category);
                product.setPrice(price);
                break;
            }
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    
}
