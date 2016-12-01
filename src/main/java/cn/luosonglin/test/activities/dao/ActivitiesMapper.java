package cn.luosonglin.test.activities.dao;

import cn.luosonglin.test.banner.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 01/12/2016.
 */
@Mapper
public interface ActivitiesMapper {
    @Select("select id, url, title, content from activities where is_display = 0")
    List<Banner> getAllActivities();
}
