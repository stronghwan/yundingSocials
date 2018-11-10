package cn.yunding.social.service.impl;

import cn.yunding.social.mapper.UsersMapper;
import cn.yunding.social.pojo.Users;
import cn.yunding.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @TODO
 * @Author stronghwan
 * @Verison
 * @Date2018/11/10-16-04
 */
@Service
public class UserServiceimpl implements UserService{

    @Autowired
    private UsersMapper usersMapper;
    @Override
    public int saveUser(Users users) {
        int insert = usersMapper.insert(users);
        return insert;
    }
}
