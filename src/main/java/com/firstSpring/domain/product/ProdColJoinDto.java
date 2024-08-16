package com.firstSpring.domain.product;

import java.util.Objects;

public class ProdColJoinDto {
    ProductColorDto productColor;
    ImageDto image;

    public ProdColJoinDto() {}

    public ProdColJoinDto(ProductColorDto productColor, ImageDto image) {
        this.productColor = productColor;
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProdColJoinDto{" +
                "\nproductColor=" + productColor +
                ",\n image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdColJoinDto that = (ProdColJoinDto) o;
        return Objects.equals(productColor, that.productColor) && Objects.equals(getImage(), that.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productColor, getImage());
    }

    public ProductColorDto getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColorDto productColor) {
        this.productColor = productColor;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
