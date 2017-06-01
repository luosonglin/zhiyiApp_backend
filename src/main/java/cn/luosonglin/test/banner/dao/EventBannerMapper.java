package cn.luosonglin.test.banner.dao;

import cn.luosonglin.test.banner.entity.EventBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 22/01/2017.
 */
@Mapper
public interface EventBannerMapper {
//    @Select("select id, title, banner, start_date, end_date from event where end_date >= CURRENT_TIMESTAMP LIMIT 3 OFFSET 2")
    //会议banner 过滤掉含有空白banner数据的banner图
//    @Select("select id, title, banner, start_date, end_date from event where end_date >= CURRENT_TIMESTAMP and banner !='' order by id desc limit 0, 3")
//    @Select("select id, title, banner, start_date, end_date from event where end_date >= CURRENT_TIMESTAMP and banner !='' order by id desc limit 0, 4")
//    @Select("select id, title, banner, start_date, end_date from event where end_date >= CURRENT_TIMESTAMP and banner !='' and id >= 207 and id <=211 order by id asc")
    @Select("select a.id, a.title, a.start_date, a.end_date, b.banner from event a, phoneweb_set b where a.id = b.event_id and a.id = 180 or a.id = b.event_id and a.id = 210 or a.id = b.event_id and a.id = 218 order by a.id asc")
    List<EventBanner> getAllEventBanner();
}

