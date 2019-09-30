package cn.yj.notes.common.fileUpload;

import cn.yj.notes.common.utils.DateTimeUtils;
import cn.yj.notes.common.web.BeanTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-28 14:33
 */
public class UploadEntity
{
    /**
     * 定义GB的计算常量
     */
    private static int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static int KB = 1024;

    /**
     * <br>
     */
    private List<InputStream> inputStreams;

    /**
     * <br>
     * 上传跟路径
     */
    private String uploadBasePath;


    /**
     * <br>
     * 上传的文件
     */
    private MultipartFile file;


    /**
     * <br>
     * 上传的文件
     */
    private InputStream inputStream;

    /**
     * <br>
     * 文件大小
     */
    private Integer size;


    /**
     * <br>
     * 上传的全路径包括 文件名字
     */
    private String uploadCompletePath;


    /**
     * <br>
     * 文件原名含后缀
     */
    private String fileOrginName;

    /**
     * <br>
     * 上传到服务器的文件名
     */
    private String uploadFileName;

    /**
     * <br>
     * 上传成功后的返回路径
     */
    private String uploadSuccessPath;

    /**
     * <br>
     * 批量上传成功后的路径
     */
    private String[] uploadSuccessPaths;


    private String[] uploadFailFileNames;

    /**
     * <br>
     * 文件名 file
     */
    private String fileName;

    /**
     * <br>
     * 文件后缀
     */
    private String suffix;

    /**
     * <br>
     * 自定义文件夹 方便特殊的情况
     */
    private String customFolder;

    /**
     * <br>
     * 区分文件的上传类型 不同类型不同处理类 不同文件夹
     */
    private FileType fileType;


    /**
     * <br>
     * 保存库里的前缀文件夹前缀
     */
    private String prefixFolder;


    public UploadEntity save() throws IOException
    {
        return BeanTool.getFileHandlerInstance(fileType.getBeanName()).save(this);
    }

    public String saveRpath() throws IOException
    {
        return this.save().getUploadSuccessPath();
    }

    public UploadEntity(InputStream inputStream, FileType fileType, String uploadPBaseath, String customFolder, String suffix)
    {
        isNull(inputStream, "文件流不能为空");
        isNull(suffix, "文件后缀不能为空，例如：.jpg/.txt");
        isNull(fileType, "文件类型不能为空");

        this.inputStream = inputStream;

        this.fileType = fileType;

        this.customFolder = customFolder;

        this.uploadBasePath = uploadPBaseath;

        /**
         * <br>
         * 包含 .
         */
        this.suffix = suffix;

        this.setUploadPath(fileType, false);
    }


    public UploadEntity(InputStream inputStream, FileType fileType, String suffix)
    {
        this(inputStream, fileType, null, suffix);
    }

    public UploadEntity(InputStream inputStream, FileType fileType, String customFolder, String suffix)
    {
        this(inputStream, fileType, null, customFolder, suffix);
    }

    public UploadEntity(MultipartFile file, FileType fileType, String uploadPBaseath, boolean flag)
    {
        isNull(file, "上传的文件不能为空");
        isNull(fileType, "文件类型不能为空");
        this.file = file;
        this.fileType = fileType;
        this.uploadBasePath = uploadPBaseath;
        paraFile(file, fileType, flag);
    }


    public UploadEntity(MultipartFile file, FileType fileType, String uploadPBaseath)
    {
        this(file, fileType, uploadPBaseath, false);
    }

    public UploadEntity(MultipartFile file, FileType fileType)
    {
        this(file, fileType, null, false);
    }

    /**
     * @param file
     * @param fileType
     * @param flag
     *         是否按照原名上传 默认是时间戳
     */
    public UploadEntity(MultipartFile file, FileType fileType, boolean flag)
    {
        this(file, fileType, null, flag);
    }

