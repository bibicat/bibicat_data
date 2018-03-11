package dataBase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import Config.ConfigUtil;
import bean.BlogBean;
import bean.BookArticleBean;
import bean.BookBean;

public class DruidUtil {

	private static final DruidDataSource dataSource = new DruidDataSource();
	
	static{
		dataSource.setUsername(ConfigUtil.getValue("mysql.username")); 
		dataSource.setPassword(ConfigUtil.getValue("mysql.password"));
		dataSource.setUrl(ConfigUtil.getValue("mysql.url")); 
		dataSource.setInitialSize(1); 
		dataSource.setMinIdle(1); 
		dataSource.setMaxActive(1);
	}
	
	public static void insertBlog(BlogBean blog){
		DruidPooledConnection con;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into blog ("
			 		+ "blog_title,"
			 		+ "blog_content,"
			 		+ "blog_url,"
			 		+ "blog_read_num"
			 		+ ") "
			 		+ "values (?,?,?,?)");  
		     ps.setString(1,blog.getBlog_title());
		     ps.setString(2,blog.getBlog_content());
		     ps.setString(3,blog.getBlog_url());
		     ps.setInt(4,blog.getBlog_read_num());
		     ps.executeUpdate();
		     ps.close();  
		     con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	
	public static Integer insertBook(BookBean book){
		DruidPooledConnection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("insert into book ("
			 		+ "book_title,"
			 		+ "book_link,"
			 		+ "book_image_url,"
			 		+ "book_author,"
			 		+ "book_introduce"
			 		+ ") "
			 		+ "values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
		     ps.setString(1,book.getTitle());
		     ps.setString(2,book.getLink());
		     ps.setString(3,book.getImage_url());
		     ps.setString(4,book.getAuthor());
		     ps.setString(5,book.getIntroduce());
		     ps.executeUpdate();
		     rs = ps.getGeneratedKeys();
		     rs.next();
		     return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				rs.close();
				ps.close();
				con.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		return null;
	}
	
	public static void insertBookArticle(BookArticleBean book_article){
		DruidPooledConnection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("insert into book_article ("
					+ "book_id,"
			 		+ "book_article_title,"
			 		+ "book_article_link,"
			 		+ "book_article_content"
			 		+ ") "
			 		+ "values (?,?,?,COMPRESS(?))");  
			 ps.setInt(1,book_article.getBook_id());
		     ps.setString(2,book_article.getBook_article_title());
		     ps.setString(3,book_article.getBook_article_link());
		     ps.setString(4,book_article.getBook_article_content());
		     ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
	}
	
	public static Integer selectBook(String book_title) {
		DruidPooledConnection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select id from book where book_title = ?");  
			ps.setString(1,book_title);
			res = ps.executeQuery();
			while(res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		return null;
	}
	
	public static Integer selectArticleTotal(Integer book_id) {
		DruidPooledConnection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select count(id) from book_article where book_id = ?");  
			ps.setInt(1,book_id);
			res = ps.executeQuery();
			while(res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws IOException {
		DruidPooledConnection con;
		try {
			con = dataSource.getConnection();
			 PreparedStatement ps = con.prepareStatement("select UNCOMPRESS(book_article_content) from book_article where id = 18");  
		     ResultSet rs =  ps.executeQuery();
		     if(rs.next()){
		    	 Blob noteBlob = rs.getBlob(1);
		    	 InputStream is = noteBlob.getBinaryStream();
					ByteArrayInputStream bais = (ByteArrayInputStream)is;
					byte[] byte_data = new byte[bais.available()]; //bais.available()返回此输入流的字节数

					bais.read(byte_data, 0,byte_data.length);//将输入流中的内容读到指定的数组
					String a = new String(byte_data,"utf-8"); //再转为String，并使用指定的编码方式
					System.out.println(a);
					is.close();
		     }
		     rs.close();
		     ps.close();  
		     con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}  
       
	}
}
