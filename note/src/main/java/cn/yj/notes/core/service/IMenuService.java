package cn.yj.notes.core.service;

import cn.yj.notes.core.dto.MenuTreeDto;
import cn.yj.notes.core.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 永健
 * @since 2019-02-23
 */
public interface IMenuService extends IService<Menu> {
    List<MenuTreeDto> selectTree(Map<String, Object> map);
}
