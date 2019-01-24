package com.txtbravo.txtbravo.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
@Where(clause="is_active=1")
@SQLDelete(sql = "UPDATE product" + " SET is_active = 0" + " WHERE product_id = ?")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long id;

    @Column(name = "product_code")
    @NotEmpty(message = "Product code can't empty!")
    @Size(min = 3, max = 30, message = "Product Code should be {min} and {max} characters.")
    private String productCode;

    @Column(name = "product_name")
    @NotEmpty(message = "Product Name can't empty!")
    @Size(min = 3, max = 30, message = "Product Name should be {min} and {max} characters.")
    private String productName;

    @Column(name = "product_description")
    @NotEmpty(message = "Product description can't empty!")
    @Size(min = 10, max = 200, message = "Product description should be {min} and {max} characters.")
    private String productDescription;

    @Column(name = "thumbnail")
    private String thumbnail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}