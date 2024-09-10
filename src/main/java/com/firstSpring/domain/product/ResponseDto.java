package com.firstSpring.domain.product;

public class ResponseDto {
    private String msg;
    private String url;
    private Object res;

    public ResponseDto() {}

    public ResponseDto( String msg, String url, Object res) {
        this.msg = msg;
        this.url = url;
        this.res = res;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", res=" + res +
                '}';
    }
}
