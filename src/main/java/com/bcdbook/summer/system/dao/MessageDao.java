package com.bcdbook.summer.system.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.system.pojo.Message;
import com.bcdbook.summer.system.pojo.Power;

@Repository(value="msgDao")
public interface MessageDao extends CrudDao<Message> {

}
