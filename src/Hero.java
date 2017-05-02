import java.util.ArrayList;

public class Hero extends Character{

	
	public Hero(String name, int baseHealth, int baseEnergy, int baseSpeed, int basePysicalDamage,
			int baseMagicDamage, int baseHealing, int baseHealability, double basePhysicalDefence, 
			double baseMagicalDefence, double critChance, double potency, double dodge, Stats playerStats) {
		super(name, baseHealth, baseEnergy, baseSpeed, basePysicalDamage, baseMagicDamage,
			 baseHealing, baseHealability, basePhysicalDefence,  baseMagicalDefence, critChance,
			 potency, dodge, playerStats);
	}
	
	public void levelUp(){
		playerStats.levelUp();
		totalHealth = baseHealth + playerStats.getHealth();
		currentHealth = totalHealth;
		totalEnergy = baseEnergy + playerStats.getEnergy();
		currentEnergy = totalEnergy;
	}
	
	public void promote(){
		playerStats.promote();
		totalHealth = baseHealth + playerStats.getHealth();
		currentHealth = totalHealth;
		totalEnergy = baseEnergy + playerStats.getEnergy();
		currentEnergy = totalEnergy;
	}
	
}
