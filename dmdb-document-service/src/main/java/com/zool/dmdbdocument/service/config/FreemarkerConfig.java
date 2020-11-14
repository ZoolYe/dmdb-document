package com.zool.dmdbdocument.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zoolye
 * @date : 2020-11-14 21:52
 * @describe : freemarker模板配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "freemarker")
public class FreemarkerConfig {

    private String templateLocation;

    private String templateName;

    private String outFilePath;

    private String outFileName;

}
