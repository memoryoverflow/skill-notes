package cn.yj.notes.common.web.contr;


import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.web.BeanTool;
import cn.yj.notes.common.web.Res;
import cn.yj.notes.common.web.auth.ReqAuth;
import cn.yj.notes.core.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 永健
 * Date 2018/12/6 9:29
 */
public class BaseController
{
    public Map<String, Object> getMap()
    {
        return new HashMap<>();
    }

    /**
     * 返回成功
     */
    protected Res success()
    {
        return Res.success();
    }

    /**
     * 返回失败消息
     */
    protected Res error(Error error)
    {
        return Res.error(error);
    }

    protected Res error()
    {
        return Res.error(Error.操作失败);
    }

    /**
     * 返回失败消息
     */
    protected Res error(Error error, String msg)
    {
        return Res.error(error, msg);
    }

    /**
     * 返回成功消息
     */
    protected Res successMsg(String message)
    {
        return Res.success(message);
    }

    /**
     * 返回成功消息
     */
    protected Res success(Object data)
    {
        return Res.success(data);
    }


    /**
     * 根据修改搜影响的行数返回结果
     */
    protected Res result(boolean flag)
    {
        return flag == true ? success() : error(Error.操作失败);
    }

    protected Res result(boolean flag, String msg)
    {
        return flag == true ? successMsg(msg) : error(Error.操作失败);
    }


    protected Res result(int row)
    {
        return row > 0 ? success() : error(Error.操作失败);
    }

    protected Res result(int row, String msg)
    {
        return row > 0 ? successMsg(msg) : error(Error.操作失败);
    }

    protected User getUser()
    {
        ReqAuth reqAuth = BeanTool.getInstance(ReqAuth.class);
        return reqAuth.getUser();
    }
}
