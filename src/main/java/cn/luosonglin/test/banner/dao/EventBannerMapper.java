package cn.luosonglin.test.banner.dao;

import cn.luosonglin.test.banner.entity.Banner;
import cn.luosonglin.test.banner.entity.EventBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 22/01/2017.
 */
@Mapper
public interface EventBannerMapper {
    @Select("select id, title, banner, start_date, end_date from event where end_date >= CURRENT_TIMESTAMP")
    List<EventBanner> getAllEventBanner();

}
