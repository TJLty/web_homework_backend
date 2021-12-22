package lty.web2021.backend.service;

import lty.web2021.backend.dao.MessageDao;
import lty.web2021.backend.dao.UserDao;
import lty.web2021.backend.model.Message;
import lty.web2021.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    @Autowired
    UserDao userDao;
    public List<Message> getFriendMessages(Integer uid,Integer fid){
        User user= userDao.selectByPrimaryKey(fid);
        if(Objects.equals(user.getType(), "user"))
            return messageDao.selectFriendMessage(uid,fid);
        else
            return messageDao.selectGroupMessage(uid,fid);
    }

    public void addTextMessage(String content,Integer from,Integer to){
        Message message=new Message();
        message.setContent(content);
        message.setFrom(from);
        message.setTo(to);
        message.setType("text");
        message.setTime(new Date());
        messageDao.insertSelective(message);
    }
    public void addFileMessage(String content,Integer from,Integer to){
        Message message=new Message();
        message.setContent(content);
        message.setFrom(from);
        message.setTo(to);
        message.setType("file");
        message.setTime(new Date());
        messageDao.insertSelective(message);
    }

    public void deleteMessage(Integer id){
        messageDao.deleteByPrimaryKey(id);
    }


}
