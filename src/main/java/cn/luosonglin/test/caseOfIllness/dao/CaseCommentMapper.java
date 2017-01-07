package cn.luosonglin.test.caseOfIllness.dao;

import cn.luosonglin.test.caseOfIllness.entity.UserAndCaseComment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 07/01/2017.
 */
@Mapper
public interface CaseCommentMapper {

    @Insert("INSERT INTO case_comment(id, user_id, case_id, comment_id, content, created_at, deleted_at) VALUES(#{id}, #{user_id}, #{case_id}, #{comment_id}, #{content}, #{created_at}, #{deleted_at})")
    int insertComment(Map<String, Object> map);

    @Update("UPDATE case_comment SET deleted_at=#{deleted_at} WHERE user_id =#{user_id} and case_id = #{case_id} and comment_id = #{comment_id}")
    void deleteComment(Map<String, Object> map);

    @Select("SELECT a.*, b.name, b.nick_name, b.user_pic, b.authen_status, b.company FROM case_comment a,user_info b WHERE a.user_id = b.id and case_id = #{case_id}")
    List<UserAndCaseComment> getComments(@Param("case_id") Integer case_id);

    @Select("select user_id from case_comment where id = #{comment_id}")
    Integer getUserId(@Param("comment_id") Integer comment_id);
}
