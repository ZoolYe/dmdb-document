package com.zool.dmdbdocument.dao.mapper;

import com.zool.dmdbdocument.dao.dataobject.TablesBaseInfoDO;
import com.zool.dmdbdocument.dao.dataobject.TablesFieldInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : zoolye
 * @date : 2020-11-04 10:35
 * @describe :
 */
@Mapper
@Repository
public interface DmdbDocMapper {

    List<TablesBaseInfoDO> queryAllTablesBaseInfoByUser();

    List<TablesFieldInfoDO> queryTableAllFieldByTableName(@Param("tableName") String tableName);
}
