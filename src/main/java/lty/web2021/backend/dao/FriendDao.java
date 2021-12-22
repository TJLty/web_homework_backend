package lty.web2021.backend.dao;

import lty.web2021.backend.model.Friend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao {
    int deleteByPrimaryKey(Friend key);

    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectFriends(@Param("filter")String filter,@Param("uid") Integer uid);
}