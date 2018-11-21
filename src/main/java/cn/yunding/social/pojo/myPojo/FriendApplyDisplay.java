package cn.yunding.social.pojo.myPojo;


import cn.yunding.social.pojo.FriendApply;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.11 17:37
 * @discription : 用于存放新的朋友列表里的好友信息
 */
public class FriendApplyDisplay extends FriendApply {

    /**
     * 为了格式化传给前端的日期格式，加的注解
     * 但是最好不要加在父类FriendApply中
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 用于存放好友申请里用户的头像
     */
    private String img;

    /**
     * 用于存放好友申请里用户的昵称
     */
    private String nickname;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
