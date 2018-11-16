package cn.yunding.social.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_friend_relationship")
public class FriendRelationship {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 好友id
     */
    @Id
    @Column(name = "friend_id")
    private String friendId;

    /**
     * 给此好友的备注
     */
    private String mark;

    /**
     * 分组信息
     */
    private String group;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取好友id
     *
     * @return friend_id - 好友id
     */
    public String getFriendId() {
        return friendId;
    }

    /**
     * 设置好友id
     *
     * @param friendId 好友id
     */
    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    /**
     * 获取给此好友的备注
     *
     * @return mark - 给此好友的备注
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置给此好友的备注
     *
     * @param mark 给此好友的备注
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 获取分组信息
     *
     * @return group - 分组信息
     */
    public String getGroup() {
        return group;
    }

    /**
     * 设置分组信息
     *
     * @param group 分组信息
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新日期
     *
     * @return update_time - 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新日期
     *
     * @param updateTime 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}