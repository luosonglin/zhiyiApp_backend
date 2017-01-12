package cn.luosonglin.test.member.dao;

import cn.luosonglin.test.domain.User;
import cn.luosonglin.test.member.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * Created by luosonglin on 25/11/2016.
 */
@Mapper
public interface UserInfoMapper {

//    @Select("SELECT * FROM user_info WHERE name = #{name}")
//    User findByName(@Param("name") String name);
//
//    @Select("SELECT * FROM user_info WHERE id = #{id}")
//    User findById(@Param("id") Long id);
//
//    @Insert("INSERT INTO user_info(name, age) VALUES(#{name}, #{age})")
//    int insert(@Param("name") String name, @Param("age") Integer age);
//
//    @Insert("INSERT INTO user_info(name, age) VALUES(#{name}, #{age})")
//    int insertByUser(User user);
//
//    @Insert("INSERT INTO user_info(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
//    int insertByMap(Map<String, Object> map);
//
//    @Update("UPDATE user_info SET age=#{age} WHERE name=#{name}")
//    void update(User user);
//
//    @Delete("DELETE FROM user_info WHERE id =#{id}")
//    void delete(Long id);

    //根据手机号查看该用户是否是注册用户
    @Select("select id from user_info where mobile_phone = #{mobile_phone}")
    Integer isRegisteredUser(@Param("mobile_phone") String mobile_phone);

    @Select("select * from user_info where mobile_phone = #{mobile_phone}")
    UserInfo getUserInfoByPhone(@Param("mobile_phone") String mobile_phone);

    //以下2种方式不能用，原因不明  原因在于position字段在数据库里是postion！！！shit!!
    @Insert("INSERT INTO user_info(id, name, nick_name, email, mobile_phone, company, postion, sex, title, address, country, province, city, zip_code, id_code, status, state_date, confirm_number, phone, user_type, user_source, password, open_id, login_source, user_pic, authen_status, token_id) VALUES(#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{nick_name,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{mobile_phone,jdbcType=VARCHAR}, #{company,jdbcType=VARCHAR}, #{postion,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{zip_code,jdbcType=VARCHAR}, #{id_code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, #{state_date,jdbcType=TIMESTAMP}, #{confirm_number,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{user_type,jdbcType=VARCHAR}, #{user_source,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{open_id,jdbcType=VARCHAR}, #{login_source,jdbcType=VARCHAR}, #{user_pic,jdbcType=VARCHAR}, #{authen_status,jdbcType=VARCHAR}, #{token_id,jdbcType=VARCHAR})")
    int insertUserInfoByMap(Map<String, Object> map);

    @Insert("INSERT INTO user_info(id, name, nick_name, email, mobile_phone, company, postion, sex, title, address, country, province, city, zip_code, id_code," +
            "status, state_date, confirm_number, phone, user_type, user_source, password, open_id, login_source, user_pic, authen_status, token_id)" +
            " VALUES(#{id}, #{name}, #{nick_name}, #{email}, #{mobile_phone}, #{company}, #{position}, #{sex}, #{title}, #{address}, #{country}, #{province}, " +
            "#{city}, #{zip_code}, #{id_code}, #{status}, #{state_date}, #{confirm_number}, #{phone}, #{user_type}, #{user_source}, #{password}, #{open_id}, " +
            "#{login_source}, #{user_pic}, #{authen_status}, #{token_id})")
    int insertByUserInfo(UserInfo userInfo);

    int insertNewUser(UserInfo userInfo);

    @Select("select * from user_info where id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Integer id);

    @Select("select name from user_info where id = #{user_id}")
    String getUserInfoName(@Param("user_id") Integer user_id);

    @Select("select token_id from user_info where id = #{user_id}")
    String getTokenId(@Param("user_id") Integer user_id);

    //fuck!  the parameter "position" of database is false!!!
    @Update("UPDATE user_info SET name=#{name}, company=#{company}, postion=#{position}, title=#{title}, authen_status=#{authen_status} WHERE id =#{id}")
    void authorization(Map<String, Object> map);

    @Update("UPDATE user_info SET name=#{name}, company=#{company}, postion=#{position},title=#{title}, authen_status=#{authen_status} WHERE id =#{id}")
    void update(UserInfo userInfo);

    @Update("UPDATE user_info SET name=#{name}, company=#{company}, postion=#{position}, title=#{title}, authen_status=#{authen_status} WHERE id =#{id}")
    void authorization2(@Param("id") Integer id, @Param("name") String name, @Param("company") String company, @Param("position") String position, @Param("title") String title, @Param("authen_status") String authen_status);

    int updateUserInfo(UserInfo userInfo);

    @Select("select max(id) from user_info")
    int getMaxUserId();

    //修改头像
    @Update("UPDATE user_info SET mobile_phone=#{mobile_phone}, user_pic=#{user_pic} WHERE id =#{user_id} ")
    void updateUserPic(Map<String, Object> map);

    //查询openId
    @Select("select * from user_info where open_id = #{open_id}")
    UserInfo getUserInfoByOpenId(@Param("open_id") String open_id);
}
