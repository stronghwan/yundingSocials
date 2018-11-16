package cn.yunding.social.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_friend_group")
public class FriendGroup {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 由该用户创建的组名
     */
    @Id
    @Column(name = "group_name")
    private String groupName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "group_id")
    private Integer groupId;

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
     * 获取由该用户创建的组名
     *
     * @return group_name - 由该用户创建的组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置由该用户创建的组名
     *
     * @param groupName 由该用户创建的组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更改时间
     *
     * @return update_time - 更改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更改时间
     *
     * @param updateTime 更改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}