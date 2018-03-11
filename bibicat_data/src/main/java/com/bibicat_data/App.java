package com.bibicat_data;

import java.io.IOException;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import ServiceImpl.D3zwwBookServiceImpl;
import bean.BookBean;
import dataBase.RedisUtil;

public class App {
	
    public static void main(String[] args) throws ClientProtocolException, IOException {
    	String start_page = RedisUtil.sPop("start_page");
    	int i = 1;
    	//从上次一次结束位开始
    	if(null == start_page) {
    		start_page = D3zwwBookServiceImpl.start_page + i + ".html";
    	}else {
    		i = Integer.valueOf(start_page);
    		start_page = D3zwwBookServiceImpl.start_page + start_page + ".html";
    	}
    	D3zwwBookServiceImpl a = new D3zwwBookServiceImpl();
    	
    	for(;;i++) {
    		RedisUtil.del("start_page");
    		RedisUtil.sAdd("start_page", String.valueOf(i));
    		System.out.println(i);
    		start_page = D3zwwBookServiceImpl.start_page + i + ".html";
    		System.out.println(start_page);
    		List<? extends BookBean> bookBeans = a.getBook_links(start_page);
    		a.getChapter_links(bookBeans);
    	}
		

//    	Document doc = Jsoup.connect("http://www.d3zww.com/book/32/32537/30235685.html").get();
//    	System.out.println(doc.toString());
////    	  for (int i = 0; i < 7000; i++) {
////    	   fixedThreadPool.execute(new Runnable() {
////    	    public void run() {
////    	    	String ip_port = RedisUtil.sPop(RedisUtil.ip);
////    	    	String[] ip_prot_list = ip_port.split(":");
////    	    	try {
////					URL url = new URL("http://www.baidu.com");
////					InetSocketAddress addr = new InetSocketAddress(ip_prot_list[0],Integer.valueOf(ip_prot_list[1]));  
////	                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理  
////					URLConnection uc = url.openConnection(proxy);
////					uc.getInputStream();
////					RedisUtil.sAdd("usable_ip",ip_port);
////				} catch (MalformedURLException e) {
////					e.printStackTrace();
////				} catch (IOException e) {
////					e.printStackTrace();
////				} catch (NullPointerException e) {
////					return;
////				}
////    	    }
////    	   });
////    	  }
//
//    	Document doc = null;
//    	String[] ip_prot_list = null;
//    	String ip_port = null;
//    	InetSocketAddress addr = null;
//    	Proxy proxy = null;
//    	RedisUtil.sAdd(RedisUtil.ip, "47.94.19.19"+":"+8888);
//    	for(int i = 1;i <= 1670;i++){
//    		while(true){
//	    		try {
//	    			ip_port = RedisUtil.sPop(RedisUtil.ip);
//	    			ip_prot_list = ip_port.split(":");
//	    			addr = new InetSocketAddress(ip_prot_list[0],Integer.valueOf(ip_prot_list[1]));
//	    			proxy = new Proxy(Proxy.Type.HTTP, addr);
//	    			
//	    			System.out.println("当前使用"+ip_prot_list[0]+":"+Integer.valueOf(ip_prot_list[1])+"\n"+"当前ip数量"+RedisUtil.scard(RedisUtil.ip)+"\n当前外层循环层次"+i);
//	    			
//	    	    	doc = DocumentUtil.getDocument("http://www.xicidaili.com/wt/"+i,proxy);
//	    	    	List<AgentBean> AgentBean_list = AnalysisUitl.getIpAndPort(doc);
//	    	    	System.out.println(AgentBean_list.size());
//	    	    	for(AgentBean agentBean : AgentBean_list){
//	    	    		final String ip = agentBean.getIp();
//	    	    		final Integer port = agentBean.getProt();
//	    	    		fixedThreadPool.execute(new Runnable() {
//	    	        	    public void run() {
//	    	        	    	try {
//	    	    					URL url = new URL("http://www.baidu.com");
//	    	    					InetSocketAddress addr = new InetSocketAddress(ip,port);  
//	    	    	                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理  
//	    	    					URLConnection uc = url.openConnection(proxy);
//	    	    					uc.setUseCaches(false);
//	    	    					uc.setConnectTimeout(30000);
//	    	    					uc.setReadTimeout(30000);
//	    	    					uc.connect();
//	    	    					System.out.println("分配任务"+ip+":"+port+"可用,加入队列;当前线程"+Thread.currentThread().getName());
//	    	    					RedisUtil.sAdd(RedisUtil.ip,ip+":"+port);
//	    	    				} catch (Exception e){
//	    	    					System.out.println(Thread.currentThread().getName()+":抛弃");
//	    	    				}
//	    	        	    }
//	    	    		});
//	    	    	}
//	    	    	System.out.println("当前使用"+ip_prot_list[0]+":"+Integer.valueOf(ip_prot_list[1])+";可用,加入队列");
//	    	    	RedisUtil.sAdd(RedisUtil.ip,ip_port);
//	    	    	Thread.sleep(2000);
//	    	    	break;
//	    	    	
//	    		} catch (Exception e) {
//	    			System.out.println("当前使用"+ip_prot_list[0]+":"+Integer.valueOf(ip_prot_list[1])+";不可用,抛弃");
//	    			
//	    		   	try {
//	    		   		ip_port = RedisUtil.sPop(RedisUtil.ip);
//		    		   	ip_prot_list = ip_port.split(":");
//	    		   		addr = new InetSocketAddress(ip_prot_list[0],Integer.valueOf(ip_prot_list[1]));
//		    			proxy = new Proxy(Proxy.Type.HTTP, addr);
//					} catch (Exception e2) {
//						RedisUtil.sAdd(RedisUtil.ip, "47.94.19.19:8888");
//						ip_port = RedisUtil.sPop(RedisUtil.ip);
//						addr = new InetSocketAddress(ip_prot_list[0],Integer.valueOf(ip_prot_list[1]));
//		    			proxy = new Proxy(Proxy.Type.HTTP, addr);
//					}
//	    			
//	    		}
//    		}
//    	}
//	}
    }
}
