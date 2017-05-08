import java.io.Serializable;
import java.util.ArrayList;

public abstract class Ability implements Serializable{
	
	String name;
	Character owner, target;
	ArrayList<Character> secondaryTargets;
	double energyCost, multiplier;
	ArrayList<BuffDebuff> appliedEffects;
	int cooldown, cooldownCounter, level, maxLevel;
	boolean hit = true;
	
	public Ability(Character owner, double energyCost, double multiplier, int cooldown, int maxLevel) {
		this.owner = owner;
		this.energyCost = energyCost;
		this.multiplier = multiplier;
		this.cooldown = cooldown;
		this.maxLevel = maxLevel;
		appliedEffects = new ArrayList();
		this.cooldownCounter = 0;
		this.level = 0;
	}

	public abstract void useAbility(Character affected);
	public abstract void levelUp();
	public abstract String toString();
	
	public void unLock(){
		levelUp();
	}
	
	public void useAbility(ArrayList<Character> affected){
		
		if (cooldownCounter > 0){
			for(Character chara: affected){
				useAbility(chara);
			}
		} else {
			
		}
	}

	public Character getOwner() {
		return owner;
	}

	public void setOwner(Character owner) {
		this.owner = owner;
	}

	public double getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(double energyCost) {
		this.energyCost = energyCost;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public ArrayList<BuffDebuff> getAppliedEffects() {
		return appliedEffects;
	}

	public void setAppliedEffects(ArrayList<BuffDebuff> appliedEffects) {
		this.appliedEffects = appliedEffects;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getCooldownCounter() {
		return cooldownCounter;
	}

	public void setCooldownCounter(int cooldownCounter) {
		this.cooldownCounter = cooldownCounter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	
}
