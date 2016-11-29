package cn.luosonglin.test.relationship.dao;

import cn.luosonglin.test.member.entity.UserInfo;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 29/11/2016.
 */
@Mapper
public interface UsersRelationshipMapper {
    @Select("select * from user_info")
    List<UserInfo> findAllUser();

    @Insert("INSERT INTO users_relationship(tid, fromuid, touid, addTime) VALUES(#{tid}, #{fromuid}, #{touid}, #{addTime})")
    int insertByRelationShip(UsersRelationship usersRelationship);

    @Delete("DELETE FROM users_relationship WHERE fromuid = #{fromuid} and touid = #{touid}")
    void deleteByRelationShip(UsersRelationship usersRelationship);


}
