package com.zool.dmdbdocument.service.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author : zoolye
 * @date : 2020-11-04 15:59
 * @describe :
 */
public class FreemarkerUtils {

    private Template getTemplate(String fileDir, String name) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), fileDir);
        try {
            return configuration.getTemplate(name);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public void generateFile(String fileDir, String templateName, Map root, String outDir, String outFileName) {
        FileWriter out = null;
        try {
            out = new FileWriter(new File(outDir, outFileName));
            Template temp = this.getTemplate(fileDir, templateName);
            temp.process(root, out);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
