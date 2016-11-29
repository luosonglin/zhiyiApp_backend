package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 28/11/2016.
 */
@Mapper
public interface BlogMapper {
    @Select("SELECT id, user_id, title, content, comment_count, like_count," +
            "created_at, deleted_at, tag_id, is_hot, images FROM blog")
    List<Blog> findAllBlog();

//    @Insert("INSERT INTO blog(id, user_id, title, content, comment_count, like_count, created_at, deleted_at, tag_id, is_hot, images)" +
//            " VALUES(#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, " +
//            "#{content,jdbcType=VARCHAR}, #{comment_count,jdbcType=INTEGER}, #{like_count,jdbcType=INTEGER}, " +
//            "#{created_at,jdbcType=TIMESTAMP}, #{deleted_at,jdbcType=TIMESTAMP}, #{tag_id,jdbcType=VARCHAR}, " +
//            "#{is_hot,jdbcType=INTEGER}, #{images,jdbcType=VARCHAR})")
//    int insertBlogInfoByMap(Map<String, Object> map);

    @Insert("INSERT INTO blog(id, user_id, title, content, comment_count, like_count, created_at, deleted_at, tag_id, is_hot, images)" +
            " VALUES(#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, " +
            "#{content,jdbcType=VARCHAR}, #{comment_count,jdbcType=INTEGER}, #{like_count,jdbcType=INTEGER}, " +
            "#{created_at,jdbcType=TIMESTAMP}, #{deleted_at,jdbcType=TIMESTAMP}, #{tag_id,jdbcType=VARCHAR}, " +
            "#{is_hot,jdbcType=INTEGER}, #{images,jdbcType=VARCHAR})")
    int insertByBlog(Blog blog);

    //注意下！！！ 如果根据主键搜索，返回单个entity；如果根据某键值搜索，返回List<entity>
    @Select("SELECT id, user_id, title, content, comment_count, like_count, created_at, deleted_at, tag_id, is_hot, images FROM blog WHERE user_id = #{user_id}")
    List<Blog> findBlogById(@Param("user_id") Integer user_id);
}