    private void paraFile(MultipartFile file, FileType fileType, boolean flag)
    {
        this.fileName = file.getName();
        this.fileOrginName = file.getOriginalFilename();
        try
        {
            this.inputStream = file.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        /**
         * <br>
         * 包含 .
         */
        this.suffix = this.fileOrginName.substring(this.fileOrginName.lastIndexOf("."));

        this.setUploadPath(fileType, flag);

    }


    /**
     * <br>
     * 设置文件上传路径
     */
    private synchronized void setUploadPath(FileType fileType, boolean flag)
    {

        // 存储路径： gdImg/文件类型文件夹/日期文件夹/文件名.jpg
        String dateStr = getDateFolder();


        // 文件名字
        this.uploadFileName = System.currentTimeMillis() + this.suffix;
        if (flag)
        {
            this.uploadFileName = this.fileOrginName;
        }


        // 上传目的地
        if (StringUtils.isBlank(this.uploadBasePath))
        {
            this.uploadBasePath = "";
        }

        // 上传的完整路径 此处不包含 文件名
        this.uploadCompletePath = this.uploadBasePath + "/" + fileType.getType() + "/" + dateStr;

        // 存到库里的路径
        if (StringUtils.isBlank(this.prefixFolder))
        {
            this.prefixFolder = "";
        }
        this.uploadSuccessPath = this.prefixFolder + "/" + fileType.getType() + "/" + dateStr + "/" + this.uploadFileName;
    }

    private static void isNull(String str, String tip)
    {
        if (StringUtils.isBlank(str))
        {
            throw new IllegalArgumentException(tip);
        }
    }

    private static void isNull(Object str, String tip)
    {
        if (null == str)
        {
            throw new IllegalArgumentException(tip);
        }
    }

    public static String getDateFolder()
    {
        return DateTimeUtils.formatNow(DateTimeUtils.PATTERM);
    }

    public List<InputStream> getInputStreams()
    {
        return inputStreams;
    }

    public void setInputStreams(List<InputStream> inputStreams)
    {
        this.inputStreams = inputStreams;
    }

    public String getUploadBasePath()
    {
        return uploadBasePath;
    }

    public void setUploadBasePath(String uploadBasePath)
    {
        this.uploadBasePath = uploadBasePath;
    }

    public MultipartFile getFile()
    {
        return file;
    }

    public void setFile(MultipartFile file)
    {
        this.file = file;
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public String getUploadCompletePath()
    {
        return uploadCompletePath;
    }

    public void setUploadCompletePath(String uploadCompletePath)
    {
        this.uploadCompletePath = uploadCompletePath;
    }

    public String getFileOrginName()
    {
        return fileOrginName;
    }

    public void setFileOrginName(String fileOrginName)
    {
        this.fileOrginName = fileOrginName;
    }

    public String getUploadFileName()
    {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName)
    {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadSuccessPath()
    {
        return uploadSuccessPath;
    }

    public void setUploadSuccessPath(String uploadSuccessPath)
    {
        this.uploadSuccessPath = uploadSuccessPath;
    }

    public String[] getUploadSuccessPaths()
    {
        return uploadSuccessPaths;
    }

    public void setUploadSuccessPaths(String[] uploadSuccessPaths)
    {
        this.uploadSuccessPaths = uploadSuccessPaths;
    }

    public String[] getUploadFailFileNames()
    {
        return uploadFailFileNames;
    }

    public void setUploadFailFileNames(String[] uploadFailFileNames)
    {
        this.uploadFailFileNames = uploadFailFileNames;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }


    /**
     * @描述 将文件大小转换为不同的单位
     * @date 2018/9/19 23:40
     */
    public String getSize()
    {
        // 格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        String resultSize = "";
        if (size / GB >= 1)
        {
            // 如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB   ";
        }
        else if (size / MB >= 1)
        {
            // 如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB   ";
        }
        else if (size / KB >= 1)
        {
            // 如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB   ";
        }
        else
        {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    public String getCustomFolder()
    {
        return customFolder;
    }

    public void setCustomFolder(String customFolder)
    {
        this.customFolder = customFolder;
    }

    public FileType getFileType()
    {
        return fileType;
    }

    public void setFileType(FileType fileType)
    {
        this.fileType = fileType;
    }

    public String getPrefixFolder()
    {
        return prefixFolder;
    }

    public void setPrefixFolder(String prefixFolder)
    {
        this.prefixFolder = prefixFolder;
    }
}
