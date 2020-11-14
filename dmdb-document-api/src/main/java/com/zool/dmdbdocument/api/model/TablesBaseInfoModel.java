package com.zool.dmdbdocument.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zoolye
 * @date : 2020-11-04 10:26
 * @describe :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablesBaseInfoModel {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;
}
