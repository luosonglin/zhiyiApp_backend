package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
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
}
