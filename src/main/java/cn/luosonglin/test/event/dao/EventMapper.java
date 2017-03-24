package cn.luosonglin.test.event.dao;

import cn.luosonglin.test.event.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 23/03/2017.
 */
@Mapper
public interface EventMapper {
    @Select("SELECT * FROM event")
    List<Event> findAllEvent();
}
