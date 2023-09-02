package com.laudate.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @NotNull(message = "Product name is required.")
    @Column(name = "product_name")
    private String productName = "";

    @NotNull(message = "Price is required.")
    @Column(name = "price")
    private Integer price = 0;

    @Column(name = "old_price")
    private int oldPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "inclusions")
    private String inclusions;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders = new ArrayList<>();


    public Products() {}

    public Products(String productName, Integer price, int oldPrice, int quantity, String description) {
        this.productName = productName;
        this.price = price;
        this.oldPrice = oldPrice;
        this.quantity = quantity;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }


    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", inclusions='" + inclusions + '\'' +
                '}';
    }
}
