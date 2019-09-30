package cn.yj.notes.common.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-23 19:32
 */
public class MyException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private String msg;
    private Error error;
    private int code = 1;

    public MyException(Error error)
    {
        super(error.getErrMsg());
        this.code = error.getCode();
        this.msg = error.getErrMsg();
    }

    public MyException(Error error, String msg)
    {
        super(msg);
        this.code = error.getCode();
        this.msg = error.getErrMsg() + "," + msg;
    }


    public String getMsg()
    {
        return msg;
    }

    public MyException setMsg(String msg)
    {
        this.msg = msg;
        return this;
    }

    public int getCode()
    {
        return code;
    }

    public MyException setCode(int code)
    {
        this.code = code;
        return this;
    }

    public Error getError()
    {
        return error;
    }

    public void setError(Error error)
    {
        this.code = error.getCode();
        this.msg = error.getErrMsg();
        this.error = error;

    }
}
