package cn.yj.notes.core.controller;


import cn.yj.notes.common.annotation.Authentication;
import cn.yj.notes.common.web.Res;
import cn.yj.notes.common.web.contr.BaseController;
import cn.yj.notes.core.entity.Knowledge;
import cn.yj.notes.core.entity.Menu;
import cn.yj.notes.core.service.IKnowledge;
import cn.yj.notes.core.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
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
@RequestMapping("/know")
public class KnowledgeController extends BaseController
{
    @Autowired
    IKnowledge thisService;

    @Autowired
    IMenuService menuService;



    @GetMapping("/list")
    public Res list(Map<String, Object> param)
    {
        return success(thisService.list());
    }

    @Authentication
    @PostMapping("/save")
    public Res insertSave(@RequestParam("status") Integer status,@RequestParam("title") String title, @RequestParam("content") String content
            , @RequestParam("menuSort") Integer menuSort, @RequestParam("menuParentId") Integer menuParentId)
    {
        return success(thisService.save(status,title, content,menuSort,menuParentId));
    }

    @Authentication
    @PostMapping("/update")
    public Res editSave(@RequestParam("articleId") Integer articleId ,@RequestParam("menuId") Integer menuId,@RequestParam("status") Integer status,@RequestParam("title") String title, @RequestParam("content") String content
            , @RequestParam("menuSort") Integer menuSort, @RequestParam("menuParentId") Integer menuParentId)
    {
        return result(thisService.update(articleId,menuId,status,title,content,menuSort,menuParentId));
    }

    @GetMapping("/selectById/{id}")
    public Res selectById(@PathVariable("id") String id)
    {
        Knowledge knowledge = thisService.getById(id);
        Menu menu = null;
        if (null!=knowledge)
        {
            menu = menuService.getOne(new QueryWrapper<Menu>().lambda().eq(Menu::getArticleId, knowledge.getId()));
        }
        Map<String, Object> map = getMap();
        map.put("knowledge", knowledge);
        map.put("menu", menu);
        return success(map);
    }

    @Authentication
    @DeleteMapping("/delete/{ids}")
    public Res delete(@PathVariable("ids") String[] ids)
    {
        return result(thisService.removeByIds(Arrays.asList(ids)));
    }
}



