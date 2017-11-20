package cn.luosonglin.test.android.dao;

import cn.luosonglin.test.android.entity.AndroidPhoneInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by luosonglin on 16/09/2017.
 */
@Mapper
public interface AndroidPhoneInfoMapper {
    @Insert("INSERT INTO android_phone_info(user_id, unique_serial_number, metrics, imei, imsi, mac_address, mcc_mnc, sim_operator_name, is_root) VALUES(#{user_id,jdbcType=INTEGER}, #{unique_serial_number,jdbcType=VARCHAR}, #{metrics,jdbcType=VARCHAR}, #{imei,jdbcType=VARCHAR}, #{imsi,jdbcType=VARCHAR}, #{mac_address,jdbcType=VARCHAR}, #{mcc_mnc,jdbcType=VARCHAR}, #{sim_operator_name,jdbcType=VARCHAR}, #{is_root,jdbcType=VARCHAR})")
    int insertByNewPhoneInfo(@Param("user_id") Integer user_id, @Param("unique_serial_number") String unique_serial_number, @Param("metrics") String metrics, @Param("imei") String imei, @Param("imsi") String imsi, @Param("mac_address") String mac_address, @Param("mcc_mnc") String mcc_mnc, @Param("sim_operator_name") String sim_operator_name, @Param("is_root") Integer is_root);


    @Select("select user_id, unique_serial_number, metrics, imei, imsi, mac_address, mcc_mnc, sim_operator_name, is_root from android_phone_info where user_id = #{metrics,jdbcType=INTEGER} and imei = #{imei,jdbcType=VARCHAR}")
    List<AndroidPhoneInfo> selecePhoneInfoList(@Param("user_id") Integer user_id, @Param("imei") String imei);

}
