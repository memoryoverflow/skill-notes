package cn.yj.notes.common.fileUpload.file;

import cn.yj.notes.common.fileUpload.DefaultFileHandler;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-08-02 13:26
 */
public class FileBookHandler extends DefaultFileHandler
{
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${img.server}")
    private String imgServer;

    @Value("${upload.save.prefix}")
    private String savePrefix;


    @Override
    protected String saveToDataBasePrefixFolder()
    {
        return savePrefix;
    }


    @Override
    protected String imgBasePath()
    {
        return uploadPath;
    }
}
