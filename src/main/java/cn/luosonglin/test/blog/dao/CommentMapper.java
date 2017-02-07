package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Comment;
import cn.luosonglin.test.blog.entity.UserAndComment;
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

    //评论后在该微博的评论数+1
    @Update("update blog set comment_count=if(isNull(comment_count),0,comment_count)+1 where id = #{blog_id}")
    void updateCommentCount(@Param("blog_id") Integer blog_id);

    @Update("UPDATE blog_comment SET deleted_at=#{deleted_at} WHERE user_id =#{user_id} and blog_id = #{blog_id} and comment_id = #{comment_id}")
    void deleteComment(Map<String, Object> map);

//    @Select("SELECT * FROM blog_comment WHERE blog_id = #{blog_id}")
    @Select("SELECT a.*, b.name, b.nick_name, b.user_pic, b.authen_status, b.company FROM blog_comment a,user_info b WHERE a.user_id = b.id and blog_id = #{blog_id} order by a.created_at ASC")
    List<UserAndComment> getComments(@Param("blog_id") Integer blog_id);

    @Select("select user_id from blog_comment where id = #{comment_id}")
    Integer getUserId(@Param("comment_id") Integer comment_id);
}
