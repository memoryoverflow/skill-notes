package cn.yj.notes.core.service;

import cn.yj.notes.core.entity.Knowledge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-11 11:47
 */
public interface IKnowledge extends IService<Knowledge>
{
   boolean update(Integer articleId, Integer menuId, Integer status, String title, String content, Integer menuSort, Integer menuParentId);

   Map<String,Object> save(Integer status, String title, String content, Integer menuSort, Integer menuParentId);
}
