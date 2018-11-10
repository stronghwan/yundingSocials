package cn.yunding.social.pojo;

import cn.yunding.social.utils.MyMapper;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

public class Users {
    @Id
    private String id;

    private String ict;

    private String password;

    private String face;

    private String nickname;

    private String qrcode;

    private String phone;

    @Column(name = "sign_name")
    private String signName;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return ict
     */
    public String getIct() {
        return ict;
    }

    /**
     * @param ict
     */
    public void setIct(String ict) {
        this.ict = ict;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return face
     */
    public String getFace() {
        return face;
    }

    /**
     * @param face
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return qrcode
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * @param qrcode
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return sign_name
     */
    public String getSignName() {
        return signName;
    }

    /**
     * @param signName
     */
    public void setSignName(String signName) {
        this.signName = signName;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}