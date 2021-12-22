package lty.web2021.backend.dao;

import lty.web2021.backend.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer mId);

    List<Message> selectFriendMessage(@Param("uid") Integer uid,@Param("fid") Integer fid);

    List<Message> selectGroupMessage(@Param("uid") Integer uid,@Param("fid") Integer fid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}