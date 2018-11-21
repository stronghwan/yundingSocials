package cn.yunding.social.controller;

import cn.yunding.social.dto.RequestResult;
import cn.yunding.social.dto.ServiceResult;
import cn.yunding.social.pojo.Comment;
import cn.yunding.social.pojo.Publish;
import cn.yunding.social.pojo.TimeLine;
import cn.yunding.social.service.FriendCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author   : beOkWithAnything
 * create   : 2018:11.18 15:46
 * describe :
 */
@Controller
@RequestMapping("/api")
public class FriendCircleController {

    @Autowired
    private FriendCircleService friendCircleService;

    /**
     * 发表朋友圈
     * @param publish userId content photoNum photoUrl location isPrivate
     * @return publishResult
     */
    @ResponseBody
    @RequestMapping(value = "friendCircle/publish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RequestResult publishCircle(@RequestBody Publish publish){

        if (publish.getPublishUserId() == null || publish.getContent() == null){
            return RequestResult.failure("用户id和内容不能为空");
        }

        try {
            ServiceResult result = friendCircleService.publishCircle(publish);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("云圈发布失败");
        }
    }

    /**
     * 点赞或取消赞
     * @param timeLine userId publishId isLike
     * @return timeLintResult
     */
    @ResponseBody
    @RequestMapping(value = "friendCircle/Like", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RequestResult setLike(@RequestBody TimeLine timeLine){

        if (timeLine.getUserId() == null ||timeLine.getPublishId() == null){
            return RequestResult.failure("用户id和云圈id不能为空");
        }

        try {
            ServiceResult result = friendCircleService.setLike(timeLine);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("点赞失败");
        }
    }

    /**
     * 发表评论
     * @param comment userId publishId content
     * @return commentResult
     */
    @ResponseBody
    @RequestMapping(value = "friendCircle/comment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RequestResult comment(@RequestBody Comment comment){

        if (comment.getCommentUserId() == null || comment.getPublishId() == null){
            return RequestResult.failure("用户id和云圈id不能为空");
        }

        try {
            ServiceResult result = friendCircleService.comment(comment);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("评论失败");
        }
    }

    /**
     * 回复好友评论
     * @param comment publishId commentWhoId userId content
     * @return commentResult
     */
    @ResponseBody
    @RequestMapping(value = "friendCircle/reply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RequestResult reply(@RequestBody Comment comment){

        if(comment.getReplyCommentId() == null || comment.getCommentUserId() == null){
            return RequestResult.failure("用户id和回复id不能为空");
        }

        try {
            ServiceResult result = friendCircleService.reply(comment);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("回复失败");
        }
    }

    /**
     * 刷云圈
     * @param timeLine userId
     * @return List<publish>
     */
    @ResponseBody
    @RequestMapping(value = "friendCircle/through", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RequestResult throughCircle(@RequestBody TimeLine timeLine){

        if (timeLine.getUserId() == null){
            return RequestResult.failure("用户id不能为空");
        }

        try {
            ServiceResult result = friendCircleService.throughCircle(timeLine);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("刷新失败");
        }
    }

    /**
     * 不让他看我的朋友圈/不看他的朋友圈
     * @return
     */
//    public RequestResult setPower(){
//
//    }

}
