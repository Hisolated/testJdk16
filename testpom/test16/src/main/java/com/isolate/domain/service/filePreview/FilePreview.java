package com.isolate.domain.service.filePreview;

import org.springframework.ui.Model;
import com.isolate.domain.model.filePreview.FileAttribute;
/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/10/18 10:58
 */
public interface FilePreview {


    String FLV_FILE_PREVIEW_PAGE = "flv";
    String PDF_FILE_PREVIEW_PAGE = "pdf";
    String PPT_FILE_PREVIEW_PAGE = "ppt";
    String COMPRESS_FILE_PREVIEW_PAGE = "compress";
    String MEDIA_FILE_PREVIEW_PAGE = "media";
    String PICTURE_FILE_PREVIEW_PAGE = "picture";
    String TIFF_FILE_PREVIEW_PAGE = "tiff";
    String OFD_FILE_PREVIEW_PAGE = "ofd";
    String OFFICE_PICTURE_FILE_PREVIEW_PAGE = "officePicture";
    String TXT_FILE_PREVIEW_PAGE = "txt";
    String CODE_FILE_PREVIEW_PAGE = "code";
    String EXEL_FILE_PREVIEW_PAGE = "html";
    String XML_FILE_PREVIEW_PAGE = "xml";
    String MARKDOWN_FILE_PREVIEW_PAGE = "markdown";
    String NOT_SUPPORTED_FILE_PAGE = "fileNotSupported";

    String filePreviewHandle(String url, Model model, FileAttribute fileAttribute);
}
