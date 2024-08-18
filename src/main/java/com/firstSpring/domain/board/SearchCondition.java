package com.firstSpring.domain.board;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer offset = 0;
    private String keyword = "";
    private String option_date = "";
    private String option_key ="";


    public SearchCondition() {
    }

    public SearchCondition(Integer page,Integer pageSize){
        this(page,pageSize,"","","");
    }

    public SearchCondition(Integer page, Integer pageSize , String keyword, String option_date, String option_key) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option_date = option_date;
        this.option_key = option_key;
    }
    public SearchCondition(Integer page, Integer pageSize , String keyword,  String option_key) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option_key = option_key;
    }

    public SearchCondition(Integer page, Integer pageSize , String keyword) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption_date() {
        return option_date;
    }

    public void setOption_date(String option_date) {
        this.option_date = option_date;
    }

    public String getOption_key() {
        return option_key;
    }

    public void setOption_key(String option_key) {
        this.option_key = option_key;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", keyword='" + keyword + '\'' +
                ", option_date='" + option_date + '\'' +
                ", option_key='" + option_key + '\'' +
                '}';
    }
}
