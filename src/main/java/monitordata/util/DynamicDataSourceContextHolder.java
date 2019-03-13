package monitordata.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态数据源上下文.
 * 动态数据源能进行自动切换的核心就是spring底层提供了AbstractRoutingDataSource类进行数据源的路由的，我们主要继承这个类，
 * 实现里面的方法即可实现我们想要的，这里主要是实现方法：determineCurrentLookupKey（），而此方法只需要返回一个数据库的名称即可，
 * 所以我们核心的是有一个类来管理数据源的线程池，这个类才是动态数据源的核心处理类。还有另外就是我们使用aop技术在执行事务方法前进行数据源的切换
 * Created by Administrator on 2017/9/25.
 */
public class DynamicDataSourceContextHolder {

    /**
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 管理所有的数据源id;
     * 主要是为了判断数据源是否存在;
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 使用setDataSourceType设置当前的
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定的DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
