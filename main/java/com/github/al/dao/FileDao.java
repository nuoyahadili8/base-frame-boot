package com.github.al.dao;

import com.github.al.entity.File;

import java.util.List;

/**
 * 地产附件表
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-17 11:52:01
 */
public interface FileDao extends BaseDao<File> {

    List<File> getByRelationId(String relationId);

    List<File> getFileList(String imgUUID);

    List<File> getFileListByUUID(List<String> list);

    void deleteByRelationId(String relationId);
}
