package cn.luosonglin.test.caseOfIllness.dao;

import cn.luosonglin.test.blog.entity.BlogCollection;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 07/01/2017.
 */
@Mapper
public interface CaseCollectionMapper {
    //收藏
    @Insert("INSERT INTO case_collection(id, user_id, case_id, created_at) VALUES(#{id}, #{user_id}, #{case_id}, #{created_at})")
    int insertCaseCollectionByMap(Map<String, Object> map);

    //取消收藏
    @Delete("DELETE FROM case_collection WHERE user_id =#{user_id} and case_id = #{case_id}")
    void deleteCaseCollectionByMap(Map<String, Object> map);

    //我收藏的病例
    @Select("select id, user_id, case_id, created_at from case_collection where user_id = #{user_id}")
    List<BlogCollection> findMyCollectionCase(@Param("user_id") Integer user_id);

    //我收藏的病例的id
    @Select("select case_id from case_collection where user_id = #{user_id}")
    List<Integer> getMyCollectionCaseIds(@Param("user_id") Integer user_id);

    //我收藏的病例数量
    @Select("select count(*) from case_collection where user_id = #{user_id}")
    Integer getMyCollectionCaseCount(@Param("user_id") Integer user_id);

    //某人是否收藏过该病例
    @Select("SELECT id FROM case_collection WHERE user_id =#{user_id} and case_id = #{case_id}")
    Integer isCaseCollected(@Param("user_id") Integer user_id, @Param("case_id") Integer case_id);
}
