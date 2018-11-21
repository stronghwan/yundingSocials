package cn.yunding.social.controller;

import cn.yunding.social.dto.RequestResult;
import cn.yunding.social.dto.ServiceResult;
import cn.yunding.social.pojo.FriendApply;
import cn.yunding.social.pojo.FriendRelationship;
import cn.yunding.social.pojo.myPojo.FriendSearchUser;
import cn.yunding.social.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.10 19:03
 * @discription :
 */
@Controller
@RequestMapping("/api")
public class FriendsController {

    /*
     * 如果是@Autowired(required = false)  会忽略这个dao的bean创建
     */
    @Autowired
    private FriendsService friendsService;

    /**
     * 查询用户
     * @param friendSearchUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RequestResult searchUser(@RequestBody FriendSearchUser friendSearchUser){

        if (friendSearchUser.getId() == null || friendSearchUser.getSearchMessage() == null){
            return RequestResult.failure("用户id和查询信息不能为空");
        }
        try{
            ServiceResult result = friendsService.searchUser(friendSearchUser);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            } else{
                return RequestResult.failure(result.getMessage());
            }
        } catch(Exception e){
            return RequestResult.failure("搜索用户失败");
        }
    }

    /**
     * 发送好友请求
     */
    @ResponseBody
    @RequestMapping(value="/friends/request/send",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult sendFriendRequest(@RequestBody FriendApply friendApply){

        if (friendApply.getUserId() == null || friendApply.getSenderId() == null){
            return RequestResult.failure("发送人和接收人不能为空");
        }
        try{
            ServiceResult result = friendsService.sendRequest(friendApply);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else{
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e){
            return RequestResult.failure("发送请求失败!");
        }
    }

    /**
     * 查看收到的好友请求（新的朋友）
     */
    @ResponseBody
    @RequestMapping(value="/friends/request/show",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult showRequest(@RequestBody FriendApply friendApply){

        if (friendApply.getUserId() == null){
            RequestResult.failure("用户id不能为空");
        }
        try{
            ServiceResult result = friendsService.checkRequest(friendApply);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            } else{
                return RequestResult.failure(result.getMessage());
            }
        } catch(Exception e){
            return RequestResult.failure("查看好友请求失败");
        }
    }

    /**
     * 接受好友请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/friends/request/agree",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult addFriend(@RequestBody FriendApply friendApply){

        if (friendApply.getUserId() == null || friendApply.getSenderId() == null){
            return RequestResult.failure("用户id和申请者id不能为空");
        }
        try{
            ServiceResult serviceResult = friendsService.addFriend(friendApply);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            } else{
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch(Exception e){
            return RequestResult.failure("添加好友失败！");
        }
    }

    /**
     * 获取好友列表(A~Z):
     * @param friendRelationship
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/friends/show",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult getFriends(@RequestBody FriendRelationship friendRelationship){

        if (friendRelationship.getUserId() == null){
            return RequestResult.failure("用户id不能为空");
        }
        try{
            ServiceResult result = friendsService.getFriends(friendRelationship);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            } else{
                return RequestResult.failure(result. getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取好友失败！");
        }
    }


    /**
     * 修改备注和分组
     * @param friendRelationship
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/friends/updateMarkAndGroup",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult updateMarkAndGroup(@RequestBody FriendRelationship friendRelationship){

        if (friendRelationship.getUserId() == null || friendRelationship.getFriendId() == null){
            return RequestResult.failure("用户id和好友id不能为空");
        }
        try{
            ServiceResult result = friendsService.updateMarkAndGroup(friendRelationship);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else{
                return RequestResult.failure(result.getMessage());
            }
        } catch(Exception e){
            return RequestResult.failure("修改备注失败！");
        }
    }

    /**
     * 获取好友列表(分组)
     */
    @ResponseBody
    @RequestMapping(value = "/friends/showByGroup",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult getFriendsByGroup(@RequestBody FriendRelationship friendRelationship){

        if (friendRelationship.getUserId() == null){
            return RequestResult.failure("用户id不能为空");
        }
        try{
            ServiceResult result = friendsService.getFriendsByGroup(friendRelationship);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            } else{
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e){
            return RequestResult.failure("获取好友列表失败！");
        }
    }

    /**
     * 在好友列表里查询想要的用户
     */
    @ResponseBody
    @RequestMapping(value = "/friends/search", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RequestResult searchFriend(@RequestBody FriendSearchUser friendSearchUser){

        if (friendSearchUser.getSearchMessage() == null){
            return RequestResult.failure("搜索内容不能为空");
        }
        try {
            ServiceResult result = friendsService.searchFriend(friendSearchUser);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("查询无结果...");
        }
    }
}
