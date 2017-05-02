import java.util.ArrayList;
import java.util.Random;

public abstract class Character {
	
	public static Random random = new Random();
	int level;
	int baseHealth, baseEnergy, baseSpeed; 
	int basePhysicalDamage, baseMagicDamage, baseHealing, baseHealability;
	double basePhysicalDefence, baseMagicDefence;
	int currentHealth, totalHealth, currentEnergy, totalEnergy;
	double critChance, potency, dodge;
	int turnMeter;
	String name;
	Stats playerStats;
	ArrayList<Ability> abilities;
	ArrayList<BuffDebuff> buffs;
	ArrayList<BuffDebuff> debuffs;
	
	public Character(String name, int baseHealth, int baseEnergy, int baseSpeed, int basePysicalDamage, int baseMagicDamage, int baseHealing, int baseHealability,
			double basePhysicalDefence, double baseMagicDefence, double critChance, double potency, double dodge, Stats playerStats) {
		super();
		this.name = name;
		this.baseHealth = baseHealth;
		this.baseEnergy = baseEnergy;
		this.baseSpeed = baseSpeed;
		this.basePhysicalDamage = basePysicalDamage;
		this.baseMagicDamage = baseMagicDamage;
		this.baseHealing = baseHealing;
		this.baseHealability = baseHealability;
		this.basePhysicalDefence = basePhysicalDefence;
		this.baseMagicDefence = baseMagicDefence;
		this.critChance = critChance;
		this.potency = potency;
		this.dodge = dodge;
		this.playerStats = playerStats;
		
		this.playerStats.levelUp();
		level = 1;
		this.totalHealth = playerStats.getHealth() + baseHealth;
		this.currentHealth = totalHealth;
		this.totalEnergy = playerStats.getEnergy() + baseEnergy;
		this.currentEnergy = totalEnergy;
		turnMeter = 0;
		abilities = new ArrayList();
		buffs = new ArrayList();
		debuffs = new ArrayList();
	}
	
	public void endTurn(){
		resetTurnMeter();
	}
	
	public void takeDamage(int damage){
		setCurrentHealth(currentHealth - damage);
		if (currentHealth <= 0){
			currentHealth = 0;
		}
	}
	
	public void recoverHealth(int recover){
		setCurrentHealth(currentHealth + recover);
		if (currentHealth >= totalHealth){
			currentHealth = totalHealth;
		}
	}
	
	public void useAbility(int index, ArrayList<Character> useOn){
		abilities.get(index).useAbility(useOn);
	}
	
	public void useAbility(Ability ability, ArrayList<Character> useOn){
		if(abilities.contains(ability)){
				ability.useAbility(useOn);
		}
	}
	
	public void recieveHeal(int health){
		double luck = random.nextInt(50) + 25;
		double tired = 62.5 * ((double)(currentEnergy)/(double)(totalEnergy));
		health = (int) (health+ (getHealability()*((luck+tired)/100.0)));
		System.out.print(" | After Healability: " + health + "\n");
		recoverHealth(health);
	}
	
	public void defendPhysicalAttack(int damage){
		damage = (int) (damage - damage*getPhysicalDefence());
		System.out.print(" | After Defence: " + damage + "\n");
		takeDamage(damage);
	}
	
	public void defendMagicalAttack(int damage){
		damage = (int) (damage - damage*getMagicDefence());
		System.out.print(" | After Defence: " + damage + "\n");
		takeDamage(damage);
	}
	
	public int heal(){
		double luck = random.nextInt(25) + 62.5;
		double tired = 62.5 * ((double)(currentEnergy)/(double)(totalEnergy));
		System.out.printf("Luck: %.0f | Tired %.0f -> %.2f\n", luck, tired, luck+tired);
		return (int) (getHealing()*((luck+tired)/100.0));
	}
	
	public int physicalAttack(){
		double luck = random.nextInt(25) + 62.5;
		double tired = 62.5 * ((double)(currentEnergy)/(double)(totalEnergy));
		System.out.printf("Luck: %.0f | Tired %.0f -> %.2f\n", luck, tired, luck+tired);
		return (int) (getPhysicalDamage()*((luck+tired)/100));
	}
	
	public int magicalAttack(){
		double luck = random.nextInt(25) + 62.5;
		double tired = 62.5 * ((double)(currentEnergy)/(double)(totalEnergy));
		System.out.printf("Luck: %.0f | Tired %.0f -> %.2f\n", luck, tired, luck+tired);
		return (int) (getMagicalDamage()*((luck+tired)/100));
	}
	
