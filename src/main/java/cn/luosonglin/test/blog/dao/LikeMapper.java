package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Like;
import cn.luosonglin.test.blog.entity.UserAndLike;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 01/12/2016.
 */
@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO blog_like(id, user_id, blog_id, created_at, is_display) VALUES(#{id}, #{user_id}, #{blog_id}, #{created_at}, #{is_display})")
    int insertByLike(Like like);

    @Insert("INSERT INTO blog_like(id, user_id, blog_id, created_at, is_display) VALUES(#{id}, #{user_id}, #{blog_id}, #{created_at}, #{is_display})")
    int insertLike(@Param("user_id") Integer user_id, @Param("blog_id") Integer blog_id);

    @Insert("INSERT INTO blog_like(id, user_id, blog_id, created_at, is_display) " +
            "VALUES(#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{blog_id,jdbcType=INTEGER}, #{created_at,jdbcType=TIMESTAMP}, #{is_display,jdbcType=INTEGER})")
    int insertByLikeMap(Map<String, Object> map);

    //点赞后在该微博的点赞数+1
    @Update("update blog set like_count=if(isNull(like_count),0,like_count)+1 where id = #{blog_id}")
    void updateLikeCount(@Param("blog_id") Integer blog_id);

    @Delete("DELETE FROM blog_like WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void delete(Like like);

    @Delete("DELETE FROM blog_like WHERE user_id =#{user_id,jdbcType=INTEGER} and blog_id = #{blog_id,jdbcType=INTEGER}")
    void deleteByLikeMap(Map<String, Object> map);

    @Select("SELECT id id, user_id userId, blog_id blogId, created_at createdAt, is_display isDisplay FROM blog_like WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    Like findById(@Param("user_id") Integer user_id, @Param("blog_id") Integer blog_id);

    @Update("UPDATE blog_like SET is_display=#{is_display} WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void cancelLike(Like like);

    @Update("UPDATE blog_like SET is_display=#{is_display} WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void cancelLikeByMap(Map<String, Object> map);

    Like getLikeById(Integer user_id);

    @Select("select a.id, a.user_id, a.blog_id, a.created_at, a.is_display, b.name, b.nick_name, b.user_pic, b.authen_status, b.company from blog_like a, user_info b where a.user_id = b.id and a.blog_id = #{blog_id} order by a.created_at DESC")
    List<UserAndLike> findAllUser(@Param("blog_id") Integer blog_id);

    @Select("select count(*) from blog_like where blog_id = #{blog_id}")
    Integer getLikeCount(@Param("blog_id") Integer blog_id);

    @Select("SELECT count(*) FROM blog_like WHERE user_id =#{user_id} and blog_id = #{blog_id} and is_display = 0")
    Integer isLiked(@Param("user_id") Integer user_id, @Param("blog_id") Integer blog_id);

}
