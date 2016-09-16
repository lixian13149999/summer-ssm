package com.bcdbook.summer.common.tools.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.tools.dao.FileDao;
import com.bcdbook.summer.common.tools.pojo.File;


@Service("fileService")
public class FileService extends CrudService<FileDao,File>{
	@Resource
	private FileDao fileDao;
	
}
