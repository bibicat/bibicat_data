package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DruidUtil {

	private static final DruidDataSource dataSource = new DruidDataSource();
	
	static{
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root"); 
		dataSource.setPassword("suuu");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bibicat_data?serverTimezone=UTC"); 
		dataSource.setInitialSize(1); 
		dataSource.setMinIdle(1); 
		dataSource.setMaxActive(2);
	}
	
	public static void insertData(){
		DruidPooledConnection con;
		try {
			con = dataSource.getConnection();
			 PreparedStatement ps = con.prepareStatement("select a from a");  
		     ResultSet rs =  ps.executeQuery();
		     if(rs.next()){
		    	 System.out.println(rs.getString("a"));
		     }
		    
		     rs.close();
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
			 PreparedStatement ps = con.prepareStatement("select a from a");  
		     ResultSet rs =  ps.executeQuery();
		     if(rs.next()){
		    	 System.out.println(rs.getString("a"));
		     }
		    
		     rs.close();
		     ps.close();  
		     con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}  
       
	}
}
