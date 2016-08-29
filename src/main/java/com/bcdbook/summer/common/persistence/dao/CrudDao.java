package com.bcdbook.summer.common.persistence.dao;

import java.util.List;

public interface CrudDao<T> extends BaseDao {

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	public int delete(String id);
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int deleteByCondition(T entity);

	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	
	/**
	 * 
	    * @Discription 根据传入的id获取实体对象
	    * @author lason       
	    * @created 2016年8月23日 下午10:02:44     
	    * @param id
	    * @return
	 */
	public T get(String id);
	/**
	 * 
	    * @Discription 根据传入的实体类中的参数,获取一个对应的实体类对象
	    * @author lason       
	    * @created 2016年8月23日 下午10:03:27     
	    * @param entity
	    * @return
	 */
	public T getByCondition(T entity);
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	/**
	 * @Description: 根据传入的数据,查询符合条件的值,不硬性排除伪删除字段
	 * @param @param entity
	 * @param @return   
	 * @return List<T>  
	 * @throws
	 * @author lason
	 * @date 2016年8月29日
	 */
	public List<T> findListOverlookDel(T entity);
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
	/**
	 * @Description: 根据条件查询出符合条件的总数据量
	 * @param @param entity
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lason
	 * @date 2016年8月29日
	 */
	public int count(T entity);
	
}
