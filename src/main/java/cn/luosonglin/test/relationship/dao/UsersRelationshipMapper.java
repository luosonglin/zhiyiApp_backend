package cn.luosonglin.test.relationship.dao;

import cn.luosonglin.test.member.entity.UserInfo;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by luosonglin on 29/11/2016.
 */
@Mapper
public interface UsersRelationshipMapper {
    @Select("select * from user_info")
    List<UserInfo> findAllUser();

    @Select("select count(*) from users_relationship where fromuid = #{fromuid} and touid = #{touid} ")
    Integer isFollowed(UsersRelationship usersRelationship);

    @Insert("INSERT INTO users_relationship(tid, fromuid, touid, addTime) VALUES(#{tid}, #{fromuid}, #{touid}, #{addTime})")
    int insertByRelationShip(UsersRelationship usersRelationship);

    @Delete("DELETE FROM users_relationship WHERE fromuid = #{fromuid} and touid = #{touid}")
    void deleteByRelationShip(UsersRelationship usersRelationship);

    //我的粉丝信息
    @Select("select * from users_relationship where touid = #{touid} order by addTime DESC")
    List<UsersRelationship> getMyFans(@Param("touid") Integer touid);

    //我的关注人信息
    @Select("select * from users_relationship where fromuid = #{fromuid}")
    List<UsersRelationship> getMyFollows(@Param("fromuid") Integer fromuid);

    //所有关注的人的id
    @Select("select touid from users_relationship where fromuid = #{fromuid}")
    List<Integer> getMyFollowIds(@Param("fromuid") Integer fromuid);

    //粉丝数
    @Select("select count(*) from users_relationship where touid = #{touid} ")
    Integer getFansCount(@Param("touid") Integer touid);

    //关注数
    @Select("select count(*) from users_relationship where fromuid = #{fromuid} ")
    Integer getFollowedCount(@Param("fromuid") Integer fromuid);

    //某用户的双向关注关系列表
    @Select("select t1.* from " +
            "(select * fromusers_relationship where fromuid = #{fromuid}) as t1" +
            "inner join fromusers_relationship t2 on t1.touid = t2.fromuid limit 10")
    List<UsersRelationship> getMyDoubleFollows(@Param("fromuid") Integer fromuid);

    //通讯录
    List<UserInfo> getContactsByListId(List<Integer> id); //


}
