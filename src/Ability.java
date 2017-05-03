import java.util.ArrayList;

public abstract class Ability {
	
	String name;
	Character owner;
	double energyCost, multiplier;
	ArrayList<BuffDebuff> appliedEffects;
	int numberOfAttacks, attackCounter;
	
	public Ability(Character owner, double energyCost, double multiplier) {
		this.owner = owner;
		this.energyCost = energyCost;
		this.multiplier = multiplier;
		this.numberOfAttacks = 1;
		appliedEffects = new ArrayList();
		this.attackCounter = this.numberOfAttacks;
	}

	public abstract void useAbility(Character affected);
	
	public void useAbility(ArrayList<Character> affected){
		
		if (attackCounter > 0){
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

	public int getNumberOfAttacks() {
		return numberOfAttacks;
	}

	public void setNumberOfAttacks(int numberOfAttacks) {
		this.numberOfAttacks = numberOfAttacks;
	}

	public int getAttackCounter() {
		return attackCounter;
	}

	public void setAttackCounter(int attackCounter) {
		this.attackCounter = attackCounter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
