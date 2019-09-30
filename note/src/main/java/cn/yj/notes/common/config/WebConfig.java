package cn.yj.notes.common.config;

import cn.yj.notes.common.web.Interceptor;
import cn.yj.notes.common.web.auth.Auth;
import cn.yj.notes.common.web.auth.ReqAuth;
import cn.yj.notes.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * @author 永健
 * Date 2018/12/6 9:32
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
    @Autowired
    RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    IUserService iUserService;

    /**
     * <br/>
     * 日期处理
     *
     * @param []
     *
     * @return void
     */

    @PostConstruct
    public void initEditableAvlidation()
    {

        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null)
        {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new DateConvert());
        }

    }

    /**
     * <br/>
     * 拦截器
     *
     * @return void
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new Interceptor(auth())).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean(name = "reqAuth")
    public ReqAuth auth()
    {
        return new ReqAuth(iUserService);
    }

    //    @Bean
    //    MultipartConfigElement multipartConfigElement()
    //    {
    //        MultipartConfigFactory factory = new MultipartConfigFactory();
    //        //文件最大
    //        factory.setMaxFileSize("5MB");
    //        /// 设置总上传数据总大小
    //        factory.setMaxRequestSize("5MB");
    //        return factory.createMultipartConfig();
    //    }
}
