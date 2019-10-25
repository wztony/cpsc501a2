
public class SomeClass3 implements SomeInterface1 {
	private int length;
	private String name;
	
	public SomeClass3(int l) {
		length = l;
		name = "none";
	}

	public void perform(int a, int b, SomeClass1 s) {
		length = a + b;
		name = s.work(length);
	}
}
