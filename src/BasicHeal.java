
public class BasicHeal extends Ability{

	public BasicHeal(Character owner, double energyCost, double multiplier, int cooldown, int maxLevel) {
		super(owner, energyCost, multiplier, cooldown, maxLevel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useAbility(Character affected) {
		int heal = (int) (owner.heal()*multiplier);
		System.out.print("Total Heal: " + heal);
		affected.recieveHeal(heal);
		owner.setCurrentEnergy((int) (owner.getCurrentEnergy()-(owner.getTotalEnergy()*energyCost)));
		cooldownCounter = cooldown;
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
