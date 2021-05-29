
public class Tester {

	public static void main(String[] args) {
		WordList w = new WordList();
		w.add("a");
		//w.add("b");
		
		System.out.println(w.contains("a"));
		//System.out.println(w.set(0, "a"));
		System.out.println(w.add(0, "a"));
	}

}
