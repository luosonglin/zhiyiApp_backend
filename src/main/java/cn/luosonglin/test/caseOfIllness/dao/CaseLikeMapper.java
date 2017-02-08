package cn.luosonglin.test.caseOfIllness.dao;

import cn.luosonglin.test.caseOfIllness.entity.CaseLike;
import cn.luosonglin.test.caseOfIllness.entity.UserAndCaseLike;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 07/01/2017.
 */
@Mapper
public interface CaseLikeMapper {

    @Insert("INSERT INTO case_like(id, user_id, case_id, created_at, is_display) " +
            "VALUES(#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{case_id,jdbcType=INTEGER}, #{created_at,jdbcType=TIMESTAMP}, #{is_display,jdbcType=INTEGER})")
    int insertByLikeMap(Map<String, Object> map);

    //点赞后在该微博的点赞数+1
    @Update("update case_of_illness set like_count=if(isNull(like_count),0,like_count)+1 where id = #{case_id}")
    void updateCaseLikeCount(@Param("case_id") Integer case_id);

    @Delete("DELETE FROM case_like WHERE user_id =#{user_id} and case_id = #{case_id}")
    void delete(CaseLike caselike);

    @Update("UPDATE case_like SET is_display=#{is_display} WHERE user_id = #{user_id} and case_id = #{case_id}")
    void cancelCaseLikeByMap(Map<String, Object> map);

    @Select("select a.id, a.user_id, a.case_id, a.created_at, a.is_display, b.name, b.nick_name, b.user_pic, b.authen_status, b.company from case_like a, user_info b where a.user_id = b.id and case_id = #{case_id} order by a.created_at DESC")
    List<UserAndCaseLike> findAllUser(@Param("case_id") Integer case_id);

    @Select("select count(*) from case_like where case_id = #{case_id}")
    Integer getLikeCount(@Param("case_id") Integer case_id);

    @Select("SELECT id FROM case_like WHERE user_id =#{user_id} and case_id = #{case_id} and is_display = 0")
    Integer isLiked(@Param("user_id") Integer user_id, @Param("case_id") Integer case_id);

}
