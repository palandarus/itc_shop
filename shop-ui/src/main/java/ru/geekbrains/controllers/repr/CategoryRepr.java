package ru.geekbrains.controllers.repr;

import ru.geekbrains.entities.Category;

public class CategoryRepr {

    private Long id;

    private String name;

    private String code;

    private long productCount;

    public CategoryRepr(Long id, String name, String code, long productCount) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.productCount = productCount;
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
    }

    public CategoryRepr() {

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
