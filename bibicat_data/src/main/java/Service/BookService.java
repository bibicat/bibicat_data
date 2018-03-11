package Service;

import java.util.List;
import bean.BookBean;

public interface BookService {
	
	public List<? extends BookBean> getBook_links(String path);
	
	public List<? extends BookBean> getChapter_links(List<? extends BookBean> BookBeans);
	
}


