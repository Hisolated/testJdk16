package com.isolate.domain.service.filePreview;


import com.isolate.domain.model.filePreview.FileAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @author : kl
 * create : 2020-12-27 2:50 下午
 * flv文件预览处理实现
 **/
@Service
public class FlvFilePreviewImpl implements FilePreview {

    @Autowired
    private MediaFilePreviewImpl mediaFilePreview;


    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        mediaFilePreview.filePreviewHandle(url,model,fileAttribute);
        return FLV_FILE_PREVIEW_PAGE;
    }
}
