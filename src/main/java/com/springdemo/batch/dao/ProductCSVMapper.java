package com.springdemo.batch.dao;

import org.springframework.context.annotation.Description;

@Description("Mapper of single item of CSV file")
public class ProductCSVMapper {

    private String name;
    private String description;
    private String productType;
    private String isMain;
    private String createdAt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString()
    {
        return "ProductCSVMapper{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productType='" + productType + '\'' +
                ", isMain=" + isMain +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
