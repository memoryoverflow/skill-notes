package cn.yj.notes.core.entity;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lyj.forward.generation.annotation.LColumn;
import lyj.forward.generation.annotation.LTable;
import lyj.forward.generation.enums.ColumnType;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-11 11:43
 */
@Data
@TableName(value = "tb_knowledge")
@LTable
public class Knowledge extends BaseEntity<Knowledge>
{
    private String title;

    @LColumn(mapping = ColumnType.LONGTEXT)
    private String content;

    /**
     * <br>
     * -1:草稿
     * 1：发布
     */
    @TableField(fill= FieldFill.INSERT)
    @LColumn(defaultValue = "1")
    private Integer status;

    @TableLogic
    @TableField(fill= FieldFill.INSERT)
    @LColumn(defaultValue = "0")
    private Integer deleted;

    public Knowledge(Integer id, String title, String content, Integer status)
    {
        setId(id);
        this.title = title;
        this.content = content;
        this.status = status;
        verifyProperty();
    }

    public Knowledge(String title, String content, Integer status)
    {
        if (status.equals(-1) || status.equals(1))
        {
            this.title = title;
            this.content = content;
            this.status = status;
        }
        else
        {
            throw new MyException(Error.参数异常, "参数错误");
        }
    }

    public Knowledge()
    {
    }

    public void verifyProperty()
    {
        isNull(content);
        isNull(title);
    }

    private void isNull(String content)
    {
        if (StringUtils.isBlank(content))
        {
            throw new MyException(Error.参数为空异常, "参数为空");
        }
    }

}
