
public class CharacterModifier extends BuffDebuff {

	int baseHealth, baseEnergy, baseSpeed; 
	int basePhysicalDamage, baseMagicDamage, baseHealing, baseHealability;
	double basePhysicalDefence, baseMagicDefence;
	int currentHealth, totalHealth, currentEnergy, totalEnergy;
	double critChance, potency, dodge;
	int turnMeter;
	
	public CharacterModifier(BuffDebuff copyFrom){
		super(copyFrom);
		if(copyFrom.getClass().equals(StatModifier.class)){
			CharacterModifier toCopy = ((CharacterModifier) copyFrom);
			this.baseHealth = toCopy.getBaseHealth();
			this.baseEnergy = toCopy.getBaseEnergy();
			this.baseSpeed = toCopy.getBaseSpeed();
			this.basePhysicalDamage = toCopy.getBasePhysicalDamage();
			this.baseMagicDamage = toCopy.getBaseMagicDamage();
			this.baseHealing = toCopy.getBaseHealing();
			this.baseHealability = toCopy.getBaseHealability();
			this.basePhysicalDefence = toCopy.getBasePhysicalDefence();
			this.baseMagicDefence = toCopy.getBaseMagicDefence();
			this.currentHealth = toCopy.getCurrentHealth();
			this.totalHealth = toCopy.getTotalHealth();
			this.currentEnergy = toCopy.getCurrentEnergy();
			this.totalEnergy = toCopy.getTotalEnergy();
			this.critChance = toCopy.getCritChance();
			this.potency = toCopy.getPotency();
			this.dodge = toCopy.getDodge();
			this.turnMeter = toCopy.getTurnMeter();
		}
	}
	
	public CharacterModifier(String name, boolean buff, boolean additive, boolean overTime, int duration) {
		super(name, buff, additive, overTime, duration);
	}

	public CharacterModifier(String name, boolean buff, boolean additive, boolean overTime, int duration, int baseHealth, int baseEnergy,
			int baseSpeed, int basePhysicalDamage, int baseMagicDamage, int baseHealing, int baseHealability,
			double basePhysicalDefence, double baseMagicDefence, int currentHealth, int totalHealth, int currentEnergy,
			int totalEnergy, double critChance, double potency, double dodge, int turnMeter) {
		super(name, buff, additive, overTime, duration);
		this.baseHealth = baseHealth;
		this.baseEnergy = baseEnergy;
		this.baseSpeed = baseSpeed;
		this.basePhysicalDamage = basePhysicalDamage;
		this.baseMagicDamage = baseMagicDamage;
		this.baseHealing = baseHealing;
		this.baseHealability = baseHealability;
		this.basePhysicalDefence = basePhysicalDefence;
		this.baseMagicDefence = baseMagicDefence;
		this.currentHealth = currentHealth;
		this.totalHealth = totalHealth;
		this.currentEnergy = currentEnergy;
		this.totalEnergy = totalEnergy;
		this.critChance = critChance;
		this.potency = potency;
		this.dodge = dodge;
		this.turnMeter = turnMeter;
	}

	@Override
	public boolean applyEffect(Character reciever) {
		if(hasBuffDebuff(reciever)){
			return false;
		}
		if(additive){
			
		} else {
			
		}
		return true;
	}

	@Override
	public boolean reverseEffect(Character reciever) {
		
		return true;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(int baseHealth) {
		this.baseHealth = baseHealth;
	}

	public int getBaseEnergy() {
		return baseEnergy;
	}

	public void setBaseEnergy(int baseEnergy) {
		this.baseEnergy = baseEnergy;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getBasePhysicalDamage() {
		return basePhysicalDamage;
	}

	public void setBasePhysicalDamage(int basePhysicalDamage) {
		this.basePhysicalDamage = basePhysicalDamage;
	}

	public int getBaseMagicDamage() {
		return baseMagicDamage;
	}

	public void setBaseMagicDamage(int baseMagicDamage) {
		this.baseMagicDamage = baseMagicDamage;
	}

	public int getBaseHealing() {
		return baseHealing;
	}

	public void setBaseHealing(int baseHealing) {
		this.baseHealing = baseHealing;
	}

	public int getBaseHealability() {
		return baseHealability;
	}

	public void setBaseHealability(int baseHealability) {
		this.baseHealability = baseHealability;
	}

	public double getBasePhysicalDefence() {
		return basePhysicalDefence;
	}

	public void setBasePhysicalDefence(double basePhysicalDefence) {
		this.basePhysicalDefence = basePhysicalDefence;
	}

	public double getBaseMagicDefence() {
		return baseMagicDefence;
	}

	public void setBaseMagicDefence(double baseMagicDefence) {
		this.baseMagicDefence = baseMagicDefence;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getTotalHealth() {
		return totalHealth;
	}

	public void setTotalHealth(int totalHealth) {
		this.totalHealth = totalHealth;
	}

	public int getCurrentEnergy() {
		return currentEnergy;
	}

	public void setCurrentEnergy(int currentEnergy) {
		this.currentEnergy = currentEnergy;
	}

	public int getTotalEnergy() {
		return totalEnergy;
	}

	public void setTotalEnergy(int totalEnergy) {
		this.totalEnergy = totalEnergy;
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public double getPotency() {
		return potency;
	}

	public void setPotency(double potency) {
		this.potency = potency;
	}

	public double getDodge() {
		return dodge;
	}

	public void setDodge(double dodge) {
		this.dodge = dodge;
	}

	public int getTurnMeter() {
		return turnMeter;
	}

	public void setTurnMeter(int turnMeter) {
		this.turnMeter = turnMeter;
	}

	@Override
	public boolean overTimeEffect(Character reciever) {
		// TODO Auto-generated method stub
		return false;
	}

}
