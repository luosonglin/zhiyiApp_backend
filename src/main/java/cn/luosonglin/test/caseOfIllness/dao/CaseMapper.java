package cn.luosonglin.test.caseOfIllness.dao;

import cn.luosonglin.test.caseOfIllness.entity.CaseOfIllness;
import cn.luosonglin.test.caseOfIllness.entity.UserAndCase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 07/01/2017.
 */
@Mapper
public interface CaseMapper {
    @Select("SELECT case_of_illness.id, case_of_illness.user_id, case_of_illness.title, case_of_illness.chief_complain, case_of_illness.chief_complain_image, case_of_illness.body_check, case_of_illness.body_check_image, case_of_illness.medical_diagnosis, case_of_illness.medical_diagnosis_image, case_of_illness.follow_up, case_of_illness.follow_up_image, case_of_illness.comment_count, case_of_illness.like_count, case_of_illness.created_at, case_of_illness.deleted_at, case_of_illness.tag_id, case_of_illness.is_hot, user_info.name, user_info.nick_name, user_info.company, user_info.user_pic, user_info.authen_status FROM case_of_illness, user_info where case_of_illness.user_id = user_info.id")
    List<UserAndCase> findAllCase();

    //某条病例的详情
    @Select("SELECT * from case_of_illness, user_info where case_of_illness.user_id = user_info.id and case_of_illness.id = #{case_id}")
    UserAndCase getCaseDetail(@Param("case_id") Integer case_id);

    //发病例
    int writeCaseOfIllness(CaseOfIllness caseOfIllness);

    //发病例2
    @Insert("INSERT INTO case_of_illness(id, user_id, title, tag_id, chief_complain, chief_complain_image,body_check, body_check_image,medical_diagnosis, medical_diagnosis_image,follow_up, follow_up_image, comment_count, like_count, created_at, deleted_at,  is_hot) VALUES(#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, #{tag_id,jdbcType=VARCHAR}, #{chief_complain,jdbcType=VARCHAR}, #{chief_complain_image,jdbcType=VARCHAR}, #{body_check,jdbcType=VARCHAR}, #{body_check_image,jdbcType=VARCHAR}, #{medical_diagnosis,jdbcType=VARCHAR}, #{medical_diagnosis_image,jdbcType=VARCHAR}, #{follow_up,jdbcType=VARCHAR}, #{follow_up_image,jdbcType=VARCHAR}, #{comment_count,jdbcType=INTEGER}, #{like_count,jdbcType=INTEGER}, #{created_at,jdbcType=TIMESTAMP}, #{deleted_at,jdbcType=TIMESTAMP}, #{is_hot,jdbcType=INTEGER})")
    int insertCaseOfIllnessByMap(Map<String, Object> map);


    @Select("SELECT id, user_id, title, chief_complain, chief_complain_image, body_check, body_check_image, medical_diagnosis, medical_diagnosis_image, follow_up, follow_up_image, comment_count, like_count, created_at, deleted_at, tag_id, is_hot FROM case_of_illness WHERE user_id = #{user_id} ORDER BY created_at desc")
    List<CaseOfIllness> findCaseById(@Param("user_id") Integer user_id);

    //我关注的所有人的微博，按时间降序排列
    //后面加分页！！！
    //根据最后拉取时间和上次拉取时间取关注人的微博，每个人的数量限制在perpage条目范围内,然后合并并排序...高性能高并发下的拉取就要缓存了
//    @Select("SELECT * FROM blog WHERE user_id = #{user_id} ORDER BY created_at desc")
//    @Select("SELECT * FROM blog WHERE user_id = #{user_id,jdbcType=INTEGER})" +
//            "union" +
//            "SELECT * FROM blog WHERE user_id = #{user_id2,jdbcType=INTEGER} ORDER BY created_at desc)")
//    @Select("select * from blog where user_id in (#{user_ids}) order by created_at desc")
    //XML自动注入
    List<UserAndCase> getFollowsCaseByListId(List<Integer> user_ids); //

    //依据blog_id来获取blog列表
    List<CaseOfIllness> getCaseListByCaseId(List<Integer> id);
}
