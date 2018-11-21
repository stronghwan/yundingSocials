package cn.yunding.social.dto;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:11.03 20:29
 * @discription : 服务是否成功
 */
public class ServiceResult {

    /**
     * 标示service是否执行成功
     */
    private boolean success;

    /**
     * 单个数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;

    public ServiceResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    //加一个 static 不用创建对象就可以直接 用类名调用该方法
    public static ServiceResult success(Object data) {
        return new ServiceResult(true, data, null);
    }

    public static ServiceResult failure(String message) {
        return new ServiceResult(false, null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
}
