package com.firstSpring.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

// 상품 정보가 들어간 Dto
public class ProductDto {
    private Integer prodNo;       // 상품 번호
    private Integer prodCatgNo;    // 상품 분류 번호
    private String name;       // 상품명
    private String barc;       // 상품바코드
    private Integer price;         // 상품 가격
    private Double avgRatg;        // 평균 별점
    private Integer totalSales;          // 총 판매량
    private Integer viewCnt;           // 조회수
    private Integer reviewCnt;         // 리뷰 수
    private Integer mainImgNo;        // 대표 이미지 번호
    private Integer explImgNo;        // 설명 이미지 번호
    private String shortDet;  // 상품 간략 소개
    private String longDet;   // 상품 상세 설명
    private String colors;          // 색상표
    private String material;        // 소재
    private String sizeBrimLen;  // 사이즈-챙길이
    private String sizeHairCm;    // 사이즈-헤어둘레
    private String sizeDepth;      // 사이즈-깊이
    private String brand;           // 브랜드명
    private String mfgDt;          // 제조년월
    private String mfgCountry;     // 제조국
    private String mfgr;            // 제조자
    private String handlePrinc;    // 취급시 주의사항
    private String washWay;        // 세탁 방법
    private String qualStd;        // 품질보증기준
    private String asMangNum;     // A/S 책임자와 전호번호
    private String exchInfo;       // 교환,환불 안내
    private String payInfo;        // 결제 안내
    private String deliInfo;       // 배송 안내
    private Integer minOrdCnt;     // 최소 주문 수량
    private Integer maxOrdCnt;     // 최대 주문 수량
    private String firstRegDt;    // 최초등록일시
    private String firstRegId;    // 최초등록자식별번호
    private String lastModDt;     // 최종수정일시
    private String lastModId;     // 최종수정자식별번호


    public ProductDto() {}
    public ProductDto( String prodName) {
        this(null, 0 , prodName, "0123456789123", "manger1", "manger1");
    }

    public ProductDto(Integer prodNo, Integer prodCatgNo, String name, String barc, String firstRegId, String lastModId) {
        this.prodNo = prodNo;
        this.prodCatgNo = prodCatgNo;
        this.name = name;
        this.barc = barc;
        this.firstRegId = firstRegId;
        this.lastModId = lastModId;
    }

    public ProductDto(Integer prodNo, Integer prodCatgNo, String name,  String barc, Integer price,  Double avgRatg, Integer viewCnt, String colors, Integer totalSales, String firstRegDt) {
        this.prodNo = prodNo;
        this.prodCatgNo = prodCatgNo;
        this.name = name;
        this.price = price;
        this.avgRatg = avgRatg;
        this.viewCnt = viewCnt;
        this.colors = colors;
        this.totalSales = totalSales;
        this.firstRegDt = firstRegDt;
    }

    public ProductDto(Integer prodNo, Integer prodCatgNo, String name, String barc, Integer price, Double avgRatg, Integer totalSales, Integer viewCnt, Integer reviewCnt, Integer mainImgNo, Integer explImgNo, String shortDet, String longDet, String colors, String material, String sizeBrimLen, String sizeHairCm, String sizeDepth, String brand, String mfgDt, String mfgCountry, String mfgr, String handlePrinc, String washWay, String qualStd, String asMangNum, String exchInfo, String payInfo, String deliInfo, Integer minOrdCnt, Integer maxOrdCnt, String firstRegDt, String firstRegId, String lastModDt, String lastModId) {
        this.prodNo = prodNo;
        this.prodCatgNo = prodCatgNo;
        this.name = name;
        this.barc = barc;
        this.price = price;
        this.avgRatg = avgRatg;
        this.totalSales = totalSales;
        this.viewCnt = viewCnt;
        this.reviewCnt = reviewCnt;
        this.mainImgNo = mainImgNo;
        this.explImgNo = explImgNo;
        this.shortDet = shortDet;
        this.longDet = longDet;
        this.colors = colors;
        this.material = material;
        this.sizeBrimLen = sizeBrimLen;
        this.sizeHairCm = sizeHairCm;
        this.sizeDepth = sizeDepth;
        this.brand = brand;
        this.mfgDt = mfgDt;
        this.mfgCountry = mfgCountry;
        this.mfgr = mfgr;
        this.handlePrinc = handlePrinc;
        this.washWay = washWay;
        this.qualStd = qualStd;
        this.asMangNum = asMangNum;
        this.exchInfo = exchInfo;
        this.payInfo = payInfo;
        this.deliInfo = deliInfo;
        this.minOrdCnt = minOrdCnt;
        this.maxOrdCnt = maxOrdCnt;
        this.firstRegDt = firstRegDt;
        this.firstRegId = firstRegId;
        this.lastModDt = lastModDt;
        this.lastModId = lastModId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(getProdNo(), that.getProdNo()) && Objects.equals(getProdCatgNo(), that.getProdCatgNo()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBarc(), that.getBarc()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProdNo(), getProdCatgNo(), getName(), getBarc(), getPrice(), getMainImgNo(), getExplImgNo(), getShortDet(), getLongDet(), getFirstRegDt(), getFirstRegId());
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "prodNo=" + prodNo +
                ", prodCatgNo=" + prodCatgNo +
                ", name='" + name + '\'' +
                ", barc='" + barc + '\'' +
                ", price=" + price +
                ", avgRatg=" + avgRatg +
                ", totalSales=" + totalSales +
                ", viewCnt=" + viewCnt +
                ", reviewCnt=" + reviewCnt +
                ", mainImgNo=" + mainImgNo +
                ", explImgNo=" + explImgNo +
                ", shortDet='" + shortDet + '\'' +
                ", longDet='" + longDet + '\'' +
                ", colors='" + colors + '\'' +
                ", material='" + material + '\'' +
                ", sizeBrimLen='" + sizeBrimLen + '\'' +
                ", sizeHairCm='" + sizeHairCm + '\'' +
                ", sizeDepth='" + sizeDepth + '\'' +
                ", brand='" + brand + '\'' +
                ", mfgDt='" + mfgDt + '\'' +
                ", mfgCountry='" + mfgCountry + '\'' +
                ", mfgr='" + mfgr + '\'' +
                ", handlePrinc='" + handlePrinc + '\'' +
                ", washWay='" + washWay + '\'' +
                ", qualStd='" + qualStd + '\'' +
                ", asMangNum='" + asMangNum + '\'' +
                ", exchInfo='" + exchInfo + '\'' +
                ", payInfo='" + payInfo + '\'' +
                ", deliInfo='" + deliInfo + '\'' +
                ", minOrdCnt=" + minOrdCnt +
                ", maxOrdCnt=" + maxOrdCnt +
                ", firstRegDt='" + firstRegDt + '\'' +
                ", firstRegId='" + firstRegId + '\'' +
                ", lastModDt='" + lastModDt + '\'' +
                ", lastModId='" + lastModId + '\'' +
                '}';
    }

    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public Integer getProdCatgNo() {
        return prodCatgNo;
    }

