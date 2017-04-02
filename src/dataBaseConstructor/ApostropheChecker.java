package dataBaseConstructor;

public class ApostropheChecker {
	public String remodify (String string) {
		String new_string = string;
		int count = string.split("\\'", -1).length - 1;
//		System.out.println(new_string);
//		System.out.println(string);
//		System.out.println(count);
		if (count % 2 != 0) {
			String x = new_string.replaceAll("'", "''");
			System.out.println(x);
			return x;
			
		} else {
			System.out.println(new_string);
			return new_string;
		}		
	}

}
