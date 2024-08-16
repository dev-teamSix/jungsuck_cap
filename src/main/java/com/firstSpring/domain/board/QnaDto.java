package com.firstSpring.domain.board;

import java.util.Objects;

public class QnaDto {
    private Integer bno;
    private String id;
    private Integer group_no;
    private String title;
    private String content;
    private String reg_dt;

    private String writer;
    private Integer view_cnt;
    private char is_secret;
    private char is_notice;
    private String pwd;
    private Integer step;
    private Integer depth;
    private String frst_reg_dt;
    private String frst_reg_id;
    private String last_mode_dt;
    private String last_mode_id;

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

    public Integer getGroup_no() {
        return group_no;
    }

    public void setGroup_no(Integer group_no) {
        this.group_no = group_no;
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

    public char getIs_secret() {
        return is_secret;
    }

    public void setIs_secret(char is_secret) {
        this.is_secret = is_secret;
    }

    public char getIs_notice() {
        return is_notice;
    }

    public void setIs_notice(char is_notice) {
        this.is_notice = is_notice;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
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

    public QnaDto() {
    }



    public QnaDto(String id, Integer group_no, String title, String content, String reg_dt, String writer, Integer view_cnt, char is_secret, char is_notice, String pwd, Integer step, Integer depth, String frst_reg_dt, String frst_reg_id, String last_mode_dt, String last_mode_id) {
        this.id = id;
        this.group_no = group_no;
        this.title = title;
        this.content = content;
        this.reg_dt = reg_dt;
        this.writer = writer;
        this.view_cnt = view_cnt;
        this.is_secret = is_secret;
        this.is_notice = is_notice;
        this.pwd = pwd;
        this.step = step;
        this.depth = depth;
        this.frst_reg_dt = frst_reg_dt;
        this.frst_reg_id = frst_reg_id;
        this.last_mode_dt = last_mode_dt;
        this.last_mode_id = last_mode_id;
    }

    public QnaDto(String id, Integer group_no, String title, String content, String writer) {
        this.id = id;
        this.group_no = group_no;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public QnaDto(String id, Integer group_no, String title, String content, String writer,char is_notice) {
        this.id = id;
        this.group_no = group_no;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.is_notice = is_notice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnaDto qnaDto = (QnaDto) o;
        return Objects.equals(bno, qnaDto.bno) && Objects.equals(id, qnaDto.id) && Objects.equals(group_no, qnaDto.group_no) && Objects.equals(title, qnaDto.title) && Objects.equals(content, qnaDto.content) && Objects.equals(reg_dt, qnaDto.reg_dt) && Objects.equals(writer, qnaDto.writer) && Objects.equals(view_cnt, qnaDto.view_cnt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, id, group_no, title, content, reg_dt, writer, view_cnt);
    }

    @Override
    public String toString() {
        return "QnaDto{" +
                "bno=" + bno +
                ", id='" + id + '\'' +
                ", group_no=" + group_no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", reg_dt='" + reg_dt + '\'' +
                ", writer='" + writer + '\'' +
                ", view_cnt=" + view_cnt +
                ", is_secret=" + is_secret +
                ", is_notice=" + is_notice +
                ", pwd='" + pwd + '\'' +
                ", step=" + step +
                ", depth=" + depth +
                ", frst_reg_dt='" + frst_reg_dt + '\'' +
                ", frst_reg_id='" + frst_reg_id + '\'' +
                ", last_mode_dt='" + last_mode_dt + '\'' +
                ", last_mode_id='" + last_mode_id + '\'' +
                '}';
    }
}
