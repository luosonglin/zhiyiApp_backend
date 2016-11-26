package cn.luosonglin.test.sms.dao;

import cn.luosonglin.test.member.entity.VerificationCode;
import cn.luosonglin.test.sms.entity.ShorMessageRecords;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

/**
 * Created by luosonglin on 26/11/2016.
 */
@Mapper
public interface shortMessageRecordsMapper {
    @Insert("INSERT INTO shor_message_records(message_record_id, user_id, event_id, phone, return_value, message_content, send_time, send_type) " +
            "VALUES(#{message_record_id}, #{user_id}, #{event_id}, #{phone}, #{return_value}, #{message_content}, #{send_time}, #{send_type)")
    int insertShortMessageRecords(ShorMessageRecords shorMessageRecords);

    @Update("UPDATE shor_message_records SET user_id=#{user_id}, event_id = #{event_id}, phone = #{phone}, return_value = #{return_value}, " +
            "message_content = #{message_content}, send_time = #{send_time}, send_type = #{send_type} WHERE message_record_id=#{message_record_id}")
    void updateShortMessageRecords(ShorMessageRecords shorMessageRecords);

}
