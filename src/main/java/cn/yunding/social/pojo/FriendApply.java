package cn.yunding.social.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_friend_apply")
public class FriendApply {
    /**
     * 已登录用户id
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 好友验证申请人的id
     */
    @Id
    @Column(name = "sender_id")
    private String senderId;

    /**
     * 请求者给接受人的备注
     */
    private String mark;

    /**
     * 分组
     */
    private String group;

    /**
     * 发送的验证信息
     */
    private String notes;

    /**
     * 是否同意加为好友
     */
    @Column(name = "is_agree")
    private Integer isAgree;

    /**
     * 根据申请的时间排好序
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 更新时间,timeStamp类型自动填充！？
     */
    @Column(name = "agree_time")
    private Date agreeTime;

    /**
     * 获取已登录用户id
     *
     * @return user_id - 已登录用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置已登录用户id
     *
     * @param userId 已登录用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取好友验证申请人的id
     *
     * @return sender_id - 好友验证申请人的id
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * 设置好友验证申请人的id
     *
     * @param senderId 好友验证申请人的id
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取请求者给接受人的备注
     *
     * @return mark - 请求者给接受人的备注
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置请求者给接受人的备注
     *
     * @param mark 请求者给接受人的备注
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 获取分组
     *
     * @return group - 分组
     */
    public String getGroup() {
        return group;
    }

    /**
     * 设置分组
     *
     * @param group 分组
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * 获取发送的验证信息
     *
     * @return notes - 发送的验证信息
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 设置发送的验证信息
     *
     * @param notes 发送的验证信息
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 获取是否同意加为好友
     *
     * @return is_agree - 是否同意加为好友
     */
    public Integer getIsAgree() {
        return isAgree;
    }

    /**
     * 设置是否同意加为好友
     *
     * @param isAgree 是否同意加为好友
     */
    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    /**
     * 获取根据申请的时间排好序
     *
     * @return apply_time - 根据申请的时间排好序
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置根据申请的时间排好序
     *
     * @param applyTime 根据申请的时间排好序
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取更新时间,timeStamp类型自动填充！？
     *
     * @return agree_time - 更新时间,timeStamp类型自动填充！？
     */
    public Date getAgreeTime() {
        return agreeTime;
    }

    /**
     * 设置更新时间,timeStamp类型自动填充！？
     *
     * @param agreeTime 更新时间,timeStamp类型自动填充！？
     */
    public void setAgreeTime(Date agreeTime) {
        this.agreeTime = agreeTime;
    }
}