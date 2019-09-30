package cn.yj.notes.common.web;

import cn.yj.notes.common.fileUpload.FileServiceHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-28 14:23
 */
@Component
public class BeanTool implements ApplicationContextAware
{
    private static ApplicationContext app;
    private static Map<String, FileServiceHandler> fileServiceMap;

    @Autowired
    Map<String, FileServiceHandler> tempFileServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        app = applicationContext;
        fileServiceMap = tempFileServiceMap;
    }

    private static ApplicationContext getApplicationContext()
    {
        return app;
    }

    public static <T> T getInstance(String beanName)
    {
        return (T) getApplicationContext().getBean(beanName);
    }

    public static <T> T getInstance(Class<T> tClass)
    {
        return (T) getApplicationContext().getBean(tClass);
    }

    public static FileServiceHandler getFileHandlerInstance(String beanName)
    {
        FileServiceHandler handler = fileServiceMap.get(beanName);
        if (handler == null)
        {
            handler = fileServiceMap.get("defaultFileHandler");
        }
        return handler;
    }

}
