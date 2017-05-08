import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Character implements Serializable{
	
	public static Random random = new Random();
	int level;
	int baseHealth, baseEnergy, baseSpeed; 
	int basePhysicalDamage, baseMagicDamage, baseHealing, baseHealability;
	double basePhysicalDefence, baseMagicDefence;
	int currentHealth, totalHealth, currentEnergy, totalEnergy;
	double critChance, potency, tenacity, evasion, counter;
	int turnMeter;
	String name;
	Stats playerStats;
	ArrayList<Ability> abilities;
	ArrayList<BuffDebuff> buffs;
	ArrayList<BuffDebuff> debuffs;
	
	public Character(String name, int baseHealth, int baseEnergy, int baseSpeed, int basePysicalDamage, int baseMagicDamage, int baseHealing, int baseHealability,
			double basePhysicalDefence, double baseMagicDefence, double critChance, double potency, double evasion, Stats playerStats) {
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
		this.evasion = evasion;
		this.playerStats = playerStats;
		
		levelUp();
		this.totalHealth = playerStats.getHealth() + baseHealth;
		this.currentHealth = totalHealth;
		this.totalEnergy = playerStats.getEnergy() + baseEnergy;
		this.currentEnergy = totalEnergy;
		turnMeter = 0;
		abilities = new ArrayList();
		buffs = new ArrayList();
		debuffs = new ArrayList();
	}
	public void levelUp(){
		playerStats.levelUp();
		totalHealth = baseHealth + playerStats.getHealth();
		currentHealth = totalHealth;
		totalEnergy = baseEnergy + playerStats.getEnergy();
		currentEnergy = totalEnergy;
		level++;
	}
	
	public void levelUp(int amount){
		for (int i = 0; i < amount; i++){
			levelUp();
		}
	}
	
	public void unlockAbility(int index){
		abilities.get(index).unLock();
	}
	
	public void levelAbility(int index){
		abilities.get(index).levelUp();
	}
	
	public void maxOut(){
		while(playerStats.starCount < 7){
			promote();
		}
		for (Ability ability: abilities){
			ability.unLock();
			while(ability.getLevel() < ability.getMaxLevel()){
				levelAbility(abilities.indexOf(ability));
			}
		}
	}
	
	public void promote(){
		playerStats.promote();
		totalHealth = baseHealth + playerStats.getHealth();
		currentHealth = totalHealth;
		totalEnergy = baseEnergy + playerStats.getEnergy();
		currentEnergy = totalEnergy;
	}
	
	public void startTurn(){
		for (BuffDebuff buff: buffs){
			buff.onStart(this);
		}
		for (BuffDebuff debuff: debuffs){
			debuff.onStart(this);
		}
	}
	
	public void endTurn(){
		resetTurnMeter();
		ArrayList<BuffDebuff> remove = new ArrayList();

		for (BuffDebuff buff: buffs){
			if(buff.getDuration() <= 1) {
				buff.reverseEffect(this);
				remove.add(buff);
			} else {
				buff.onEnd(this);
				buff.setDuration(buff.getDuration()-1);
			}
		}
		buffs.removeAll(remove);
		remove.clear();
		for (BuffDebuff debuff: debuffs){
			if(debuff.getDuration() <= 0) {
				debuff.reverseEffect(this);
				remove.add(debuff);
			} else {
				debuff.onEnd(this);
				debuff.setDuration(debuff.getDuration()-1);
			}
		}
		for (Ability ability: abilities){
			int cooldownCounter = ability.getCooldownCounter();
			if (cooldownCounter != 0){
				ability.setCooldownCounter(cooldownCounter-1);
			}
		}
		debuffs.removeAll(remove);
	}
	
	public void takeDamage(int damage){
		setCurrentHealth(currentHealth - damage);
		if (currentHealth <= 0){
			currentHealth = 0;
		}
	}
	
	private boolean evadeAttampt() {
		int evasion = random.nextInt(100);
		if(evasion < (int) (this.evasion*100)){
			return true;
		}
		return false;
	}
	
	public void useAbility(int index, Character useOn){
		Ability ability = abilities.get(index);
		useAbility(ability, useOn);
	}
	
	public void useAbility(Ability ability, Character useOn){
		int uses = 1;
		if(abilities.contains(ability)){
			if(this.hasBuff("Duel Wield") != null){
				uses += 1;
			}
			for (int i = 0; i < uses; i++){
				ability.useAbility(useOn);
			}
		}
	}

	public void useAbility(int index, ArrayList<Character> useOn){
		Ability ability = abilities.get(index);
		useAbility(ability, useOn);
	}
	
	public void useAbility(Ability ability, ArrayList<Character> useOn){
		int uses = 1;
		if(abilities.contains(ability)){
			if(this.hasBuff("Duel Wield") != null){
				uses += 1;
			}
			for (int i = 0; i < uses; i++){
				ability.useAbility(useOn);
			}
		}
	}
	
	public void recieveHeal(int health){
		double luck = random.nextInt(50) + 25;
		double tired = 62.5 * ((double)(currentEnergy)/(double)(totalEnergy));
		health = (int) (health+ (getHealability()*((luck+tired)/100.0)));
		System.out.print(" | After Healability: " + health + "\n");
		setCurrentHealth(health);
	}
	
	public boolean defendPhysicalAttack(int damage){
		
		boolean success = true;
		if(!evadeAttampt()){
			damage = (int) (damage - (damage*getPhysicalDefence()));
			System.out.print(" | After Defence: " + damage + "\n");
		} else {
			damage = 0;
			success = false;
			System.out.println("\nEvaded");
			for(BuffDebuff buff: buffs){
				buff.onEvade(this);
			}
			for(BuffDebuff debuff: debuffs){
				debuff.onEvade(this);
			}
		}
		takeDamage(damage);
		return success;
	}
	
	public boolean defendMagicalAttack(int damage){
		
		boolean success = true;
		if(!evadeAttampt()){
			damage = (int) (damage - (damage*getPhysicalDefence()));
			System.out.print(" | After Defence: " + damage + "\n");
		} else {
			damage = 0;
			success = false;
			System.out.println("\nEvaded");
			for(BuffDebuff buff: buffs){
				buff.onEvade(this);
			}
			for(BuffDebuff debuff: debuffs){
				debuff.onEvade(this);
			}
		}
		takeDamage(damage);
		return success;
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
		turnMeter += (1000 - getSpeed());
	}
	
	public BuffDebuff hasBuffDebuff(BuffDebuff buffDebuff){
		if(buffDebuff.isBuff()){
			return hasBuff(buffDebuff.getName());
		} else{
			return hasDebuff(buffDebuff.getName());
		}
		
	}
	
	public BuffDebuff hasBuff(String name){
		for(BuffDebuff buff: buffs){
			if (buff.getName().equals(name)){
				return buff;
			}
		}
		return null;
	}
	
	public BuffDebuff hasDebuff(String name){
		for(BuffDebuff debuff: debuffs){
			if (debuff.getName().equals(name)){
				return debuff;
			}
		}
		return null;
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
		if (this.currentHealth < 0){
			this.currentHealth = 0;
		} else if(this.currentHealth > totalHealth){
			this.currentHealth = totalHealth;
		}
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
		if(this.currentEnergy < -totalEnergy){
			this.currentEnergy = -totalEnergy;
		} else if(this.currentEnergy > 2*totalEnergy){
			this.currentEnergy = totalEnergy;
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
		return evasion;
	}
	public void setDodge(double evasion) {
		this.evasion = evasion;
	}
	public int getTurnMeter() {
		return turnMeter;
	}
	public void setTurnMeter(int turnMeter) {
		this.turnMeter = turnMeter;
		if (this.turnMeter > (1000-getSpeed())){
			resetTurnMeter();
		}
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

	public void addBuff(BuffDebuff buff){
		buffs.add((BuffDebuff) Utilities.copy(buff));
	}
	
	public void setBuffs(ArrayList<BuffDebuff> buffs) {
		this.buffs = buffs;
	}

	public ArrayList<BuffDebuff> getDebuffs() {
		return debuffs;
	}
	
	public void addDebuff(BuffDebuff debuff){
		debuffs.add((BuffDebuff) Utilities.copy(debuff));
	}
	
	public BuffDebuff getDebuff(int index) {
		return debuffs.get(index);
	}
	
	public void setDebuffs(ArrayList<BuffDebuff> debuffs) {
		this.debuffs = debuffs;
	}
	
	public String currentStats(){
		String s = name + ":\n";
		s += "Health: " + String.valueOf(getCurrentHealth());
		s += " | Energy: " + String.valueOf(getCurrentEnergy());
		s += " | Turnmeter: " + String.valueOf(getTurnMeter()) + "\n";
		s += "Buffs: ";
		for (BuffDebuff buff: buffs){
			s += buff.getName() + " " + (buff.getDuration()) + " | ";
		} 
		s += "\n";
		s += "debuffs: ";
		for (BuffDebuff debuff: debuffs){
			s += debuff.getName() +  " " + (debuff.getDuration()) + " | ";
		} 
		//s+= abilitiesString();
		return s +"\n";
	}
	
	public String abilitiesString(){
		String s = "";
		String words[];
		int lineLength = 60, length = 0;
		for (Ability ability: abilities){
			words = ability.toString().split(" ");
			s += "\n" + ability.getName() + ": ";
			length += ability.getName().length();
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
			s += "Cooldown: " + String.valueOf(ability.getCooldownCounter());
			length = 0;
		}
		return s;
	}
	
	@Override
	public String toString(){
		String s = currentStats();
		String words[];
		int lineLength = 60, length = 0;
		s += String.format("Stars: %d, Agi: %.2f, Dex: %.2f, Eth: %.2f, Int: %.2f, Psc: %.2f, Stam: %.2f, Str: %.2f\nHealth: %d | Energy: %d | Speed: %d\n"
				+ "Pysical Damage: %d | Magic Damgage: %d | Healing: %d | Healability: %d\nPysical Defence: %.2f%% | Magic Defence: %.2f%% | Dodge Chance: %.2f%%",
				playerStats.starCount, playerStats.agility, playerStats.dexterity, playerStats.ethics, playerStats.intellect, playerStats.psychotics, 
				playerStats.stamina, playerStats.strength, totalHealth, totalEnergy, playerStats.getSpeed() + baseSpeed, playerStats.getPhysicalDamage()+basePhysicalDamage, 
				playerStats.getMagicDamage() + baseMagicDamage, playerStats.getHealing() + baseHealing, playerStats.getHealability() + baseHealability, 
				100*getPhysicalDefence(), 100*getMagicDefence(), 100*evasion);
		return s + "\n\n";
		
	}
}
