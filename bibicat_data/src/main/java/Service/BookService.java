package Service;

import java.util.List;
import bean.BookBean;

public interface BookService {

	public List<BookBean> getBook_links();
	
	public List<BookBean> getChapter_links();
	
}


