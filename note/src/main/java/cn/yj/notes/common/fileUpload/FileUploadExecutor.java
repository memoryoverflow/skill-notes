package cn.yj.notes.common.fileUpload;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.yj.notes.common.utils.FilterNull.isNull;


/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-31 16:39
 */
public abstract class FileUploadExecutor
{
    /**
     * 默认大小 5M //字节
     */
    public static final long DEFAULT_MAX_SIZE = 5242880;


    protected Long defaultMaxSize(){
        return DEFAULT_MAX_SIZE;
    }

    /**
     * 文件大小校验
     *
     * @param file
     * @throws
     */
    protected void assertAllowed(MultipartFile file) throws MyException
    {
        isNull(defaultMaxSize(),"设置默认上传文件大小不能为空");
        long size = file.getSize();
        if (defaultMaxSize() != -1 && size > defaultMaxSize()) {
            throw new MyException(Error.文件上传异常,"文件大小超出范围，最大值:" + defaultMaxSize() / 1024 /1024 + "/MB");
        }
    }

    public abstract void excute(UploadEntity uploadEntity) throws MyException, IOException;
    public abstract UploadMoreResult excuteBacth(List<UploadEntity> uploadEntitys) throws MyException,IOException;
}
