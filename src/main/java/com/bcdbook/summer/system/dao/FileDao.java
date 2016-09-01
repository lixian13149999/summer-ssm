package com.bcdbook.summer.system.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.File;

@Repository(value="fileDao")
public interface FileDao extends CrudDao<File> {

}
