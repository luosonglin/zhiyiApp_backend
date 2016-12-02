package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Like;
import cn.luosonglin.test.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by luosonglin on 01/12/2016.
 */
@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO blog_like(id, user_id, blog_id, created_at, is_display) VALUES(#{id}, #{user_id}, #{blog_id}, #{created_at}, #{is_display})")
    int insertByLike(Like like);

    @Delete("DELETE FROM blog_like WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void delete(Like like);

    @Select("SELECT id id, user_id userId, blog_id blogId, created_at createdAt, is_display isDisplay FROM blog_like WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    Like findById(@Param("user_id") Integer user_id, @Param("blog_id") Integer blog_id);

    @Update("UPDATE blog_like SET is_display=#{is_display} WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void cancelLike(Like like);

    Like getLikeById(Integer user_id);

    @Select("select id, user_id, blog_id, created_at, is_display from blog_like where blog_id = #{blog_id}")
    List<Like> findAllUser(@Param("blog_id") Integer blog_id);
}
