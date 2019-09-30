package cn.yj.notes.common.web.contr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br>
 *
 * @author 永健
 * @since $date$ $time$
 */
@Controller
public class IndexController
{

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/druid")
    public void druid(HttpServletResponse response) throws IOException
    {
        response.sendRedirect("/druid/login.html");
    }
}
