package cn.yj.notes;

import cn.yj.notes.core.entity.User;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-30 10:59
 */
public class Test
{
    @org.junit.Test
    public void y()
    {
        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();

        for (Field field : fields)
        {
            System.out.println(field.getType() == List.class);
        }
    }
}
