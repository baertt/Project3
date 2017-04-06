package dataBaseConstructor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class NewDataConstructer {
	static final int pickcourse = 4;
	public String newschedule(List<?> schedule) {
		if (schedule.size() == 0) {
			return "TBD";
		} else {
			HashMap<?, ?> sch_in = (HashMap<?, ?>) schedule.get(0);
			String sch_out = sch_in.get("DayPattern").toString() + " "
					+ checklength(sch_in.get("StartHour").toString()) + ":" + checklength(sch_in.get("StartMinute").toString()) + " - " 
					+ checklength(sch_in.get("EndHour").toString()) + ":" + checklength(sch_in.get("EndMinute").toString()) + " "
					+ sch_in.get("Building").toString() + " " + sch_in.get("Room").toString();
			return sch_out;
		}
	}
	
	public String checklength(String string) {
		if (string.length() < 2) {
			string = "0" + string;
			return string;
		} else {
			return string;
		}
	}
	
	public String instructor(List<?> instructor) {
		String ins_out = "";
		for (int i = 0; i < instructor.size(); i++) {
			HashMap<?, ?> ins_in = (HashMap<?, ?>) instructor.get(i); 
			if (i + 1 == instructor.size()) {
				ins_out = ins_out + ins_in.get("LastName").toString() + ", " + ins_in.get("FirstName");
			} else {
				ins_out = ins_out + ins_in.get("LastName").toString() + ", " + ins_in.get("FirstName") + " & ";
			}
		}
		return ins_out;
	}
	
	public String collegiateCode(List<?> cocode) {
		String code = "";
		if (cocode == null) {
			return "NA";
		} else {
			for (int i = 0; i < cocode.size(); i++) {
				if (i + 1 == cocode.size()) {
					code = code + cocode.get(i).toString();
				} else {
					code = code + cocode.get(i).toString() + "/";
				}
			}
			System.out.println(code);
			return code;
		}
	}
	
	public String ID() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DATE);
		int hr = Calendar.getInstance().get(Calendar.HOUR);
		int min = Calendar.getInstance().get(Calendar.MINUTE);
		int sec = Calendar.getInstance().get(Calendar.SECOND);
		String id = Integer.toString(year) + Integer.toString(month) + Integer.toString(day) + Integer.toString(hr) + Integer.toString(min) + Integer.toString(sec);
		System.out.println(id);
		return id;
	}
	
	public int year() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}
}
