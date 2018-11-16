package cn.yunding.social.service;

import cn.yunding.social.pojo.Users;
import cn.yunding.social.pojo.bo.UserBo;
import org.owasp.esapi.User;

import java.io.IOException;

/**
 * @TODO
 * @Author stronghwan
 * @Verison
 * @Date2018/11/10-16-03
 */
public interface UserService {
    /**
     * 发送验证码
     * @param phone 用户手机号
     */
    public void createSmsCode(String phone);

    /**
     * 检验验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    public boolean checkSmsCode(String phone,String code);

    /**
     * 用户注册的时候添加 并且检测验证码
     * @return
     */
    public Users saveUser(UserBo userBo) throws IOException;

    /**
     * 检查用户是否存在
     * @param phone
     * @return
     */
    public boolean userCheckRepate(String phone);

    public Users userLoginByIct(Users users);

    public Users userLoginByPhone(Users users);

    /**
     * 修改密码
     * @param users
     * @return
     */
    public Users updatePassword(Users users);

    /**
     * 修改个人信息
     * @param users
     * @return
     */
    public Users updateUserInfo(Users users);
 }
