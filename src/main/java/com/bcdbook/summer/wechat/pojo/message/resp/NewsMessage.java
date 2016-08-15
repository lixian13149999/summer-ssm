package com.bcdbook.summer.wechat.pojo.message.resp;

import java.util.List;

/**
 * 
 * @Title: NewsMessage.java
 * @Description: 文图相应消息 多文图消息 
 * 			单文图的时候Articles只放一个就行了
 * @author lason
 * @created 2016年5月25日 上午9:54:33
 */
public class NewsMessage extends BaseMessage {
	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int ArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
