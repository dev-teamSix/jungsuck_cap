package com.firstSpring.domain.product;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer offset = 0;
    private Integer catgNo = null;
    private String sortBy = "firstRegDt";
    private String keyword = "";

    public SearchCondition() {}

    public SearchCondition(Integer pageNo, Integer pageSize, Integer catgNo, String sortBy, String keyword) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.catgNo = catgNo;
        this.sortBy = sortBy;
        this.keyword = keyword;
    }

    public String getQueryString() {
        return getQueryString(pageNo);
    }

    public String getQueryString(Integer pageNo) {
        return UriComponentsBuilder.newInstance()
                .queryParam("pageNo", pageNo)
                .queryParam("pageSize", pageSize)
                .queryParam("catgNo", catgNo)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public Integer getOffset() {
        return (pageNo-1)*pageSize;
    }


    public Integer getCatgNo() {
        return catgNo;
    }

    public void setCatgNo(Integer catgNo) {
        this.catgNo = catgNo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", catgNo=" + catgNo +
                ", sortBy='" + sortBy + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
