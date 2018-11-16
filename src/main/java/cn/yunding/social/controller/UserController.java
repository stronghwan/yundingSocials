package cn.yunding.social.controller;

import cn.yunding.social.pojo.Users;
import cn.yunding.social.pojo.bo.UserBo;
import cn.yunding.social.pojo.vo.UserVo;
import cn.yunding.social.service.UserService;
import cn.yunding.social.utils.MD5Utils;
import cn.yunding.social.utils.YDResult;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @TODO
 * @Author stronghwan
 * @Verison
 * @Date2018/11/9-20-09
 */
@RestController
public class UserController {

    @Autowired
    private Sid sid;



    @Autowired
    private UserService userService;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/user/sendCode.do",method = RequestMethod.POST)
    public YDResult sendCode(String phone){
        if (StringUtils.isBlank(phone)){
            return YDResult.errorMsg("电话号不能为空");
        }
        boolean isUser = userService.userCheckRepate(phone);
        if (!isUser){
            return YDResult.errorMsg("手机号已经注册");
        }
        try {
            userService.createSmsCode(phone);
            return YDResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return YDResult.errorMsg("");
        }
    }

    /**
     * 注册、检验验证码
     * @param userBo
     * @return返回 ict号
     */
    @RequestMapping(value = "/user/register.do", method = RequestMethod.POST)
    public YDResult saveUser(@RequestBody UserBo userBo){
       // todo  前端传过来的参数不能为空
        boolean isCode = userService.checkSmsCode(userBo.getPhone(), userBo.getCode());
        if (!isCode) {
            return YDResult.errorMsg("验证码输入错误");
        }
        try {
            userBo.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
            Users users = userService.saveUser(userBo);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(users,userVo);
            return YDResult.ok(userVo);
        } catch (Exception e) {
            e.printStackTrace();
            return YDResult.errorMsg("注册失败");
        }
    }

    /**
     * 登录
     * @param users
     * @return
     */
    @RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
    public YDResult login(@RequestBody Users users) throws Exception {
        if (StringUtils.isNotBlank(users.getIct()) && StringUtils.isNotBlank(users.getPassword())){
            // todo ict登录
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            Users usersIct = userService.userLoginByIct(users);
            if (usersIct != null){
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(usersIct,userVo);
                return YDResult.ok(userVo);
            }else {
                return YDResult.errorMsg("账号或者密码错误");
            }
        }
        if (StringUtils.isNotBlank(users.getPhone()) && StringUtils.isNotBlank(users.getPassword())){
            // todo phone登录
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            Users usersPhone = userService.userLoginByPhone(users);
            if (usersPhone != null){
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(usersPhone,userVo);
                return YDResult.ok(userVo);
            }else {
                return YDResult.errorMsg("账号或者密码错误");
            }
        }
        return null;
    }

    /**
     *  修改密码发送验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/user/sendCodePassword.do",method = RequestMethod.POST)
    public YDResult sendCodePassword(String phone){
        if (StringUtils.isBlank(phone)){
            return YDResult.errorMsg("电话号不能为空");
        }
        boolean isUser = userService.userCheckRepate(phone);
        if (isUser){
            return YDResult.errorMsg("手机号没有注册");
        }
        try {
            userService.createSmsCode(phone);
            return YDResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return YDResult.errorMsg("");
        }
    }

    /**
     * 找回密码
     * @param
     * @return
     */
    @RequestMapping(value = "/user/findPassword.do",method = RequestMethod.POST)
    public YDResult findPassword(@RequestBody UserBo userBo){
        // todo 前端传入不为空
        boolean isCode = userService.checkSmsCode(userBo.getPhone(), userBo.getCode());
        if (!isCode) {
            return YDResult.errorMsg("验证码输入错误");
        }
        try {
            Users users = new Users();
            BeanUtils.copyProperties(userBo,users);
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            Users usersResult = userService.updatePassword(users);
            UserVo userVoResult = new UserVo();
            BeanUtils.copyProperties(usersResult,userVoResult);
            return YDResult.ok(userVoResult);
        } catch (Exception e) {
            e.printStackTrace();
            return YDResult.errorMsg("修改失败");
        }
    }

    /**
     * 修改个人信息
     * @param users
     * @return
     */
    @RequestMapping(value = "/user/updateUser.do", method = RequestMethod.POST)
    public YDResult updateUser(@RequestBody Users users){

        try {
            Users usersResult = userService.updateUserInfo(users);
            return YDResult.ok(usersResult);
        } catch (Exception e) {
            e.printStackTrace();
            return YDResult.errorMsg("修改失败");
        }
    }
    @Value("${IMAGE_URL}")
    private String IMAGE_URL;

    /**
     * 头像图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/user/upload.do")
    public YDResult upload(@RequestParam(required = false) MultipartFile file, String phone){

        try {
            String originalFilename = file.getOriginalFilename();
            String etx = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String format = dateFormat.format(new Date());
            Random random = new Random();
            for (int i = 0; i <= 3; i++) {
                // nextint产生0-9的数字
                format += random.nextInt(10);
            }
            String path = "upload/"+format+"."+etx;
            String url = IMAGE_URL + path;
            if (etx.equals("jpg") || etx.equals("png")){
                Client client = new Client();
                WebResource webResource = client.resource(url);
                webResource.put(String.class,file.getBytes());
                Users users = new Users();
                users.setFace(path);
                users.setPhone(phone);
//                userService.updateUserInfo(users);
                System.out.println(url);
                return YDResult.ok(url);
            }else {
                return YDResult.errorMsg("图片格式不支持");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return YDResult.errorMsg("图片上传失败");
        }
    }
}
