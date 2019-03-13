package monitordata;

import monitordata.util.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
@ServletComponentScan
@MapperScan(basePackages = "monitordata.dao", annotationClass = Repository.class)
@EnableScheduling
public class MonitorDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorDataApplication.class, args);
	}
}
