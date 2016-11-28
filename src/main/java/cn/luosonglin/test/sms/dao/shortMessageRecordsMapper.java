package cn.luosonglin.test.sms.dao;

import cn.luosonglin.test.sms.entity.ShorMessageRecords;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Created by luosonglin on 26/11/2016.
 */
@Mapper
public interface shortMessageRecordsMapper {
    @Insert("INSERT INTO shor_message_records(message_record_id, user_id, event_id, phone, return_value, message_content, send_time, send_type) " +
            "VALUES(#{message_record_id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{event_id,jdbcType=INTEGER}, #{phone,jdbcType=VARCHAR}}, " +
            "#{return_value,jdbcType=VARCHAR}}, #{message_content,jdbcType=VARCHAR}}, #{send_time,jdbcType=TIMESTAMP}, #{send_type,jdbcType=VARCHAR})")
    int insertShortMessageRecords(ShorMessageRecords shorMessageRecords);

    @Insert("INSERT INTO shor_message_records(message_record_id, user_id, event_id, phone, return_value, message_content, send_time, send_type) " +
            "VALUES(#{message_record_id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{event_id,jdbcType=INTEGER}, #{phone,jdbcType=VARCHAR}, " +
            "#{return_value,jdbcType=VARCHAR}, #{message_content,jdbcType=VARCHAR}, #{send_time,jdbcType=TIMESTAMP}, #{send_type,jdbcType=VARCHAR})")
    int insertShortMessageRecordsByMap(Map<String, Object> map);

    @Update("UPDATE shor_message_records SET user_id=#{user_id}, event_id = #{event_id}, phone = #{phone}, return_value = #{return_value}, " +
            "message_content = #{message_content}, send_time = #{send_time}, send_type = #{send_type} WHERE message_record_id=#{message_record_id}")
    void updateShortMessageRecords(ShorMessageRecords shorMessageRecords);

}
