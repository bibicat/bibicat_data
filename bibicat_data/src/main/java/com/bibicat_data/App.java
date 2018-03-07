package com.bibicat_data;

import java.io.IOException;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import dataBase.AnalysisUitl;

public class App {
	static Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	
	static Queue<Integer> queue2 = new LinkedBlockingQueue<Integer>();
	
    public static void main(String[] args) throws ClientProtocolException, IOException {
//    	Document doc = Jsoup.connect("http://blog.csdn.net/caimouse/article/details/79434735").get();
////    	System.out.println(doc.html());
//		Elements links =  doc.select("div.article_content");
//    	System.out.print(links.html());
//		
    	 
    	Document doc = Jsoup.connect("http://blog.csdn.net/caimouse/article/details/79434735").get();
//    	System.out.println(doc.html());
		Elements links =  doc.select("div.article_content");
    	System.out.print(AnalysisUitl.getRead_Num(doc));
        
	}
}
