package cn.yj.notes.common.utils;


import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;

import java.util.Collection;
import java.util.Map;

/**
 * <br>
 * 校验非空 并且跑出异常
 *
 * @author 永健
 * @since 2019/7/12 12:26
 */
public class FilterNull
{
    /**
     * @param obj
     * @param tip
     */
    public static void isNull(final Object obj, String tip)
    {
        if (obj == null)
        {
            throw new MyException(Error.参数为空异常,tip);
        }
    }

    public static void isBlank(final CharSequence cs, String tip)
    {
        isNull(cs, tip);
        if (cs.length() == 0)
        {
            throw new MyException(Error.参数为空异常,tip);
        }
    }

    public static void isBlank(String tip, CharSequence... cs)
    {
        for (CharSequence sequence : cs)
        {
            isBlank(sequence, tip);
        }
    }

    public static void isEmpty(Collection coll, String tip)
    {
        isNull(coll, tip);
        if (coll.isEmpty())
        {
            throw new MyException(Error.参数为空异常,tip);
        }
    }

    public static void isEmpty(Map map, String tip)
    {
        isNull(map, tip);
        if (map.isEmpty())
        {
            throw new MyException(Error.参数为空异常,tip);
        }
    }


    public static void main(String[] args)
    {
        isBlank("参数为空", "渣渣们花果山", " ", "");

    }
}
