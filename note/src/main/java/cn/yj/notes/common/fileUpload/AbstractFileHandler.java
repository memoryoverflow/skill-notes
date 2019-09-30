package cn.yj.notes.common.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-31 16:29
 */
public abstract class AbstractFileHandler implements FileServiceHandler
{

    /**
     * 默认大小 5M //字节
     */
    public static final long DEFAULT_MAX_SIZE = 5242880;

    protected Long defaultUploadMaxSize;

    protected abstract void setDefaultMaxSize();

    protected abstract FileUploadSimpleExecutor executor();

    @Override
    public String save(MultipartFile file, FileType fileType) throws IOException
    {

        return doSave(file, fileType);
    }

    @Override
    public UploadEntity save(UploadEntity uploadEntity) throws IOException
    {
        return doSave(uploadEntity);
    }

    protected abstract UploadEntity doSave(UploadEntity uploadEntity) throws IOException;

    @Override
    public String[] saveBatch(List<MultipartFile> files, FileType fileType)
    {
        return doSaveBatch(files, fileType);
    }

    @Override
    public String save(InputStream inputStream, FileType fileType, String suffix) throws IOException
    {
        return doSave(inputStream, fileType, suffix);
    }
    protected abstract String doSave(MultipartFile file, FileType fileType) throws IOException;

    protected abstract String doSave(InputStream inputStream, FileType fileType, String suffix) throws IOException;

    protected abstract String[] doSaveBatch(List<MultipartFile> files, FileType fileType);

    /**
     * <br>
     * 文件上传的跟路径
     */
    protected abstract String imgBasePath();

    /**
     * <br>
     * 保存到库里的统一前缀文件夹
     */
    protected String saveToDataBasePrefixFolder()
    {
        return "";
    }
}
