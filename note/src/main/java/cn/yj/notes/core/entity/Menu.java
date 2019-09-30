package cn.yj.notes.core.entity;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lyj.forward.generation.annotation.LColumn;
import lyj.forward.generation.annotation.LTable;
import lyj.forward.generation.annotation.LTableId;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-11 11:43
 */
@Data
@TableName(value = "tb_menu")
@LTable
public class Menu extends BaseEntity<Menu>
{
    private String name;
    @LColumn(defaultValue = "0")
    private int parentId;
    private String articleId;

    @TableField(fill = FieldFill.INSERT)
    @LColumn(defaultValue = "1")
    private Integer status;

    private Integer sort;


    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @LColumn(defaultValue = "0")
    private Integer deleted;

    public Menu()
    {
    }

    public Menu(Integer id, String name, Integer parentId, String articleId, Integer status, Integer sort)
    {
        isNull(name);
        isNull(parentId);
        isNull(id);
        isNull(status);
        this.name = name;
        this.parentId = parentId;
        this.articleId = articleId;
        this.status = status;
        this.sort = sort;
        setId(id);
    }

    public Menu(String name, Integer parentId, String articleId, Integer status, Integer sort)
    {
        isNull(name);
        isNull(parentId);
        isNull(status);
        this.name = name;
        this.parentId = parentId;
        this.articleId = articleId;
        this.status = status;
        this.sort = sort;
    }


    private void isNull(String content)
    {
        if (StringUtils.isBlank(content))
        {
            throw new MyException(Error.参数为空异常);
        }
    }

    private void isNull(Object obj)
    {
        if (null == obj)
        {
            throw new MyException(Error.参数为空异常);
        }
    }

}
