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
	static final NewDataConstructer newdata = new NewDataConstructer();
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
			stat.execute("create table course (CourseId integer, TermCode string, CourseCode string, Title string, Period string, Schedule string, Instructors string, Description string, CollegiateCode string)");
			List<?> x = (List<?>) json_obj.get("value");
			for (int i = 0; i < x.size(); i++) {
//				System.out.println(x.get(i));
				String des = new String();
				HashMap<?, ?> y = (HashMap<?, ?>) x.get(i);
				if (y.get("Description") == null) {
					des = "None";
				} else {
					des = y.get("Description").toString().trim();
				}
				List<?> schedule = (List<?>) y.get("Schedule");
				List<?> instructors = (List<?>) y.get("Instructors");
				List<?> collegiate = (List<?>) y.get("CollegiateCode");
			//	System.out.println(i);
//				System.out.println(y.get("CourseId"));
//				System.out.println(y.get("Title"));

				stat.execute("insert into course values("
				+ y.get("CourseId").toString().trim()
				+ ", '" + y.get("TermCode").toString().trim() + "'"
				+ ", '" + y.get("CourseCode").toString().trim() + "'"
				+ ", '" + apos.remodify(y.get("Title").toString().trim()) + "'"
				+ ", '" + y.get("Period").toString().trim() + "'"
				+ ", '" + newdata.newschedule(schedule) + "'"
				+ ", '" + apos.remodify(newdata.instructor(instructors)) + "'"
				+ ", '" + apos.remodify(des) + "'"
				+ ", '" + newdata.collegiateCode(collegiate) + "'"
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

	public void ConstructUserInfo() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:user.db");
		Statement stat = con.createStatement();
		stat.execute("create table user (ProjectName string, ProjectId string)");
		stat.execute("create table schedule (ProjectId string, CourseId integer)");
	}

	public void addUserInfo(String projectname) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:user.db");
		Statement stat = con.createStatement();
		stat.execute("insert into user values("
				+ ", '" + apos.remodify(projectname) + "'"
				+ ", '" + newdata.ID() + "'"
				+ ")");
	}
<<<<<<< HEAD
	
	public void addSchedule(String id, String courseid) throws ClassNotFoundException, SQLException {
=======

	public void addSchedule(String id, String courses) throws ClassNotFoundException, SQLException {
>>>>>>> master
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:user.db");
		Statement stat = con.createStatement();
		stat.execute("insert into user values("
				+ ", '" + id + "'"
				+ ", '" + courseid + "'"
				+ ")");
	}
}
