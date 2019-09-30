package cn.yj.notes.common.fileUpload;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-28 14:13
 */
public enum FileType
{
     文章图片("articleImg","fileHandler"),
     文章内容图片("articleContentImg","fileHandler"),
     书籍资料("fileBook","fileHandler");

    private String type;
    private String beanName;

    FileType(String type, String beanName)
    {
        this.type = type;
        this.beanName = beanName;
    }

    public String getType()
    {
        return type;
    }

    public String getBeanName()
    {
        return beanName;
    }
}
