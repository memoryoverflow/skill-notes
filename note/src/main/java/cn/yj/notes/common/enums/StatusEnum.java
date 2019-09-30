package cn.yj.notes.common.enums;
/**
 * <br>
 *
 * @author 永健
 * @since 2019/2/23 15:50
 */
public enum StatusEnum
{
    SUCCESS_STATUS(0,"正常"),ERROR_STATUS(1,"异常"),草稿(-1,"草稿");

    private Integer value;
    private String msg;

    StatusEnum(Integer value, String msg)
    {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public static String getMsg(Integer i)
    {
        for (StatusEnum logEnum: StatusEnum.values())
        {
            if (i.equals(logEnum.getValue()))
            {
                return logEnum.getMsg();
            }
        }
        return null;
    }

    public static Boolean isEquals(Integer i)
    {
        for (StatusEnum logEnum: StatusEnum.values())
        {
            if (i.equals(logEnum.getValue()))
            {
                return true;
            }
        }
        return false;
    }


}