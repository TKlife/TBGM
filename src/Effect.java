import java.io.Serializable;

public abstract class Effect implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2022648043674640074L;
	String name;
	double multiplier, adder;
	boolean onStart = false, onEvade = false;
	
	public Effect(){
		
	}
	
	public Effect(String name, double multiplier, double adder, boolean onStart, boolean onEvade) {
		super();
		this.name = name;
		this.multiplier = multiplier;
		this.adder = adder;
		this.onStart = onStart;
	}

	public Effect(Effect copyFrom){
		this.name = copyFrom.getName();
		this.adder = copyFrom.getAdder();
		this.multiplier = copyFrom.getMultiplier();
		this.onStart = copyFrom.isOnStart();
	}
	
	public abstract void apply(Character affected);
	public abstract void reverse(Character affected);
	public abstract void overTime(Character affected);
	public abstract void onDamage(Character affected);
	public abstract String toString();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAdder() {
		return adder;
	}
	public void setAdder(double adder) {
		this.adder = adder;
	}
	public double getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	public boolean isOnStart() {
		return onStart;
	}
	public void setOnStart(boolean onStart) {
		this.onStart = onStart;
	}
	public boolean isOnEvade() {
		return onEvade;
	}
	public void setOnEvade(boolean onEvade) {
		this.onEvade = onEvade;
	}
	
}
