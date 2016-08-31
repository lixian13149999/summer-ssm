//package com.bcdbook.summer.system.service;
//
//public class AddrService {
//
//}
package com.bcdbook.summer.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.system.dao.AddrDao;
import com.bcdbook.summer.system.pojo.Addr;


@Service("addrService")
public class AddrService extends CrudService<AddrDao,Addr>{
	@Resource
	private AddrDao addrDao;
	
}
