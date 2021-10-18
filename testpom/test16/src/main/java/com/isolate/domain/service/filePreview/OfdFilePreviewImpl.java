package com.isolate.domain.service.filePreview;

import com.isolate.domain.model.filePreview.FileAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


/**
 * ofd 图片文件处理
 * @author kl (http://kailing.pub)
 * @since 2021/2/8
 */
@Service
public class OfdFilePreviewImpl implements FilePreview {

    @Autowired
    private PictureFilePreviewImpl pictureFilePreview;


    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        pictureFilePreview.filePreviewHandle(url,model,fileAttribute);
        return OFD_FILE_PREVIEW_PAGE;
    }
}
