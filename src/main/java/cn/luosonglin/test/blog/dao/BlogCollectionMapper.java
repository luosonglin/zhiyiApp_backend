package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.BlogCollection;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 02/12/2016.
 */
@Mapper
public interface BlogCollectionMapper {
    //收藏
//    @Insert("INSERT INTO blog_collection(id, user_id, blog_id, created_at) VALUES(#{id}, #{user_id}, #{blog_id}, #{created_at})")
//    int insertCollection(BlogCollection blogCollection);
    @Insert("INSERT INTO blog_collection(id, user_id, blog_id, created_at) VALUES(#{id}, #{user_id}, #{blog_id}, #{created_at})")
    int insertCollectionByMap(Map<String, Object> map);

    //取消收藏
//    @Delete("DELETE FROM blog_collection WHERE user_id =#{user_id} and blog_id = #{blog_id}")
//    void deleteCollection(BlogCollection blogCollection);
    @Delete("DELETE FROM blog_collection WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void deleteCollectionByMap(Map<String, Object> map);

    //我收藏的微博
    @Select("select id, user_id, blog_id, created_at from blog_collection where user_id = #{user_id}")
    List<BlogCollection> findMyCollectionBlog(@Param("user_id") Integer user_id);

    //我收藏的微博的id
    @Select("select blog_id from blog_collection where user_id = #{user_id}")
    List<Integer> getMyCollectionBlogIds(@Param("user_id") Integer user_id);

    //我收藏的微博数量
    @Select("select count(*) from blog_collection where user_id = #{user_id}")
    Integer getMyCollectionCount(@Param("user_id") Integer user_id);
}
