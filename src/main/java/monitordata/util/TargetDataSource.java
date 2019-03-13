package monitordata.util;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于指定使用哪个数据源
 * <p>
 * Created by Administrator on 2017/9/25.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
