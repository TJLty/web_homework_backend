package lty.web2021.backend.service;

import lty.web2021.backend.dao.FriendDao;
import lty.web2021.backend.dao.UserDao;
import lty.web2021.backend.model.Friend;
import lty.web2021.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendDao friendDao;

    @Autowired
    UserDao userDao;
    public List<Friend> getFriends(String filter, Integer uid){
        return friendDao.selectFriends(filter,uid);
    }

    public void addFriends(Integer uid,Integer fid) throws Exception {
        if(userDao.selectByPrimaryKey(fid)==null){
            throw new Exception("No such user");
        }
        Friend friend=new Friend();
        friend.setUId(uid);
        friend.setFId(fid);
        System.out.println(friend.getUId());
        System.out.println(friend.getFId());
        int ans = friendDao.insert(friend);
        friend.setUId(fid);
        friend.setFId(uid);
        ans= friendDao.insert(friend);
    }

    public void deleteFriends(Integer uid,Integer fid){
        Friend friend=new Friend();
        friend.setUId(uid);
        friend.setFId(fid);
        System.out.println(friend.getUId());
        System.out.println(friend.getFId());
        friendDao.deleteByPrimaryKey(friend);
        friend.setUId(fid);
        friend.setFId(uid);
        friendDao.deleteByPrimaryKey(friend);
    }
}
