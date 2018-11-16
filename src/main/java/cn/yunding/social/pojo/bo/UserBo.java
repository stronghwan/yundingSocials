package cn.yunding.social.pojo.bo;

/**
 * @TODO
 * @Author stronghwan
 * @Verison
 * @Date2018/11/15-13-05
 */
public class UserBo {

    private String id;

    private String ict;

    private String face;

    private String nickname;

    private String qrcode;

    private String phone;

    private String code;

    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIct() {
        return ict;
    }

    public void setIct(String ict) {
        this.ict = ict;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
