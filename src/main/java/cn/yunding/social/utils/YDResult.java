package cn.yunding.social.utils;

/**
 * 响应结构体
 * @Author stronghwan
 * @Verison
 * @Date2018/11/15-12-47
 */
public class YDResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok;	// 不使用

    public static YDResult build(Integer status, String msg, Object data) {
        return new YDResult(status, msg, data);
    }

    public static YDResult ok(Object data) {
        return new YDResult(data);
    }

    public static YDResult ok() {
        return new YDResult(null);
    }

    public static YDResult errorMsg(String msg) {
        return new YDResult(500, msg, null);
    }

    public static YDResult errorMap(Object data) {
        return new YDResult(501, "error", data);
    }

    public static YDResult errorTokenMsg(String msg) {
        return new YDResult(502, msg, null);
    }

    public static YDResult errorException(String msg) {
        return new YDResult(555, msg, null);
    }

    public YDResult() {

    }

//    public static LeeJSONResult build(Integer status, String msg) {
//        return new LeeJSONResult(status, msg, null);
//    }

    public YDResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public YDResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
