package cn.yj.notes.common.web;

import cn.yj.notes.common.exception.Error;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-24 10:17
 */
@Data
public class Res
{
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;
    private static final String SUCCESS_R = "操作成功！";
    private static final String ERROR_R = "操作失败！";


    private int code;
    private String msg;
    private Object data;

    public Res() {}

    public static Res success()
    {
        Res res = new Res();
        res.setCode(SUCCESS_CODE);
        res.setMsg(SUCCESS_R);
        return res;
    }

    public static Res success(Object data)
    {
        Res res = new Res();
        res.setCode(SUCCESS_CODE);
        res.setMsg(SUCCESS_R);
        res.setData(data);
        return res;
    }

    public static Res success(String msg)
    {
        Res res = new Res();
        res.setCode(SUCCESS_CODE);
        res.setMsg(msg);
        return res;
    }


    public static Res error(Error error, String tip)
    {
        if (tip != null || StringUtils.isNotBlank(tip))
        {
            tip = "，" + tip;
        }
        else
        {
            tip = "";
        }
        return error(error.getCode(), error.getErrMsg() + tip);
    }

    public static Res error(Error error)
    {
        return error(error, null);
    }


    public static Res error(int code, String msg)
    {
        Res res = new Res();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
}
