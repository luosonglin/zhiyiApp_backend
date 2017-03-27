package cn.luosonglin.test.member.dao;

import cn.luosonglin.test.domain.User;
import cn.luosonglin.test.member.entity.ThirdUserBindPhoneInfo;
import cn.luosonglin.test.member.entity.UpdateUserAvatar;
import cn.luosonglin.test.member.entity.UpdateUserPhone;
import cn.luosonglin.test.member.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.annotation.IntegerMemberValue;

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
    @Insert("INSERT INTO user_info(id, name, nick_name, email, mobile_phone, company, postion, sex, title, address, country, province, city, zip_code, id_code, status, state_date, confirm_number, phone, user_type, user_source, password, open_id, qq_open_id, login_source, user_pic, authen_status, token_id) VALUES(#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{nick_name,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{mobile_phone,jdbcType=VARCHAR}, #{company,jdbcType=VARCHAR}, #{postion,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{zip_code,jdbcType=VARCHAR}, #{id_code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, #{state_date,jdbcType=TIMESTAMP}, #{confirm_number,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{user_type,jdbcType=VARCHAR}, #{user_source,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{open_id,jdbcType=VARCHAR}, #{qq_open_id,jdbcType=VARCHAR}, #{login_source,jdbcType=VARCHAR}, #{user_pic,jdbcType=VARCHAR}, #{authen_status,jdbcType=VARCHAR}, #{token_id,jdbcType=VARCHAR})")
    int insertUserInfoByMap(Map<String, Object> map);

