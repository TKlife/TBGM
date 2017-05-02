
public abstract class BuffDebuff {
	
	String name;
	boolean buff, additive;
	
	public BuffDebuff(String name, boolean buff, boolean additive) {
		super();
		this.name = name;
		this.buff = buff;
		this.additive = additive;
	}
	public abstract void applyEffect(Character reciever);
	public abstract void reverseEffect(Character reciever);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getBuff() {
		return buff;
	}
	public void setBuff(boolean buff) {
		this.buff = buff;
	}
	public boolean isAdditive() {
		return additive;
	}
	public void setAdditive(boolean additive) {
		this.additive = additive;
	}
	
	
	
}
