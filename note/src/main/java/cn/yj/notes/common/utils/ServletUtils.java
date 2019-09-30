package cn.yj.notes.common.utils;

import cn.yj.notes.common.web.Res;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author 永健
 * 客户端工具类
 * @since 2018/12/11 12:53
 */
public class ServletUtils
{

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response
     *         渲染对象
     * @param string
     *         待渲染的字符串
     *
     * @return null
     */
    public static void out(Res res)
    {
        PrintWriter out = null;
        try
        {
            HttpServletResponse response = getResponse();
            //设置编码
            response.setCharacterEncoding("UTF-8");
            //设置返回类型
            response.setContentType("application/json");
            out = response.getWriter();
            //输出
            out.write(JSON.toJSONString(res));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            out.flush();
            out.close();
        }

    }


}