	public int getHealing(){
		return playerStats.getHealing() + baseHealing;
	}
	
	public int getHealability(){
		return playerStats.getHealability() + baseHealability;
	}
	
	public int getPhysicalDamage(){
		return playerStats.getPhysicalDamage() + basePhysicalDamage;
	}
	
	public double getPhysicalDefence(){
		return playerStats.getPhysicalDefence() + basePhysicalDefence;
	}
	
	public int getMagicalDamage(){
		return playerStats.getMagicDamage() + baseMagicDamage;
	}
	
	public double getMagicDefence(){
		return playerStats.getMagicDefence() + baseMagicDefence;
	}
	
	public void resetTurnMeter(){
		turnMeter = 1000 - getSpeed();
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
	public int getSpeed() {
		return playerStats.getSpeed() + baseSpeed;
	}
	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}
	public int getBasePysicalDamage() {
		return basePhysicalDamage;
	}
	public void setBasePysicalDamage(int basePysicalDamage) {
		this.basePhysicalDamage = basePysicalDamage;
	}
	public int getBaseMagicDamage() {
		return baseMagicDamage;
	}
	public void setBaseMagicDamage(int baseMagicDamage) {
		this.baseMagicDamage = baseMagicDamage;
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
	public void setBaseMagicDefence(double baseMagicalDefence) {
		this.baseMagicDefence = baseMagicalDefence;
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
		if(this.currentEnergy < 0){
			this.currentEnergy = 0;
		}
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
	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		Character.random = random;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBasePhysicalDamage() {
		return basePhysicalDamage;
	}

	public void setBasePhysicalDamage(int basePhysicalDamage) {
		this.basePhysicalDamage = basePhysicalDamage;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stats getPlayerStats() {
		return playerStats;
	}
	public void setPlayerStats(Stats playerStats) {
		this.playerStats = playerStats;
	}
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
	}
	
	public void addAbility(Ability ability){
		abilities.add(ability);
	}
	
	public ArrayList<BuffDebuff> getBuffs() {
		return buffs;
	}
	
	public BuffDebuff getBuff(int index) {
		return buffs.get(index);
	}

	public void setBuffs(ArrayList<BuffDebuff> buffs) {
		this.buffs = buffs;
	}

	public ArrayList<BuffDebuff> getDebuffs() {
		return debuffs;
	}
	
	public BuffDebuff getDebuff(int index) {
		return debuffs.get(index);
	}
	
	public void setDebuffs(ArrayList<BuffDebuff> debuffs) {
		this.debuffs = debuffs;
	}
	
	public String currentStats(){
		String s = name + "\n";
		s += "Health: " + String.valueOf(getCurrentHealth());
		s += " | Energy: " + String.valueOf(getCurrentEnergy()) + "\n";
		s += "Buffs: ";
		for (BuffDebuff buff: buffs){
			s += buff.getName() + " | ";
		} s += "\n";
		s += "debuffs: ";
		for (BuffDebuff debuff: debuffs){
			s += debuff.getName() + " | ";
		} s += "\n";
		return s;
	}
	
	@Override
	public String toString(){
		String s = name + "\n";
		s += "Health: " + String.valueOf(getCurrentHealth());
		s += " | Energy: " + String.valueOf(getCurrentEnergy()) + "\n";
		s += String.format("Stars: %d, Agi: %f, Dex: %f, Eth: %f, Int: %f, Psc: %f, Stam: %f, Str: %f\nHealth: %d | Energy: %d | Speed: %d\n"
				+ "Pysical Damage: %d | Magic Damgage: %d | Healing: %d | Healability: %d\nPysical Defence: %.2f%% | Magic Defence: %.2f%%\n\n", playerStats.starCount, 
				playerStats.agility, playerStats.dexterity, playerStats.ethics, playerStats.intellect, playerStats.psychotics, playerStats.stamina, playerStats.strength, 
				totalHealth, totalEnergy, playerStats.getSpeed() + baseSpeed, playerStats.getPhysicalDamage() + basePhysicalDamage, playerStats.getMagicDamage() + baseMagicDamage, 
				playerStats.getHealing() + baseHealing, playerStats.getHealability() + baseHealability, 100*getPhysicalDefence(), 100*getMagicDefence());
		return s;
		
	}
}
