
public abstract class BuffDebuff {
	
	String name;
	int duration, stack;
	boolean buff, additive, overTime;
	
	public BuffDebuff(BuffDebuff toCopy){
		this.name = toCopy.getName();
		this.buff = toCopy.isBuff();
		this.additive = toCopy.isAdditive();
		this.duration = toCopy.getDuration();
		this.overTime = toCopy.isOverTime();
	}
	public BuffDebuff(String name, boolean buff, boolean additive, boolean overTime, int duration) {
		super();
		this.name = name;
		this.buff = buff;
		this.additive = additive;
		this.duration = duration;
		this.overTime = overTime;
	}
	public abstract boolean applyEffect(Character reciever);
	public abstract boolean reverseEffect(Character reciever);
	public abstract boolean overTimeEffect(Character reciever);

	public boolean endTurn(Character affected){
		if(duration == 0){
			return true;
		} else {
			if(overTime){
				this.overTimeEffect(affected);
			}
			duration--;
			return false;
		}
	}

	public boolean hasBuffDebuff(Character reciever){
		BuffDebuff hasBuff = reciever.hasBuffDebuff(this);
		if (hasBuff != null){
			hasBuff.setDuration(this.getDuration());
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isBuff() {
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}
	public boolean isOverTime() {
		return overTime;
	}
	public void setOverTime(boolean overTime) {
		this.overTime = overTime;
	}
	
	
	
	
}
