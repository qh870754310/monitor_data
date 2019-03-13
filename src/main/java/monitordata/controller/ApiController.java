package monitordata.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import monitordata.domain.DataVO;
import monitordata.domain.DevicesVO;
import monitordata.domain.SwitchesVO;
import monitordata.service.ApiService;
import monitordata.util.ResponseHelper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URI;
import java.util.*;

/**
 * 监控数据接口
 * Created by Administrator on 2017/9/25.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 获取电表参数(思博学院)
     *
     * @param response HttpServletResponse对象
     */
   /* @RequestMapping("/getElectricityMeterParameter")
    public void getElectricityMeterParameter(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject ElectricityMeterParameters = apiService.getElectricityMeterParameter();
            jsonObject.put("ElectricityMeterParameter", ElectricityMeterParameters);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }*/

    /**
     * 获取规定日期内的电表参数(思博学院)
     *
     * @param id        电表id
     * @param startTime 开始日期
     * @param endTime   结束日期
     */
    /*@RequestMapping("/getElectricityMeterParameterByTimeZones")
    public void getElectricityMeterParameterByTimeZones(HttpServletResponse response, String id, String startTime, String endTime) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray ElectricityMeterParameters = apiService.getElectricityMeterParameterByTimeZones(id, startTime, endTime);
            jsonObject.put("ElectricityMeterParameters", ElectricityMeterParameters);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }*/

    /**
     * 获取最新一条的刷卡记录(思博学院)
     *
     * @param response HttpServletResponse对象
     * @param doorID 门的id
     */
   /* @RequestMapping("/getLatestSwipeRecord")
    public void getLatestSwipeRecord(HttpServletResponse response, String doorID) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject swipeRecord = apiService.getLatestSwipeRecord(doorID);
            jsonObject.put("swipeRecord", swipeRecord);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }*/

    /**
     * 获取刷卡历史记录(思博学院)
     *
     * @param response   HttpServletResponse对象
     * @param consumerID 持卡人id
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param doorID   门的id
     */
   /* @RequestMapping("/getHistoryOfSwipeRecord")
    public void getHistoryOfSwipeRecord(HttpServletResponse response, String consumerID, String startTime, String endTime, String doorID) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray swipeRecords = apiService.getHistoryOfSwipeRecord(doorID,consumerID, startTime, endTime);
            jsonObject.put("swipeRecords", swipeRecords);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }*/

    /**
     * 获取今天最新一条异常电表参数(思博学院)
     *
     * @param response HttpServletResponse对象
     */
    /*@RequestMapping("/getAbnormalElectricityMeterParameter")
    public void getAbnormalElectricityMeterParameter(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject abnormalElectricityMeterParameter = apiService.getAbnormalElectricityMeterParameter();
            if (!StringUtils.isEmpty(abnormalElectricityMeterParameter)) {
                jsonObject.put("abnormalElectricityMeterParameter", abnormalElectricityMeterParameter);
            } else {
                jsonObject.put("abnormalElectricityMeterParameter", "");
            }
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }*/

    /**
     * 获取所有的异常打卡记录(思博学院)
     *
     * @param response HttpServletResponse对象
     */
    /*@RequestMapping("/getAbnormalSwipeRecord")
    public void getAbnormalSwipeRecord(HttpServletResponse response, String doorID, String consumerID, String startTime, String endTime) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray abnormalSwipeRecords = apiService.getAbnormalSwipeRecord(doorID, consumerID, startTime, endTime);
            jsonObject.put("abnormalSwipeRecords", abnormalSwipeRecords);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }*/

    /**
     * 获取最新一条的电表参数
     *
     * @param response HttpServletResponse对象
     */
    @RequestMapping("/getDbParameter")
    public void getDbParameter(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject object = apiService.getDbParameter();
            jsonObject.put("dbParamter", object);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取规定日期内的电表参数
     *
     * @param response  HttpServletResponse对象
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    @RequestMapping("/getDbParameterByTimeZones")
    public void getDbParameterByTimeZones(HttpServletResponse response, String startTime, String endTime) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray objects = apiService.getDbParameterByTimeZones(startTime, endTime);
            jsonObject.put("dbParamters", objects);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取当天最新的一条异常记录
     *
     * @param response HttpServletResponse对象
     */
    @RequestMapping("/getAbnormalDbParameter")
    public void getAbnormalDbParameter(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject object = apiService.getAbnormalDbParameter();
            if (!StringUtils.isEmpty(object)) {
                jsonObject.put("abnormalDbParamter", object);
            } else {
                jsonObject.put("abnormalDbParamter", "");
            }
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }


    /**
     * 获取空调的最新参数
     *
     * @param ari_conditionerCode 那个空调
     * @param response            HttpServletResponse对象
     */
    @RequestMapping("/getAri_ConditionerParameter")
    public void getAri_ConditionerParameter(HttpServletResponse response, String ari_conditionerCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject object = apiService.getAri_ConditionerParameter(ari_conditionerCode);
            jsonObject.put("Ari_ConditionerParameter", object);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取一定范围内的空调的参数
     *
     * @param response            HttpServletResponse对象
     * @param ari_conditionerCode 空调码
     * @param startTime           开始时间
     * @param endTime             结束时间
     */
    @RequestMapping("/getAri_ConditionerParameterByTimeZones")
    public void getAri_ConditionerParameterByTimeZones(HttpServletResponse response, String ari_conditionerCode, String startTime, String endTime) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray objects = apiService.getAri_ConditionerParameterByTimeZones(ari_conditionerCode, startTime, endTime);
            jsonObject.put("Ari_ConditionerParameters", objects);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取空调的最新一条的异常记录
     *
     * @param response HttpServletResponse对象
     */
    @RequestMapping("/getAbnormalAri_ConditionerParameter")
    public void getAbnormalAri_ConditionerParameter(HttpServletResponse response, String ari_conditionerCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject object = apiService.getAbnormalAri_ConditionerParameter(ari_conditionerCode);
            if (!StringUtils.isEmpty(object)) {
                jsonObject.put("AbnormalAri_ConditionerParameters", object);
            } else {
                jsonObject.put("AbnormalAri_ConditionerParameters", "");
            }
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 人员定位（程序在208服务器，数据库在202服务器）
     *
     * @param response HttpServletResponse对象
     * @param personId 人员id
     */
    @RequestMapping("/getLocationInfo")
    public void getLocationInfo(HttpServletResponse response, String personId) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = apiService.getLocationInfo(personId);
            jsonObject.put("personLocation", jsonArray);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取门禁最新一条的打开记录(科技学院)
     * @param response HttpServletResponse对象
     * @param doorID 门的id
     */
    @RequestMapping("/getKJLatestSwipeRecord")
    public void getKJLatestSwipeRecord(HttpServletResponse response, String doorID) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject swipeRecord = apiService.getKJLatestSwipeRecord(doorID);
            jsonObject.put("swipeRecord", swipeRecord);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取刷卡历史记录(科技学院)
     *
     * @param response   HttpServletResponse对象
     * @param consumerID 持卡人id
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param doorID     门的id
     */
    @RequestMapping("/getKJHistoryOfSwipeRecord")
    public void getKJHistoryOfSwipeRecord(HttpServletResponse response, String consumerID, String startTime, String endTime, String doorID) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray swipeRecords = apiService.getKJHistoryOfSwipeRecord(consumerID, startTime, endTime, doorID);
            jsonObject.put("swipeRecords", swipeRecords);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    /**
     * 获取所有的异常打卡记录(科技学院)
     *
     * @param response HttpServletResponse对象
     * @param doorID 门的ID
     * @param consumerID 持卡人id
     * @param startTime  开始时间
     * @param endTime    结束时间
     *
     */
    @RequestMapping("/getKJAbnormalSwipeRecord")
    public void getKJAbnormalSwipeRecord(HttpServletResponse response, String doorID, String consumerID, String startTime, String endTime) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray abnormalSwipeRecords = apiService.getKJAbnormalSwipeRecord(doorID, consumerID, startTime, endTime);
            jsonObject.put("abnormalSwipeRecords", abnormalSwipeRecords);
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    @RequestMapping(value = "/getDataGridTest")
    public void getDataGrid(HttpServletResponse response, String deviceName) {
        JSONObject jsonObject = new JSONObject();
        try {
            String text = "{\n" +
                    "        \"Ac4700\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.2\",\n" +
                    "                \"name\": \"AC4700\",\n" +
                    "                \"states\": 1,\n" +
                    "                \"type\": \"Other\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"Dr.com\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.3\",\n" +
                    "                \"name\": \"Dr.com\",\n" +
                    "                \"states\": 1,\n" +
                    "                \"type\": \"Unknown\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"S5500T\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.11\",\n" +
                    "                \"name\": \"S5500T\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"HuaweiDevice\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"USG6650\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.4\",\n" +
                    "                \"name\": \"USG6650\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"USG6650\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"devices\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.129\",\n" +
                    "                \"name\": \"E9000_Blade\",\n" +
                    "                \"status\": 1\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"switches\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.5\",\n" +
                    "                \"macAddress\": \"4C-1F-CC-DD-4A-A0\",\n" +
                    "                \"name\": \"2#1F_Srv_A-5\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"S5700-52P-LI-AC\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.2.254\",\n" +
                    "                \"macAddress\": \"E0-97-96-B7-22-F1\",\n" +
                    "                \"name\": \"CE6850\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"CE6850-48S4Q-EI\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.254\",\n" +
                    "                \"macAddress\": \"20-0B-C7-3C-42-01\",\n" +
                    "                \"name\": \"2#1F_SW_C-254_stack\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"CE12808\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"vCenter\": [\n" +
                    "            {\n" +
                    "                \"alarmName\": \"虚拟机内存使用情况\",\n" +
                    "                \"objectInstance\": \"虚拟机:App3(IP:10.1.2.29 , id-vm:50);主机:10.1.2.202(IP:10.1.2.202 , host:46);集群:cluster_1F(domain:c52);数据中心:scst\",\n" +
                    "                \"perceivedSeverity\": \"重要\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"磁盘上数据存储的使用情况\",\n" +
                    "                \"objectInstance\": \"数据存储:datastore1 (1)\",\n" +
                    "                \"perceivedSeverity\": \"重要\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"虚拟机内存使用情况\",\n" +
                    "                \"objectInstance\": \"虚拟机:zhiyejiaoyupingtai(id-vm:317);主机:10.1.2.201(IP:10.1.2.201 , host:9);集群:cluster_1F(domain:c52);\",\n" +
                    "                \"perceivedSeverity\": \"重要\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"虚拟机 CPU 使用情况\",\n" +
                    "                \"objectInstance\": \"虚拟机:ZCGL(IP:10.1.2.125 , id-vm:221);主机:10.1.2.205(IP:10.1.2.205 , host:34);集群:cluster_1F(domain:c5\",\n" +
                    "                \"perceivedSeverity\": \"重要\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"虚拟机内存使用情况\",\n" +
                    "                \"objectInstance\": \"虚拟机:baoxiu system(IP:10.1.2.130 , id-vm:322);主机:10.1.2.206(IP:10.1.2.206 , host:38);集群:cluster_1F(domain:c52);数据中心:scst\",\n" +
                    "                \"perceivedSeverity\": \"重要\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"磁盘上数据存储的使用情况\",\n" +
                    "                \"objectInstance\": \"数据存储:ESXi_Cluster_SAS-2T_06\",\n" +
                    "                \"perceivedSeverity\": \"紧急\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"磁盘上数据存储的使用情况\",\n" +
                    "                \"objectInstance\": \"数据存储:datastore1 (2)\",\n" +
                    "                \"perceivedSeverity\": \"紧急\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"磁盘上数据存储的使用情况\",\n" +
                    "                \"objectInstance\": \"数据存储:datastore1 (3)\",\n" +
                    "                \"perceivedSeverity\": \"紧急\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"虚拟机 CPU 使用情况\",\n" +
                    "                \"objectInstance\": \"虚拟机:baoxiu system(id-vm:329);主机:10.1.2.204(IP:10.1.2.204 , host:27);集群:cluster_1F(domain:c52);数据\",\n" +
                    "                \"perceivedSeverity\": \"紧急\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"alarmName\": \"虚拟机 CPU 使用情况\",\n" +
                    "                \"objectInstance\": \"虚拟机:W2K3-Sp2-HRS-scst_hrs_20120703_Main_scst_hrs.bak(id-vm:67);主机:10.1.2.206(IP:10.1.2.206 , host:38);集群:cluster_1F(domain:c52);数据中心:scst\",\n" +
                    "                \"perceivedSeverity\": \"紧急\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"2#1F_Srv_A-5\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.5\",\n" +
                    "                \"macAddress\": \"4C-1F-CC-DD-4A-A0\",\n" +
                    "                \"name\": \"2#1F_Srv_A-5\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"S5700-52P-LI-AC\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"CE6850\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.2.254\",\n" +
                    "                \"macAddress\": \"E0-97-96-B7-22-F1\",\n" +
                    "                \"name\": \"CE6850\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"CE6850-48S4Q-EI\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"2#1F_SW_C-254_stack\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.254\",\n" +
                    "                \"macAddress\": \"20-0B-C7-3C-42-01\",\n" +
                    "                \"name\": \"2#1F_SW_C-254_stack\",\n" +
                    "                \"status\": 1,\n" +
                    "                \"type\": \"CE12808\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"E9000_Blade\": [\n" +
                    "            {\n" +
                    "                \"ipAddress\": \"10.1.1.129\",\n" +
                    "                \"name\": \"E9000_Blade\",\n" +
                    "                \"status\": 1\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }";
            JSONObject json = JSON.parseObject(text);
            if (!StringUtils.isEmpty(deviceName)) {
                JSONArray o = (JSONArray) json.get(deviceName);
                jsonObject.put("result", o);
            } else {
                jsonObject.put("result", json);
            }
            jsonObject.put("state", true);
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(response, jsonObject);
    }

    @RequestMapping("/getDataGrid")
    public void  getDataGridTest(HttpServletResponse httpServletResponse, String deviceName) {
        JSONObject jsonObject = new JSONObject();
        final String url = "http://10.1.2.243:8081/net/queryNeDeviceTest";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Map<String, String> param = null;
        String ret = "";
        CloseableHttpResponse response = null;
        List<String> deviceList = new ArrayList<>(Arrays.asList("USG6650", "E9000_Blade", "AC4700", "S5500T", "CE6850",  "Dr.com", "2#1F_Srv_A-5", "2#1F_SW_C-254_stack", "vCenter"));
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key:param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            response =  httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            if (null == ret || ret.isEmpty()) {
                jsonObject.put("state", false);
            }
            JSONObject parseObject = JSONObject.parseObject(ret);
            JSONObject p = JSONObject.parseObject(parseObject.getString("result"));
            System.out.println(p.getString("code").equals("0"));
            Map<String, List<?>> map = null;
            if (p.getString("code").equals("0")) {
                if (!StringUtils.isEmpty(p.get("data"))) {
                    JSONArray json = JSONArray.parseArray(p.getString("data"));
                    map = new HashMap<>();
                    if (deviceList != null && deviceList.size() > 0) {
                        List switches = new ArrayList();
                        List devices = new ArrayList();
                        for (String device_name : deviceList) {
                            if (json.size() > 0) {
                                for (Object obj : json) {
                                    JSONObject object = (JSONObject) obj;
                                    if (object.get("nename").equals(device_name)) {
                                        List list = new ArrayList<>();
                                        if ("E9000_Blade".equals(device_name)) {
                                            DevicesVO devicesVO = new DevicesVO();
                                            devicesVO.setIpAddress(object.getString("neip"));
                                            devicesVO.setName(object.getString("nename"));
                                            devicesVO.setStatus(object.getInteger("nestate"));
                                            list.add(devicesVO);
                                            devices.add(devicesVO);
                                        } else if ("2#1F_Srv_A-5".equals(device_name) || "CE6850".equals(device_name) || "2#1F_SW_C-254_stack".equals(device_name)) {
                                            SwitchesVO switchesVO = new SwitchesVO();
                                            switchesVO.setIpAddress(object.getString("neip"));
                                            switchesVO.setMacAddress("");
                                            switchesVO.setName(object.getString("nename"));
                                            switchesVO.setType(object.getString("netype") != null ? object.getString("netype") : "");
                                            switchesVO.setStatus(object.getInteger("nestate"));
                                            list.add(switchesVO);
                                            switches.add(switchesVO);
                                        } else if ("vCenter".equals(device_name)) {

                                        } else {
                                            DataVO dataVO = new DataVO();
                                            dataVO.setIpAddress(object.getString("neip"));
                                            dataVO.setName(object.getString("nename"));
                                            dataVO.setStatus(object.getInteger("nestate"));
                                            dataVO.setType(object.getString("netype") != null ? object.getString("netype") : "");
                                            list.add(dataVO);
                                        }
                                        if ("AC4700".equals(device_name)) {
                                            map.put("Ac4700", list);
                                        } else {
                                            map.put(device_name, list);
                                        }
                                    }
                                }
                            }
                        }
                        map.put("switches", switches);
                        map.put("devices", devices);
                    }
                }
            }

            if (map != null && map.size() > 0) {
                String text = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
                JSONObject jsons = JSON.parseObject(text);
                if (!StringUtils.isEmpty(deviceName)) {
                    JSONArray o = (JSONArray) jsons.get(deviceName);
                    jsonObject.put("result", o);
                } else {
                    jsonObject.put("result", jsons);
                }
                jsonObject.put("state", true);
            }
        } catch (Exception e) {
            jsonObject.put("state", false);
        }
        ResponseHelper.write(httpServletResponse, jsonObject);
    }

    @RequestMapping(value = "d")
    public void t(HttpServletResponse httpServletResponse, String deviceName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        File jsonFile = ResourceUtils.getFile("classpath:area.json");
        String json2 = FileUtil.readAsString(jsonFile);
        Map<String, List> map = null;
        List<String> deviceList = new ArrayList<>(Arrays.asList("USG6650", "E9000_Blade", "AC4700", "S5500T", "CE6850",  "Dr.com", "2#1F_Srv_A-5", "2#1F_SW_C-254_stack", "vCenter"));
        JSONObject parseObject = JSONObject.parseObject(json2);
        map = new HashMap<>();
        JSONArray json = JSONArray.parseArray(parseObject.getString("result"));
        if (deviceList != null && deviceList.size() > 0) {
            List switches = new ArrayList();
            List devices = new ArrayList();
            for (String device_name : deviceList) {
                if (json.size() > 0) {
                    for (Object obj : json) {
                        JSONObject object = (JSONObject) obj;
                        if (object.get("nename").equals(device_name)) {
                            List list = new ArrayList<>();
                            if ("E9000_Blade".equals(device_name)) {
                                DevicesVO devicesVO = new DevicesVO();
                                devicesVO.setIpAddress(object.getString("neip"));
                                devicesVO.setName(object.getString("nename"));
                                devicesVO.setStatus(object.getInteger("nestate"));
                                list.add(devicesVO);
                                devices.add(devicesVO);
                            } else if ("2#1F_Srv_A-5".equals(device_name) || "CE6850".equals(device_name) || "2#1F_SW_C-254_stack".equals(device_name)) {
                                SwitchesVO switchesVO = new SwitchesVO();
                                switchesVO.setIpAddress(object.getString("neip"));
                                switchesVO.setMacAddress("");
                                switchesVO.setName(object.getString("nename"));
                                switchesVO.setType(object.getString("netype") != null ? object.getString("netype") : "");
                                switchesVO.setStatus(object.getInteger("nestate"));
                                list.add(switchesVO);
                                switches.add(switchesVO);
                            } else if ("vCenter".equals(device_name)) {

                            } else {
                                DataVO dataVO = new DataVO();
                                dataVO.setIpAddress(object.getString("neip"));
                                dataVO.setName(object.getString("nename"));
                                dataVO.setStatus(object.getInteger("nestate"));
                                dataVO.setType(object.getString("netype") != null ? object.getString("netype") : "");
                                list.add(dataVO);
                            }
                            if ("AC4700".equals(device_name)) {
                                map.put("Ac4700", list);
                            } else {
                                map.put(device_name, list);
                            }
                        }
                    }
                }
            }
            map.put("switches", switches);
            map.put("devices", devices);
        }

        if (map != null && map.size() > 0) {
            String text = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            JSONObject jsons = JSON.parseObject(text);
            if (!StringUtils.isEmpty(deviceName)) {
                JSONArray o = (JSONArray) jsons.get(deviceName);
                jsonObject.put("result", o);
            } else {
                jsonObject.put("result", jsons);
            }
            jsonObject.put("state", true);
        }
        ResponseHelper.write(httpServletResponse, jsonObject);
    }

}