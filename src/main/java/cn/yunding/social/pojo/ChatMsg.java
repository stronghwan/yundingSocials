package cn.yunding.social.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_chat_msg")
public class ChatMsg {
    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "receive_user_id")
    private String receiveUserId;

    private String msg;

    @Column(name = "sign_flag")
    private Integer signFlag;

    @Column(name = "creat_time")
    private Date creatTime;

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
     * @return send_user_id
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * @param sendUserId
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * @return receive_user_id
     */
    public String getReceiveUserId() {
        return receiveUserId;
    }

    /**
     * @param receiveUserId
     */
    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return sign_flag
     */
    public Integer getSignFlag() {
        return signFlag;
    }

    /**
     * @param signFlag
     */
    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }

    /**
     * @return creat_time
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}