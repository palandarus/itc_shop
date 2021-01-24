package ru.geekbrains.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends AbstractItem {

    @Column(name = "title")
    private String title;

//    @Column(name = "image")
//    private String image;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name="brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    public Product() {
    }

    public Product(String title, Brand brand, BigDecimal price, String image, Category category) {
        this.title = title;
        this.price = price;
        this.brand=brand;
//        this.image = image;
        this.category = category;
    }

    public Product(String title, BigDecimal price, Category category, Brand brand) {
        this.title = title;
        this.price = price;
        this.brand=brand;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", brandName='" + brand.getName() + '\'' +
//                ", image='" + image + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
