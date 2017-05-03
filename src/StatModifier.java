import java.util.ArrayList;

public class StatModifier extends BuffDebuff {

	boolean additive;
	double intellect, strength, dexterity, stamina, agility, ethics, psychotics;
	
	public StatModifier(BuffDebuff copyFrom){
		super(copyFrom);
		if(copyFrom.getClass().equals(StatModifier.class)){
			StatModifier toCopy = ((StatModifier) copyFrom);
			this.intellect = toCopy.getIntellect();
			this.strength = toCopy.getStrength();
			this.dexterity = toCopy.getDexterity();
			this.stamina = toCopy.getStamina();
			this.agility = toCopy.getAgility();
			this.ethics = toCopy.getEthics();
			this.psychotics = toCopy.getPsychotics();
			this.additive = toCopy.isAdditive();
		}
	}
	public StatModifier(String name, boolean buff, boolean overTime, int duration, boolean additive) {
		super(name, buff, overTime, duration);
		this.additive = additive;
		if(!additive){
			this.intellect = 1;
			this.strength = 1;
			this.dexterity = 1;
			this.stamina = 1;
			this.agility = 1;
			this.ethics = 1;
			this.psychotics = 1;
		} else {
			this.intellect = 0;
			this.strength = 0;
			this.dexterity = 0;
			this.stamina = 0;
			this.agility = 0;
			this.ethics = 0;
			this.psychotics = 0;
		}
	}

	public StatModifier(String name, boolean buff, boolean overTime, int duration, boolean additive, double intellect, double strength, double dexterity,
			double stamina, double agility, double ethics, double psychotics) {
		super(name, buff, overTime, duration);
		this.intellect = intellect;
		this.strength = strength;
		this.dexterity = dexterity;
		this.stamina = stamina;
		this.agility = agility;
		this.ethics = ethics;
		this.psychotics = psychotics;
		this.additive = additive;
	}

	@Override
	public boolean applyEffect(Character reciever) {
		if(hasBuffDebuff(reciever)){
			return false;
		}
		if (buff){
			reciever.getBuffs().add(this);
		} else {
			reciever.getDebuffs().add(this);
		}
		if(overTime){
			return true;
		}
		if (additive){
			reciever.playerStats.setIntellect(reciever.playerStats.getIntellect() + intellect);
			reciever.playerStats.setStrength(reciever.playerStats.getStrength() + strength);
			reciever.playerStats.setDexterity(reciever.playerStats.getDexterity() + dexterity);
			reciever.playerStats.setStamina(reciever.playerStats.getStamina() + stamina); 
			reciever.playerStats.setAgility(reciever.playerStats.getAgility() + agility);
			reciever.playerStats.setEthics(reciever.playerStats.getEthics() + ethics);
			reciever.playerStats.setPschotics(reciever.playerStats.getPschotics() + psychotics);
		} else {
			reciever.playerStats.setIntellect(reciever.playerStats.getIntellect()*intellect);
			reciever.playerStats.setStrength(reciever.playerStats.getStrength() * strength);
			reciever.playerStats.setDexterity(reciever.playerStats.getDexterity() * dexterity);
			reciever.playerStats.setStamina(reciever.playerStats.getStamina() * stamina); 
			reciever.playerStats.setAgility(reciever.playerStats.getAgility() * agility);
			reciever.playerStats.setEthics(reciever.playerStats.getEthics() * ethics);
			reciever.playerStats.setPschotics(reciever.playerStats.getPschotics() * psychotics);
		}
		return true;
	}

	@Override
	public boolean reverseEffect(Character reciever) {
		
		if (!(reciever.hasBuffDebuff(this) == null || reciever.hasBuffDebuff(this) == null)){
			return false;
		}
		if (additive){
			reciever.playerStats.setIntellect(reciever.playerStats.getIntellect() - intellect);
			reciever.playerStats.setStrength(reciever.playerStats.getStrength() - strength);
			reciever.playerStats.setDexterity(reciever.playerStats.getDexterity() - dexterity);
			reciever.playerStats.setStamina(reciever.playerStats.getStamina() - stamina); 
			reciever.playerStats.setAgility(reciever.playerStats.getAgility() - agility);
			reciever.playerStats.setEthics(reciever.playerStats.getEthics() - ethics);
			reciever.playerStats.setPschotics(reciever.playerStats.getPschotics() - psychotics);
		} else {
			reciever.playerStats.setIntellect(reciever.playerStats.getIntellect()/intellect);
			reciever.playerStats.setStrength(reciever.playerStats.getStrength() / strength);
			reciever.playerStats.setDexterity(reciever.playerStats.getDexterity() / dexterity);
			reciever.playerStats.setStamina(reciever.playerStats.getStamina() / stamina); 
			reciever.playerStats.setAgility(reciever.playerStats.getAgility() / agility);
			reciever.playerStats.setEthics(reciever.playerStats.getEthics() / ethics);
			reciever.playerStats.setPschotics(reciever.playerStats.getPschotics() / psychotics);
		}
		return true;
	}

	public double getIntellect() {
		return intellect;
	}

	public void setIntellect(double intellect) {
		this.intellect = intellect;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public double getDexterity() {
		return dexterity;
	}

	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}

	public double getStamina() {
		return stamina;
	}

	public void setStamina(double stamina) {
		this.stamina = stamina;
	}

	public double getAgility() {
		return agility;
	}

	public void setAgility(double agility) {
		this.agility = agility;
	}

	public double getEthics() {
		return ethics;
	}

	public void setEthics(double ethics) {
		this.ethics = ethics;
	}

	public double getPsychotics() {
		return psychotics;
	}

	public void setPsychotics(double psychotics) {
		this.psychotics = psychotics;
	}
	
	public boolean isAdditive() {
		return additive;
	}
	public void setAdditive(boolean additive) {
		this.additive = additive;
	}
	@Override
	public boolean overTimeEffect(Character reciever) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
