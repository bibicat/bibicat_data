package dataBase;

import org.jsoup.nodes.Document;

public class AnalysisUitl {
	//内容
	private static final String article_content = "div.article_content";
	//标题
	private static final String csdn_top = "h1.csdn_top";

	/**
	 * 获取博客内容
	 * @param url
	 * @return
	 */
	public static String getArticle_Content(Document doc) {
		return doc.select(article_content).toString();
	}
	
	/**
	 * 获取标题内容
	 * @param url
	 * @return
	 */
	public static String getCsdn_Top(Document doc) {
		return doc.select(csdn_top).text();
	}
}
