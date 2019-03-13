package monitordata.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * 某系统除了需要从自己的主要数据库上读取和管理数据外，还有一部分业务涉及到其他多个数据库，要求可以在任何方法上可以灵活指定具体要操作的数据库。
 * 为了在开发中以最简单的方法使用，本文基于注解和AOP的方法实现，
 * 在spring boot框架的项目中，添加本文实现的代码类后，只需要配置好数据源就可以直接通过注解使用，简单方便。
 * 一配置二使用
 * 1. 启动类注册动态数据源
 * 2. 配置文件中配置多个数据源
 * 3. 在需要的方法上使用注解指定数据源
 * 1、在启动类添加 @Import({DynamicDataSourceRegister.class, MProxyTransactionManagementConfiguration.class})
 * Created by Administrator on 2017/9/25.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 代码中的determineCurrentLookupKey方法取得一个字符串，
     * 该字符串将与配置文件中的相应字符串进行匹配以定位数据源，配置文件，即applicationContext.xml文件中需要要如下代码：(non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        /**
         * DynamicDataSourceContextHolder代码中使用setDataSourceType
         * 设置当前的数据源，在路由类中使用getDataSourceType进行获取，
         * 交给AbstractRoutingDataSource进行注入使用。
         */
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
