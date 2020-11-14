package com.zool.dmdbdocument.web;

import com.zool.dmdbdocument.api.DmdbDocService;
import com.zool.dmdbdocument.api.model.TablesBaseInfoModel;
import com.zool.dmdbdocument.api.model.TablesFieldInfoModel;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : zoolye
 * @date : 2020-11-04 10:23
 * @describe :
 */
@RestController
@RequestMapping("/dmdbDoc")
public class DmdbDocController {

    private DmdbDocService dmdbDocService;

    public DmdbDocController(DmdbDocService dmdbDocService) {
        this.dmdbDocService = dmdbDocService;
    }

    @GetMapping("/getAllTablesByUser")
    public List<TablesBaseInfoModel> getAllTablesByUser() {
        return dmdbDocService.getAllTablesByUser();
    }

    @GetMapping("/getTableAllFieldByTableName")
    public List<TablesFieldInfoModel> getTableAllFieldByTableName(@RequestParam("tableName") String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }
        return dmdbDocService.getTableAllFieldByTableName(tableName);
    }

    @GetMapping("/generateDoc")
    public String generateDoc() {
        dmdbDocService.generateDoc();
        return "OK";
    }

}
