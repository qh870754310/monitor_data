package monitordata.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 * Created by Administrator on 2017/9/25.
 */
@Repository
public interface ApiDao {

    /**
     * 获取所有的电表的参数
     *
     * @return
     */
    Map getElectricityMeterParameter();

    /**
     * 获取规定日期内的电表参数
     *
     * @param param 查询参数
     * @return
     */
    List<Map> getElectricityMeterParameterByTimeZones(Map<String, Object> param);

    /**
     * 获取最新一条的刷卡记录
     *
     * @return
     */
    Map getLatestSwipeRecord();

    /**
     * 刷卡历史记录
     *
     * @param param 查询参数
     * @return
     */
    List<Map> getHistoryOfSwipeRecord(Map<String, Object> param);

    /**
     * 获取今天最新一条异常电表参数
     *
     * @param param 查询参数
     * @return 异常电表参数
     */
    Map getAbnormalElectricityMeterParameter(Map<String, Object> param);

    /**
     * 获取最新一条的电表参数
     *
     * @return
     */
    Map getDbParameter();

    /**
     * 获取规定时间内的电表参数
     *
     * @param param 查询参数
     * @return
     */
    List<Map> getDbParameterByTimeZones(Map<String, Object> param);

    /**
     * 获取当天最新的一条异常记录
     *
     * @param param 查询参数
     * @return
     */
    Map getAbnormalDbParameter(Map<String, Object> param);

    /**
     * 获取空调的最新一条参数
     *
     * @param param 查询参数
     * @return
     */
    Map getAri_ConditionerParameter(Map<String, Object> param);

    /**
     * 获取一定范围内的空调的参数
     *
     * @param param 查询参数
     * @return
     */
    List<Map> getAri_ConditionerParameterByTimeZones(Map<String, Object> param);

    /**
     * 获取空调的最新一条的异常记录
     *
     * @param param 查询参数
     * @return
     */
    Map getAbnormalAri_ConditionerParameter(Map<String, Object> param);

    /**
     * 获取人员的位置信息
     *
     * @param personId 人员的id
     * @return list对象
     */
    List<Map> getLocationInfo(String personId);

    /**
     * 获取科技学院门禁打卡的最新一条记录
     *
     * @param doorID
     * @return
     */
    Map getKJLatestSwipeRecord(String doorID);

    /**
     * 获取科技学院门禁打卡的历史记录
     *
     * @param params
     * @return
     */
    List<Map> getKJHistoryOfSwipeRecord(Map<String, Object> params);

    /**
     * 获取科技学院门禁打卡的异常打卡
     * @param params
     * @return
     */
    List<Map> getKJAbnormalSwipeRecord(Map<String, Object> params);

    void deleteKjData();

    void delHKDiaobiao();

    void delHKKongtiao1();

    void delHKKongtiao2();

    void delHKKongtiao3();
}
