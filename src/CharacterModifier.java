
public class CharacterModifier extends BuffDebuff {
	
	public CharacterModifier(BuffDebuff copyFrom){
		super(copyFrom);
		if(copyFrom.getClass().equals(StatModifier.class)){
			CharacterModifier toCopy = ((CharacterModifier) copyFrom);
		}
	}
	
	public CharacterModifier(String name, boolean buff, boolean overTime, int duration) {
		super(name, buff, overTime, duration);
	}

	public CharacterModifier(String name, boolean buff, boolean overTime, int duration, int baseHealth, int baseEnergy,
			int baseSpeed, int basePhysicalDamage, int baseMagicDamage, int baseHealing, int baseHealability,
			double basePhysicalDefence, double baseMagicDefence, int currentHealth, int totalHealth, int currentEnergy,
			int totalEnergy, double critChance, double potency, double dodge, int turnMeter) {
		super(name, buff, overTime, duration);
		
	}

	@Override
	public boolean applyEffect(Character reciever) {
		if(hasBuffDebuff(reciever)){
			return false;
		}
		for (Effect effect: effects){
			effect.apply(reciever);	
		}
		if(buff){
			reciever.getBuffs().add(this);
		} else {
			reciever.getDebuffs().add(this);
		}
		return true;
	}

	@Override
	public boolean reverseEffect(Character reciever) {
		for (Effect effect: effects){
			effect.reverse(reciever);
		}
		return true;
	}

	@Override
	public boolean overTimeEffect(Character reciever) {
		for(Effect effect: effects){
			effect.overTime(reciever);
		}
		return false;
	}

}
