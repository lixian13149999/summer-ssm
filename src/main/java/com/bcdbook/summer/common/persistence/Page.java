package com.bcdbook.summer.common.persistence;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int pageNum = 1;//当前页面(默认第一页)
	private int pageSize = 10;//每页显示数量(默认十条数据)
	private int totalPage;//一共多少页
	
	private int countResult;//一共多少条数据
	
	private String func;//页面用于回调的方法
	
	//存放查询出的结果的集合
	private List<T> list = new ArrayList<T>();
}
