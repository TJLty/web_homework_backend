package lty.web2021.backend.service;

import lty.web2021.backend.dao.UserDao;
import lty.web2021.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public Boolean checkPassword(Integer uId,String password){
        User user=userDao.selectByPrimaryKey(uId);
        if(user==null|| !Objects.equals(user.getType(), "user"))
            return false;
        else
            return password.equals(user.getPassword());
    }

    public User getInformation(Integer uid){
        return userDao.selectByPrimaryKey(uid);
    }

    public void addUser(User user){
        userDao.insertSelective(user);
    }

    public void changeImage(String path,Integer uid){
        User user=userDao.selectByPrimaryKey(uid);

        user.setImg(path);
        userDao.updateByPrimaryKeySelective(user);
    }

    public void updateUser(User user){
        userDao.updateByPrimaryKeySelective(user);
    }

    public Integer addGroup(String name){
        User user=new User();
        user.setUsername(name);
        user.setImg("https://lty-web-2021.oss-cn-shanghai.aliyuncs.com/img/group.jpeg");
        userDao.insertSelective(user);
        return user.getUId();
    }
}
