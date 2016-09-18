package com.bcdbook.summer.common.tools.service;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.tools.dao.MailDao;
import com.bcdbook.summer.common.tools.pojo.Mail;

@Service("mailService")
public class MailService extends CrudService<MailDao,Mail> {
	
}
