package com.zool.dmdbdocument.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zoolye
 * @date : 2020-11-04 10:37
 * @describe :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablesBaseInfoDO {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    private List<TablesFieldInfoDO> tablesFieldInfoDOList;

}
