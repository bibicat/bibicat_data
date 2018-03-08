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
				.timeout(30000)
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
				.get();
		return doc;
	}
}
