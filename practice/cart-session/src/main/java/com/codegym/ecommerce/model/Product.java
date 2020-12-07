package com.codegym.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "DESCRIPTION")
    private String description;

    public Product() {
    }

    public Product(String name, String manufacturer, Float price, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", manufacturer='" + manufacturer + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            '}';
    }
}
