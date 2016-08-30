package com.bcdbook.summer.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.MessageDao;
import com.bcdbook.summer.system.pojo.Message;


@Service("msgService")
public class MessageService extends CrudService<MessageDao,Message>{
	@Resource
	private MessageDao messageDao;
	
}
