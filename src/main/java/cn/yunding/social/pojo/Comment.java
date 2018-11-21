package cn.yunding.social.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_friendcircle_comment")
public class Comment {
    /**
     * 评论的id
     */
    @Id
    private String id;

    /**
     * 评论者id
     */
    @Column(name = "comment_user_id")
    private String commentUserId;

    /**
     * 评论的内容/回复的内容
     */
    private String content;

    /**
     * 标识是哪条朋友圈的评论
     */
    @Column(name = "publish_id")
    private String publishId;

    /**
     * 标识是给哪条评论的回复
     */
    @Column(name = "reply_comment_id")
    private String replyCommentId;

    /**
     * 标识content是评论还是给评论的回复(默认0:评论 1:回复)
     */
    @Column(name = "comment_or_reply")
    private Integer commentOrReply;

    /**
     * 发表时间
     */
    @Column(name = "say_time")
    private Date sayTime;

    /**
     * 获取评论的id
     *
     * @return id - 评论的id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置评论的id
     *
     * @param id 评论的id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取评论者id
     *
     * @return comment_user_id - 评论者id
     */
    public String getCommentUserId() {
        return commentUserId;
    }

    /**
     * 设置评论者id
     *
     * @param commentUserId 评论者id
     */
    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    /**
     * 获取评论的内容/回复的内容
     *
     * @return content - 评论的内容/回复的内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论的内容/回复的内容
     *
     * @param content 评论的内容/回复的内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取标识是哪条朋友圈的评论
     *
     * @return publish_id - 标识是哪条朋友圈的评论
     */
    public String getPublishId() {
        return publishId;
    }

    /**
     * 设置标识是哪条朋友圈的评论
     *
     * @param publishId 标识是哪条朋友圈的评论
     */
    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    /**
     * 获取标识是给哪条评论的回复
     *
     * @return reply_comment_id - 标识是给哪条评论的回复
     */
    public String getReplyCommentId() {
        return replyCommentId;
    }

    /**
     * 设置标识是给哪条评论的回复
     *
     * @param replyCommentId 标识是给哪条评论的回复
     */
    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    /**
     * 获取标识content是评论还是给评论的回复(默认0:评论 1:回复)
     *
     * @return comment_or_reply - 标识content是评论还是给评论的回复(默认0:评论 1:回复)
     */
    public Integer getCommentOrReply() {
        return commentOrReply;
    }

    /**
     * 设置标识content是评论还是给评论的回复(默认0:评论 1:回复)
     *
     * @param commentOrReply 标识content是评论还是给评论的回复(默认0:评论 1:回复)
     */
    public void setCommentOrReply(Integer commentOrReply) {
        this.commentOrReply = commentOrReply;
    }

    /**
     * 获取发表时间
     *
     * @return say_time - 发表时间
     */
    public Date getSayTime() {
        return sayTime;
    }

    /**
     * 设置发表时间
     *
     * @param sayTime 发表时间
     */
    public void setSayTime(Date sayTime) {
        this.sayTime = sayTime;
    }
}