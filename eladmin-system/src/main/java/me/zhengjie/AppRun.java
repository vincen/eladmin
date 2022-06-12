package me.zhengjie;

import io.swagger.annotations.Api;
import me.zhengjie.annotation.rest.AnonymousGetMapping;
import me.zhengjie.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开启审计功能 -> @EnableJpaAuditing
 */
@EnableAsync
@RestController
@Api(hidden = true)
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AppRun {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AppRun.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/eladmin/app.pid
        // 或者在 application.yml 添加文件路径，方便 kill，kill `cat /home/eladmin/app.pid`
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    // 最新的tomcat不允许查询参数中包含[]{}字符，如果不用 tomcat 注释掉该行就行。
//    @Bean
//    public ServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
//        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
//        return fa;
//    }


    /**
     * 访问首页提示
     *
     * @return /
     */
    @AnonymousGetMapping("/")
    public String index() {
        return "Backend service started successfully";
    }
}
