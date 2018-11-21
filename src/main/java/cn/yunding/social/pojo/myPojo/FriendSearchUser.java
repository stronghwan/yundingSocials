package cn.yunding.social.pojo.myPojo;


import cn.yunding.social.pojo.Users;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.11 10:43
 * @discription :
 */
public class FriendSearchUser extends Users {

    /**
     * 搜索用户时接受前端传来的参数(可能是手机号或ICT号)
     */
    private String searchMessage;

    /**
     * 此属性用来告诉前端，查询到的信息，是用户自己还是用户的好友还是陌生人
     */
    private int whoMessage;

    public String getSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }

    public int getWhoMessage() {
        return whoMessage;
    }

    public void setWhoMessage(int whoMessage) {
        this.whoMessage = whoMessage;
    }
}
