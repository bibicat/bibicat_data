package ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Service.BookService;
import bean.BookArticleBean;
import bean.BookBean;
import dataBase.DocumentUtil;
import dataBase.DruidUtil;

public class D3zwwBookServiceImpl implements BookService{
	
	public static final String start_page = "http://www.d3zww.com/xs/quanbu-default-0-0-0-0-0-0-";
	
	public List<? extends BookBean> getBook_links(String path) {
		//获取doc
		Document doc = DocumentUtil.getDocumentProbablyProxy(path);
		//解析dom
		Elements elements = doc.select("div.sitebox").select("dl");
		if(null != elements) {
			List<BookBean> BookBeans = new ArrayList<BookBean>();
			BookBean bb = null;
			for(Element element : elements) {
				bb = new BookBean();
				Elements els = element.select("dt").select("a");
				//链接
				String link = els.attr("href");
				bb.setLink(link);
				//封面图
				String image_url = els.select("img").attr("src");
				bb.setImage_url(image_url);
				//书名
				String title = element.select("dd").select("h3").select("a").text();
				bb.setTitle(title);
				//作者
				String author = element.select("dd.book_other").select("span").text();
				bb.setAuthor(author);
				//简介
				String introduce = element.select("dd.book_des").html();
				bb.setIntroduce(introduce);
				BookBeans.add(bb);
			}
			return BookBeans;
		}
		return null;
	}

	public List<? extends BookBean> getChapter_links(List<? extends BookBean> BookBeans) {
		for(BookBean bb: BookBeans) {
			//保存书信息,并查询是否有入库
			Integer book_id = DruidUtil.selectBook(bb.getTitle());
			if(null == book_id) {
				book_id = DruidUtil.insertBook(bb);
			}

			//解析内容
			String link = bb.getLink();
			String article_link = null;
			
			Document doc = DocumentUtil.getDocumentProbablyProxy(link);
			
			Elements bookArticles = null;
			BookArticleBean bookArticleBean = null;
			bookArticles = doc.select("div.book_list").select("ul").select("li").select("a");
			
			//如果章节数量一致则跳过(未更新)
			Integer data_total = DruidUtil.selectArticleTotal(book_id);
			Integer web_total = bookArticles.size();
			if(data_total.intValue() == web_total.intValue()) {
				break;
			}else {	//否则更新章节
				int i = data_total.intValue();
				int j = web_total.intValue();
				Element bookArticle = null;
				for(;i < j;i++) {
					bookArticle = bookArticles.get(i);
					
					bookArticleBean = new BookArticleBean();
					//添加id
					bookArticleBean.setBook_id(book_id);
					//章节链接
					article_link = link+bookArticle.attr("href");
					bookArticleBean.setBook_article_link(article_link);
					//章节标题
					String article_title = bookArticle.text();
					bookArticleBean.setBook_article_title(article_title);
					//章节内容
					Document article = DocumentUtil.getDocumentProbablyProxy(article_link);
					System.out.println(article_link);
					String article_content = article.select("div#htmlContent").html();
					bookArticleBean.setBook_article_content(article_content);
					//保存章节
					DruidUtil.insertBookArticle(bookArticleBean);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
