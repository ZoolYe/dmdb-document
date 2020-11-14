package com.zool.dmdbdocument.service;

import com.google.common.collect.Maps;
import com.zool.dmdbdocument.api.DmdbDocService;
import com.zool.dmdbdocument.api.model.TablesBaseInfoModel;
import com.zool.dmdbdocument.api.model.TablesFieldInfoModel;
import com.zool.dmdbdocument.dao.dataobject.TablesBaseInfoDO;
import com.zool.dmdbdocument.dao.dataobject.TablesFieldInfoDO;
import com.zool.dmdbdocument.dao.mapper.DmdbDocMapper;
import com.zool.dmdbdocument.service.config.FreemarkerConfig;
import com.zool.dmdbdocument.service.utils.BeanCopierUtils;
import com.zool.dmdbdocument.service.utils.FreemarkerUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zoolye
 * @date : 2020-11-04 10:30
 * @describe :
 */
@Service
public class DmdbDocServiceImpl implements DmdbDocService {


    private DmdbDocMapper dmdbDocMapper;

    private FreemarkerConfig freemarkerConfig;

    public DmdbDocServiceImpl(DmdbDocMapper dmdbDocMapper, FreemarkerConfig freemarkerConfig) {
        this.dmdbDocMapper = dmdbDocMapper;
        this.freemarkerConfig = freemarkerConfig;
    }

    /**
     * 根据数据库用户获取全部表基本信息
     *
     * @return
     */
    @Override
    public List<TablesBaseInfoModel> getAllTablesByUser() {
        List<TablesBaseInfoDO> tablesBaseInfoDOS = dmdbDocMapper.queryAllTablesBaseInfoByUser();
        return BeanCopierUtils.copyList(tablesBaseInfoDOS, TablesBaseInfoModel.class);
    }

    /**
     * 根据表名获取表字段基本信息
     *
     * @param tableName
     * @return
     */
    @Override
    public List<TablesFieldInfoModel> getTableAllFieldByTableName(String tableName) {
        List<TablesFieldInfoDO> tablesFieldInfoDOS = dmdbDocMapper.queryTableAllFieldByTableName(tableName);
        if (CollectionUtils.isEmpty(tablesFieldInfoDOS)) {
            return null;
        }
        return BeanCopierUtils.copyList(tablesFieldInfoDOS, TablesFieldInfoModel.class);
    }

    @Override
    public void generateDoc() {
        //根据yml配置文件里的用户名获取该用户下的所有表
        List<TablesBaseInfoDO> tablesBaseInfoDOS = dmdbDocMapper.queryAllTablesBaseInfoByUser();
        //根据表名循环获取表结构信息
        for (TablesBaseInfoDO tablesBaseInfo : tablesBaseInfoDOS) {
            List<TablesFieldInfoDO> tablesFieldInfoDOS = dmdbDocMapper
                    .queryTableAllFieldByTableName(tablesBaseInfo.getTableName());
            tablesBaseInfo.setTablesFieldInfoDOList(tablesFieldInfoDOS);
        }
        //准备填充到ftl模板数据
        HashMap<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("tablesInfo", tablesBaseInfoDOS);
        FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
        freemarkerUtils.generateFile(
                freemarkerConfig.getTemplateLocation(),
                freemarkerConfig.getTemplateName(),
                dataMap,
                freemarkerConfig.getOutFilePath(),
                freemarkerConfig.getOutFileName());
    }
}
