package cn.yj.notes.common.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-05 17:58
 */
public enum Error
{
    操作失败(-1, "操作失败"),
    系统异常(1, "系统异常"),
    参数为空异常(2, "参数为空异常"),
    参数异常(14, "参数异常"),
    参数绑定异常(3, "参数绑定异常"),
    文件上传异常(4, "文件上传异常"),
    请求方式错误(5, "请求方式不支持"),
    请求路径异常(6, "请检查url是否正确"),
    权限异常(7, "权限不足"),
    登陆异常(9, "登陆异常"),
    数据解析异常(11, "数据解析异常"),
    Http接口响应异常(12, "数据解析异常"),
    请求凭证有误(13, "请求凭证有误");

    private int code;
    private String errMsg;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getErrMsg()
    {
        return errMsg;
    }

    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    Error(int code, String errMsg)
    {
        this.code = code;
        this.errMsg = errMsg;
    }
}
