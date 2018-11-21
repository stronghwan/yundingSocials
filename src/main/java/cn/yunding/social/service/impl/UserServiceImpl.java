package cn.yunding.social.service.impl;

import cn.yunding.social.mapper.UsersMapper;
import cn.yunding.social.pojo.Users;
import cn.yunding.social.pojo.bo.UserBo;
import cn.yunding.social.service.UserService;
import cn.yunding.social.utils.FileUtils;
import cn.yunding.social.utils.MD5Utils;
import cn.yunding.social.utils.QRCodeUtils;
import com.alibaba.fastjson.JSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.owasp.esapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.jms.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @TODO
 * @Author stronghwan
 * @Verison
 * @Date2018/11/10-16-04
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination smsDestination;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Sid sid;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Value("${template_code}")
    private String template_code;

    @Value("${qrCode_url}")
    private String qrCode_url;

    @Value("${IMAGE_URL}")
    private String IMAGE_URL;

    @Override
    public void createSmsCode(final String phone) {
        final String code = (long)(Math.random()*1000000) + "";
        System.out.println(code);
        redisTemplate.boundHashOps("smsCode").put(phone,code);
        jmsTemplate.send(smsDestination,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("sign_name","云顶社交");
                mapMessage.setString("template_code",template_code);
                mapMessage.setString("mobile",phone);
                Map map = new HashMap();
                map.put("code",code);
                map.put("name","521");
                mapMessage.setString("param", JSON.toJSONString(map));
                return mapMessage;
            }
        });
    }

    @Override
    public boolean checkSmsCode(String phone, String code) {
       if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(code)){
           String smsCode = (String) redisTemplate.boundHashOps("smsCode").get(phone);
           if (code.equals(smsCode)){
               return true;
           }else {
               return false;
           }
       }
       return true;
    }

    @Transactional
    @Override
    public Users saveUser(UserBo userBo) throws IOException {
        Users users = new Users();
        String id = sid.nextShort();
        users.setId(id);
        users.setPhone(userBo.getPhone());
        // todo 自动生成一个二维码
        String path = users.getId()+"qrcode.png";
        String url = qrCode_url+path;
        qrCodeUtils.createQRCode(url,"qrcode:"+users.getPhone());
        MultipartFile multipartFile = FileUtils.fileToMultipart(url);
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);
        String urlResult = IMAGE_URL + "qrcode/"+path;
        Client client = new Client();
        WebResource webResource = client.resource(urlResult);
        webResource.put(String.class, multipartFile.getBytes());
        System.out.println(urlResult);
        users.setQrcode(urlResult);
        users.setPassword(userBo.getPassword());
        // todo 自动生成一个头像
        String faceUrl = IMAGE_URL+"upload/201811161610336754834.jpg";
        users.setFace(faceUrl);
        users.setNickname("");
        String ict = (long)(Math.random()*1000000) + "";
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ict",ict);
        Users userIct = usersMapper.selectOneByExample(example);
        while (ict.equals(userIct)){
            ict = (long)(Math.random()*1000000) + "";
        }
        users.setIct(ict);
        users.setSignName("");
        users.setCreateTime(new Date());
        int insert = usersMapper.insert(users);
        return users;
    }

    @Override
    public boolean userCheckRepate(String phone) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",phone);
        Users users = usersMapper.selectOneByExample(example);
        if (users != null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Users userLoginByIct(Users users) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ict",users.getIct());
        criteria.andEqualTo("password",users.getPassword());
        Users usersResult = usersMapper.selectOneByExample(example);
        return usersResult;
    }

    @Override
    public Users userLoginByPhone(Users users) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",users.getPhone());
        criteria.andEqualTo("password",users.getPassword());
        Users usersResult = usersMapper.selectOneByExample(example);
        return usersResult;
    }

    @Transactional
    @Override
    public Users updatePassword(Users users) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",users.getPhone());
        int i = usersMapper.updateByExampleSelective(users,example);
        Users userResult = usersMapper.selectOne(users);
        return userResult;
    }

    @Transactional
    @Override
    public Users updateUserInfo(Users users) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",users.getId());
        int i = usersMapper.updateByExampleSelective(users,example);
        Users userResult = usersMapper.selectOne(users);
        return userResult;
    }


}
