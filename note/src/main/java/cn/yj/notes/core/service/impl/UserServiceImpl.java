package cn.yj.notes.core.service.impl;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import cn.yj.notes.core.entity.User;
import cn.yj.notes.core.mapper.UserMapper;
import cn.yj.notes.core.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-29 23:09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService
{

    private final Long EXPIRE_TIME = 2 * 60 * 60 * 1000L;

    @Override
    public User getUserByToken(String token)
    {
        return baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getToken, token));
    }

    @Override
    public User login(String loginName, String password)
    {
        User user = baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getLoginName, loginName));
        if (user == null)
        {
            throw new MyException(Error.登陆异常, "用户不存在");
        }

        if (!password.equals(user.getPassword()))
        {
            throw new MyException(Error.登陆异常, "密码错误");
        }
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpireTime(EXPIRE_TIME);
        updateById(user);
        return user;
    }

}
