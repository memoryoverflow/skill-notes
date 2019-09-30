package cn.yj.notes.core.controller;

import cn.yj.notes.common.annotation.Authentication;
import cn.yj.notes.common.web.Res;
import cn.yj.notes.common.web.contr.BaseController;
import cn.yj.notes.core.entity.User;
import cn.yj.notes.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-30 14:52
 */
@RestController
public class LoginController extends BaseController
{
    @Autowired
    private IUserService iUserService;

    @PostMapping("/login")
    public Res login(@RequestParam String loginName, @RequestParam String password)
    {
        User user = iUserService.login(loginName, password);
        Map<String, Object> map = getMap();
        map.put("token", user.getToken());
        return success(map);
    }


    @Authentication
    @PostMapping("/logout")
    public void test()
    {
        User user = getUser();
        user.setToken(null);
        iUserService.updateById(user);
    }
}
