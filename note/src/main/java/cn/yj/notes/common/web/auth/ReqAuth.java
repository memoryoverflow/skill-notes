package cn.yj.notes.common.web.auth;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import cn.yj.notes.common.utils.ServletUtils;
import cn.yj.notes.core.entity.User;
import cn.yj.notes.core.service.IUserService;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-29 22:57
 */
public class ReqAuth implements Auth
{

    private final static String AUTH_KEY = "auth";

    private IUserService userService;

    public ReqAuth(IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public String getToken(String headerName)
    {
        String header = ServletUtils.getRequest().getHeader(headerName);
        if (header == null || header.equals(""))
        {
            throw new MyException(Error.请求凭证有误,"!@_@");
        }
        return header;
    }

    public User getUser(){
        return userService.getUserByToken(getToken(AUTH_KEY));
    }

    @Override
    public boolean isReqToken()
    {
        User user = userService.getUserByToken(getToken(AUTH_KEY));
        if (user == null)
        {
            throw new MyException(Error.请求凭证有误,"!@_@");
        }

        long time = user.getUpdateTime().getTime();

        long timeMillis = System.currentTimeMillis();

        if (timeMillis - time > user.getTokenExpireTime())
        {
            throw new MyException(Error.请求凭证有误,"会话过期");
        }
        user.setUpdateTime(new Date());
        userService.updateById(user);
        return true;
    }
}
