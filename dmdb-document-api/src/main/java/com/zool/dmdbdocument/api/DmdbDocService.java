package com.zool.dmdbdocument.api;

import com.zool.dmdbdocument.api.model.TablesBaseInfoModel;
import com.zool.dmdbdocument.api.model.TablesFieldInfoModel;

import java.util.List;

/**
 * @author : zoolye
 * @date : 2020-11-04 10:29
 * @describe :
 */
public interface DmdbDocService {

    /**
     * 根据数据库用户获取全部表基本信息
     * @return
     */
    List<TablesBaseInfoModel> getAllTablesByUser();

    /**
     * 根据表名获取表字段基本信息
     * @param tableName
     * @return
     */
    List<TablesFieldInfoModel> getTableAllFieldByTableName(String tableName);

    void generateDoc();

}
