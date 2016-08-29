package com.bcdbook.summer.common.persistence;

import java.util.ArrayList;
import java.util.List;

import com.bcdbook.summer.common.config.Global;

public class Page<T> {
	private int pageNum;//当前页面(默认第一页)
	private int pageSize;//每页显示数量(默认十条数据)
	private int lineStart;
	private int totalPage;//一共多少页
	
	private int countResult;//一共多少条数据
	
	private String func;//页面用于回调的方法
	
	//存放查询出的结果的集合
	private List<T> list = new ArrayList<T>();
	
	/**
	 * @Description: 在查询页码之前设定分页相关数据
	 * 1. 设置当前页码
	 * 2. 设置每页的数据量
	 * 3. 设置分页的起始值
	 * @param    
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年8月29日
	 */
	public void prePageList(){
//		如果页码大于1,设置成传入的页码,否则设置成默认值
		setPageNum(pageNum>=1?pageNum:Global.getStartPage());
//		如果每页大小大于0,设置成当前页码,否则设置成默认值
		setPageSize(pageSize>=0?pageSize:Global.getPageSize());
		//直接设置分页的起始数值
		setLineStart((pageNum-1)*pageSize);
	}

	/**
	 * 
	 * @Description: 分页查询之后执行的对分页参数的操作
	 * 1. 设置总数据量
	 * 2. 设置总页数
	 * @param @param count   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年8月29日
	 */
	public void afterPageList(int count) {
//		如果总数据量小于0,设置成0
		setCountResult(count>=0?count:0);
//		如果中数据量大于零,判断是否能被分页值整除,如果能整除则直接设值,否则设值成值加1
//		如果数据值小于0,则直接设置成0
		setTotalPage(count>=0?(count%getPageSize()==0?count/getPageSize():count/getPageSize()+1):0);
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getLineStart() {
		return lineStart;
	}
	public void setLineStart(int lineStart) {
		this.lineStart = lineStart;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCountResult() {
		return countResult;
	}
	public void setCountResult(int countResult) {
		this.countResult = countResult;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", lineStart=" + lineStart + ", totalPage=" + totalPage
				+ ", countResult=" + countResult + ", "
				+ (func != null ? "func=" + func + ", " : "")
				+ (list != null ? "list=" + list : "") + "]";
	}

	
}
