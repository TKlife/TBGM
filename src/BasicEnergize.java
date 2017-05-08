
public class BasicEnergize extends Ability {

	public BasicEnergize(Character owner, double energyCost, double multiplier, int cooldown,  int maxLevel) {
		super(owner, energyCost, multiplier, cooldown, maxLevel);
	}

	@Override
	public void useAbility(Character affected) {
		int energy = (int) (owner.getTotalEnergy()*multiplier);
		System.out.print("Total Energy: " + energy + "\n");
		affected.setCurrentEnergy(affected.getCurrentEnergy() + energy);
		owner.setCurrentEnergy((int) (owner.getCurrentEnergy()-(owner.getTotalEnergy()*energyCost)));
		cooldownCounter = cooldown;
	}

	@Override
	public void levelUp() {
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
