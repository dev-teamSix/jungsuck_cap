package com.firstSpring.domain.product;

import java.util.Objects;

public class ImageDto {
    private Integer imgNo;          // 이미지 번호
    private String url;          // 이미지 url
    private String fileName;
    private String fileExt;          // 파일 확장자
    private String fileSize;     // 파일 크기
    private String asptRatio;    // 이미지 비율
    private String horzLen;      // 이미지 세로 길이
    private String vertLen;      // 이미지 가로 길이
    private String detail;          // 이미지 상세 설명
    private String firstRegDt;      // 최초등록일시
    private String firstRegId;      // 최초등록자식별번호
    private String lastModDt;       // 최종수정일시
    private String lastModId;       // 최종수정자식별번호

    public ImageDto() {}
    public ImageDto(Integer imgNo, String url, String fileName, String fileExt, String fileSize, String asptRatio, String horzLen, String vertLen, String detail, String firstRegDt, String firstRegId, String lastModDt, String lastModId) {
        this.imgNo = imgNo;
        this.url = url;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.asptRatio = asptRatio;
        this.horzLen = horzLen;
        this.vertLen = vertLen;
        this.detail = detail;
        this.firstRegDt = firstRegDt;
        this.firstRegId = firstRegId;
        this.lastModDt = lastModDt;
        this.lastModId = lastModId;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "imgNo=" + imgNo +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExt='" + fileExt + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", asptRatio='" + asptRatio + '\'' +
                ", horzLen='" + horzLen + '\'' +
                ", vertLen='" + vertLen + '\'' +
                ", detail='" + detail + '\'' +
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
        ImageDto imageDto = (ImageDto) o;
        return Objects.equals(getImgNo(), imageDto.getImgNo()) && Objects.equals(getUrl(), imageDto.getUrl()) && Objects.equals(getFileName(), imageDto.getFileName()) && Objects.equals(getFileExt(), imageDto.getFileExt()) && Objects.equals(getFileSize(), imageDto.getFileSize()) && Objects.equals(getAsptRatio(), imageDto.getAsptRatio()) && Objects.equals(getHorzLen(), imageDto.getHorzLen()) && Objects.equals(getVertLen(), imageDto.getVertLen()) && Objects.equals(getDetail(), imageDto.getDetail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImgNo(), getUrl(), getFileName(), getFileExt(), getFileSize(), getAsptRatio(), getHorzLen(), getVertLen(), getDetail(), getFirstRegDt(), getFirstRegId(), getLastModDt(), getLastModId());
    }

    public Integer getImgNo() {
        return imgNo;
    }

    public void setImgNo(Integer imgNo) {
        this.imgNo = imgNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getAsptRatio() {
        return asptRatio;
    }

    public void setAsptRatio(String asptRatio) {
        this.asptRatio = asptRatio;
    }

    public String getHorzLen() {
        return horzLen;
    }

    public void setHorzLen(String horzLen) {
        this.horzLen = horzLen;
    }

    public String getVertLen() {
        return vertLen;
    }

    public void setVertLen(String vertLen) {
        this.vertLen = vertLen;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
