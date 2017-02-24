package cn.luosonglin.test.member.dao;

import cn.luosonglin.test.member.entity.Authen;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created by luosonglin on 21/02/2017.
 */
@Mapper
public interface AuthenMapper {

    @Insert("INSERT INTO authen(user_id, user_type, authen_status, state_date, moblie_phone, user_name, user_title, user_department, user_hospital) VALUES(#{user_id}, #{user_type}, #{authen_status}, #{state_date}, #{moblie_phone}, #{user_name}, #{user_title}, #{user_department}, #{user_hospital})")
    int insertAuthen(Authen authen);

    @Update("UPDATE authen SET user_type='A', authen_status=#{authenStatus}, state_date=#{stateDate}, moblie_phone=#{mobliePhone}, user_name=#{userName}, user_hospital=#{userHospital}, user_department=#{userDepartment}, user_title=#{userTitle} WHERE user_id =#{userId} ")
    void updateAuthen(Authen authen);
}
