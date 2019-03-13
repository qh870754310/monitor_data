package monitordata.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import monitordata.dao.ApiDao;
import monitordata.service.ApiService;
import monitordata.util.TargetDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 业务逻辑实现
 * Created by Administrator on 2017/9/25.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApiDao apiDao;

    /**
     * 获取所有的电表参数
     *
     * @return 列表数据
     */
    /*@Override
    @TargetDataSource(name = "ds1")
    public JSONObject getElectricityMeterParameter() {
        Map map = apiDao.getElectricityMeterParameter();
        return (JSONObject) JSON.toJSON(map);
    }*/

    /**
     * 获取规定日期内的电表参数
     *
     * @param id        电表id
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
   /* @Override
    @TargetDataSource(name = "ds1")
    public JSONArray getElectricityMeterParameterByTimeZones(String id, String startTime, String endTime) {
        Map<String, Object> param = new HashMap<>();
        param.put("ID", id);
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        List<Map> maps = apiDao.getElectricityMeterParameterByTimeZones(param);
        return (JSONArray) JSON.toJSON(maps);
    }*/

    /**
     * 获取最新一条的刷卡记录
     *
     * @param doorID 门的id
     * @return
     */
    /*
    @Override
    public JSONObject getLatestSwipeRecord(String doorID) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            List<String> params = new ArrayList<String>();
            connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\DC\\iCCard3000.mdb", "dbur1", "168168");
            String sql = "select top 1 F.f_DoorID,  C.f_ReaderName, C.f_ReaderID, A.f_ReadDate, A.f_ConsumerID, B.f_ConsumerName, A.f_CardNO,  A.f_InOut, A.f_Character  " +
                    " from ((((t_d_SwipeRecord as A " +
                    " left join t_b_Consumer as B ON B.f_ConsumerID = A.f_ConsumerID) " +
                    " left join t_b_Reader as C ON C.f_ReaderID = A.f_ReaderID) " +
                    " left join t_b_Controller as D ON D.f_ControllerID = C.f_ControllerID) " +
                    " left join t_b_Door as F ON F.f_ControllerID = D.f_ControllerID) " +
                    " where 1=1 AND Left(C.f_ReaderName,instr(C.f_ReaderName,\"-\")-1) = F.f_DoorName ";
            StringBuilder sb = new StringBuilder(sql);
            if (StringUtils.isNotEmpty(doorID)) {
                sb.append(" AND F.f_DoorID = ?");
                params.add(doorID.trim());
            }
            sb.append(" ORDER BY A.f_ReadDate DESC");
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            Map map = null;
            while (rs.next()) {
                map = new HashMap();
                map.put("f_ConsumerName", rs.getString("f_ConsumerName") == null ? "" : rs.getString("f_ConsumerName"));
                map.put("f_ReaderName", rs.getString("f_ReaderName") == null ? "" : rs.getString("f_ReaderName"));
                map.put("f_ReadDate", rs.getString("f_ReadDate") == null ? "" : rs.getString("f_ReadDate").substring(0, rs.getString("f_ReadDate").lastIndexOf(".")));
                map.put("f_DoorID", rs.getString("f_DoorID") == null ? "" : rs.getString("f_DoorID"));
                map.put("f_ReaderID", rs.getString("f_ReaderID") == null ? "" : rs.getString("f_ReaderID"));
                map.put("f_CardNO", rs.getString("f_CardNO") == null ? "" : rs.getString("f_CardNO"));
                map.put("f_ConsumerID", rs.getString("f_ConsumerID") == null ? "" : rs.getString("f_ConsumerID"));
            }
            return (JSONObject) JSON.toJSON(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("关闭结果集异常:" + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("关闭预处理器异常:" + ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("关闭数据库链接异常:" + ex.getMessage());
                }
            }
        }
        return null;
    }*/

    /**
     * 刷卡历史记录
     *
     * @param consumerID 持卡人id
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param doorID     门的id
     * @return
     */
    /*
    @Override
    public JSONArray getHistoryOfSwipeRecord(String doorID, String consumerID, String startTime, String endTime) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\DC\\iCCard3000.mdb", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = "select F.f_DoorID,  C.f_ReaderName, C.f_ReaderID, A.f_ReadDate, A.f_ConsumerID, B.f_ConsumerName, A.f_CardNO,  A.f_InOut, iif( A.f_Character =1, '正常', '无权限异常刷卡') as f_Character  " +
                    " from ((((t_d_SwipeRecord as A " +
                    " left join t_b_Consumer as B ON B.f_ConsumerID = A.f_ConsumerID) " +
                    " left join t_b_Reader as C ON C.f_ReaderID = A.f_ReaderID) " +
                    " left join t_b_Controller as D ON D.f_ControllerID = C.f_ControllerID) " +
                    " left join t_b_Door as F ON F.f_ControllerID = D.f_ControllerID) " +
                    " where 1=1 AND Left(C.f_ReaderName,instr(C.f_ReaderName,\"-\")-1) = F.f_DoorName ";
            StringBuilder sb = new StringBuilder(sql);
            if (StringUtils.isNotEmpty(doorID)) {
                sb.append(" AND F.f_DoorID = ?");
                params.add(doorID.trim());
            }
            if (StringUtils.isNotEmpty(consumerID)) {
                sb.append(" AND B.f_ConsumerID = ? ");
                params.add(consumerID.trim());
            }
            if (StringUtils.isNotEmpty(startTime)) {
                sb.append(" AND A.f_ReadDate >= CDate(?) ");
                params.add(startTime + " 0:00:00");
            }
            if (StringUtils.isNotEmpty(endTime)) {
                sb.append(" AND A.f_ReadDate <= CDate(?) ");
                params.add(endTime + " 23:59:59");
            }
            sb.append(" ORDER BY A.f_ReadDate DESC");
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            List<Map> maps = new ArrayList<>();
            Map map = null;
            while (rs.next()) {
                map = new HashMap();
                map.put("f_ConsumerName", rs.getString("f_ConsumerName") == null ? "" : rs.getString("f_ConsumerName"));
                map.put("f_ReaderName", rs.getString("f_ReaderName") == null ? "" : rs.getString("f_ReaderName"));
                map.put("f_ReadDate", rs.getString("f_ReadDate") == null ? "" : rs.getString("f_ReadDate").substring(0, rs.getString("f_ReadDate").lastIndexOf(".")));
                map.put("f_DoorID", rs.getString("f_DoorID") == null ? "" : rs.getString("f_DoorID"));
                map.put("f_ReaderID", rs.getString("f_ReaderID") == null ? "" : rs.getString("f_ReaderID"));
                map.put("f_CardNO", rs.getString("f_CardNO") == null ? "" : rs.getString("f_CardNO"));
                map.put("f_ConsumerID", rs.getString("f_ConsumerID") == null ? "" : rs.getString("f_ConsumerID"));
                map.put("f_Character", rs.getString("f_Character") == null ? "" : rs.getString("f_Character"));
                maps.add(map);
            }
            return (JSONArray) JSON.toJSON(maps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("关闭结果集异常:" + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("关闭预处理器异常:" + ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("关闭数据库链接异常:" + ex.getMessage());
                }
            }
        }
        return null;
    }*/

    /**
     * 获取今天最新一条异常电表参数
     *
     * @return 电表参数json对象
     */
    /*@Override
    @TargetDataSource(name = "ds1")
    public JSONObject getAbnormalElectricityMeterParameter() {
        Map<String, Object> param = new HashMap<>();
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        param.put("startDate", sdf.format(calendar.getTime()));
        param.put("endDate", today);
        Map map = apiDao.getAbnormalElectricityMeterParameter(param);
        return (JSONObject) JSON.toJSON(map);
    }*/

    /**
     * 获取所有的异常打卡记录
     *
     * @return json数组
     * @param doorID
     * @param consumerID
     * @param startTime
     * @param endTime
     */
    /*
    @Override
    public JSONArray getAbnormalSwipeRecord(String doorID, String consumerID, String startTime, String endTime) {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String startDate = sdf.format(calendar.getTime());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\DC\\iCCard3000.mdb;memory=false", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = "select F.f_DoorID,  C.f_ReaderName, C.f_ReaderID, Format(A.f_ReadDate, 'General Date') AS f_ReadDate, A.f_ConsumerID, B.f_ConsumerName, A.f_CardNO,  A.f_InOut, iif( A.f_Character =1, '正常', '无权限异常刷卡') as f_Character  " +
                    " from ((((t_d_SwipeRecord as A " +
                    " left join t_b_Consumer as B ON B.f_ConsumerID = A.f_ConsumerID) " +
                    " left join t_b_Reader as C ON C.f_ReaderID = A.f_ReaderID) " +
                    " left join t_b_Controller as D ON D.f_ControllerID = C.f_ControllerID) " +
                    " left join t_b_Door as F ON F.f_ControllerID = D.f_ControllerID) " +
                    " where 1=1 AND Left(C.f_ReaderName,instr(C.f_ReaderName,\"-\")-1) = F.f_DoorName ";

            StringBuilder sb = new StringBuilder(sql);
            if (StringUtils.isNotEmpty(doorID)) {
                sb.append(" AND F.f_DoorID = ? ");
                params.add(doorID);
            }
            if (StringUtils.isNotEmpty(consumerID)) {
                sb.append(" AND B.f_ConsumerID = ? ");
                params.add(consumerID);
            }
            sb.append("AND Format(A.f_ReadDate, 'yyyy-mm-dd hh:mm:ss') >= CDate(?) AND Format(A.f_ReadDate, 'yyyy-mm-dd hh:mm:ss') <= CDate(?) ");
            if (StringUtils.isNotEmpty(startTime)) {
                params.add(startTime + " 0:00:00");
            } else {
                params.add(startDate);
            }
            if (StringUtils.isNotEmpty(endTime)) {
                params.add(endTime + " 23:59:59");
            } else {
                params.add(today);
            }
            sb.append(" ORDER BY A.f_ReadDate DESC ");
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            List<Map> maps = new ArrayList<>();
            Map map = null;
            while (rs.next()) {
                map = new HashMap();
                map.put("f_ConsumerName", rs.getString("f_ConsumerName") == null ? "" : rs.getString("f_ConsumerName"));
                map.put("f_ReaderName", rs.getString("f_ReaderName") == null ? "" : rs.getString("f_ReaderName"));
                map.put("f_ReadDate", rs.getString("f_ReadDate") == null ? "" : rs.getString("f_ReadDate"));
                map.put("f_DoorID", rs.getString("f_DoorID") == null ? "" : rs.getString("f_DoorID"));
                map.put("f_ReaderID", rs.getString("f_ReaderID") == null ? "" : rs.getString("f_ReaderID"));
                map.put("f_CardNO", rs.getString("f_CardNO") == null ? "" : rs.getString("f_CardNO"));
                map.put("f_ConsumerID", rs.getString("f_ConsumerID") == null ? "" : rs.getString("f_ConsumerID"));
                map.put("f_Character", rs.getString("f_Character") == null ? "" : rs.getString("f_Character"));
                maps.add(map);
            }
            return (JSONArray) JSON.toJSON(maps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    System.out.println("关闭结果集异常:" + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (SQLException ex) {
                    System.out.println("关闭预处理器异常:" + ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                    System.out.println("关闭数据库链接异常:" + ex.getMessage());
                }
            }
        }
        return null;
    }*/
    /** 科技学院 */

    /**
     * 获取最新一条的电表参数
     *
     * @return
     */
    @Override
    @TargetDataSource(name = "ds2")
    public JSONObject getDbParameter() {
        Map map = apiDao.getDbParameter();
        return (JSONObject) JSON.toJSON(map);
    }
    /**
     * 获取规定时间内的电表参数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    @TargetDataSource(name = "ds2")
    public JSONArray getDbParameterByTimeZones(String startTime, String endTime) {
        Map<String, Object> param = new HashMap<>();
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        List<Map> maps = apiDao.getDbParameterByTimeZones(param);
        return (JSONArray) JSON.toJSON(maps);
    }

    /**
     * 获取当天最新的一条异常记录
     *
     * @return
     */
    @Override
    @TargetDataSource(name = "ds2")
    public JSONObject getAbnormalDbParameter() {
        Map<String, Object> param = new HashMap<>();
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        param.put("startDate", sdf.format(calendar.getTime()));
        param.put("endDate", today);
        Map map = apiDao.getAbnormalDbParameter(param);
        return (JSONObject) JSON.toJSON(map);
    }

    /**
     * 获取空调的最新一条参数
     *
     * @param ari_conditionerCode 空调编码
     * @return
     */
    @Override
    @TargetDataSource(name = "ds2")
    public JSONObject getAri_ConditionerParameter(String ari_conditionerCode) {
        Map<String, Object> param = new HashMap();
        param.put("ari_conditionerCode", Integer.valueOf(ari_conditionerCode));
        Map map = apiDao.getAri_ConditionerParameter(param);
        return (JSONObject) JSON.toJSON(map);
    }

    /**
     * 获取一定范围内的空调的参数
     *
     * @param ari_conditionerCode 空调码
     * @param startTime           开始时间
     * @param endTime             结束时间
     * @return
     */
    @Override
    @TargetDataSource(name = "ds2")
    public JSONArray getAri_ConditionerParameterByTimeZones(String ari_conditionerCode, String startTime, String endTime) {
        Map<String, Object> param = new HashMap<>();
        param.put("ari_conditionerCode", ari_conditionerCode);
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        List<Map> maps = apiDao.getAri_ConditionerParameterByTimeZones(param);
        return (JSONArray) JSON.toJSON(maps);
    }

    /**
     * 获取空调的最新一条的异常记录
     *
     * @param ari_conditionerCode 空调码
     * @return
     */
    @Override
    @TargetDataSource(name = "ds2")
    public JSONObject getAbnormalAri_ConditionerParameter(String ari_conditionerCode) {
        Map<String, Object> param = new HashMap<>();
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        param.put("startDate", sdf.format(calendar.getTime()));
        param.put("endDate", today);
        param.put("ari_conditionerCode", ari_conditionerCode);
        Map map = apiDao.getAbnormalAri_ConditionerParameter(param);
        return (JSONObject) JSON.toJSON(map);
    }

    @Override
    @TargetDataSource(name = "ds2")
    public void delHKDiaobiao() {
        apiDao.delHKDiaobiao();
    }

    @Override
    @TargetDataSource(name = "ds2")
    public void delHKKongtiao1() {
        apiDao.delHKKongtiao1();
    }

    @Override
    @TargetDataSource(name = "ds2")
    public void delHKKongtiao2() {
        apiDao.delHKKongtiao2();
    }

    @Override
    @TargetDataSource(name = "ds2")
    public void delHKKongtiao3() {
        apiDao.delHKKongtiao3();
    }

    /**
     * 根据人员的id获取人员的位置信息
     *
     * @param personId 人员的id
     * @return json数组
     */
    @Override
    @TargetDataSource(name = "ds3")
    public JSONArray getLocationInfo(String personId) {
        List<Map> maps = apiDao.getLocationInfo(personId);
        return (JSONArray) JSON.toJSON(maps);
    }

    /**
     * 获取科技学院门禁最新一条的打开记录
     *
     * @param doorID 门的id
     * @return
     */
    /*
    @Override
    public JSONObject getKJLatestSwipeRecord(String doorID) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\AccessControl\\iCCard3000.mdb", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = "select top 1 F.f_DoorID,  C.f_ReaderName, C.f_ReaderID, A.f_ReadDate, A.f_ConsumerID, B.f_ConsumerName, A.f_CardNO,  A.f_InOut, A.f_Character  " +
                    " from ((((t_d_SwipeRecord as A " +
                    " left join t_b_Consumer as B ON B.f_ConsumerID = A.f_ConsumerID) " +
                    " left join t_b_Reader as C ON C.f_ReaderID = A.f_ReaderID) " +
                    " left join t_b_Controller as D ON D.f_ControllerID = C.f_ControllerID) " +
                    " left join t_b_Door as F ON F.f_ControllerID = D.f_ControllerID) " +
                    " where 1=1 AND Left(C.f_ReaderName,instr(C.f_ReaderName,\"-\")-1) = F.f_DoorName ";
            StringBuilder sb = new StringBuilder(sql);
            if (StringUtils.isNotEmpty(doorID)) {
                sb.append(" AND F.f_DoorID = ?");
                params.add(doorID.trim());
            }
            sb.append(" ORDER BY A.f_ReadDate DESC");
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            Map map = new HashMap();
            while (rs.next()) {
                map.put("f_ConsumerName", rs.getString("f_ConsumerName") == null ? "" : rs.getString("f_ConsumerName"));
                map.put("f_ReaderName", rs.getString("f_ReaderName") == null ? "" : rs.getString("f_ReaderName"));
                map.put("f_ReadDate", rs.getString("f_ReadDate") == null ? "" : rs.getString("f_ReadDate").substring(0, rs.getString("f_ReadDate").lastIndexOf(".")));
                map.put("f_DoorID", rs.getString("f_DoorID") == null ? "" : rs.getString("f_DoorID"));
                map.put("f_ReaderID", rs.getString("f_ReaderID") == null ? "" : rs.getString("f_ReaderID"));
                map.put("f_CardNO", rs.getString("f_CardNO") == null ? "" : rs.getString("f_CardNO"));
                map.put("f_ConsumerID", rs.getString("f_ConsumerID") == null ? "" : rs.getString("f_ConsumerID"));
            }
            return (JSONObject) JSON.toJSON(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("关闭结果集异常:" + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("关闭预处理器异常:" + ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("关闭数据库链接异常:" + ex.getMessage());
                }
            }
        }
        return null;
    }*/

    /**
     * 获取刷卡历史记录(科技学院)
     *
     * @param f_ConsumerID 持卡人id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param doorID  门的id
     * @return
     */
    /*
    @Override
    public JSONArray getKJHistoryOfSwipeRecord(String f_ConsumerID, String startTime, String endTime, String doorID) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\AccessControl\\iCCard3000.mdb", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = "select " +
                    "A.f_DoorID, " +
                    "C.f_ReaderName, " +
                    "C.f_ReaderID, " +
                    "D.f_ReadDate, " +
                    "D.f_ConsumerID, " +
                    "E.f_ConsumerName, " +
                    "D.f_CardNO,  " +
                    "D.f_InOut, " +
                    "iif( D.f_Character =1, '正常', '无权限异常刷卡') as f_Character " +
                    "from ((((t_b_Door as A " +
                    "left join t_b_Controller as B on A.f_ControllerID = B.f_ControllerID) " +
                    "left join t_b_Reader as C on B.f_ControllerID = C.f_ControllerID) " +
                    "left join t_d_SwipeRecord as D on C.f_ReaderID = D.f_ReaderID) " +
                    "left join t_b_Consumer as E on D.f_ConsumerID = E.f_ConsumerID) " +
                    "where 1=1 AND Left(C.f_ReaderName,instr(C.f_ReaderName,\"-\")-1) = A.f_DoorName  AND D.f_ConsumerID <> 0 AND E.f_ConsumerName <> null ";
            StringBuilder sb = new StringBuilder(sql);
            if (StringUtils.isNotEmpty(f_ConsumerID)) {
                sb.append(" AND E.f_ConsumerName = ?");
                params.add(f_ConsumerID.trim());
            }
            if (StringUtils.isNotEmpty(startTime)) {
                sb.append(" ANDD.f_ReadDate >=  CDate(?)");
                params.add("#" + startTime +"#");
            }
            if (StringUtils.isNotEmpty(endTime)) {
                sb.append(" AND D.f_ReadDate <= CDate(?)");
                params.add("#" + endTime +"#");
            }
            if (StringUtils.isNotEmpty(doorID)) {
                sb.append(" AND A.f_DoorID = ?");
                params.add(doorID.trim());
            }
            sb.append(" ORDER BY D.f_ReadDate DESC");
            System.out.println(sb.toString());
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            List<Map> maps = new ArrayList<>();
            Map map = null;
            while (rs.next()) {
                map = new HashMap();
                map.put("f_ConsumerName", rs.getString("f_ConsumerName") == null ? "" : rs.getString("f_ConsumerName"));
                map.put("f_ReaderName", rs.getString("f_ReaderName") == null ? "" : rs.getString("f_ReaderName"));
                map.put("f_ReadDate", rs.getString("f_ReadDate") == null ? "" : rs.getString("f_ReadDate").substring(0, rs.getString("f_ReadDate").lastIndexOf(".")));
                map.put("f_DoorID", rs.getString("f_DoorID") == null ? "" : rs.getString("f_DoorID"));
                map.put("f_ReaderID", rs.getString("f_ReaderID") == null ? "" : rs.getString("f_ReaderID"));
                map.put("f_CardNO", rs.getString("f_CardNO") == null ? "" : rs.getString("f_CardNO"));
                map.put("f_ConsumerID", rs.getString("f_ConsumerID") == null ? "" : rs.getString("f_ConsumerID"));
                map.put("f_Character", rs.getString("f_Character") == null ? "" : rs.getString("f_Character"));
                maps.add(map);
            }
            return (JSONArray) JSON.toJSON(maps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("关闭结果集异常:" + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("关闭预处理器异常:" + ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("关闭数据库链接异常:" + ex.getMessage());
                }
            }
        }
        return null;
    }*/

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
    /*
    @Override
    public JSONArray getKJAbnormalSwipeRecord(String doorID, String consumerID, String startTime, String endTime) {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String startDate = sdf.format(calendar.getTime());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\AccessControl\\iCCard3000.mdb", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = "select " +
                    "A.f_DoorID, " +
                    "C.f_ReaderName, " +
                    "C.f_ReaderID, " +
                    "D.f_ReadDate, " +
                    "D.f_ConsumerID, " +
                    "E.f_ConsumerName, " +
                    "D.f_CardNO,  " +
                    "D.f_InOut, " +
                    "iif( D.f_Character =1, '正常', '无权限异常刷卡') as f_Character " +
                    "from ((((t_b_Door as A " +
                    "left join t_b_Controller as B on A.f_ControllerID = B.f_ControllerID) " +
                    "left join t_b_Reader as C on B.f_ControllerID = C.f_ControllerID) " +
                    "left join t_d_SwipeRecord as D on C.f_ReaderID = D.f_ReaderID) " +
                    "left join t_b_Consumer as E on D.f_ConsumerID = E.f_ConsumerID) " +
                    "where 1=1 AND Left(C.f_ReaderName,instr(C.f_ReaderName,\"-\")-1) = A.f_DoorName  AND D.f_ConsumerID <> 0 AND E.f_ConsumerName <> null ";
            StringBuilder sb = new StringBuilder(sql);
            if (StringUtils.isNotEmpty(doorID)) {
                sb.append(" AND A.f_DoorID = ? ");
                params.add(doorID);
            }
            if (StringUtils.isNotEmpty(consumerID)) {
                sb.append(" AND E.f_ConsumerID = ? ");
                params.add(consumerID);
            }
            sb.append("AND D.f_ReadDate >= CDate(?) AND D.f_ReadDate <= CDate(?)" );
            if (StringUtils.isNotEmpty(startTime)) {
                params.add(startTime);
            } else {
                params.add(startDate);
            }
            if (StringUtils.isNotEmpty(endTime)) {
                params.add(endTime);
            } else {
                params.add(today);
            }
            sb.append(" ORDER BY D.f_ReadDate DESC ");
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            List<Map> maps = new ArrayList<>();
            Map map = null;
            while (rs.next()) {
                map = new HashMap();
                map.put("f_ConsumerName", rs.getString("f_ConsumerName") == null ? "" : rs.getString("f_ConsumerName"));
                map.put("f_ReaderName", rs.getString("f_ReaderName") == null ? "" : rs.getString("f_ReaderName"));
                map.put("f_ReadDate", rs.getString("f_ReadDate") == null ? "" : rs.getString("f_ReadDate").substring(0, rs.getString("f_ReadDate").lastIndexOf(".")));
                map.put("f_DoorID", rs.getString("f_DoorID") == null ? "" : rs.getString("f_DoorID"));
                map.put("f_ReaderID", rs.getString("f_ReaderID") == null ? "" : rs.getString("f_ReaderID"));
                map.put("f_CardNO", rs.getString("f_CardNO") == null ? "" : rs.getString("f_CardNO"));
                map.put("f_ConsumerID", rs.getString("f_ConsumerID") == null ? "" : rs.getString("f_ConsumerID"));
                map.put("f_Character", rs.getString("f_Character") == null ? "" : rs.getString("f_Character"));
                maps.add(map);
            }
            return (JSONArray) JSON.toJSON(maps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("关闭结果集异常:" + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("关闭预处理器异常:" + ex.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("关闭数据库链接异常:" + ex.getMessage());
                }
            }
        }
        return null;
    }*/


    @Override
    @TargetDataSource(name = "ds4")
    public JSONObject getKJLatestSwipeRecord(String doorID) {
        Map map = apiDao.getKJLatestSwipeRecord(doorID);
        System.out.println(map);
        return (JSONObject) JSON.toJSON(map);
    }

    @Override
    @TargetDataSource(name = "ds4")
    public JSONArray getKJHistoryOfSwipeRecord(String f_ConsumerID, String startTime, String endTime, String doorID) {
        Map<String, Object> params = new HashMap();
        params.put("f_ConsumerName", f_ConsumerID);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("doorID", doorID);
        List<Map> maps = apiDao.getKJHistoryOfSwipeRecord(params);
        return (JSONArray) JSON.toJSON(maps);
    }

    @Override
    @TargetDataSource(name = "ds4")
    public JSONArray getKJAbnormalSwipeRecord(String doorID, String consumerID, String startTime, String endTime) {
        Map<String, Object> params = new HashMap();
        params.put("f_ConsumerName", consumerID);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("doorID", doorID);
        List<Map> maps = apiDao.getKJAbnormalSwipeRecord(params);
        return (JSONArray) JSON.toJSON(maps);
    }

    @Override
    @TargetDataSource(name = "ds4")
    public void deleteKjData() {
        apiDao.deleteKjData();
    }

}