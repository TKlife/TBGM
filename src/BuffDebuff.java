import java.util.ArrayList;

public abstract class BuffDebuff {
	
	String name;
	int duration, stack;
	boolean buff, overTime;
	ArrayList<Effect> effects;
	
	public BuffDebuff(BuffDebuff toCopy){
		this.name = toCopy.getName();
		this.buff = toCopy.isBuff();
		this.duration = toCopy.getDuration();
		this.overTime = toCopy.isOverTime();
		this.effects = toCopy.getEffects();
		this.stack = toCopy.getStack();
	}
	public BuffDebuff(String name, boolean buff, boolean overTime, int duration) {
		super();
		this.name = name;
		this.buff = buff;
		this.duration = duration;
		this.overTime = overTime;
		this.stack = 1;
		this.effects = new ArrayList();
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
	
	public void addEffect(Effect effect){
		effects.add(effect);
	}
	
	public void removeEffect(Effect effect){
		effects.remove(effect);
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
	public ArrayList<Effect> getEffects() {
		return effects;
	}
	public void setEffects(ArrayList<Effect> effects) {
		this.effects = effects;
	}
	
	
	
	
}
