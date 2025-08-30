package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseUtils {
//	variable
	
	private static Connection con;
	private static final String db_url = System.getenv("DB_URL");
	private static final String db_password = System.getenv("PASSWORD_DB");
	private static final String db_user = System.getenv("USER_DB");
	
	private static List<Map<String, String>> testData(String query, Object...params) {
		List<Map<String,String>> rows = new LinkedList<>();
		try {
			con = DriverManager.getConnection(db_url,db_user, db_password);
			PreparedStatement ps = con.prepareStatement(query);
			
			for(int i=0; i<params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();
			
			while(rs.next()) {
				Map<String,String> row= new LinkedHashMap<>();
				for(int i=1; i<=colCount; i++) {
					String colName = meta.getColumnLabel(i);
					String value = rs.getString(i);
					row.put(colName, value);
				}
				rows.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public static List<Map<String, String>> getData(String param1, String params2, String queryOf) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream("C:\\Users\\hp\\git\\repository3\\DemoWebShop\\src\\queries.properties"));
		String query = props.getProperty(queryOf);
		
		return testData(query, param1, params2);
	}
	
	public static void close() throws SQLException {
		con.close();
	}
}
