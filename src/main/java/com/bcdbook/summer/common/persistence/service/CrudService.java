package com.bcdbook.summer.common.persistence.service;

import java.util.ArrayList;
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

	/**
	 * 根据id删除相关数据
	 */
	public int delete(String id){
		return dao.delete(id);
	}
	/**
	 * 删除数据,根据实体类中的相关数据
	 * @param entity
	 */
	public int deleteByCondition(T entity) {
		return dao.deleteByCondition(entity);
	}
	
	/**
	 * @Description: 根据实体类,修改对应的数据
	 * @param @param entity
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月29日
	 */
	public int update(T entity){
		entity.preUpdate();
		return dao.update(entity);
	}
	
	
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
	 * 根据条件查询相关数据,不直接忽略伪删除字段
	 */
//	public List<T> findListOverlookDel(T entity){
//		return dao.findListOverlookDel(entity);
//	}
	/**
	 * 查询所有的数据
	 */
	public List<T> findAllList(){
		return dao.findAllList();
	}
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		//调用分页查询前的方法
		page.prePageList();
		//为当前类设置分页查询的参数
		entity.setPage(page);
		//执行分页查询,并把值设置到page对象中
		page.setList(dao.findList(entity));
		//调用分页查询后的方法
		page.afterPageList(dao.count(entity));
		return page;
	}
}
