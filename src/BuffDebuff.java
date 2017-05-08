import java.io.Serializable;
import java.util.ArrayList;

public class BuffDebuff implements Serializable {
	
	String name;
	int duration, stack;
	boolean buff, overTime;
	ArrayList<Effect> effects;
	
	public BuffDebuff(BuffDebuff toCopy){
		this.name = toCopy.getName();
		this.buff = toCopy.isBuff();
		this.duration = toCopy.getDuration();
		this.overTime = toCopy.isOverTime();
		this.effects = new ArrayList<Effect>();
		for(Effect effect: toCopy.getEffects()){
			effects.add((Effect) Utilities.copy(effect));
		}
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
	
	public boolean applyEffect(Character reciever) {
		if(hasBuffDebuff(reciever)){
			return false;
		}
		for (Effect effect: effects){
			effect.apply(reciever);	
		}
		if(buff){
			reciever.addBuff(this);
		} else {
			reciever.addDebuff(this);
		}
		return true;
	}

	public boolean reverseEffect(Character reciever) {
		for (Effect effect: effects){
			effect.reverse(reciever);
		}
		return true;
	}

	public boolean onEnd(Character affected) {
		for(Effect effect: effects){
			if(!effect.isOnStart()){
				effect.overTime(affected);
			} else return false;
		}
		return true;
	}
	
	public boolean onStart(Character affected){
		for(Effect effect: effects){
			if(effect.isOnStart()){
				effect.overTime(affected);
			} else return false;
		}
		return true;
	}
	
	public boolean onDamage(Character affected){
		for(Effect effect: effects){
			if(!effect.isOnEvade()){
				effect.onDamage(affected);
			}
		}
		return true;
	}
	
	public boolean onEvade(Character affected) {
		for(Effect effect: effects){
			if(effect.isOnEvade()){
				effect.onDamage(affected);
			} else return false;
		}
		return true;
	}

	public boolean endTurn(Character affected){
		if(duration == 0){
			return true;
		} else {
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
	
	public String toString(){
		String s = name + ":\n";
		String[] words;
		int length = 0, lineLength = 30;
		for (Effect e: effects){
			words = e.toString().split(" ");
			for (int i = 0; i < words.length; i++){
				if(length > lineLength){
					s += "\n";
					length = 0;
				}
				s += words[i];
				length += words[i].length() + 1;
				if (i != words.length - 1){
					s += " ";
				}
			}
			s += ". ";
		}
		return s;
	}	
}
