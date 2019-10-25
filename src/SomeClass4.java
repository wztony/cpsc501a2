
public class SomeClass4 implements SomeInterface2 {
	private int length;
	private String name;
	
	public SomeClass4(int l) {
		length = l;
		name = "none";
	}

	public void perform(int a, int b, SomeClass1 s) {
		length = a + b;
		name = s.work(length);
	}
}
