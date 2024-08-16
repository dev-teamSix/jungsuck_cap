package com.firstSpring.domain.product;

import java.util.Objects;

public class ProductColorDto {
    private Integer colNo;
    private Integer prodNo;
    private String colName;
    private Integer imgNo;
    private String colRgbCd;
    private Integer addPric;
    private Integer invAmnt;
    private String stCd;
    private String isDisp;
    private Integer dispOrd;
    private String firstRegDt;    // 최초등록일시
    private String firstRegId;    // 최초등록자식별번호
    private String lastModDt;     // 최종수정일시
    private String lastModId;     // 최종수정자식별번호

    public ProductColorDto() {}

    public ProductColorDto(Integer colNo, String colName, Integer prodNo) {
        this(colNo, prodNo, colName, 999, "PP", "Y", 0, 1);
    }

    public ProductColorDto(Integer colNo, Integer prodNo, String colName, Integer invAmnt, String stCd, String isDisp, Integer addPric, Integer dispOrd) {
        this(colNo, prodNo, colName, null, null, addPric, invAmnt, stCd, isDisp, dispOrd, null,"manager1", null, "manager1");
    }

    public ProductColorDto(Integer colNo, Integer prodNo, String colName, Integer imgNo, String colRgbCd, Integer addPric, Integer invAmnt, String stCd, String isDisp, Integer dispOrd, String firstRegDt, String firstRegId, String lastModDt, String lastModId) {
        this.colNo = colNo;
        this.prodNo = prodNo;
        this.colName = colName;
        this.imgNo = imgNo;
        this.colRgbCd = colRgbCd;
        this.addPric = addPric;
        this.invAmnt = invAmnt;
        this.stCd = stCd;
        this.isDisp = isDisp;
        this.dispOrd = dispOrd;
        this.firstRegDt = firstRegDt;
        this.firstRegId = firstRegId;
        this.lastModDt = lastModDt;
        this.lastModId = lastModId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductColorDto that = (ProductColorDto) o;
        return Objects.equals(getColNo(), that.getColNo()) && Objects.equals(getProdNo(), that.getProdNo()) && Objects.equals(getColName(), that.getColName()) && Objects.equals(getImgNo(), that.getImgNo()) && Objects.equals(getColRgbCd(), that.getColRgbCd()) && Objects.equals(getAddPric(), that.getAddPric()) && Objects.equals(getInvAmnt(), that.getInvAmnt()) && Objects.equals(getStCd(), that.getStCd()) && Objects.equals(getIsDisp(), that.getIsDisp()) && Objects.equals(getDispOrd(), that.getDispOrd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColNo(), getProdNo(), getColName(), getImgNo(), getColRgbCd(), getAddPric(), getInvAmnt(), getStCd(), getIsDisp(), getDispOrd());
    }

    @Override
    public String toString() {
        return "ProductColorDto{" +
                "colNo=" + colNo +
                ", prodNo=" + prodNo +
                ", colName='" + colName + '\'' +
                ", imgNo=" + imgNo +
                ", colRgbCd='" + colRgbCd + '\'' +
                ", addPric='" + addPric + '\'' +
                ", invAmnt='" + invAmnt + '\'' +
                ", stCd='" + stCd + '\'' +
                ", isDisp=" + isDisp +
                ", dispOrd=" + dispOrd +
                ", firstRegDt='" + firstRegDt + '\'' +
                ", firstRegId='" + firstRegId + '\'' +
                ", lastModDt='" + lastModDt + '\'' +
                ", lastModId='" + lastModId + '\'' +
                '}';
    }

    public Integer getColNo() {
        return colNo;
    }

    public void setColNo(Integer colNo) {
        this.colNo = colNo;
    }

    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Integer getImgNo() {
        return imgNo;
    }

    public void setImgNo(Integer imgNo) {
        this.imgNo = imgNo;
    }

    public String getColRgbCd() {
        return colRgbCd;
    }

    public void setColRgbCd(String colRgbCd) {
        this.colRgbCd = colRgbCd;
    }

    public Integer getAddPric() {
        return addPric;
    }

    public void setAddPric(Integer addPric) {
        this.addPric = addPric;
    }

    public Integer getInvAmnt() {
        return invAmnt;
    }

    public void setInvAmnt(Integer invAmnt) {
        this.invAmnt = invAmnt;
    }

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    public String getIsDisp() {
        return isDisp;
    }

    public void setIsDisp(String isDisp) {
        this.isDisp = isDisp;
    }

    public Integer getDispOrd() {
        return dispOrd;
    }

    public void setDispOrd(Integer dispOrd) {
        this.dispOrd = dispOrd;
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
