package cn.luosonglin.test.member.dao;

import cn.luosonglin.test.member.entity.VerificationCode;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 26/11/2016.
 */
@Mapper
public interface VerificationCodeMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "send_date", column = "send_date"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "code_content", column = "code_content"),
            @Result(property = "sub_date", column = "sub_date"),
            @Result(property = "source", column = "source")
    })
    @Select("SELECT id, send_date, phone, code_content, sub_date, source FROM ver_code")
    List<VerificationCode> findAll();

    @Select("select * from ver_code where phone = #{phone}")
    VerificationCode getVerificationCodeByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO ver_code(id, send_date, phone, code_content, sub_date, source) VALUES(#{id}, #{send_date}, #{phone}, #{code_centent}, #{sub_date}, #{source})")
    int insertVerificationCode(VerificationCode verificationCode);

    @Update("UPDATE ver_code SET send_date=#{send_date}, phone = #{phone}, code_content = #{codeContent}, sub_date = #{subDate}, source = #{source} WHERE id=#{id}")
    void updateVerificationCode(VerificationCode verificationCode);

    @Update("UPDATE ver_code SET send_date=#{send_date,jdbcType=TIMESTAMP}, phone = #{phone,jdbcType=VARCHAR}, " +
            "code_content = #{code_content,jdbcType=VARCHAR}, sub_date = #{sub_date,jdbcType=TIMESTAMP}, source = #{source,jdbcType=VARCHAR} WHERE id=#{id,jdbcType=INTEGER}")
    void updateVerificationCodeByMap(Map<String, Object> map);
}
