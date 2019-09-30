
package cn.yj.notes.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *
 * @author 永健
 * @since ${date}
 */
@Configuration
public class MpConfig
{
    /**
     *  mybatis-plus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MPMetaObjectHandler();
    }


    /**
     * <br>
     * sql格式化拦截器
     */
    //@Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }

}
