package monitordata.util;

import monitordata.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 定时删除门禁打卡的过期数据
 *
 * Created by Administrator on 2018/1/23.
 */
@Component
public class SchedulerTask {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ApiService apiService;

    @Scheduled(cron = "0 0/30 20 * * ?")
    public void delHKDiaobiao() {
        apiService.delHKDiaobiao();
    }

    @Scheduled(cron = "0 0/30 21 * * ?")
    public void delHKKongtiao1() {
        apiService.delHKKongtiao1();
    }

    @Scheduled(cron = "0 0/30 22 * * ?")
    public void delHKKongtiao2() {
        apiService.delHKKongtiao2();
    }

    @Scheduled(cron = "0 0/30 23 * * ?")
    public void delHKKongtiao3() {
        apiService.delHKKongtiao3();
    }

   /* @Scheduled(cron = "0 0/30 22 * * ?")
    @Async
    public void process() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\AccessControl\\iCCard3000.mdb", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = " DELETE FROM t_d_SwipeRecord WHERE f_ReadDate < CDate(?) ";
            StringBuilder sb = new StringBuilder(sql);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -15);
            params.add(dateFormat.format(calendar.getTime()));
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("数据清除成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }*/

//    @Scheduled(cron = "0 0/1 * * * ?")
    /*public void sbProcess() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\DC\\iCCard3000.mdb", "dbur1", "168168");
            List<String> params = new ArrayList<String>();
            String sql = " DELETE FROM t_d_SwipeRecord WHERE f_ReadDate < CDate(?) ";
            StringBuilder sb = new StringBuilder(sql);
            Calendar calendar = Calendar.getInstance();
     //       calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DAY_OF_MONTH, -15);
            params.add(dateFormat.format(calendar.getTime()));
            ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("数据清除成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }*/

    /*@Scheduled(cron = "0 0/30 22 * * ?")
    @Async
    public void deleteKjData() {
        apiService.deleteKjData();
    }*/
}
