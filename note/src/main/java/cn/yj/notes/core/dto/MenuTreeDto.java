package cn.yj.notes.core.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-11 12:46
 */
@Data
public class MenuTreeDto
{
    private Integer id;

    private String name;

    private Integer pId;

    private String articleId;

    private Integer sort;

    private List<MenuTreeDto> children;
}
