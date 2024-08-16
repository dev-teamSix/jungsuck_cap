package com.firstSpring.domain.board;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

public class PageHandler {
    private SearchCondition sc;
    private int navSize = 10; // 내비게이션 크기
    private int totalCnt; // 총 게시물 갯수
    private Integer page; // 현재 페이지
    private Integer pageSize; // 페이지 크기
    private int totalPage; // 총 페이지 갯수
    private int beginPage; // 제일 첫번쨰 페이지
    private int endPage; // 제일 마지막 페이지
    private boolean prevPage; // 이전페이지로 이동할수있는지
    private boolean nextPage; // 다음페이지로 이동할수있는지
    private int offset; // 페이지 offset

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public PageHandler(){} // 기본 생성자

    public PageHandler(int totalCnt,Integer page){
        this(totalCnt,new SearchCondition(page,10));
    }

    public PageHandler(int totalCnt, Integer page, Integer pageSize){
        this(totalCnt, new SearchCondition(page,pageSize));
    }

    public PageHandler(int totalCnt,SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;
        System.out.println("PageHandler(sc):"+sc);
        System.out.println("sc값체크:):"+sc.getKeyword());
        System.out.println("sc값체크2:):"+sc.getOption_date());
        System.out.println("sc값체크3:):"+sc.getOption_key());
        doPaging(totalCnt,sc);
    }

    private void doPaging(int totalCnt, SearchCondition sc) {
        this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize()==0? 0:1);
        this.sc.setPage(Math.min(sc.getPage(), totalPage));  // page가 totalPage보다 크지 않게
        this.beginPage = (this.sc.getPage() -1) / navSize * navSize + 1; // 11 -> 11, 10 -> 1, 15->11. 따로 떼어내서 테스트
        this.endPage = Math.min(beginPage + navSize - 1, totalPage);
        this.prevPage = beginPage!=1;
        this.nextPage = endPage!=totalPage;
    }


    public PageHandler(Integer totalCnt,Integer page,Integer pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil(totalCnt/(double)pageSize);
        this.beginPage = ((page-1)/navSize)*navSize+1;
        this.endPage = Math.min(beginPage+(navSize-1),totalPage);
        this.prevPage = beginPage != 1;
        this.nextPage = endPage != totalPage;
        this.offset = (this.page-1)*this.pageSize;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.firstSpring.domain.board.PageHandler that = (com.firstSpring.domain.board.PageHandler) o;
        return totalCnt == that.totalCnt && page == that.page && pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCnt, page, pageSize);
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "navSize=" + navSize +
                ", totalCnt=" + totalCnt +
                ", page ="+page+
                ", pageSize ="+pageSize+
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                '}';
    }


    public String getQueryString() {
        return getQueryString(this.sc.getPage());
    }

    public String getQueryString(Integer page) {
        // ?page=10&pageSize=10&option=A&keyword=title
        return UriComponentsBuilder.newInstance()
                .queryParam("page",     page)
                .queryParam("pageSize", sc.getPageSize())
                .queryParam("option_key",   sc.getOption_key())
                .queryParam("option_date",sc.getOption_date())
                .queryParam("keyword",  sc.getKeyword())
                .build().toString();

    }

    public int getNavSize() {
        return navSize;
    }

    public void setNavSize(int navSize) {
        this.navSize = navSize;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }
}
