
public class BasicHeal extends Ability{

	public BasicHeal(Character owner, double energyCost, double multiplier) {
		super(owner, energyCost, multiplier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useAbility(Character affected) {
		int heal = (int) (owner.heal()*multiplier);
		System.out.print("Total Heal: " + heal);
		affected.recieveHeal(heal);
		owner.setCurrentEnergy((int) (owner.getCurrentEnergy()-(owner.getTotalEnergy()*energyCost)));
	}

}
