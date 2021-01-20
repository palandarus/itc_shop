package ru.geekbrains.controllers.repr;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.entities.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ProductRepr implements Serializable {
    private Long id;

    private String name;

    private BigDecimal price;

    private CategoryRepr category;

    private BrandRepr brand;

    private Date createDate;

    private Date modifyDate;

    private List<PictureRepr> pictures;

    private MultipartFile[] newPictures;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getTitle();
        this.price = product.getPrice();
        this.category = new CategoryRepr(product.getCategory());
        this.brand = new BrandRepr(product.getBrand());
        this.pictures = product.getPictures().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
        this.modifyDate = product.getModifyDate();
        this.createDate = product.getCreateDate();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryRepr getCategory() {
        return category;
    }

    public void setCategory(CategoryRepr category) {
        this.category = category;
    }

    public BrandRepr getBrand() {
        return brand;
    }

    public void setBrand(BrandRepr brand) {
        this.brand = brand;
    }

    public List<PictureRepr> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureRepr> pictures) {
        this.pictures = pictures;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepr that = (ProductRepr) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

