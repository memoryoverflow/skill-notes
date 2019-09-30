package cn.yj.notes.common.fileUpload.file;

import cn.yj.notes.common.fileUpload.DefaultFileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  上传文章内容图片的
 * </p>
 *
 * @author 永健
 * @since 2019-07-24 15:34
 */
@Component
public class FileHandler extends DefaultFileHandler
{
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${img.server}")
    private String imgServer;

    @Value("${upload.save.prefix}")
    private String savePrefix;


    // 25M
    public static final long DEFAULT_MAX_SIZE = 5 * 5242880;

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

    @Override
    public void setDefaultMaxSize()
    {
        this.defaultUploadMaxSize = DEFAULT_MAX_SIZE;
    }
}
