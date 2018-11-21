package cn.yunding.social.dto;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.03 20:30
 * @discription : 请求是否成功
 */
public class RequestResult {

    /**
     * 表示请求是否成功
     */
    private Integer code;

    /**
     * 单个数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 构造函数
     * @param code
     * @param data
     * @param message
     */
    public RequestResult(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RequestResult success() {
        return new RequestResult(200, null, "操作成功！");
    }

    public static RequestResult success(Object data) {
        return new RequestResult(200, data, "操作成功！");
    }

    public static RequestResult failure() {
        return new RequestResult(500, null, "操作失败");
    }

    public static RequestResult failure(String message) {
        return new RequestResult(500, null, message);
    }

}
