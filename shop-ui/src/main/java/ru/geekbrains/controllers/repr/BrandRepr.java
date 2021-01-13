package ru.geekbrains.controllers.repr;

import ru.geekbrains.entities.Brand;

import java.io.Serializable;

public class BrandRepr implements Serializable {

    private Long id;

    private String name;
    private long productCount;

    public BrandRepr(Long id, String name, long productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    public BrandRepr(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
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

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

}
