package com.girl.bibicat_data;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class App {
	
    public static void main(String[] args) throws ClientProtocolException, IOException {
    	Document doc = Jsoup.connect("http://blog.csdn.net/caimouse/article/details/79434735").get();
//    	System.out.println(doc.html());
		Elements links =  doc.select("div.article_content");
    	System.out.print(links.html());
		
//    	String page = new String();  
//        try {  
//            WebClient webClient = new WebClient();  
//            webClient.getOptions().setCssEnabled(false);  
//            webClient.getOptions().setJavaScriptEnabled(false);  
//            //去拿网页  
//            HtmlPage htmlPage = webClient.getPage("http://blog.csdn.net/caimouse/article/details/79367100");  
//            //得到提交按钮  
//            HtmlSubmitInput button = htmlPage.getInputByValue("");  
//            //得到输入框  
//            HtmlTextInput textField = form.getInputByName("query");  
//            //输入内容  
//            textField.setValueAttribute(key);  
//            //点一下按钮  
//            HtmlPage nextPage = button.click();  
//            String str = nextPage.toString();  
//            page = cutString(str);  
//            webClient.close();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }   
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpget = new HttpGet("http://blog.csdn.net/caimouse/article/details/79367100");
//		CloseableHttpResponse response = httpclient.execute(httpget);
//		HttpEntity he = response.getEntity();
//		Document document = Jsoup.parse(EntityUtils.toString(he));
//		document.select("div.article_content");
//		Elements elements = document.select("div.container clearfix");
//		System.out.print(elements.toString());
//		response.close();
	}
}
