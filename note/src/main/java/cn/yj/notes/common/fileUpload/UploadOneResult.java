package cn.yj.notes.common.fileUpload;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-31 16:51
 */
public class UploadOneResult
{
    private String uploadSuccessPath;

    public UploadOneResult(String uploadSuccessPath)
    {
        this.uploadSuccessPath = uploadSuccessPath;
    }

    public String getUploadSuccessPath()
    {
        return uploadSuccessPath;
    }

    public void setUploadSuccessPath(String uploadSuccessPath)
    {
        this.uploadSuccessPath = uploadSuccessPath;
    }
}
