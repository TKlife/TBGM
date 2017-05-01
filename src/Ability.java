
public abstract class Ability {
	
	Character owner;
	double energyCost, multiplier;
	
	public Ability(Character owner, double energyCost, double multiplier) {
		this.owner = owner;
		this.energyCost = energyCost;
		this.multiplier = multiplier;
	}

	public abstract void applyEffect(Character affected);
}
