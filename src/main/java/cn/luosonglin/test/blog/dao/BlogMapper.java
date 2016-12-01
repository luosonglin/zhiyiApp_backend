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

    //发微博
    int writeBlog(Blog blog);

    //注意下！！！ 如果根据主键搜索，返回单个entity；如果根据某键值搜索，返回List<entity>
    @Select("SELECT id, user_id, title, content, comment_count, like_count, created_at, deleted_at, tag_id, is_hot, images FROM blog WHERE user_id = #{user_id} ORDER BY created_at desc")
    List<Blog> findBlogById(@Param("user_id") Integer user_id);

    //我关注的所有人的微博，按时间降序排列
    //后面加分页！！！
    //根据最后拉取时间和上次拉取时间取关注人的微博，每个人的数量限制在perpage条目范围内,然后合并并排序...高性能高并发下的拉取就要缓存了
//    @Select("SELECT * FROM blog WHERE user_id = #{user_id} ORDER BY created_at desc")
//    @Select("SELECT * FROM blog WHERE user_id = #{user_id,jdbcType=INTEGER})" +
//            "union" +
//            "SELECT * FROM blog WHERE user_id = #{user_id2,jdbcType=INTEGER} ORDER BY created_at desc)")
//    @Select("select * from blog where user_id in (#{user_ids}) order by created_at desc")
    //XML自动注入
    List<Blog> getFollowsBlogByListId(List<Integer> user_ids); //
}
