package com.firstSpring.domain.user;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    @JsonProperty("id")
    private final String id; // 회원ID

    @JsonProperty("grd_cd")
    private final String grdCd; // 회원등급코드

    @JsonProperty("st_cd")
    private final String stCd; // 회원상태코드

    @JsonProperty("email")
    private final String email; // 이메일

    @JsonProperty("pwd")
    private final String pwd; // 비밀번호

    @JsonProperty("name")
    private final String name; // 이름

    @JsonProperty("birth")
    private final String birth; // 생년월일

    @JsonProperty("gender")
    private final char gender; // 성별

    @JsonProperty("ph_num")
    private final String phNum; // 전화번호

    @JsonProperty("zip")
    private final String zip; // 우편번호

    @JsonProperty("road_adr")
    private final String roadAdr; // 도로명주소

    @JsonProperty("jibun_adr")
    private final String jibunAdr; // 지번주소

    @JsonProperty("det_adr")
    private final String detAdr; // 상세주소

    @JsonProperty("is_mail_auth")
    private final char isMailAuth; // 메일 인증 여부

    @JsonProperty("mail_key")
    private final String mailKey; // 메일 인증 번호

    @JsonProperty("is_adm")
    private final char isAdm; // 관리자여부

    @JsonProperty("reg_dt")
    private final String regDt; // 가입일

    @JsonProperty("quit_dt")
    private final String quitDt; // 탈퇴일

    @JsonProperty("is_quit")
    private final char isQuit; // 탈퇴여부

    @JsonProperty("is_blok")
    private final char isBlok; // 차단여부

    @JsonProperty("blok_res")
    private final String blokRes; // 차단사유

    @JsonProperty("blok_dt")
    private final String blokDt; // 차단날짜

    @JsonProperty("un_blok_dt")
    private final String unBlokDt; // 차단해지날짜

    @JsonProperty("frst_reg_dt")
    private final String frstRegDt; // 최초등록일시

    @JsonProperty("frst_reg_id")
    private final String frstRegId; // 최종등록자식별자번호

    @JsonProperty("last_mod_dt")
    private final String lastModDt; // 최종수정일시

    @JsonProperty("last_mod_id")
    private final String lastModId; // 최종수정자식별번호

    private UserDto(Builder builder) {
        this.id = builder.id;
        this.grdCd = builder.grdCd;
        this.stCd = builder.stCd;
        this.email = builder.email;
        this.pwd = builder.pwd;
        this.name = builder.name;
        this.birth = builder.birth;
        this.gender = builder.gender;
        this.phNum = builder.phNum;
        this.zip = builder.zip;
        this.roadAdr = builder.roadAdr;
        this.jibunAdr = builder.jibunAdr;
        this.detAdr = builder.detAdr;
        this.isMailAuth = builder.isMailAuth;
        this.mailKey = builder.mailKey;
        this.isAdm = builder.isAdm;
        this.regDt = builder.regDt;
        this.quitDt = builder.quitDt;
        this.isQuit = builder.isQuit;
        this.isBlok = builder.isBlok;
        this.blokRes = builder.blokRes;
        this.blokDt = builder.blokDt;
        this.unBlokDt = builder.unBlokDt;
        this.frstRegDt = builder.frstRegDt;
        this.frstRegId = builder.frstRegId;
        this.lastModDt = builder.lastModDt;
        this.lastModId = builder.lastModId;
    }

    public static class Builder {
        private String id;
        private String grdCd;
        private String stCd;
        private String email;
        private String pwd;
        private String name;
        private String nick;
        private String birth;
        private char gender;
        private String phNum;
        private String zip;
        private String divAdr;
        private String roadAdr;
        private String jibunAdr;
        private String detAdr;
        private char isMailAuth;
        private String mailKey;
        private char isAdm;
        private String regDt;
        private String quitDt;
        private char isQuit;
        private char isBlok;
        private String blokRes;
        private String blokDt;
        private String unBlokDt;
        private String frstRegDt;
        private String frstRegId;
        private String lastModDt;
        private String lastModId;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder grdCd(String grdCd) {
            this.grdCd = grdCd;
            return this;
        }

        public Builder stCd(String stCd) {
            this.stCd = stCd;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nick(String nick) {
            this.nick = nick;
            return this;
        }

        public Builder birth(String birth) {
            this.birth = birth;
            return this;
        }

        public Builder gender(char gender) {
            this.gender = gender;
            return this;
        }

        public Builder phNum(String phNum) {
            this.phNum = phNum;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder divAdr(String divAdr) {
            this.divAdr = divAdr;
            return this;
        }

        public Builder roadAdr(String roadAdr) {
            this.roadAdr = roadAdr;
            return this;
        }

        public Builder jibunAdr(String jibunAdr) {
            this.jibunAdr = jibunAdr;
            return this;
        }

        public Builder detAdr(String detAdr) {
            this.detAdr = detAdr;
            return this;
        }

        public Builder isMailAuth(char isMailAuth) {
            this.isMailAuth = isMailAuth;
            return this;
        }

        public Builder mailKey(String mailKey) {
            this.mailKey = mailKey;
            return this;
        }

        public Builder isAdm(char isAdm) {
            this.isAdm = isAdm;
            return this;
        }

        public Builder regDt(String regDt) {
            this.regDt = regDt;
            return this;
        }

        public Builder quitDt(String quitDt) {
            this.quitDt = quitDt;
            return this;
        }

        public Builder isQuit(char isQuit) {
            this.isQuit = isQuit;
            return this;
        }

        public Builder isBlok(char isBlok) {
            this.isBlok = isBlok;
            return this;
        }

        public Builder blokRes(String blokRes) {
            this.blokRes = blokRes;
            return this;
        }

        public Builder blokDt(String blokDt) {
            this.blokDt = blokDt;
            return this;
        }

        public Builder unBlokDt(String unBlokDt) {
            this.unBlokDt = unBlokDt;
            return this;
        }

        public Builder frstRegDt(String frstRegDt) {
            this.frstRegDt = frstRegDt;
            return this;
        }

        public Builder frstRegId(String frstRegId) {
            this.frstRegId = frstRegId;
            return this;
        }

        public Builder lastModDt(String lastModDt) {
            this.lastModDt = lastModDt;
            return this;
        }

        public Builder lastModId(String lastModId) {
            this.lastModId = lastModId;
            return this;
        }

        public Builder birth(String birthYear, String birthMonth, String birthDay) {
            // 생년월일을 'YYYY-MM-DD' 형식으로 조합
            this.birth = String.format("%04d-%02d-%02d", Integer.parseInt(birthYear), Integer.parseInt(birthMonth), Integer.parseInt(birthDay));
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

        public Builder from(UserDto customer) {
            this.id = customer.id;
            this.grdCd = customer.grdCd;
            this.stCd = customer.stCd;
            this.email = customer.email;
            this.pwd = customer.pwd;
            this.name = customer.name;
            this.birth = customer.birth;
            this.gender = customer.gender;
            this.phNum = customer.phNum;
            this.zip = customer.zip;
            this.roadAdr = customer.roadAdr;
            this.jibunAdr = customer.jibunAdr;
            this.detAdr = customer.detAdr;
            this.isMailAuth = customer.isMailAuth;
            this.mailKey = customer.mailKey;
            this.isAdm = customer.isAdm;
            this.regDt = customer.regDt;
            this.quitDt = customer.quitDt;
            this.isQuit = customer.isQuit;
            this.isBlok = customer.isBlok;
            this.blokRes = customer.blokRes;
            this.blokDt = customer.blokDt;
            this.unBlokDt = customer.unBlokDt;
            this.frstRegDt = customer.frstRegDt;
            this.frstRegId = customer.frstRegId;
            this.lastModDt = customer.lastModDt;
            this.lastModId = customer.lastModId;
            return this;
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getGrdCd() {
        return grdCd;
    }

    public String getStCd() {
        return stCd;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public char getGender() {
        return gender;
    }

    public String getPhNum() {
        return phNum;
    }

    public String getZip() {
        return zip;
    }

    public String getRoadAdr() {
        return roadAdr;
    }

    public String getJibunAdr() {
        return jibunAdr;
    }

    public String getDetAdr() {
        return detAdr;
    }

    public char getIsMailAuth() {
        return isMailAuth;
    }

    public String getMailKey() {
        return mailKey;
    }

    public char getIsAdm() {
        return isAdm;
    }

    public String getRegDt() {
        return regDt;
    }

    public String getQuitDt() {
        return quitDt;
    }

    public char getIsQuit() {
        return isQuit;
    }

    public char getIsBlok() {
        return isBlok;
    }

    public String getBlokRes() {
        return blokRes;
    }

    public String getBlokDt() {
        return blokDt;
    }

    public String getUnBlokDt() {
        return unBlokDt;
    }

    public String getFrstRegDt() {
        return frstRegDt;
    }

    public String getFrstRegId() {
        return frstRegId;
    }

    public String getLastModDt() {
        return lastModDt;
    }

    public String getLastModId() {
        return lastModId;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + id + '\'' +
                ", grdCd='" + grdCd + '\'' +
                ", stCd='" + stCd + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", gender=" + gender +
                ", phNum='" + phNum + '\'' +
                ", zip='" + zip + '\'' +
                ", roadAdr='" + roadAdr + '\'' +
                ", jibunAdr='" + jibunAdr + '\'' +
                ", detAdr='" + detAdr + '\'' +
                ", isMailAuth=" + isMailAuth +
                ", mailKey='" + mailKey + '\'' +
                ", isAdm=" + isAdm +
                ", regDt='" + regDt + '\'' +
                ", quitDt='" + quitDt + '\'' +
                ", isQuit=" + isQuit +
                ", isBlok=" + isBlok +
                ", blokRes='" + blokRes + '\'' +
                ", blokDt='" + blokDt + '\'' +
                ", unBlokDt='" + unBlokDt + '\'' +
                ", frstRegDt='" + frstRegDt + '\'' +
                ", frstRegId='" + frstRegId + '\'' +
                ", lastModDt='" + lastModDt + '\'' +
                ", lastModId='" + lastModId + '\'' +
                '}';
    }
}

