package com.bcdbook.summer.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.PowerDao;
import com.bcdbook.summer.system.pojo.Power;


@Service("powerService")
public class PowerService extends CrudService<PowerDao,Power>{
	@Resource
	private PowerDao powerDao;
}
