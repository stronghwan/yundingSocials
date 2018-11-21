package cn.yunding.social.service;

import cn.yunding.social.dto.ServiceResult;
import cn.yunding.social.pojo.Comment;
import cn.yunding.social.pojo.Publish;
import cn.yunding.social.pojo.TimeLine;

/**
 * author   : beOkWithAnything
 * create   : 2018:11.18 15:53
 * describe :
 */
public interface FriendCircleService {

    /**
     * 发表朋友圈
     * @param publish userId content photoNum photoUrl location isPrivate
     * @return publishResult
     */
    ServiceResult publishCircle(Publish publish);

    /**
     * 点赞
     * @param timeLine userId publishId isLike
     * @return timeLineResult
     */
    ServiceResult setLike(TimeLine timeLine);

    /**
     * 评论
     * @param comment userId publishId content
     * @return commentResult
     */
    ServiceResult comment(Comment comment);

    /**
     * 回复好友评论
     * @param comment publishId commentWhoId userId content
     * @return commentResult
     */
    ServiceResult reply(Comment comment);

    /**
     * 刷云圈
     * @param timeLine userId
     * @return List<publish>
     */
    ServiceResult throughCircle(TimeLine timeLine);
}
