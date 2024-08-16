package com.firstSpring.domain.product;
import java.util.List;
public class ProductRequest {
    ProductDto product;
    ImageDto mainImg;
    List<ProdColJoinDto> prodColList;

    public ProductRequest() {}
    @Override
    public String toString() {
        return "ProductRequest{" +
                "\nproduct=" + product +
                "\n,mainImg=" + mainImg +
                "\n,prodColList=" + prodColList +
                '}';
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

    public List<ProdColJoinDto> getProdColList() {
        return prodColList;
    }

    public void setProdColList(List<ProdColJoinDto> prodColList) {
        this.prodColList = prodColList;
    }
}