//    @Insert("INSERT INTO user_info(id, name, nick_name, email, mobile_phone, company, postion, sex, title, address, country, province, city, zip_code, id_code," +
//            "status, state_date, confirm_number, phone, user_type, user_source, password, open_id, login_source, user_pic, authen_status, token_id)" +
//            " VALUES(#{id}, #{name}, #{nickName}, #{email}, #{mobilePhone}, #{company}, #{position}, #{sex}, #{title}, #{address}, #{country}, #{province}, " +
//            "#{city}, #{zipCode}, #{idCode}, #{status}, #{stateDate}, #{confirmNumber}, #{phone}, #{userType}, #{userSource}, #{password}, #{openId}, " +
//            "#{loginSource}, #{userPic}, #{authenStatus}, #{tokenId})")
    @Insert("INSERT INTO user_info(id, name, nick_name, email, mobile_phone, company,department, postion, hospital, sex,birthday, title, address, country, province, city, zip_code, id_code, status, state_date, confirm_number, phone, user_type, user_source, password, open_id, login_source, user_pic, authen_status, token_id, county)" +
        " VALUES(#{id}, #{name}, #{nickName}, #{email}, #{mobilePhone}, #{company}, #{department}, #{postion}, #{hospital}, #{sex},#{birthday}, #{title}, #{address}, #{country}, #{province},#{city}, #{zipCode}, #{idCode}, #{status}, #{stateDate}, #{confirmNumber}, #{phone}, #{userType}, #{userSource}, #{password}, #{openId},#{loginSource},#{userPic}, #{authenStatus}, #{tokenId}, #{county})")
    int insertByUserInfo(UserInfo userInfo);

    int insertNewUser(UserInfo userInfo);

    @Select("select * from user_info where id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Integer id);

    @Select("select name from user_info where id = #{user_id}")
    String getUserInfoName(@Param("user_id") Integer user_id);

    @Select("select mobile_phone from user_info where id = #{user_id}")
    String getUserInfoMobilePhone(@Param("user_id") Integer user_id);

    @Select("select token_id from user_info where id = #{user_id}")
    String getTokenId(@Param("user_id") Integer user_id);

    //fuck!  the parameter "position" of database is false!!!
    @Update("UPDATE user_info SET name=#{name}, company=#{company}, department=#{department}, hospital=#{hospital}, title=#{title}, mobile_phone=#{mobile_phone}, authen_status=#{authen_status} WHERE id =#{id}")
    void authorization(Map<String, Object> map);

    //更新user数据
    @Update("UPDATE user_info SET name=#{name}, nick_name=#{nickName}, email=#{email}, mobile_phone=#{mobilePhone}, company=#{company}, department=#{department}, postion=#{postion},hospital=#{hospital},sex=#{sex},birthday=#{birthday},title=#{title}, address=#{address}, country=#{country}, province=#{province}, city=#{city}, zip_code=#{zipCode}, id_code=#{idCode}, status=#{status}, state_date=#{stateDate}, confirm_number=#{confirmNumber}, phone=#{phone}, user_type=#{userType}, user_source=#{userSource}, password=#{password}, open_id=#{openId}, login_source=#{loginSource}, user_pic=#{userPic}, authen_status=#{authenStatus}, token_id=#{tokenId}, county=#{county}  WHERE id =#{id}")
    void update(UserInfo userInfo);

    @Update("UPDATE user_info SET name=#{name}, company=#{company}, postion=#{postion}, title=#{title}, authen_status=#{authen_status} WHERE id =#{id}")
    void authorization2(@Param("id") Integer id, @Param("name") String name, @Param("company") String company, @Param("position") String position, @Param("title") String title, @Param("authen_status") String authen_status);

    int updateUserInfo(UserInfo userInfo);

    @Select("select max(id) from user_info")
    int getMaxUserId();

    //修改头像
    @Update("UPDATE user_info SET mobile_phone=#{mobile_phone}, user_pic=#{user_pic} WHERE id =#{user_id} ")
    void updateUserPic(Map<String, Object> map);

    @Update("UPDATE user_info SET user_pic=#{avatar} WHERE id =#{userId} ")
    void updateUserAvatar(UpdateUserAvatar updateUserAvatar);

    @Update("UPDATE user_info SET mobile_phone=#{mobilePhone} WHERE id =#{userId} ")
    void updateUserPhone(UpdateUserPhone updateUserPhone);

    //根据wechat openId查询用户
    @Select("select * from user_info where open_id = #{open_id}")
    UserInfo getUserInfoByOpenId(@Param("open_id") String open_id);

    //根据qq openId查询用户
    @Select("select * from user_info where qq_open_id = #{qq_open_id}")
    UserInfo getUserInfoByQQOpenId(@Param("qq_open_id") String qq_open_id);

    //根据userId查看该用户是否是第三方注册用户
    @Select("select open_id from user_info where id = #{user_id}")
    String isWeChatRegisteredUser(@Param("user_id") Integer user_id);

    @Select("select qq_open_id from user_info where id = #{user_id}")
    String isQQRegisteredUser(@Param("user_id") Integer user_id);

    //三方登陆用户绑定手机号
    @Update("UPDATE user_info SET mobile_phone=#{phone} WHERE id =#{user_id} ")
    int updateThirdUserByPhoneInfo(@Param("user_id") Integer user_id, @Param("phone") String phone);

    @Select("select nick_name from user_info where confirm_number = #{confirm_number}")
    String isConfirmNumberExists(@Param("confirm_number") String confirm_number);




    //手机号登陆用户绑定三方账号
    @Update("UPDATE user_info SET open_id=#{open_id}, login_source=#{login_source} WHERE id =#{user_id} ")
    int updateUserWeChatByThirdInfo(@Param("user_id") Integer user_id, @Param("open_id") String open_id, @Param("login_source") String login_source);

    @Update("UPDATE user_info SET qq_open_id=#{qq_open_id}, login_source=#{login_source} WHERE id =#{user_id} ")
    int updateUserQQByThirdInfo(@Param("user_id") Integer user_id, @Param("qq_open_id") String qq_open_id, @Param("login_source") String login_source);

    //删除用户
    @Delete("DELETE FROM user_info WHERE id =#{id}")
    void deleteUserInfo(Integer id);


    //绑定emails
    @Update("UPDATE user_info SET email=#{email} WHERE id =#{user_id} ")
    int updateUserByEmailInfo(@Param("user_id") Integer user_id, @Param("email") String email);

    //绑定password
    @Update("UPDATE user_info SET password=#{password} WHERE id =#{user_id} ")
    int updateUserByPasswordInfo(@Param("user_id") Integer user_id, @Param("password") String password);

    //已有手机号注册用户添加wechat登陆信息
    @Update("UPDATE user_info SET nick_name=#{nick_name}, open_id=#{open_id}, login_source=#{login_source}, user_pic=#{user_pic} WHERE mobile_phone =#{mobile_phone} ")
    void updateUserByWeChatInfo(Map<String, Object> map);

    //已有手机号注册用户添加qq登陆信息
    @Update("UPDATE user_info SET nick_name=#{nick_name}, qq_open_id=#{qq_open_id}, login_source=#{login_source}, user_pic=#{user_pic} WHERE mobile_phone =#{mobile_phone} ")
    void updateUserByQQInfo(Map<String, Object> map);

    //已有wechat openid用户添加手机信息
    @Update("UPDATE user_info SET mobile_phone=#{mobile_phone} WHERE open_id =#{open_id} ")
    int updateUserPhoneByOpenId(@Param("mobile_phone") String mobile_phone, @Param("open_id") String open_id);

}
