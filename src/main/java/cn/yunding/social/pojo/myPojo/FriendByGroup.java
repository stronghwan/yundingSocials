package cn.yunding.social.pojo.myPojo;

import java.util.List;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.13 20:07
 * @discription :
 */
public class FriendByGroup {

    /**
     * 组名
     */
    private String groupName;

    /**
     * 该组的全部好友信息
     */
    private List<FriendInfo> friendInfoList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<FriendInfo> getFriendInfoList() {
        return friendInfoList;
    }

    public void setFriendInfoList(List<FriendInfo> friendInfoList) {
        this.friendInfoList = friendInfoList;
    }
}
