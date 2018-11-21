package cn.yunding.social.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_friendcircle_timeline")
public class TimeLine {
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
     * 朋友圈消息id
     */
    @Column(name = "publish_id")
    private String publishId;

    /**
     * 是否是自己的
     */
    @Column(name = "is_own")
    private Integer isOwn;

    /**
     * 是否点赞(默认0:不喜欢 1:喜欢)
     */
    @Column(name = "is_like")
    private Integer isLike;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取朋友圈消息id
     *
     * @return publish_id - 朋友圈消息id
     */
    public String getPublishId() {
        return publishId;
    }

    /**
     * 设置朋友圈消息id
     *
     * @param publishId 朋友圈消息id
     */
    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    /**
     * 获取是否是自己的
     *
     * @return is_own - 是否是自己的
     */
    public Integer getIsOwn() {
        return isOwn;
    }

    /**
     * 设置是否是自己的
     *
     * @param isOwn 是否是自己的
     */
    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }

    /**
     * 获取是否点赞(默认0:不喜欢 1:喜欢)
     *
     * @return is_like - 是否点赞(默认0:不喜欢 1:喜欢)
     */
    public Integer getIsLike() {
        return isLike;
    }

    /**
     * 设置是否点赞(默认0:不喜欢 1:喜欢)
     *
     * @param isLike 是否点赞(默认0:不喜欢 1:喜欢)
     */
    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
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
}