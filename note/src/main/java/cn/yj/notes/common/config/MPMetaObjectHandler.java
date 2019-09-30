package cn.yj.notes.common.config;

import cn.yj.notes.common.enums.StatusEnum;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-30 15:15
 */
public class MPMetaObjectHandler implements MetaObjectHandler
{
    @Override
    public void insertFill(MetaObject metaObject)
    {
        this.setInsertFieldValByName("create_time", new Date(), metaObject);
        this.setInsertFieldValByName("update_time", new Date(), metaObject);
        this.setInsertFieldValByName("status", 1, metaObject);
        this.setInsertFieldValByName("deleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject)
    {
        this.setUpdateFieldValByName("update_time", new Date(), metaObject);
    }
}
