package cn.yunding.social.service;

import cn.yunding.social.dto.ServiceResult;
import cn.yunding.social.pojo.FriendApply;
import cn.yunding.social.pojo.FriendRelationship;
import cn.yunding.social.pojo.myPojo.FriendSearchUser;

/**
 * 
 * @author swqing
 *
 */
public interface FriendsService {


    /**
     * 搜索用户
     * @param friendSearchUser
     * @return
     */
    ServiceResult searchUser(FriendSearchUser friendSearchUser);

    /**
     * 发送好友请求
     * @param friendApply
     * @return
     */
    ServiceResult sendRequest(FriendApply friendApply);

    /**
     * 查看收到的好友请求
     * @param friendApply
     * @return
     */
    ServiceResult checkRequest(FriendApply friendApply);

	/**
	 * 接受好友请求
	 * @param friendApply
	 * @return
	 */
	ServiceResult addFriend(FriendApply friendApply);

	/**
	 * 查询该用户的好友列表
	 * @param friendRelationship
	 * @return
	 */
	ServiceResult getFriends(FriendRelationship friendRelationship);

    /**
     * 修改备注
     * @param friendRelationship
     * @return
     */
    ServiceResult updateMarkAndGroup(FriendRelationship friendRelationship);

    /**
     *  获取好友分组列表
     * @param friendRelationship
     * @return
     */
    ServiceResult getFriendsByGroup(FriendRelationship friendRelationship);

    /**
     *  根据好友信息查询
     * @param friendSearchUser
     * @return
     */
    ServiceResult searchFriend(FriendSearchUser friendSearchUser);
}
