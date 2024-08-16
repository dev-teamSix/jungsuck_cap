package com.firstSpring.domain.product;

import java.util.List;
import java.util.Objects;

public class ProductHighCategoryDto {
    private Integer highCatgNo;
    private String highName;
    private String highIsUsed;
    private String highIsDisp;
    private List<ProductCategoryDto> lowCategoryList;

    public ProductHighCategoryDto() {}

    public ProductHighCategoryDto(Integer highCatgNo, String highName, String highIsUsed, String highIsDisp, List<ProductCategoryDto> lowCategoryList) {
        this.highCatgNo = highCatgNo;
        this.highName = highName;
        this.highIsUsed = highIsUsed;
        this.highIsDisp = highIsDisp;
        this.lowCategoryList = lowCategoryList;
    }

    @Override
    public String toString() {
        return "ProductHighCategoryDto{" +
                "highCatgNo=" + highCatgNo +
                ", highName='" + highName + '\'' +
                ", highIsUsed='" + highIsUsed + '\'' +
                ", highIsDisp='" + highIsDisp + '\'' +
                ", lowCategoryList=" + lowCategoryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductHighCategoryDto that = (ProductHighCategoryDto) o;
        return Objects.equals(getHighCatgNo(), that.getHighCatgNo()) && Objects.equals(getHighName(), that.getHighName()) && Objects.equals(getHighIsUsed(), that.getHighIsUsed()) && Objects.equals(getHighIsDisp(), that.getHighIsDisp()) && Objects.equals(getLowCategoryList(), that.getLowCategoryList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHighCatgNo(), getHighName(), getHighIsUsed(), getHighIsDisp(), getLowCategoryList());
    }

    public Integer getHighCatgNo() {
        return highCatgNo;
    }

    public void setHighCatgNo(Integer highCatgNo) {
        this.highCatgNo = highCatgNo;
    }

    public String getHighName() {
        return highName;
    }

    public void setHighName(String highName) {
        this.highName = highName;
    }

    public String getHighIsUsed() {
        return highIsUsed;
    }

    public void setHighIsUsed(String highIsUsed) {
        this.highIsUsed = highIsUsed;
    }

    public String getHighIsDisp() {
        return highIsDisp;
    }

    public void setHighIsDisp(String highIsDisp) {
        this.highIsDisp = highIsDisp;
    }

    public List<ProductCategoryDto> getLowCategoryList() {
        return lowCategoryList;
    }

    public void setLowCategoryList(List<ProductCategoryDto> lowCategoryList) {
        this.lowCategoryList = lowCategoryList;
    }
}
