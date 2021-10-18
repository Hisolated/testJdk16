package com.isolate.domain.service.filePreview;

import com.isolate.domain.model.filePreview.FileAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @author kl (http://kailing.pub)
 * @since 2020/12/25
 */
@Service
public class XmlFilePreviewImpl implements FilePreview {

    @Autowired
    private SimTextFilePreviewImpl simTextFilePreview;

    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        simTextFilePreview.filePreviewHandle(url, model, fileAttribute);
        return XML_FILE_PREVIEW_PAGE;
    }
}
