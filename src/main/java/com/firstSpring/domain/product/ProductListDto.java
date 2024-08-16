package com.firstSpring.domain.product;

import java.util.List;
import java.util.Objects;

public class ProductListDto {
    private Integer prodNo;
    private ProductDto product;
    private ImageDto mainImg;
    private List<ProductColorDto> prodColList;

    public ProductListDto() {}

    public ProductListDto(ProductDto product, ImageDto mainImg, List<ProductColorDto> prodColList) {
        this.product = product;
        this.mainImg = mainImg;
        this.prodColList = prodColList;
    }

    @Override
    public String toString() {
        return "ProductListDto{" +
                "\nproduct=" + product +
                "\n, mainImg=" + mainImg +
                "\n, prodColList=" + prodColList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductListDto that = (ProductListDto) o;
        return Objects.equals(getProdNo(), that.getProdNo());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProdNo());
    }

    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public ImageDto getMainImg() {
        return mainImg;
    }

    public void setMainImg(ImageDto mainImg) {
        this.mainImg = mainImg;
    }

    public List<ProductColorDto> getProdColList() {
        return prodColList;
    }

    public void setProdColList(List<ProductColorDto> prodColList) {
        this.prodColList = prodColList;
    }
}
