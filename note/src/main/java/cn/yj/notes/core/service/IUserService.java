package cn.yj.notes.core.service;

import cn.yj.notes.core.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-29 23:08
 */
public interface IUserService extends IService<User>
{
    User getUserByToken(String token);

    User login(String loginName, String password);
}
