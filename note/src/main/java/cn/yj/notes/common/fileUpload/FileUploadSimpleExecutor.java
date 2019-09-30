package cn.yj.notes.common.fileUpload;

import cn.yj.notes.common.exception.MyException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-28 14:50
 */
public abstract class FileUploadSimpleExecutor extends FileUploadExecutor
{

    private static Logger log = LoggerFactory.getLogger(FileUploadSimpleExecutor.class);

    @Override
    public void excute(UploadEntity uploadEntity) throws IOException
    {
        if (uploadEntity.getFile() != null)
        {
            assertAllowed(uploadEntity.getFile());
        }
        fileExits(uploadEntity.getUploadCompletePath());
        File file = null;
        FileOutputStream outs = null;
        file = new File(uploadEntity.getUploadCompletePath() + "/" + uploadEntity.getUploadFileName());
        outs = new FileOutputStream(file);
        InputStream stream = uploadEntity.getInputStream();

        uploadEntity.setSize(IOUtils.copy(stream, outs));

        if (stream != null)
        {
            stream.close();
        }
        outs.close();
    }

    @Override
    public UploadMoreResult excuteBacth(List<UploadEntity> uploadEntitys)
    {
        String[] success = {};
        String[] failPaths = {};
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < uploadEntitys.size(); i++)
        {
            UploadEntity entity = uploadEntitys.get(i);
            try
            {
                this.excute(entity);
                success[i] = entity.getUploadSuccessPath();
            }
            catch (MyException e)
            {
                log.info("文件上传异常：" + e.getMsg());
                failPaths[i] = entity.getFileOrginName();

                hashMap.put(entity.getFileOrginName(), e.getMsg());
            }
            catch (IOException e)
            {
                log.info("文件上传异常：" + e);
                failPaths[i] = entity.getFileOrginName();
                hashMap.put(entity.getFileOrginName(), "服务器异常！");
            }
        }
        Boolean all = true;
        if (success.length != uploadEntitys.size())
        {
            all = false;
        }

        return new UploadMoreResult(success, hashMap, all);
    }

    protected void fileExits(String path)
    {
        File fileNew = new File(path);
        if (!fileNew.exists())
        {
            fileNew.mkdirs();
        }
    }
}
