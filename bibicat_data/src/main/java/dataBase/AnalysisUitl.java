package dataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import bean.AgentBean;

public class AnalysisUitl {
	//内容
	private static final String article_content = "div.article_content";
	//标题
	private static final String csdn_top = "h1.csdn_top";
	//阅读数量
	private static final String read_num = "button.btn-noborder";
	//端口正则
	private static final String pattern = "^\\d{1,5}$";

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
	
	/**
	 * 获取阅读数量
	 * @param doc
	 * @return
	 */
	public static String getRead_Num(Document doc){
		return doc.select(read_num).text();
	}
	
	
	/**
	 * 获取页面link并过滤
	 * @param doc
	 * @return
	 */
	public static List<String> getCsdn_Links(Document doc){
		Elements elements = doc.getAllElements();
		List<String> links = new ArrayList<String>();
		for(Element link : elements){
			String link_href = link.attr("href");
			if(link_href.indexOf("/article/details/") != -1){
				links.add(link_href);
			}
		}
		return links;
	}
	
	/**
	 * 获取代理ip及端口
	 */
	public static List<AgentBean> getIpAndPort(Document doc){
		Elements elements = doc.getElementsByTag("tr");
		List<AgentBean> agent = new ArrayList<AgentBean>();
		AgentBean agentBean = null;
		for(Element ip_port : elements){
			agentBean = new AgentBean();
			Elements ip_port_el = ip_port.getElementsByTag("td");
			for(Element ip_or_port : ip_port_el){	
				String ip_prot = ip_or_port.text();
				if(ip_prot.indexOf(".") != -1){
					agentBean.setIp(ip_prot);
				}else if(Pattern.matches(pattern, ip_prot)){
					agentBean.setProt(Integer.valueOf(ip_prot));
				}
			}
			if(null != agentBean.getIp()){
				agent.add(agentBean);
			}	
		}
		return agent;
	}
}
