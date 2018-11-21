package cn.yunding.social.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_friend_relationship")
public class FriendRelationship {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 好友id
     */
    @Column(name = "friend_id")
    private String friendId;

    /**
     * 给此好友的备注
     */
    private String mark;

    /**
     * 是否可见朋友圈(0:不可见 默认1:可见)
     */
    @Column(name = "circle_is_see")
    private Integer circleIsSee;

    /**
     * 分组信息
     */
    @Column(name = "group_name")
    private String groupName;

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
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * 获取是否可见朋友圈(0:不可见 默认1:可见)
     *
     * @return circle_is_see - 是否可见朋友圈(0:不可见 默认1:可见)
     */
    public Integer getCircleIsSee() {
        return circleIsSee;
    }

    /**
     * 设置是否可见朋友圈(0:不可见 默认1:可见)
     *
     * @param circleIsSee 是否可见朋友圈(0:不可见 默认1:可见)
     */
    public void setCircleIsSee(Integer circleIsSee) {
        this.circleIsSee = circleIsSee;
    }

    /**
     * 获取分组信息
     *
     * @return group_name - 分组信息
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组信息
     *
     * @param groupName 分组信息
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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