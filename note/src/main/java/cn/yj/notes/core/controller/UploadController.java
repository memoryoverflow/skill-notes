package cn.yj.notes.core.controller;

import cn.yj.notes.common.annotation.Authentication;
import cn.yj.notes.common.fileUpload.FileType;
import cn.yj.notes.common.fileUpload.UploadEntity;
import cn.yj.notes.common.web.Res;
import cn.yj.notes.common.web.contr.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <br>
 *
 * @author 永健
 * @since 2019/3/8 15:59
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController
{


    @Value("${img.server}")
    private String imgServer;

    /**
     * 上传文章内容里面的图片
     *
     * @param file
     *
     * @return
     * @throws IOException
     */
    @Authentication
    @PostMapping("/editor/img")
    public Res editorUpload(MultipartFile file) throws IOException
    {
        String path = new UploadEntity(file, FileType.文章内容图片).saveRpath();
        return success(imgServer + path);
    }

}
