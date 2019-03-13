package monitordata.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 * <p>
 * Created by Administrator on 2017/9/25.
 */

@Aspect
@Order(-10)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * @Before("@annotation(ds)")的意思是：
     * @Before：在方法执行之前进行执行：
     * @annotation(targetDataSource)：会拦截注解targetDataSource的方法，否则不拦截;
     * @param point
     * @param dataSource
     * @throws Throwable
     */
    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource dataSource) throws Throwable {
        //获取当前的指定的数据源;
        String dsId = dataSource.name();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", dataSource.name(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", dataSource.name(), point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.name());
        }
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource dataSource) {
        logger.debug("Revert DataSource : {} > {}", dataSource.name(), point.getSignature());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
