package com.bcdbook.summer.common.tools.dao;

import org.springframework.stereotype.Repository;

import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.common.tools.pojo.Mail;

@Repository(value="emailDao")
public interface MailDao extends CrudDao<Mail> {

}
