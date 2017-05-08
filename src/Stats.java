import java.awt.geom.Arc2D.Double;
import java.io.Serializable;

public class Stats implements Serializable{
	
	public static int MAXLEVEL = 70; 
	public static double MAXADDER = 6.0;
	public static double STARGROWTH = 1.1;
	
	int starCount;
	double intellect, strength, dexterity, stamina, agility, ethics, psychotics;
	double intelAdder, strAdder, dexAdder, stamAdder, agiAdder, ethAdder, pscAdder;
	
	public Stats(int starCount, int allStats){
		this.starCount = starCount;
		this.intellect = allStats;
		this.strength = allStats;
		this.dexterity = allStats;
		this.stamina = allStats;
		this.agility = allStats;
		this.ethics = allStats;
		this.psychotics = allStats;
	}
	
	
	public Stats(int starCount, int intellect, int strength, int dexterity, int stamina, int agility, int ethics, int psychotics) {
		this.starCount = starCount;
		this.intellect = intellect;
		this.strength = strength;
		this.dexterity = dexterity;
		this.stamina = stamina;
		this.agility = agility;
		this.ethics = ethics;
		this.psychotics = psychotics;
	}
	
	public Stats(int starCount, double intelAdder, double strAdder, double dexAdder, double stamAdder, double agiAdder,
			double ethAdder, double pscAdder) {
		this.starCount = starCount;
		this.intellect = 0;
		this.strength = 0;
		this.dexterity = 0;
		this.stamina = 0;
		this.agility = 0;
		this.ethics = 0;
		this.psychotics = 0;
		this.intelAdder = intelAdder / Math.pow(1.1, 7 - starCount);
		this.strAdder = strAdder / Math.pow(1.1, 7 - starCount);
		this.dexAdder = dexAdder / Math.pow(1.1, 7 - starCount);
		this.stamAdder = stamAdder / Math.pow(1.1, 7 - starCount);
		this.agiAdder = agiAdder / Math.pow(1.1, 7 - starCount);
		this.ethAdder = ethAdder / Math.pow(1.1, 7 - starCount);
		this.pscAdder = pscAdder / Math.pow(1.1, 7 - starCount);
	}


	public Stats(int starCount, int allStats, double allAdder){
		this.starCount = starCount;
		this.intellect = allStats;
		this.strength = allStats;
		this.dexterity = allStats;
		this.stamina = allStats;
		this.agility = allStats;
		this.ethics = allStats;
		this.psychotics = allStats;
		this.intelAdder = allAdder;
		this.strAdder = allAdder;
		this.dexAdder = allAdder;
		this.stamAdder = allAdder;
		this.agiAdder = allAdder;
		this.ethAdder = allAdder;
		this.pscAdder = allAdder;
	}
	
	public Stats(int starCount, int intellect, int strength, int dexterity, int stamina, int agility, int ethics, int psychotics,
			double intelAdder, double strAdder, double dexAdder, double stamAdder, double agiAdder, double ethAdder,
			double pscAdder) {
		this.starCount = starCount;
		this.intellect = intellect;
		this.strength = strength;
		this.dexterity = dexterity;
		this.stamina = stamina;
		this.agility = agility;
		this.ethics = ethics;
		this.psychotics = psychotics;
		this.intelAdder = intelAdder;
		this.strAdder = strAdder;
		this.dexAdder = dexAdder;
		this.stamAdder = stamAdder;
		this.agiAdder = agiAdder;
		this.ethAdder = ethAdder;
		this.pscAdder = pscAdder;
	}
	
	public int getHealth(){
		return  (int) (((1.5)*(stamina))*((0.5)*(dexterity)));
	}
	
	public int getEnergy(){
		return  (int) (((1.5)*(dexterity)) + ((0.5)*(stamina)));
	}
	
	public int getSpeed(){
		return (int) (((0.4)*(agility)));
	}
	
	public double getPhysicalDefence(){
		return (((1.3*stamina)+(.5*strength)+(.2*intellect))/(12*(MAXADDER*MAXLEVEL)));
	}
	
