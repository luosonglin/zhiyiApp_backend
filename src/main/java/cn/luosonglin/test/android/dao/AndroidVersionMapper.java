package cn.luosonglin.test.android.dao;

import cn.luosonglin.test.android.entity.AndroidVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by luosonglin on 31/08/2017.
 */
@Mapper
public interface AndroidVersionMapper {
    @Select("select a.version, a.is_force, a.log, a.url, a.created_at from android_version a where id = (select Max(id) from android_version)")
    AndroidVersion getLastVersion();
}
