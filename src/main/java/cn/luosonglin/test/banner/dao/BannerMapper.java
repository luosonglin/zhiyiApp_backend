package cn.luosonglin.test.banner.dao;

import cn.luosonglin.test.banner.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 01/12/2016.
 */
@Mapper
public interface BannerMapper {

    @Select("select id, url, title, content from banner where is_display = 0")
    List<Banner> getAllBanner();
}
