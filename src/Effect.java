
public abstract class Effect {
	
	String name;
	
	public abstract void apply(Character affected);
	public abstract void reverse(Character affected);
	public abstract void overTime(Character affected);
	public abstract String toString();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
