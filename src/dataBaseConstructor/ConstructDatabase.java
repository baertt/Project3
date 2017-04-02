package dataBaseConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.lang.reflect.Array;
//import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
//import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConstructDatabase {
	static final ApostropheChecker apos = new ApostropheChecker();
	public void Construct(String filename) throws ClassNotFoundException{
//		URL urlObject = new URL(url);
		JSONParser parser=new JSONParser();
		Class.forName("org.sqlite.JDBC");
        Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + filename + ".db");
	        Statement stat = con.createStatement();
			Object obj = parser.parse(new FileReader(filename + ".json"));
			JSONObject json_obj = (JSONObject) obj;
			stat.execute("drop table if exists course");
			stat.execute("create table course (CourseId integer, Title string)");
			List<?> x = (List<?>) json_obj.get("value");
			System.out.println(x);
			for (int i = 0; i < x.size(); i++) {
//				System.out.println(x.get(i));
				HashMap<?, ?> y = (HashMap<?, ?>) x.get(i);
				System.out.println(i);
//				System.out.println(y.get("CourseId"));
//				System.out.println(y.get("Title"));
				stat.execute("insert into course values(" 
				+ y.get("CourseId").toString().trim() 
				+ ", '" + apos.remodify(y.get("Title").toString().trim()) + "'" 
//				+ ", '" + apos.remodify(y.get("Title").toString().trim()) + "'"			
				+ ")");
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
