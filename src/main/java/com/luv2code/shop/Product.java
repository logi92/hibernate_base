package com.luv2code.shop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private float price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "consumer_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "consumer_id")
    )
    private List<Consumer> consumers;

    public Product() {
    }

    @Override
    public String toString() {
        return productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<Consumer> consumers) {
        this.consumers = consumers;
    }

    public void addConsumer(Consumer theConsumer) {
        if (consumers == null) {
            consumers = new ArrayList<>();
        }
        consumers.add(theConsumer);
    }

    public Product(String productName, float price, List<Consumer> consumers) {
        this.productName = productName;
        this.price = price;
        this.consumers = consumers;
    }

    public Product(String productName, float price) {
        this.productName = productName;
        this.price = price;
    }
}
