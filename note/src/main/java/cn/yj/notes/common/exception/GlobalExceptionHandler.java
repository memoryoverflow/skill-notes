package cn.yj.notes.common.exception;


import cn.yj.notes.common.web.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-23 19:32
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Res handleRRException(MissingServletRequestParameterException e)
    {
        return Res.error(Error.参数绑定异常,"参数绑定失败，请求参数异常");
    }

    /**
     * GET,POST 等方法
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Res handleRRException(HttpRequestMethodNotSupportedException e)
    {
        return Res.error(Error.请求方式错误,"请求方式不对，换个姿势再来！@_@ ,试试 /GET/POST/PUT...");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MyException.class)
    public Res handleRRException(MyException e)
    {
        return Res.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(Exception.class)
    public Res handleException(Exception e)
    {
        e.printStackTrace();
        return Res.error(Error.系统异常,"服务器错误");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Res handlerNoFoundException(Exception e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return Res.error(Error.请求路径异常, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Res handleDuplicateKeyException(DataIntegrityViolationException e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return Res.error(Error.系统异常,"操作数据库出现异常：字段重复、有外键关联等");
    }

}
