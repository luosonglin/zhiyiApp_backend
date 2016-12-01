package cn.luosonglin.test.blog.dao;

import cn.luosonglin.test.blog.entity.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by luosonglin on 01/12/2016.
 */
@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO blog_like(id, user_id, blog_id, created_at, is_display) VALUES(#{id}, #{user_id}, #{blog_id}, #{created_at}, #{is_display})")
    int insertByLike(Like like);

    @Delete("DELETE FROM blog_like WHERE user_id =#{user_id} and blog_id = #{blog_id}")
    void delete(Like like);
}
