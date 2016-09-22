package com.bcdbook.summer.wechat.pojo.message.resp;

import java.util.List;

/**
 * @Description: 用于回复的文图消息
 * @author lason
 * @date 2016年9月20日
 */
public class NewsMessage extends WechatRespMessage {
	private int ArticleCount;//图文消息个数，限制为10条以内
	private List<Article> Articles;//单个文图消息的基类
	
	//空参构造
	public NewsMessage() {
		super();
	}
	//全参构造
	public NewsMessage(int articleCount, List<Article> articles) {
		super();
		ArticleCount = articleCount;
		Articles = articles;
	}
	
	//getter and setter
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
	
	//toString
	@Override
	public String toString() {
		return "NewsMessage [ArticleCount=" + ArticleCount + ", "
				+ (Articles != null ? "Articles=" + Articles : "") + "]";
	}
}
