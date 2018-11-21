package cn.yunding.social.mapper;


import cn.yunding.social.pojo.FriendApply;
import cn.yunding.social.pojo.FriendRelationship;
import cn.yunding.social.pojo.myPojo.FriendApplyDisplay;
import cn.yunding.social.pojo.myPojo.FriendInfo;

import java.util.List;

/**
 * author   : beOkWithAnything
 * create   : 2018:11.16 22:03
 * describe :
 */
public interface MyFriendMapper {

    /**
     * 好友表userId关联查询Users表
     * @param friendRelationship
     * @return
     */
    List<FriendInfo> selectFriendAll(FriendRelationship friendRelationship);

    /**
     * 好友申请表关联查询Users表
     * @param friendApply
     * @return
     */
    List<FriendApplyDisplay> selectNewFriendAll(FriendApply friendApply);

    /**
     * 好友表userId+groupName关联查询user表
     * @param friendRelationship
     * @return
     */
    List<FriendInfo> selectFriendAllByGroup(FriendRelationship friendRelationship);


}
