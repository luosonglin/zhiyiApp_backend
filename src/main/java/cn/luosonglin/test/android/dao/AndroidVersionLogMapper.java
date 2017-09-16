package cn.luosonglin.test.android.dao;

import cn.luosonglin.test.android.entity.AndroidVersionLog;
import cn.luosonglin.test.blog.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by luosonglin on 05/09/2017.
 */
@Mapper
public interface AndroidVersionLogMapper {

    @Insert("INSERT INTO android_version_log(user_id, old_version_id, new_version_id) VALUES(#{user_id,jdbcType=INTEGER}, #{old_version_id,jdbcType=VARCHAR}, #{new_version_id,jdbcType=VARCHAR})")
    int insertByNewVersion(@Param("user_id") Integer user_id, @Param("old_version_id") String old_version_id, @Param("new_version_id") String new_version_id);
}
