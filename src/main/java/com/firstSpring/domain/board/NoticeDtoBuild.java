package com.firstSpring.domain.board;

public class NoticeDtoBuild {
    //NoticeDto에서 생성자 너무 많아져서 빌더 패턴을 사용해보려고 만들었음
    //필수 매개변수
    private String id;
    private String title;
    private String content;
    private String writer;


    //선택적 매개변수
    private Integer bno;
    private String reg_dt;
    private Integer view_cnt;
    private char is_notice;
    private String frst_reg_dt;
    private String frst_reg_id;
    private String last_mode_dt;
    private String last_mode_id;

    //private 생성자, 빌더를 통해서만 객체를 생성할 수 있습니다.
    private NoticeDtoBuild(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.writer = builder.writer;
        this.bno = builder.bno;
        this.reg_dt = builder.reg_dt;
        this.view_cnt = builder.view_cnt;
        this.is_notice = builder.is_notice;
        this.frst_reg_dt = builder.frst_reg_dt;
        this.frst_reg_id = builder.frst_reg_id;
        this.last_mode_dt = builder.last_mode_dt;
        this.last_mode_id = builder.last_mode_id;
    }

    public static class Builder{
        //필수 매개변수
        private String id;
        private String title;
        private String content;
        private String writer;

        //선택적 매개변수 (기본값으로 초기화)
        private Integer bno = null;
        private String reg_dt ="";
        private Integer view_cnt=0;
        private char is_notice ='0';
        private String frst_reg_dt ="";
        private String frst_reg_id ="";
        private String last_mode_dt ="";
        private String last_mode_id ="";

        public Builder(String id,String title,String content,String writer){
            this.id = id;
            this.title = title;
            this.content = content;
            this.writer = writer;
        }

        public Builder bno(Integer bno){
            this.bno = bno;
            return this;
        }

        public Builder reg_dt(String reg_dt){
            this.reg_dt = reg_dt;
            return this;
        }
        public Builder view_cnt(Integer view_cnt){
            this.view_cnt = view_cnt;
            return this;
        }
        public Builder is_notice(char is_notice){
            this.is_notice = is_notice;
            return this;
        }
        public Builder frst_reg_dt(String frst_reg_dt){
            this.frst_reg_dt = frst_reg_dt;
            return this;
        }
        public Builder frst_reg_id(String frst_reg_id){
            this.frst_reg_id = frst_reg_id;
            return this;
        }
        public Builder last_mode_dt(String last_mode_dt){
            this.last_mode_dt = last_mode_dt;
            return this;
        }
        public Builder last_mode_id(String last_mode_id){
            this.last_mode_id = last_mode_id;
            return this;
        }
        public NoticeDtoBuild build(){
            return new NoticeDtoBuild(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", writer='" + writer + '\'' +
                    ", bno=" + bno +
                    ", reg_dt='" + reg_dt + '\'' +
                    ", view_cnt=" + view_cnt +
                    ", is_notice=" + is_notice +
                    ", frst_reg_dt='" + frst_reg_dt + '\'' +
                    ", frst_reg_id='" + frst_reg_id + '\'' +
                    ", last_mode_dt='" + last_mode_dt + '\'' +
                    ", last_mode_id='" + last_mode_id + '\'' +
                    '}';
        }
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
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
}
