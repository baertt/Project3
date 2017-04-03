package dataBaseConstructor;

public class ApostropheChecker {
	public String remodify(String string) {
//		String new_string = string;
//		int count = string.split("\\'", -1).length - 1;
//		System.out.println(new_string);
//		System.out.println(string);
//		System.out.println(count);
		String x = string.replaceAll("'", "''");
		System.out.println(x);
		return x;
	}
}
