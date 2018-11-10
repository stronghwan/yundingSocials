package cn.yunding.social.controller;

import cn.yunding.social.mapper.UsersMapper;
import cn.yunding.social.pojo.Users;
import cn.yunding.social.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping("/user/find.do")
    public String find(){
        Users users = new Users();
        users.setCreateTime(new Date());
        String s = sid.nextShort();
        users.setId(s);
        users.setFace("");
        users.setNickname("");
        users.setIct("");
        users.setPassword("");
        users.setQrcode("");
        users.setPhone("");
        users.setSignName("");
        userService.saveUser(users);
        return "sdfsdf";
    }
}
