
public class Tester {
	public static void main(String[] args) {
		Infix test = new Infix("10 + 2 / 3");
		
		System.out.println(test.toPostFix());
		System.out.println(test.compute());
	}
}
