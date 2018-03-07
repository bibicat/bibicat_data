package com.bibicat_data;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import dataBase.AnalysisUitl;

public class App {
	
    public static void main(String[] args) throws ClientProtocolException, IOException {
    	Document doc = Jsoup.connect("http://blog.csdn.net/caimouse/article/details/79434735").get();
//    	System.out.println(doc.html());
		Elements links =  doc.select("div.article_content");
    	System.out.print(AnalysisUitl.getRead_Num(doc));
        
	}
}
