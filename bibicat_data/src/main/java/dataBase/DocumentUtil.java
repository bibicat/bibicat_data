package dataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
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
	public static Document getDocument(String url,Proxy proxy){
		Document doc = null;
		while(null == doc) {
			try {
				doc = Jsoup.connect(url)
						.header("Accept-Charset", "utf-8")
						.header("Content-Type", "application/x-www-form-urlencoded")
						.header("accept", "*/*")
						.header("connection", "Keep-Alive")
						.header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
						.proxy(proxy)
						.timeout(30000)
						.get();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}
	
	/**
	 * 获取Document
	 * @param url
	 * @param proxy
	 * @return
	 * @throws IOException 
	 */
	public static Document getDocument(String url){
		BufferedReader br = null;
		HttpURLConnection connection = null;
		String html = null;
		while(null == html) {
			try {
				URL ur = new URL(url);
				connection = (HttpURLConnection)ur.openConnection();
				connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;");
				connection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
				connection.addRequestProperty("Referer", "http://www.d3zww.com/book/112/112906/");
				connection.addRequestProperty("Accept-Encoding", "gzip, deflate");
				connection.addRequestProperty("Connection", "Keep-Alive");
				connection.addRequestProperty("Upgrade-Insecure-Requests", "1");
				connection.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");
				connection.setConnectTimeout(30000);
				connection.setReadTimeout(30000);
				String content_encode = connection.getContentEncoding();
				InputStream is = connection.getInputStream();
				if (null != content_encode && !"".equals(content_encode) && content_encode.contains("gzip")) {
					is = new GZIPInputStream(is);
				}
				html = IOUtils.toString(is,"gbk");
				
				
//				br = new BufferedReader(new InputStreamReader(gzip, "utf-8"));
//				String line;  
//		        sb = new StringBuilder();  
//		        while ((line = br.readLine()) != null) {// 循环读取流  
//		        	sb.append(line);  
//		        }
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				connection.disconnect();// 断开连接 
			}
		}

//		while(null == doc) {
//			try {
//				doc = Jsoup.connect(url)
//						.header("Accept-Charset", "utf-8")
//						.header("Content-Type", "application/x-www-form-urlencoded")
//						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
//						.header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
//						.header("Accept-Encoding", "gzip, deflate")
//						.header("Referer", "http://www.d3zww.com/book/112/112906/")
//						.header("Connection", "Keep-Alive")
//						.header("Upgrade-Insecure-Requests", "1")
//						.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0")
//						.timeout(30000)
//						.get();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return Jsoup.parse(html);
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
		if(null != (proxy = ProxyUtil.getProxy())) {
			doc = DocumentUtil.getDocument(path,proxy);
		}else {
			doc = DocumentUtil.getDocument(path);
		}	
		return doc;
	}
	
	public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
