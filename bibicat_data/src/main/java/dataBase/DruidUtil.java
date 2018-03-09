package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import Config.ConfigUtil;
import bean.BlogBean;

public class DruidUtil {

	private static final DruidDataSource dataSource = new DruidDataSource();
	
	static{
		dataSource.setUsername(ConfigUtil.getValue("mysql.username")); 
		dataSource.setPassword(ConfigUtil.getValue("mysql.password"));
		dataSource.setUrl(ConfigUtil.getValue("mysql.url")); 
		dataSource.setInitialSize(1); 
		dataSource.setMinIdle(1); 
		dataSource.setMaxActive(2);
	}
	
	public static void insertData(BlogBean blog){
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
	
	
	public static void main(String[] args) {
		DruidPooledConnection con;
		try {
			con = dataSource.getConnection();
			 PreparedStatement ps = con.prepareStatement("select blog_title from blog");  
		     ResultSet rs =  ps.executeQuery();
		     if(rs.next()){
		    	 System.out.println(rs.getString("blog_title"));
		     }
		     rs.close();
		     ps.close();  
		     con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}  
       
	}
}
