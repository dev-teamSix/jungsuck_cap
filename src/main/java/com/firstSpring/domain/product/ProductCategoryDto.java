package com.firstSpring.domain.product;

import java.util.Objects;

public class ProductCategoryDto {
    private Integer catgNo;      // 카테고리 번호
    private String name;            // 상품 카테고리명
    private String detail;          // 상품 카테고리 설명
    private Integer highCatgNo;      // 상위 카테고리 번호
    private String isUsed;       // 사용 여부
    private String isDisp;       // 표시 여부
    private String firstRegDt;    // 최초등록일시
    private String firstRegId;    // 최초등록자식별번호
    private String lastModDt;     // 최종수정일시
    private String lastModId;     // 최종수정자식별번호

    public ProductCategoryDto() {}
    public ProductCategoryDto(Integer catgNo, String name) {
        this(catgNo, name, null,  "Y", "Y");
    }

    public ProductCategoryDto(Integer catgNo, String name, String detail, String isUsed, String isDisp) {
        this(catgNo, name, detail, null, isUsed, isDisp,  null, "manager1", null, "manager1");
    }

    public ProductCategoryDto(Integer catgNo, String name, String detail, Integer highCatgNo, String isUsed, String isDisp, String firstRegDt, String firstRegId, String lastModDt, String lastModId) {
        this.catgNo = catgNo;
        this.name = name;
        this.detail = detail;
        this.highCatgNo = highCatgNo;
        this.isUsed = isUsed;
        this.isDisp = isDisp;
        this.firstRegDt = firstRegDt;
        this.firstRegId = firstRegId;
        this.lastModDt = lastModDt;
        this.lastModId = lastModId;
    }

    @Override
    public String toString() {
        return "ProductCategoryDto{" +
                "catgNo=" + catgNo +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", highCatgNo=" + highCatgNo +
                ", isUsed='" + isUsed + '\'' +
                ", isDisp='" + isDisp + '\'' +
                ", firstRegDt='" + firstRegDt + '\'' +
                ", firstRegId='" + firstRegId + '\'' +
                ", lastModDt='" + lastModDt + '\'' +
                ", lastModId='" + lastModId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryDto that = (ProductCategoryDto) o;
        return Objects.equals(getCatgNo(), that.getCatgNo()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDetail(), that.getDetail()) && Objects.equals(getHighCatgNo(), that.getHighCatgNo()) && Objects.equals(getIsUsed(), that.getIsUsed()) && Objects.equals(getIsDisp(), that.getIsDisp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCatgNo(), getName(), getDetail(), getHighCatgNo(), getIsUsed(), getIsDisp());
    }

    public Integer getCatgNo() {
        return catgNo;
    }

    public void setCatgNo(Integer catgNo) {
        this.catgNo = catgNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getHighCatgNo() {
        return highCatgNo;
    }

    public void setHighCatgNo(Integer highCatgNo) {
        this.highCatgNo = highCatgNo;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getIsDisp() {
        return isDisp;
    }

    public void setIsDisp(String isDisp) {
        this.isDisp = isDisp;
    }

    public String getFirstRegDt() {
        return firstRegDt;
    }

    public void setFirstRegDt(String firstRegDt) {
        this.firstRegDt = firstRegDt;
    }

    public String getFirstRegId() {
        return firstRegId;
    }

    public void setFirstRegId(String firstRegId) {
        this.firstRegId = firstRegId;
    }

    public String getLastModDt() {
        return lastModDt;
    }

    public void setLastModDt(String lastModDt) {
        this.lastModDt = lastModDt;
    }

    public String getLastModId() {
        return lastModId;
    }

    public void setLastModId(String lastModId) {
        this.lastModId = lastModId;
    }
}
