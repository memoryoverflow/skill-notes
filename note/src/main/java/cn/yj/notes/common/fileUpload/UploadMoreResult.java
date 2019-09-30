package cn.yj.notes.common.fileUpload;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-31 16:58
 */
public class UploadMoreResult
{
    /**
     * <br>
     * 上传成公的所有路径
     */
    private String[] success;

    /**
     * <br>
     * 上传失败的路径以及错误消息
     * 文件名：错误消息
     */
    private Map<String,String> error;

    private Boolean allSuccess;

    public Boolean getAllSuccess()
    {
        return allSuccess;
    }

    public void setAllSuccess(Boolean allSuccess)
    {
        this.allSuccess = allSuccess;
    }

    public UploadMoreResult(String[] success, Map<String, String> error,Boolean allSuccess)
    {
        this.success = success;
        this.error = error;
        this.allSuccess=allSuccess;
    }

    public String[] getSuccess()
    {
        return success;
    }

    public void setSuccess(String[] success)
    {
        this.success = success;
    }

    public Map<String, String> getError()
    {
        return error;
    }

    public void setError(Map<String, String> error)
    {
        this.error = error;
    }
}
