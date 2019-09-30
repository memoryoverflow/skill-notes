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
 * @since 2019-05-31 16:27
 */
public interface FileServiceHandler
{
    public String save(MultipartFile file, FileType fileType) throws IOException;

    public UploadEntity save(UploadEntity uploadEntity) throws IOException;

    public  String save(InputStream stream, FileType fileType, String suffix) throws IOException;
    public  String[] saveBatch(List<MultipartFile> files, FileType fileType);
}
