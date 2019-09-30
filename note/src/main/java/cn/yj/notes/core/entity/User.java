package cn.yj.notes.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lyj.forward.generation.annotation.LColumn;
import lyj.forward.generation.annotation.LTable;
import lyj.forward.generation.annotation.LTableId;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-29 23:04
 */
@Data
@TableName("tb_user")
@LTable
public class User extends BaseEntity<User>
{

    private String name;
    private String loginName;
    private String password;

    @TableField(strategy = FieldStrategy.IGNORED)
    private String token;
    @LColumn(defaultValue = "7200000")
    private long tokenExpireTime;


}
