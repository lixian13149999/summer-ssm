package com.bcdbook.summer.system.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.Addr;

@Repository(value="addrDao")
public interface AddrDao extends CrudDao<Addr> {

}
