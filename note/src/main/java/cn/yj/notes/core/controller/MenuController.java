package cn.yj.notes.core.controller;


import cn.yj.notes.common.annotation.Authentication;
import cn.yj.notes.common.enums.StatusEnum;
import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.web.Res;
import cn.yj.notes.common.web.contr.BaseController;
import cn.yj.notes.core.entity.Menu;
import cn.yj.notes.core.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 永健
 * @since 2019-02-23
 */

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController
{
    @Autowired
    IMenuService thisService;

    @GetMapping("/tree")
    public Res list(@RequestParam Map<String, Object> param)
    {
        return success(thisService.selectTree(param));
    }


    /**
     * 查询 parentId 不为0的菜单
     *
     * @return
     */
    @GetMapping("/listNePidNull")
    public Res listNePidNull(String name)
    {
        List<Menu> list = null;
        if (StringUtils.isBlank(name))
        {
            list = thisService.list(new QueryWrapper<Menu>().lambda().ne(Menu::getParentId, 0).ne(Menu::getStatus, StatusEnum.草稿.getValue()));
        }
        else
        {
            list = thisService.list(new QueryWrapper<Menu>().lambda().ne(Menu::getParentId, 0).ne(Menu::getStatus, StatusEnum.草稿.getValue()).like(Menu::getName, name));
        }
        return success(list);
    }

    /**
     * 页面做新增修改时候获取的数据 只获取文件夹
     *
     * @param excludeId
     *
     * @return
     */
    @GetMapping("/list")
    public Res list(Integer excludeId)
    {
        List<Menu> list = null;
        if (null == excludeId)
        {
            list = thisService.list(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0).ne(Menu::getStatus, StatusEnum.草稿.getValue()));
        }
        else
        {
            list = thisService.list(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0).ne(Menu::getId, excludeId).ne(Menu::getStatus, StatusEnum.草稿.getValue()));
        }
        return success(list);
    }

    @Authentication
    @PostMapping("/save")
    public Res save(Menu menu)
    {
        if (StringUtils.isBlank(menu.getName()) || menu.getName().length() > 50)
        {
            return error(Error.参数异常, "名字为空/名字太长");
        }
        menu.setCreateTime(new Date());
        return success(thisService.save(menu));
    }

    @Authentication
    @PutMapping("/update")
    public Res update(Menu menu)
    {
        if (StringUtils.isBlank(menu.getName()) || menu.getName().length() > 50)
        {
            return error(Error.参数异常, "名字为空/名字太长");
        }
        return success(thisService.updateById(menu));
    }


    @Authentication
    @DeleteMapping("/delete/{ids}")
    public Res delete(@PathVariable("ids") String[] ids)
    {
        return result(thisService.removeByIds(Arrays.asList(ids)));
    }
}

