package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Comment;
import cn.luosonglin.test.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 03/12/2016.
 */
@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO blog_comment(id, user_id, blog_id, comment_id, content, created_at, deleted_at) VALUES(#{id}, #{user_id}, #{blog_id}, #{comment_id}, #{content}, #{created_at}, #{deleted_at})")
    int insertComment(Map<String, Object> map);

    @Update("UPDATE blog_comment SET deleted_at=#{deleted_at} WHERE user_id =#{user_id} and blog_id = #{blog_id} and comment_id = #{comment_id}")
    void deleteComment(Map<String, Object> map);

    @Select("SELECT * FROM blog_comment WHERE blog_id = #{blog_id}")
    List<Comment> getComments(@Param("blog_id") Integer blog_id);
}
