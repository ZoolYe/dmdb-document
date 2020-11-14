package com.zool.dmdbdocument.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zoolye
 * @date : 2020-11-04 11:58
 * @describe :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablesFieldInfoModel {

    private String columnName;

    private String comments;

    private String dataType;

    private String dataLength;

    private String nullable;

    private String dataDefault;

    private String key;
}
