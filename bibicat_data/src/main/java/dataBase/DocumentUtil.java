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
	 */
	public static Document getDocument(String url,Proxy proxy) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url)
						.proxy(proxy)
						.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 获取Document
	 * @param url
	 * @param proxy
	 * @return
	 */
	public static Document getDocument(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}
