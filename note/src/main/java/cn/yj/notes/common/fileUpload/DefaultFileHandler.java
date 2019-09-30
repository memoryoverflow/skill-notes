package cn.yj.notes.common.fileUpload;

import cn.yj.notes.common.exception.Error;
import cn.yj.notes.common.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-31 16:32
 */
public abstract class DefaultFileHandler extends AbstractFileHandler
{

    /**
     * <br>
     * 默认上传路径
     */
    private static final String UPLOAD_BASE_PATH = "/usr/local/img";

    /**
     * <br>
     * 默认上传文件大小
     */
    public static final Long DEFAULT_MAX_SIZE = 5242880L;


    @Override
    public void setDefaultMaxSize()
    {
        this.defaultUploadMaxSize = DEFAULT_MAX_SIZE;
    }

    @Override
    protected FileUploadSimpleExecutor executor()
    {
        return new FileUploadSimpleExecutor()
        {
            @Override
            protected Long defaultMaxSize()
            {
                return DEFAULT_MAX_SIZE;
            }
        };
    }

    @Override
    protected UploadEntity doSave(UploadEntity uploadEntity) throws IOException
    {
        if (StringUtils.isBlank(uploadEntity.getUploadBasePath()))
        {
            String path = uploadEntity.getUploadCompletePath();
            uploadEntity.setUploadCompletePath(imgBasePath() + path);
        }

        String successPath = uploadEntity.getUploadSuccessPath();
        String preFixFolder = "";
        if (StringUtils.isNotBlank(uploadEntity.getPrefixFolder()))
        {
            preFixFolder = uploadEntity.getPrefixFolder();
        }
        else
        {
            preFixFolder = saveToDataBasePrefixFolder();
        }
        uploadEntity.setUploadSuccessPath(preFixFolder + successPath);

        executor().excute(uploadEntity);
        return uploadEntity;
    }

    @Override
    protected String doSave(MultipartFile file, FileType fileType) throws IOException
    {
        UploadEntity uploadEntity = new UploadEntity(file, fileType, imgBasePath());
        uploadEntity.setPrefixFolder(saveToDataBasePrefixFolder());
        doSave(uploadEntity);
        return uploadEntity.getUploadSuccessPath();
    }

    @Override
    protected String doSave(InputStream inputStream, FileType fileType, String suffix) throws IOException
    {
        UploadEntity uploadEntity = new UploadEntity(inputStream, fileType, imgBasePath(), saveToDataBasePrefixFolder(), suffix);
        doSave(uploadEntity);
        return uploadEntity.getUploadSuccessPath();
    }

    @Override
    protected String[] doSaveBatch(List<MultipartFile> files, FileType fileType)
    {
        List<UploadEntity> uploadEntities = new ArrayList<>();
        files.forEach(multipartFile ->
        {
            uploadEntities.add(new UploadEntity(multipartFile, fileType, this.imgBasePath()));
        });

        UploadMoreResult moreResult = executor().excuteBacth(uploadEntities);
        if (moreResult.getAllSuccess())
        {
            return moreResult.getSuccess();
        }

        // 抛出错误信息

        Map<String, String> error = moreResult.getError();

        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, String>> entries = error.entrySet();
        builder.append("共有" + error.size() + "文件 上传失败 \\n");
        for (Map.Entry<String, String> entry : entries)
        {
            builder.append("文件" + entry.getKey() + "上传失败：" + entry.getValue() + "\\n");
        }
        throw new MyException(Error.文件上传异常,builder.toString());
    }
}
