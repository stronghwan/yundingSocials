package cn.yunding.social.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @TODO
 * @Author stronghwan
 * @Verison
 * @Date2018/11/9-20-09
 */
@RestController
public class UserController {
    @RequestMapping("/user/find.do")
    public String find(){
        return "dsfs";
    }
}
