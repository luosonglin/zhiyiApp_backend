package cn.luosonglin.test.member.dao;

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

    @Select("select * from user_info where mobile_phone = #{mobile_phone}")
    UserInfo getUserInfoByPhone(@Param("mobile_phone") String mobile_phone);

    @Insert("INSERT INTO user_info(id, name, nick_name, email, mobile_phone, company, position, sex, title, address, country, province, city, zip_code, id_code," +
            "status, state_date, confirm_number, phone, user_type, user_source, password, open_id, login_source, user_pic, authen_status, token_id)" +
            " VALUES(#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{nick_name,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{mobile_phone,jdbcType=VARCHAR}, #{company,jdbcType=VARCHAR}, " +
            "#{position,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, " +
            "#{city,jdbcType=VARCHAR}, #{zip_code,jdbcType=VARCHAR}, #{id_code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, #{state_date,jdbcType=TIMESTAMP}, #{confirm_number,jdbcType=VARCHAR}, " +
            "#{phone,jdbcType=VARCHAR}, #{user_type,jdbcType=VARCHAR}, #{user_source,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{open_id,jdbcType=VARCHAR}, #{login_source,jdbcType=VARCHAR}, " +
            "#{user_pic,jdbcType=VARCHAR}, #{authen_status,jdbcType=VARCHAR}, #{token_id,jdbcType=VARCHAR})")
    int insertUserInfoByMap(Map<String, Object> map);



}
