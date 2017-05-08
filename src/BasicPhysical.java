
public class BasicPhysical extends Ability{

	public BasicPhysical(Character owner, double energyCost, double multipler, int cooldown, int maxLevel) {
		super(owner, energyCost, multipler, cooldown, maxLevel);
	}

	public void useAbility(Character affected) {
		int damage = (int) (owner.physicalAttack()*multiplier);
		System.out.print("Total Damage: " + damage);
		hit = affected.defendPhysicalAttack(damage);
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
