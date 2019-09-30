package cn.yj.notes.core.mapper;

import cn.yj.notes.core.dto.MenuTreeDto;
import cn.yj.notes.core.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-11 11:49
 */
public interface MenuMapper extends BaseMapper<Menu>
{
    List<MenuTreeDto> selectTree(Map<String, Object> map);
}
