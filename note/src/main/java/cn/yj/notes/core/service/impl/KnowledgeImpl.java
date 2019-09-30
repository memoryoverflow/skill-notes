package cn.yj.notes.core.service.impl;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import cn.yj.notes.core.entity.Knowledge;
import cn.yj.notes.core.entity.Menu;
import cn.yj.notes.core.mapper.KnowledgeMapper;
import cn.yj.notes.core.service.IKnowledge;
import cn.yj.notes.core.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-11 11:47
 */
@Service
@Transactional
public class KnowledgeImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledge
{

    @Autowired
    IMenuService menuService;


    @Override
    public Knowledge getById(Serializable id)
    {
        return super.getById(id);
    }

    @Override
    public boolean update(Integer articleId, Integer menuId, Integer status, String title, String content, Integer menuSort, Integer menuParentId)
    {
        Knowledge knowledge = new Knowledge(articleId, title, content, status);
        int update = baseMapper.updateById(knowledge);
        if (update <= 0)
        {
            throw new MyException(Error.参数异常, "参数错误");
        }
        knowledge.setUpdateTime(new Date());

        Menu menu = new Menu(menuId, title, menuParentId, knowledge.getId().toString(), status, menuSort);
        menuService.updateById(menu);
        return true;
    }


    @Override
    public Map<String, Object> save(Integer status, String title, String content, Integer menuSort, Integer menuParentId)
    {
        Knowledge knowledge = new Knowledge(title, content, status);
        knowledge.setCreateTime(new Date());
        baseMapper.insert(knowledge);

        Menu menu = new Menu(title, menuParentId, knowledge.getId().toString(), status, menuSort);
        menuService.save(menu);

        Map<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("id", knowledge.getId());
        hashMap.put("menuId", menu.getId());
        return hashMap;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList)
    {
        idList.forEach(id -> {
            removeById(id);
            menuService.remove(new QueryWrapper<Menu>().lambda().eq(Menu::getArticleId, id));
        });
        return true;
    }
}
