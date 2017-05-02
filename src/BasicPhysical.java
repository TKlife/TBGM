
public class BasicPhysical extends Ability{

	public BasicPhysical(Character owner, double energyCost, double multipler) {
		super(owner, energyCost, multipler);
	}

	public void useAbility(Character affected) {
		int damage = (int) (owner.physicalAttack()*multiplier);
		System.out.print("Total Damage: " + damage);
		affected.defendPhysicalAttack(damage);
		owner.setCurrentEnergy((int) (owner.getCurrentEnergy()-(owner.getTotalEnergy()*energyCost)));
	}
	
	
}
