import java.util.ArrayList;

public class StatModifier extends BuffDebuff {

	double intellect, strength, dexterity, stamina, agility, ethics, psychotics;
	
	
	public StatModifier(String name, boolean buff, boolean additive) {
		super(name, buff, additive);
		// TODO Auto-generated constructor stub
	}

	public StatModifier(String name, boolean buff, boolean additive, double intellect, double strength, double dexterity,
			double stamina, double agility, double ethics, double psychotics) {
		super(name, buff, additive);
		this.intellect = intellect;
		this.strength = strength;
		this.dexterity = dexterity;
		this.stamina = stamina;
		this.agility = agility;
		this.ethics = ethics;
		this.psychotics = psychotics;
	}

	@Override
	public void applyEffect(Character reciever) {
		
		if (buff){
			reciever.getBuffs().add(this);
		} else {
			reciever.getDebuffs().add(this);
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
	}

	@Override
	public void reverseEffect(Character reciever) {
		
		if (buff && reciever.getBuffs().contains(this)){
			reciever.getBuffs().remove(this);
		} else if(reciever.getDebuffs().contains(this)) {
			reciever.getDebuffs().remove(this);
		} else {
			return;
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
	}

}
