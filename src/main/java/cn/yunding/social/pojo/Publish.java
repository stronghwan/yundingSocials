package cn.yunding.social.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_friendcircle_publish")
public class Publish {
    /**
     * 发表内容id
     */
    @Id
    private String id;

    /**
     * 发表用户id
     */
    @Column(name = "publish_user_id")
    private String publishUserId;

    /**
     * 文字内容
     */
    private String content;

    /**
     * 发表的图片数量
     */
    @Column(name = "photo_num")
    private Integer photoNum;

    /**
     * 发表的图片地址
     */
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * 点赞数
     */
    @Column(name = "like_num")
    private Integer likeNum;

    /**
     * 评论数
     */
    @Column(name = "comment_num")
    private Integer commentNum;

    /**
     * 用户的当前位置
     */
    private String location;

    /**
     * 是否私密(默认0:公开 1:私密)
     */
    @Column(name = "is_private")
    private Integer isPrivate;

    /**
     * 发布时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取发表内容id
     *
     * @return id - 发表内容id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置发表内容id
     *
     * @param id 发表内容id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取发表用户id
     *
     * @return publish_user_id - 发表用户id
     */
    public String getPublishUserId() {
        return publishUserId;
    }

    /**
     * 设置发表用户id
     *
     * @param publishUserId 发表用户id
     */
    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    /**
     * 获取文字内容
     *
     * @return content - 文字内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文字内容
     *
     * @param content 文字内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发表的图片数量
     *
     * @return photo_num - 发表的图片数量
     */
    public Integer getPhotoNum() {
        return photoNum;
    }

    /**
     * 设置发表的图片数量
     *
     * @param photoNum 发表的图片数量
     */
    public void setPhotoNum(Integer photoNum) {
        this.photoNum = photoNum;
    }

    /**
     * 获取发表的图片地址
     *
     * @return photo_url - 发表的图片地址
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 设置发表的图片地址
     *
     * @param photoUrl 发表的图片地址
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取点赞数
     *
     * @return like_num - 点赞数
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * 设置点赞数
     *
     * @param likeNum 点赞数
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * 获取评论数
     *
     * @return comment_num - 评论数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 设置评论数
     *
     * @param commentNum 评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 获取用户的当前位置
     *
     * @return location - 用户的当前位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置用户的当前位置
     *
     * @param location 用户的当前位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取是否私密(默认0:公开 1:私密)
     *
     * @return is_private - 是否私密(默认0:公开 1:私密)
     */
    public Integer getIsPrivate() {
        return isPrivate;
    }

    /**
     * 设置是否私密(默认0:公开 1:私密)
     *
     * @param isPrivate 是否私密(默认0:公开 1:私密)
     */
    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * 获取发布时间
     *
     * @return create_time - 发布时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发布时间
     *
     * @param createTime 发布时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}