package com.isolate.domain.model.filePreview;


import com.isolate.infrastructure.general.config.ConfigConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kl on 2018/1/17.
 * Content :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileAttribute {

    private FileType type;
    private String suffix;
    private String name;
    private String url;
    private String fileKey;
    private String officePreviewType = ConfigConstants.getOfficePreviewType();
}
