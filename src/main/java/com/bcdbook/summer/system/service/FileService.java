package com.bcdbook.summer.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.FileDao;
import com.bcdbook.summer.system.pojo.File;


@Service("fileService")
public class FileService extends CrudService<FileDao,File>{
	@Resource
	private FileDao fileDao;
	
}
