package monitordata.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 业务逻辑接口
 * Created by Administrator on 2017/9/25.
 */

public interface ApiService {

    /** 思博学院 */

    /**
     * 获取所有的电表参数
     *
     * @return 列表数据
     */
 //   JSONObject getElectricityMeterParameter();

    /**
     * 获取规定日期内的电表参数
     *
     * @param id        电表id
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
 //   JSONArray getElectricityMeterParameterByTimeZones(String id, String startTime, String endTime);

    /**
     * 获取最新一条的刷卡记录
     *
     * @param doorID 门的id
     * @return
     */
  //  JSONObject getLatestSwipeRecord(String doorID);

    /**
     * 刷卡历史记录
     *
     * @param consumerID 持卡人id
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param doorID   门的id
     * @return
     */
 //   JSONArray getHistoryOfSwipeRecord(String doorID, String consumerID, String startTime, String endTime);

    /**
     * 获取今天最新一条异常电表参数
     *
     * @return 电表参数json对象
     */
 //   JSONObject getAbnormalElectricityMeterParameter();

    /**
     * 获取所有的异常打卡记录
     *
     * @return json数组
     * @param doorID
     * @param consumerID
     * @param startTime
     * @param endTime
     */
//    JSONArray getAbnormalSwipeRecord(String doorID, String consumerID, String startTime, String endTime);

    /** 科技学院 */

    /**
     * 获取最新一条的电表参数
     *
     * @return
     */
    JSONObject getDbParameter();

    /**
     * 获取规定时间范围内的电表参数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    JSONArray getDbParameterByTimeZones(String startTime, String endTime);

    /**
     * 获取当天最新的一条异常记录
     *
     * @return
     */
    JSONObject getAbnormalDbParameter();

    /**
     * 获取空调的最新一条参数
     *
     * @param ari_conditionerCode 空调编码
     * @return
     */
    JSONObject getAri_ConditionerParameter(String ari_conditionerCode);

    /**
     * 获取一定范围内的空调的参数
     *
     * @param ari_conditionerCode 空调码
     * @param startTime           开始时间
     * @param endTime             结束时间
     * @return
     */
    JSONArray getAri_ConditionerParameterByTimeZones(String ari_conditionerCode, String startTime, String endTime);

    /**
     * 获取空调的最新一条的异常记录
     *
     * @param ari_conditionerCode
     * @return
     */
    JSONObject getAbnormalAri_ConditionerParameter(String ari_conditionerCode);

    void delHKDiaobiao();

    void delHKKongtiao1();

    void delHKKongtiao2();

    void delHKKongtiao3();

    /**
     * 根据人员的id获取人员的位置信息
     *
     * @param personId 人员的id
     * @return json数组
     */
    JSONArray getLocationInfo(String personId);

    /**
     * 获取门禁最新一条的打开记录(科技学院)
     *
     * @param doorID 门的id
     * @return
     */
    JSONObject getKJLatestSwipeRecord(String doorID);

    /**
     * 获取刷卡历史记录(科技学院)
     *
     * @param consumerID 持卡人id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param doorID    门的id
     * @return
     */
    JSONArray getKJHistoryOfSwipeRecord(String consumerID, String startTime, String endTime, String doorID);

    /**
     * 获取所有的异常打卡记录(科技学院)
     *
     * @param doorID  门的ID
     * @param consumerID 持卡人id
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return
     *
     */
    JSONArray getKJAbnormalSwipeRecord(String doorID, String consumerID, String startTime, String endTime);

    void deleteKjData();
}
