package cn.yj.notes.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lyj.forward.generation.annotation.LTableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 *
 * @author 永健
 * @since 2019/3/3 12:28
 */
@Data
public class BaseEntity<T> extends Model implements Serializable
{

    @TableId(type = IdType.AUTO)
    @LTableId(type = lyj.forward.generation.enums.IdType.AUTO)
    private Integer id;


    @TableField(fill= FieldFill.INSERT)
    private Date createTime;

    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
