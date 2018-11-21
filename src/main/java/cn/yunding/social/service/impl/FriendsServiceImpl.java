package cn.yunding.social.service.impl;


import cn.yunding.social.dto.ServiceResult;
import cn.yunding.social.mapper.FriendApplyMapper;
import cn.yunding.social.mapper.FriendRelationshipMapper;
import cn.yunding.social.mapper.MyFriendMapper;
import cn.yunding.social.mapper.UsersMapper;
import cn.yunding.social.pojo.FriendApply;
import cn.yunding.social.pojo.FriendGroup;
import cn.yunding.social.pojo.FriendRelationship;
import cn.yunding.social.pojo.Users;
import cn.yunding.social.pojo.myPojo.FriendApplyDisplay;
import cn.yunding.social.pojo.myPojo.FriendByGroup;
import cn.yunding.social.pojo.myPojo.FriendInfo;
import cn.yunding.social.pojo.myPojo.FriendSearchUser;
import cn.yunding.social.service.FriendsService;
import cn.yunding.social.utils.ListSorting;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author swqing
 * 向数据库存储日期时间的类 :
 * 若对应数据库数据是oracle的Date类型，即只需要年月日的，选择使用java.sql.Date类型，
 * 若对应的是Mysqlserver数据库的DateTime类型，即需要年月日时分秒的，选择java.sql.Timestamp类型
 */
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private UsersMapper userMapper;

	@Autowired
	private FriendApplyMapper friendApplyMapper;

	@Autowired
	private FriendRelationshipMapper friendRelationshipMapper;

	@Autowired
	private MyFriendMapper myFriendMapper;

    /**
     * 搜索用户
     * @param friendSearchUser
     * @return
     */
    @Override
    public ServiceResult searchUser(FriendSearchUser friendSearchUser) {

        try{
            // 判断输入的是ICT号，还是手机号
            Users user = new Users();
            if(friendSearchUser.getSearchMessage().length()==6){
                user.setIct(friendSearchUser.getSearchMessage());
            } else{
                user.setPhone(friendSearchUser.getSearchMessage());
            }

            // 根据手机号或者ICT号查询查询
            user = userMapper.selectOne(user);

            // -1:陌生人
            friendSearchUser.setWhoMessage(-1);

            //  0:用户自己
            if (user.getId().equals(friendSearchUser.getId())){
                friendSearchUser.setWhoMessage(0);
            }

            //  1:用户的好友
            FriendRelationship friendRelationship = new FriendRelationship();
            friendRelationship.setUserId(friendSearchUser.getId());
            // 根据用户id查到所有的好友id
            List<FriendRelationship> list = friendRelationshipMapper.select(friendRelationship);
            for (FriendRelationship ls: list) {
                // 如果好友的id和查出来的用户id相等就返回1
                if (ls.getFriendId().equals(user.getId())){
                    friendSearchUser.setWhoMessage(1);
                    break;
                }
            }

            // 返回带 whoMessage 的用户信息
            BeanUtils.copyProperties(user, friendSearchUser);

            if(friendSearchUser.getNickname() != null){
                return ServiceResult.success(friendSearchUser);
            }else{
                return ServiceResult.failure("该用户不存在");
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 发送好友请求
     * 加入请求发送的时间createTime
     * @param friendApply
     * @return
     */
    @Override
    public ServiceResult sendRequest(FriendApply friendApply) {
        try{
            friendApply.setIsAgree(0);
            Date date = new Date();
            friendApply.setApplyTime(new Timestamp(date.getTime()));
            int result = friendApplyMapper.insertSelective(friendApply);
            if (result > 0){
                return ServiceResult.success("发送成功");
            } else{
                return ServiceResult.failure("发送失败");
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查看好友请求
     * @param friendApply
     * @return
     */
    @Override
    public ServiceResult checkRequest(FriendApply friendApply) {

        try{
            List<FriendApplyDisplay> friendApplyDisplayList = myFriendMapper.selectNewFriendAll(friendApply);

            // 给该用户收到的所有请求按时间顺序显示
            ListSorting.sortStringMethods(friendApplyDisplayList);
            if (friendApplyDisplayList.size() > 0){
                return ServiceResult.success(friendApplyDisplayList);
            } else{
                return ServiceResult.failure("查看好友请求失败");
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 接受好友请求,并且把新好友加入好友列表:
     * update_time 类型设为 timestamp ,修改或插入数据时自动填充修改时间
     * 打开 设计表，左下角有一个 根据时间戳更新，打钩
     * @param friendApply
     * @return
     */
	@Override
	public ServiceResult addFriend(FriendApply friendApply) {

	    // 修改is_agree,在新的朋友列表中同意该请求,还必须要拿出请求者给接受者的备注和分组
        Date date = new Date();

        // 根据senderId查到sender信息
        Users user = new Users();
        user.setId(friendApply.getSenderId());
        user = userMapper.selectOne(user);

        // 把请求者加入到接受者的好友列表
        FriendRelationship friendRelationshipAccepter = new FriendRelationship();
        friendRelationshipAccepter.setUserId(friendApply.getUserId());
        friendRelationshipAccepter.setFriendId(friendApply.getSenderId());
        friendRelationshipAccepter.setGroupName("云顶书院");
        friendRelationshipAccepter.setCreateTime(new Timestamp(date.getTime()));

        // 把接受者加入请求者的好友列表，还要记得把请求者给接受者的备注和分组加入请求者给接受者的信息中
        FriendRelationship friendRelationshipRequester = new FriendRelationship();
        friendRelationshipRequester.setUserId(friendApply.getSenderId());
        friendRelationshipRequester.setFriendId(friendApply.getUserId());

        // 如果发送请求时填写过备注，则直接对该好友备注;如果没有则默认为该好友的昵称
        if (friendApply.getMark() != null && !"".equals(friendApply.getMark())){
            friendRelationshipRequester.setMark(friendApply.getMark());
        } else{
            friendRelationshipRequester.setMark(user.getNickname());
        }

        // 如果发送请求时填写过分组，则直接放入该分组;如果没有则默认放到"云顶书院"
        if (friendApply.getGroup() != null && !"".equals(friendApply.getGroup())){
            friendRelationshipRequester.setGroupName(friendApply.getGroup());
        } else{
            friendRelationshipRequester.setGroupName("云顶书院");
        }
        friendRelationshipRequester.setCreateTime(new Timestamp(date.getTime()));

        try{
            // 同意好友请求
            friendApply.setIsAgree(1);
            int result1 = friendApplyMapper.updateByPrimaryKeySelective(friendApply);
            int result2 = friendRelationshipMapper.insertSelective(friendRelationshipAccepter);
            int result3 = friendRelationshipMapper.insertSelective(friendRelationshipRequester);
            if(result1 > 0 && result2 > 0 && result3 > 0){
                return ServiceResult.success("添加好友成功");
            }else{
				return ServiceResult.failure("添加好友失败");
			}
		} catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

    /**
     * 获取好友列表->并按首字母排序
     * @param friendRelationship
     * @return
     */
    @Override
    public ServiceResult getFriends(FriendRelationship friendRelationship) {
        try{
            // 传入的是 userId
            List<FriendInfo> friendInfos = getAllFriendByUserId(friendRelationship);

            // 给排好序的列表按首字母分组
            Map<String,List<FriendInfo>> map = new HashMap<String, List<FriendInfo>>();
            map = ListSorting.GroupByInitials(friendInfos);

            if(map.size() > 0){
                return ServiceResult.success(map);
            }else{
                return ServiceResult.failure("查到的条数为0");
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 修改好友备注和分组
     * @param friendRelationship
     * @return
     */
    @Override
    public ServiceResult updateMarkAndGroup(FriendRelationship friendRelationship) {
	    try{
            int result = friendRelationshipMapper.updateByPrimaryKeySelective(friendRelationship);
            if (result > 0){
                return ServiceResult.success(friendRelationship);
            }else{
                return ServiceResult.failure("修改备注失败");
            }
        } catch(Exception e){
	        throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 得到好友分组
     * @param friendRelationship
     * @return
     */
    @Override
    public ServiceResult getFriendsByGroup(FriendRelationship friendRelationship) {

        FriendGroup groupInfo = new FriendGroup();
        groupInfo.setUserId(friendRelationship.getUserId());

        // 先把所有的默认的固定分组自己建好，直接放到list里，数据库中只放用户自己的分组
        List<String> ls = new ArrayList<String>();
        ls.add("云顶院");
        ls.add("云顶行政");
        ls.add("云顶Java");
        ls.add("云顶设计");
        ls.add("云顶前端");
        ls.add("云顶Python");
        ls.add("云顶NodeJS");
        ls.add("云顶书院");

        // 暂时没有添加分组的功能。如果要加，就要先来一个方法用userId去查出用户的自定义分组。
        // 再遍历list，用list的分组去查friend表，得到该组的所有好友，放进一个list，再合成Map
        try{
            /*
             * 这个list存放组名+该组的所有好友信息
             */
            List<FriendByGroup> groupList = new ArrayList<FriendByGroup>();

            for (int i = 0; i < ls.size(); i++) {

                // 填入userId和组名
                FriendRelationship f = new FriendRelationship();
                f.setUserId(friendRelationship.getUserId());
                f.setGroupName(ls.get(i));

                // 用 userId + groupName 去查该组所有的好友
                List<FriendInfo> friendInfos = getAllFriendByUserIdAndGroupName(f);

                // 给好友列表排序
                ListSorting.sortStringMethod(friendInfos);

                // 放进对应的组里
                FriendByGroup friendByGroup = new FriendByGroup();
                friendByGroup.setGroupName(ls.get(i) + "（" + friendInfos.size() + "）");
                friendByGroup.setFriendInfoList(friendInfos);
                groupList.add(friendByGroup);
            }
            if (groupList.size() > 0){
                return ServiceResult.success(groupList);
            } else{
                return ServiceResult.failure("获取好友列表失败");
            }
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 根据好友信息查询好友:ICT号、手机号、昵称、备注等
     * @param friendSearchUser
     * @return
     */
    @Override
    public ServiceResult searchFriend(FriendSearchUser friendSearchUser) {

        // 存放查到的符合的条件的好友
        List<FriendInfo> friendsBySearch = new ArrayList<FriendInfo>();

        // 查询条件
        String s = friendSearchUser.getSearchMessage();

        // 放入 userId
        FriendRelationship friendRelationship = new FriendRelationship();
        friendRelationship.setUserId(friendSearchUser.getId());
        try{
            // 用 userId 查到所有的好友的信息
            List<FriendInfo> friendInfos = getAllFriendByUserId(friendRelationship);

            //遍历所有的好友寻找符合条件的好友
            for (FriendInfo f : friendInfos) {
                int a = f.getIct().indexOf(s);
                int b = f.getPhone().indexOf(s);
                int c = f.getNickname().indexOf(s);
                int d = f.getMark().indexOf(s);
                if (a >= 0 || b >= 0 || c >= 0 || d >= 0){
                    friendsBySearch.add(f);
                }
            }
            if(friendsBySearch.size() > 0){
                return ServiceResult.success(friendsBySearch);
            }else{
                return ServiceResult.failure("未找到匹配的结果");
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询号用户的所有好友
     * 传入的是 userId
     */
    private List<FriendInfo> getAllFriendByUserId(FriendRelationship friendRelationship){

        // 用 userId 关联查询所有的好友信息
        List<FriendInfo> friendInfo = myFriendMapper.selectFriendAll(friendRelationship);

        for (FriendInfo friend : friendInfo) {
            // 设置 display 属性
            if (friend.getMark() != null && !"".equals(friend.getMark())){
                friend.setDisplay(friend.getMark());
            } else{
                friend.setDisplay(friend.getNickname());
            }
        }
        return friendInfo;
    }

    /**
     * 查询该分组的所有好友
     * 传入的是 userId + groupName
     */
    private List<FriendInfo> getAllFriendByUserIdAndGroupName(FriendRelationship friendRelationship){

        // 用 userId + groupName 关联查询所有的好友信息
        List<FriendInfo> friendInfo = myFriendMapper.selectFriendAllByGroup(friendRelationship);

        for (FriendInfo friend : friendInfo) {
            // 设置 display 属性
            if (friend.getMark() != null && !"".equals(friend.getMark())){
                friend.setDisplay(friend.getMark());
            } else{
                friend.setDisplay(friend.getNickname());
            }
        }
        return friendInfo;
    }
}
