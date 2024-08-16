package com.firstSpring.domain.board;

public class FaqDto {
    private Integer bno;
    private String id;
    private String title;
    private String content;
    private String reg_dt;

    private String writer;
    private Integer view_cnt;
    private char is_notice;
    private String frst_reg_dt;
    private String frst_reg_id;
    private String last_mode_dt;
    private String last_mode_id;

    public FaqDto() {}

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(Integer view_cnt) {
        this.view_cnt = view_cnt;
    }

    public char getIs_notice() {
        return is_notice;
    }

    public void setIs_notice(char is_notice) {
        this.is_notice = is_notice;
    }

    public String getFrst_reg_dt() {
        return frst_reg_dt;
    }

    public void setFrst_reg_dt(String frst_reg_dt) {
        this.frst_reg_dt = frst_reg_dt;
    }

    public String getFrst_reg_id() {
        return frst_reg_id;
    }

    public void setFrst_reg_id(String frst_reg_id) {
        this.frst_reg_id = frst_reg_id;
    }

    public String getLast_mode_dt() {
        return last_mode_dt;
    }

    public void setLast_mode_dt(String last_mode_dt) {
        this.last_mode_dt = last_mode_dt;
    }

    public String getLast_mode_id() {
        return last_mode_id;
    }

    public void setLast_mode_id(String last_mode_id) {
        this.last_mode_id = last_mode_id;
    }

    @Override
    public String toString() {
        return "FaqDto{" +
                "bno=" + bno +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", reg_dt='" + reg_dt + '\'' +
                ", writer='" + writer + '\'' +
                ", view_cnt=" + view_cnt +
                ", is_notice=" + is_notice +
                ", frst_reg_dt='" + frst_reg_dt + '\'' +
                ", frst_reg_id='" + frst_reg_id + '\'' +
                ", last_mode_dt='" + last_mode_dt + '\'' +
                ", last_mode_id='" + last_mode_id + '\'' +
                '}';
    }
}