    public void setProdCatgNo(Integer prodCatgNo) {
        this.prodCatgNo = prodCatgNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarc() {
        return barc;
    }

    public void setBarc(String barc) {
        this.barc = barc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getAvgRatg() {
        return avgRatg;
    }

    public void setAvgRatg(Double avgRatg) {
        this.avgRatg = avgRatg;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Integer getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(Integer reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    public Integer getMainImgNo() {
        return mainImgNo;
    }

    public void setMainImgNo(Integer mainImgNo) {
        this.mainImgNo = mainImgNo;
    }

    public Integer getExplImgNo() {
        return explImgNo;
    }

    public void setExplImgNo(Integer explImgNo) {
        this.explImgNo = explImgNo;
    }

    public String getShortDet() {
        return shortDet;
    }

    public void setShortDet(String shortDet) {
        this.shortDet = shortDet;
    }

    public String getLongDet() {
        return longDet;
    }

    public void setLongDet(String longDet) {
        this.longDet = longDet;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSizeBrimLen() {
        return sizeBrimLen;
    }

    public void setSizeBrimLen(String sizeBrimLen) {
        this.sizeBrimLen = sizeBrimLen;
    }

    public String getSizeHairCm() {
        return sizeHairCm;
    }

    public void setSizeHairCm(String sizeHairCm) {
        this.sizeHairCm = sizeHairCm;
    }

    public String getSizeDepth() {
        return sizeDepth;
    }

    public void setSizeDepth(String sizeDepth) {
        this.sizeDepth = sizeDepth;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMfgDt() {
        return mfgDt;
    }

    public void setMfgDt(String mfgDt) {
        this.mfgDt = mfgDt;
    }

    public String getMfgCountry() {
        return mfgCountry;
    }

    public void setMfgCountry(String mfgCountry) {
        this.mfgCountry = mfgCountry;
    }

    public String getMfgr() {
        return mfgr;
    }

    public void setMfgr(String mfgr) {
        this.mfgr = mfgr;
    }

    public String getHandlePrinc() {
        return handlePrinc;
    }

    public void setHandlePrinc(String handlePrinc) {
        this.handlePrinc = handlePrinc;
    }

    public String getWashWay() {
        return washWay;
    }

    public void setWashWay(String washWay) {
        this.washWay = washWay;
    }

    public String getQualStd() {
        return qualStd;
    }

    public void setQualStd(String qualStd) {
        this.qualStd = qualStd;
    }

    public String getAsMangNum() {
        return asMangNum;
    }

    public void setAsMangNum(String asMangNum) {
        this.asMangNum = asMangNum;
    }

    public String getExchInfo() {
        return exchInfo;
    }

    public void setExchInfo(String exchInfo) {
        this.exchInfo = exchInfo;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getDeliInfo() {
        return deliInfo;
    }

    public void setDeliInfo(String deliInfo) {
        this.deliInfo = deliInfo;
    }

    public Integer getMinOrdCnt() {
        return minOrdCnt;
    }

    public void setMinOrdCnt(Integer minOrdCnt) {
        this.minOrdCnt = minOrdCnt;
    }

    public Integer getMaxOrdCnt() {
        return maxOrdCnt;
    }

    public void setMaxOrdCnt(Integer maxOrdCnt) {
        this.maxOrdCnt = maxOrdCnt;
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
