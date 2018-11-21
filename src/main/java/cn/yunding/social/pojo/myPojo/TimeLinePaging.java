package cn.yunding.social.pojo.myPojo;


import cn.yunding.social.pojo.Comment;
import cn.yunding.social.pojo.Publish;
import cn.yunding.social.pojo.TimeLine;

import java.util.List;

/**
 * author   : beOkWithAnything
 * create   : 2018:11.20 11:19
 * describe :
 */
public class TimeLinePaging {

    /**
     * 当前页数
     */
    private int nowPage;

    /**
     * 每页条数
     */
    private final int interval = 5;

    /**
     * timeline对象的属性
     */
    private TimeLine timeLine;

    /**
     * publish对象的属性
     */
    private Publish publish;

    /**
     * comment属性
     */
    private List<Comment> commentList;

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getInterval() {
        return interval;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

    public Publish getPublish() {
        return publish;
    }

    public void setPublish(Publish publish) {
        this.publish = publish;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
