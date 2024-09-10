package com.firstSpring.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class UserDto {
    @JsonProperty("id")
    private String id; // 회원ID

    @JsonProperty("grd_cd")
    private String grd_cd; // 회원등급코드

    @JsonProperty("st_cd")
    private String st_cd; // 회원상태코드

    @JsonProperty("email")
    private String email; // 이메일

    @JsonProperty("pwd")
    private String pwd; // 비밀번호

    @JsonProperty("name")
    private String name; // 이름

    @JsonProperty("birth")
    private String birth; // 생년월일

    @JsonProperty("gender")
    private String gender; // 성별

    @JsonProperty("ph_num")
    private String ph_num; // 전화번호

    @JsonProperty("zip")
    private String zip; // 우편번호

    @JsonProperty("road_adr")
    private String road_adr; // 도로명주소

    @JsonProperty("jibun_adr")
    private String jibunAdr; // 지번주소

    @JsonProperty("det_adr")
    private String detAdr; // 상세주소

    @JsonProperty("is_mail_auth")
    private String is_mail_auth; // 메일 인증 여부

    @JsonProperty("mail_key")
    private String mailKey; // 메일 인증 번호

    @JsonProperty("is_adm")
    private String is_adm; // 관리자여부

    @JsonProperty("reg_dt")
    private String regDt; // 가입일

    @JsonProperty("quit_dt")
    private String quitDt; // 탈퇴일

    @JsonProperty("is_quit")
    private String isQuit; // 탈퇴여부

    @JsonProperty("is_blok")
    private String isBlok; // 차단여부

    @JsonProperty("blok_res")
    private String blokRes; // 차단사유

    @JsonProperty("blok_dt")
    private String blokDt; // 차단날짜

    @JsonProperty("un_blok_dt")
    private String unBlokDt; // 차단해지날짜

    @JsonProperty("frst_reg_dt")
    private String frstRegDt; // 최초등록일시

    @JsonProperty("frst_reg_id")
    private String frstRegId; // 최종등록자식별자번호

    @JsonProperty("last_mod_dt")
    private String lastModDt; // 최종수정일시

    @JsonProperty("last_mod_id")
    private String lastModId; // 최종수정자식별번호

    @JsonProperty("birthYear")
    private String birthYear;

    @JsonProperty("birthMonth")
    private String birthMonth;

    @JsonProperty("birthDay")
    private String birthDay;

    @JsonProperty("recent_login")
    private String recentLogin;

    public String getRecentLogin() {
        return recentLogin;
    }

    public void setRecentLogin(String recentLogin) {
        this.recentLogin = recentLogin;
    }

    // 기본 생성자
    public UserDto() {
    }

    // 회원 정보 조회시 사용할 생성자
    public UserDto(String id, String st_cd, String email, String pwd, String name, String birth, String gender, String ph_num, String mailKey, String regDt) {
        this.id = id;
        this.st_cd = st_cd;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.ph_num = ph_num;
        this.mailKey = mailKey;
        this.regDt = regDt;
    }

    // 회원가입시 사용할 생성자
    public UserDto(String id, String email, String pwd, String name, String birth,
                   String gender, String ph_num, String zip, String road_adr, String jibunAdr, String detAdr,
                   String mailKey) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.ph_num = ph_num;
        this.zip = zip;
        this.road_adr = road_adr;
        this.jibunAdr = jibunAdr;
        this.detAdr = detAdr;
        this.mailKey = mailKey;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", grd_cd='" + grd_cd + '\'' +
                ", st_cd='" + st_cd + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", gender='" + gender + '\'' +
                ", ph_num='" + ph_num + '\'' +
                ", zip='" + zip + '\'' +
                ", road_adr='" + road_adr + '\'' +
                ", jibunAdr='" + jibunAdr + '\'' +
                ", detAdr='" + detAdr + '\'' +
                ", is_mail_auth='" + is_mail_auth + '\'' +
                ", mailKey='" + mailKey + '\'' +
                ", is_adm='" + is_adm + '\'' +
                ", regDt='" + regDt + '\'' +
                ", quitDt='" + quitDt + '\'' +
                ", isQuit='" + isQuit + '\'' +
                ", isBlok='" + isBlok + '\'' +
                ", blokRes='" + blokRes + '\'' +
                ", blokDt='" + blokDt + '\'' +
                ", unBlokDt='" + unBlokDt + '\'' +
                ", frstRegDt='" + frstRegDt + '\'' +
                ", frstRegId='" + frstRegId + '\'' +
                ", lastModDt='" + lastModDt + '\'' +
                ", lastModId='" + lastModId + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", birthMonth='" + birthMonth + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", recentLogin='" + recentLogin + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrd_cd() {
        return grd_cd;
    }

    public void setGrd_cd(String grd_cd) {
        this.grd_cd = grd_cd;
    }

    public String getSt_cd() {
        return st_cd;
    }

    public void setSt_cd(String st_cd) {
        this.st_cd = st_cd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPh_num() {
        return ph_num;
    }

    public void setPh_num(String ph_num) {
        this.ph_num = ph_num;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getRoad_adr() {
        return road_adr;
    }

    public void setRoad_adr(String road_adr) {
        this.road_adr = road_adr;
    }

    public String getJibunAdr() {
        return jibunAdr;
    }

    public void setJibunAdr(String jibunAdr) {
        this.jibunAdr = jibunAdr;
    }

    public String getDetAdr() {
        return detAdr;
    }

    public void setDetAdr(String detAdr) {
        this.detAdr = detAdr;
    }

    public String getIs_mail_auth() {
        return is_mail_auth;
    }

    public void setIs_mail_auth(String is_mail_auth) {
        this.is_mail_auth = is_mail_auth;
    }

    public String getMailKey() {
        return mailKey;
    }

    public void setMailKey(String mailKey) {
        this.mailKey = mailKey;
    }

    public String getIs_adm() {
        return is_adm;
    }

    public void setIs_adm(String is_adm) {
        this.is_adm = is_adm;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(String quitDt) {
        this.quitDt = quitDt;
    }

    public String getIsQuit() {
        return isQuit;
    }

    public void setIsQuit(String isQuit) {
        this.isQuit = isQuit;
    }

    public String getIsBlok() {
        return isBlok;
    }

    public void setIsBlok(String isBlok) {
        this.isBlok = isBlok;
    }

    public String getBlokRes() {
        return blokRes;
    }

    public void setBlokRes(String blokRes) {
        this.blokRes = blokRes;
    }

    public String getBlokDt() {
        return blokDt;
    }

    public void setBlokDt(String blokDt) {
        this.blokDt = blokDt;
    }

    public String getUnBlokDt() {
        return unBlokDt;
    }

    public void setUnBlokDt(String unBlokDt) {
        this.unBlokDt = unBlokDt;
    }

    public String getFrstRegDt() {
        return frstRegDt;
    }

    public void setFrstRegDt(String frstRegDt) {
        this.frstRegDt = frstRegDt;
    }

    public String getFrstRegId() {
        return frstRegId;
    }

    public void setFrstRegId(String frstRegId) {
        this.frstRegId = frstRegId;
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

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
