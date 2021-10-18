package com.isolate.domain.service.filePreview;


import com.isolate.application.service.file.FilePreviewHandler;
import com.isolate.domain.model.filePreview.FileAttribute;
import com.isolate.domain.model.filePreview.ReturnResponse;
import com.isolate.domain.service.CompressFileReader;
import com.isolate.infrastructure.general.config.ConfigConstants;
import com.isolate.infrastructure.general.toolkit.DownloadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

/**
 * Created by kl on 2018/1/17.
 * Content :处理压缩包文件
 */
@Service
public class CompressFilePreviewImpl implements FilePreview {

    @Autowired
    private FilePreviewHandler fileHandlerService;
    @Autowired
    private CompressFileReader compressFileReader;
    @Autowired
    private OtherFilePreviewImpl otherFilePreview;
    

    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        String fileName=fileAttribute.getName();
        String suffix=fileAttribute.getSuffix();
        String fileTree = null;
        // 判断文件名是否存在(redis缓存读取)
        if (!StringUtils.hasText(fileHandlerService.getConvertedFile(fileName))  || !ConfigConstants.isCacheEnabled()) {
            ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, fileName);
            if (response.isFailure()) {
                return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
            }
            String filePath = response.getContent();
            if ("zip".equalsIgnoreCase(suffix) || "jar".equalsIgnoreCase(suffix) || "gzip".equalsIgnoreCase(suffix)) {
                fileTree = compressFileReader.readZipFile(filePath, fileName);
            } else if ("rar".equalsIgnoreCase(suffix)) {
                fileTree = compressFileReader.unRar(filePath, fileName);
            } else if ("7z".equalsIgnoreCase(suffix)) {
                fileTree = compressFileReader.read7zFile(filePath, fileName);
            }
            if (fileTree != null && !"null".equals(fileTree) && ConfigConstants.isCacheEnabled()) {
                fileHandlerService.addConvertedFile(fileName, fileTree);
            }
        } else {
            fileTree = fileHandlerService.getConvertedFile(fileName);
        }
        if (fileTree != null && !"null".equals(fileTree)) {
            model.addAttribute("fileTree", fileTree);
            return COMPRESS_FILE_PREVIEW_PAGE;
        } else {
            return otherFilePreview.notSupportedFile(model, fileAttribute, "压缩文件类型不受支持，尝试在压缩的时候选择RAR4格式");
        }
    }
}
