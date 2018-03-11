package dataBase;

import java.io.IOException;
import java.net.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtil {

	/**
	 * 获取Document
	 * @param url
	 * @param proxy
	 * @return
	 * @throws IOException 
	 */
	public static Document getDocument(String url,Proxy proxy) throws IOException {
		Document doc = null;
		doc = Jsoup.connect(url)
				.header("Accept-Charset", "utf-8")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("accept", "*/*")
				.header("connection", "Keep-Alive")
				.header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
				.proxy(proxy)
				.timeout(40000)
				.get();
		return doc;
	}
	
	/**
	 * 获取Document
	 * @param url
	 * @param proxy
	 * @return
	 * @throws IOException 
	 */
	public static Document getDocument(String url) throws IOException {
		Document doc = null;
		doc = Jsoup.connect(url)
				.header("Accept-Charset", "utf-8")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("accept", "*/*")
				.header("connection", "Keep-Alive")
				.header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
				.timeout(40000)
				.get();
		return doc;
	}
	
	/**
	 * 获取Document
	 * @param url
	 * @param proxy
	 * @return
	 * @throws IOException 
	 */
	public static Document getDocumentProbablyProxy(String path){
		Document doc = null;
		Proxy proxy = null;
		//获取doc
		try {
			if(null != (proxy = ProxyUtil.getProxy())) {
					doc = DocumentUtil.getDocument(path,proxy);
			}else {
				doc = DocumentUtil.getDocument(path);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
