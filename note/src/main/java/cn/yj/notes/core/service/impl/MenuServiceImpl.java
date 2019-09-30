package cn.yj.notes.core.service.impl;

import cn.yj.notes.core.dto.MenuTreeDto;
import cn.yj.notes.core.entity.Menu;
import cn.yj.notes.core.mapper.MenuMapper;
import cn.yj.notes.core.service.IKnowledge;
import cn.yj.notes.core.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService
{

    @Autowired
    IKnowledge iKnowledge;


    @Override
    public List<MenuTreeDto> selectTree(Map<String, Object> map)
    {
        return baseMapper.selectTree(map);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList)
    {
        for (Serializable id : idList)
        {
            remove(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId,id));
        }
        return super.removeByIds(idList);
    }
}