	public double getMagicDefence(){
		return (((1.3*dexterity)+(.5*psychotics)+(.2*ethics))/(12*(MAXADDER*MAXLEVEL)));
	}
	
	public int getPhysicalDamage(){
		return (int) ((2)*(psychotics) + (6)*(strength));
	}
	
	public int getMagicDamage(){
		return (int) ((2)*(strength) + (6)*(psychotics));
	}
	
	public int getHealing(){
		return (int) ((10)*(intellect) + (0.25)*(ethics));
	}
	
	public int getHealability(){
		return (int) ((10)*(ethics) + (0.5)*(intellect));
	}
	
	public void levelUp(){
		
		this.intellect += intelAdder;
		this.strength += strAdder;
		this.agility += agiAdder;
		this.dexterity += dexAdder;
		this.ethics += ethAdder;
		this.stamina += stamAdder;
		this.psychotics += pscAdder;
	}

	public void promote(){
		starCount += 1;
		intellect = intellect*STARGROWTH ;
		strength = strength*STARGROWTH;
		dexterity = dexterity*STARGROWTH;
		stamina =  stamina*STARGROWTH;
		agility = agility*STARGROWTH;
		ethics = ethics*STARGROWTH;
		psychotics = psychotics*STARGROWTH;
		intelAdder *= STARGROWTH;
		strAdder *= STARGROWTH;
		dexAdder *= STARGROWTH;
		stamAdder *= STARGROWTH;
		agiAdder *= STARGROWTH;
		ethAdder  *= STARGROWTH;
		pscAdder *= STARGROWTH;
	}
	
	public double getIntellect() {
		return  intellect;
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
	public double getIntelAdder() {
		return intelAdder;
	}

	public void setIntelAdder(double intelAdder) {
		this.intelAdder = intelAdder;
	}

	public double getStenAdder() {
		return strAdder;
	}

	public void setStenAdder(double strAdder) {
		this.strAdder = strAdder;
	}

	public double getDexAdder() {
		return dexAdder;
	}

	public void setDexAdder(double dexAdder) {
		this.dexAdder = dexAdder;
	}

	public double getStamAdder() {
		return stamAdder;
	}

	public void setStamAdder(double stamAdder) {
		this.stamAdder = stamAdder;
	}

	public double getAgiAdder() {
		return agiAdder;
	}

	public void setAgiAdder(double agiAdder) {
		this.agiAdder = agiAdder;
	}

	public double getEthAdder() {
		return ethAdder;
	}

	public void setEthAdder(double ethAdder) {
		this.ethAdder = ethAdder;
	}

	public double getPscAdder() {
		return pscAdder;
	}

	public void setPscAdder(double pscAdder) {
		this.pscAdder = pscAdder;
	}
	
	private int pow(double a, double b){
		return (int) Math.pow(a, b);
	}
	
	public int getStarCount() {
		return starCount;
	}


	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}


	public double getStrAdder() {
		return strAdder;
	}


	public void setStrAdder(double strAdder) {
		this.strAdder = strAdder;
	}


	@Override
	public String toString(){
		Stats playerStats = this;
		return String.format("Stars: %d, Agi: %f, Dex: %f, Eth: %f, Int: %f, Psc: %f, Stam: %f, Str: %f\nHealth: %d | Energy: %d | Speed: %d\n"
						+ "Pysical Damage: %d | Magic Damgage: %d | Healing: %d | Healability: %d\nPysical Defence: %.2f%% | Magic Defence: %.2f%%\n\n", starCount, 
						playerStats.agility, playerStats.dexterity, playerStats.ethics, playerStats.intellect, playerStats.psychotics, playerStats.stamina, playerStats.strength, 
						playerStats.getHealth(), playerStats.getEnergy(), playerStats.getSpeed(), playerStats.getPhysicalDamage(), playerStats.getMagicDamage(), 
						playerStats.getHealing(), playerStats.getHealability(), 100*playerStats.getPhysicalDefence(), 100*playerStats.getMagicDefence());
	}
	
}
