package cn.yunding.social.pojo.myPojo;


import cn.yunding.social.pojo.FriendRelationship;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.13 15:01
 * @discription :
 */
public class FriendInfo extends FriendRelationship {

    private String ict;

    private String phone;

    private String nickname;

    /**
     * 头像
     */
    private String face;

    private String signName;

    /**
     * 如果有备注就存放备注，没有就存放昵称
     */
    private String display;

    public String getIct() {
        return ict;
    }

    public void setIct(String ict) {
        this.ict = ict;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
