package dataBaseConstructor;

public class ExecuteInfo {
	public String executeinfo(String courseTime, String subject, String professor, String courseTitle, String courseNumber) {
		String exe = new String();
		if (subject.length() == 0) {
			exe = "select * from course where " + "Period " + "like " + "'%" + courseTime + "%' " + "and "
					+ "CourseCode " + "like " + "'%" + subject + "%' " + "and " 
					+ "Instructors " + "like " + "'%" + professor + "%' " + "and "
					+ "Title " + "like " + "'%" + courseTitle + "%' " + "and "
					+ "CourseCode " + "like " + "'%" + courseNumber + "%'";
		} else {
			exe = "select * from course where " + "Period " + "like " + "'%" + courseTime + "%' " + "and "
					+ "CourseCode " + "like " + "'%" + subject.substring(0, 4) + "%' " + "and " 
					+ "Instructors " + "like " + "'%" + professor + "%' " + "and "
					+ "Title " + "like " + "'%" + courseTitle + "%' " + "and "
					+ "CourseCode " + "like " + "'%" + courseNumber + "%'";
		}
		
		return exe;
	}
}
