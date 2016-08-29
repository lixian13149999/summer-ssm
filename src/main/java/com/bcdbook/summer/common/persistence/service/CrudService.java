package com.bcdbook.summer.common.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.bcdbook.summer.common.persistence.Page;
import com.bcdbook.summer.common.persistence.dao.CrudDao;
import com.bcdbook.summer.common.persistence.pojo.DataEntity;


//public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T getByCondition(T entity) {
		return dao.getByCondition(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		//- TODO 分页这里还要再次处理
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	
	/**
	 * 
	 * @Description: 执行插入数据
	 * @param @param entity
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月25日
	 */
	public int add(T entity){
		entity.preInsert();
		return dao.insert(entity);
	}
	
	public int update(T entity){
		entity.preUpdate();
		return dao.update(entity);
	}
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
//	@Transactional(readOnly = false)
//	public void save(T entity) {
//		if (entity.getIsNewRecord()){
//			entity.preInsert();
//			dao.insert(entity);
//		}else{
//			entity.preUpdate();
//			dao.update(entity);
//		}
//	}
	
	/**
	 * 删除数据,根据实体类中的相关数据
	 * @param entity
	 */
	public int deleteByCondition(T entity) {
		return dao.deleteByCondition(entity);
	}
	
	/**
	 * 根据id删除相关数据
	 */
	public int delete(String id){
		return dao.delete(id);
	}
}
