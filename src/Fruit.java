public class Fruit {

	public int size;
	private int seeds;
	private String name;
	
	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Fruit(String n, int s) {
		name = n;
		size = s;
		seeds = 5;
	}
	public Fruit(String n, int s, int a, int b) {
		name = n;
		size = s;
		seeds = a+b;
	}
	
	public int uselessCalc() {
		return size * seeds;
	}
	
}
