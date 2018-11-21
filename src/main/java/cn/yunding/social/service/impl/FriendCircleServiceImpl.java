package cn.yunding.social.service.impl;


import cn.yunding.social.dto.ServiceResult;
import cn.yunding.social.mapper.CommentMapper;
import cn.yunding.social.mapper.FriendRelationshipMapper;
import cn.yunding.social.mapper.PublishMapper;
import cn.yunding.social.mapper.TimeLineMapper;
import cn.yunding.social.pojo.Comment;
import cn.yunding.social.pojo.FriendRelationship;
import cn.yunding.social.pojo.Publish;
import cn.yunding.social.pojo.TimeLine;
import cn.yunding.social.pojo.myPojo.TimeLinePaging;
import cn.yunding.social.service.FriendCircleService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author   : beOkWithAnything
 * create   : 2018:11.18 15:53
 * describe :
 */
@Service
public class FriendCircleServiceImpl implements FriendCircleService {

    @Autowired
    private Sid sid;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private TimeLineMapper timeLineMapper;

    @Autowired
    private FriendRelationshipMapper friendRelationshipMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 发布云圈消息
     * @param publish userId content photoNum photoUrl location isPrivate
     * @return publishResult
     */
    @Override
    public ServiceResult publishCircle(Publish publish) {

        // 发布的消息的id
        String publishId = sid.nextShort();
        // 消息发布时间
        Date date = new Date();

        try {
            // 添加到消息发布表
            publish.setId(publishId);
            int result = publishMapper.insertSelective(publish);
            // 返回数据，检测是否添加成功
            Publish publishResult = publishMapper.selectByPrimaryKey(publishId);

            // 添加到自己的时间轴表
            TimeLine timeLine0 = new TimeLine();
            timeLine0.setUserId(publish.getPublishUserId());
            timeLine0.setPublishId(publishId);
            timeLine0.setIsOwn(1);
            timeLine0.setCreateTime(new Timestamp(date.getTime()));
            int result2 = timeLineMapper.insertSelective(timeLine0);

            //如果该消息不是私密的
            if (publish.getIsPrivate() != 1) {
                // 添加到所有好友的时间轴表
                FriendRelationship friendRelationship = new FriendRelationship();
                friendRelationship.setUserId(publish.getPublishUserId());
                List<FriendRelationship> list = friendRelationshipMapper.select(friendRelationship);
                for (int i = 0; i < list.size(); i++) {
                    /*
                     * --->      -1:自己不允许好友看且好友也不想看此用户,
                     * --->       0:不允许好友看且好友没有设置不看此用户,
                     * ---> 默认为 1:允许好友看自己且好友没有设置不看此用户,
                     * --->       2:允许好友看自己但好友不看
                     */
                    if (list.get(i).getCircleIsSee() == 1) {
                        // 如果允许此好友查看自己的朋友圈且该好友未设置不看,则放到该好友的时间轴表里
                        TimeLine timeLine = new TimeLine();
                        timeLine.setUserId(list.get(i).getFriendId());
                        timeLine.setPublishId(publishId);
                        timeLine.setIsOwn(0);
                        timeLine.setCreateTime(new Timestamp(date.getTime()));
                        timeLineMapper.insertSelective(timeLine);
                    }
                }
            }
            if (result > 0 && result2 > 0){
                return ServiceResult.success(publishResult);
            } else {
                return ServiceResult.failure("操作失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 给好友的云圈点赞或者取消赞
     * @param timeLine userId publishId isLike
     * @return timeLineResult
     */
    public ServiceResult setLike(TimeLine timeLine){

        try {
            // 直接把主键设置成 userId + publishId 便于updateByPrimaryKey操作(否则还要先查询到主键id)
            int result = timeLineMapper.updateByPrimaryKeySelective(timeLine);
            // 返回数据，检测是否添加成功
            TimeLine timeLineResult = timeLineMapper.selectOne(timeLine);
            if (result > 0){
                return ServiceResult.success(timeLineResult);
            } else{
                return ServiceResult.failure("操作失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 评论
     * @param comment userId publishId content
     * @return commentResult
     */
    @Override
    public ServiceResult comment(Comment comment) {

        // 设置评论主键Id
        String id = sid.nextShort();
        comment.setId(id);

        try {
            // 插入评论
            int result = commentMapper.insertSelective(comment);
            //返回数据，检测是否添加成功
            Comment commentResult = commentMapper.selectOne(comment);
            if (result > 0){
                return ServiceResult.success(commentResult);
            } else {
                return ServiceResult.failure("操作失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 回复好友评论
     * @param comment publishId commentWhoId userId content
     * @return commentResult
     */
    @Override
    public ServiceResult reply(Comment comment) {

        //设置回复主键id
        String id = sid.nextShort();
        comment.setId(id);
        comment.setCommentOrReply(1);

        try {
            //插入回复
            int result = commentMapper.insertSelective(comment);
            //返回数据，检测是否添加成功
            Comment commentResult = commentMapper.selectOne(comment);
            if (result > 0){
                return ServiceResult.success(commentResult);
            } else {
                return ServiceResult.failure("操作失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 刷云圈
     * @param timeLine userId
     * @return List<publish>
     */
    @Override
    public ServiceResult throughCircle(TimeLine timeLine) {

        List<TimeLinePaging> timeLinePagingList = new ArrayList<TimeLinePaging>();
        try {
            List<TimeLine> timeLineList = timeLineMapper.select(timeLine);
            for (TimeLine timeLine1 : timeLineList) {
                TimeLinePaging timeLinePaging = new TimeLinePaging();
                // 添加timeLine属性
                timeLinePaging.setTimeLine(timeLine1);
                // 添加publish属性
                Publish publish = publishMapper.selectByPrimaryKey(timeLine1.getPublishId());
                timeLinePaging.setPublish(publish);
                // 添加List<comment>属性
                Comment comment = new Comment();
                comment.setPublishId(timeLine1.getPublishId());
                List<Comment> commentList = commentMapper.select(comment);
                timeLinePaging.setCommentList(commentList);
                timeLinePagingList.add(timeLinePaging);
            }
            if (timeLinePagingList.size() > 0){
                return ServiceResult.success(timeLinePagingList);
            } else{
                return ServiceResult.failure("您的云圈暂无动态...");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
