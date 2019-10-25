
public class SomeClass2 {
	private int length;
	private String name;
	
	public SomeClass2(int l) {
		length = l;
		name = "none";
	}
	
	public String work(int a) throws NullPointerException {
		return name + a;
		}
}
