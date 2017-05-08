
public class BasicMagic extends Ability {

	public BasicMagic(Character owner, double energyCost, double multiplier, int cooldown, int maxLevel) {
		super(owner, energyCost, multiplier, cooldown, maxLevel);
	}

	@Override
	public void useAbility(Character affected) {
		// TODO Auto-generated method stub
		int damage = (int) (owner.magicalAttack()*multiplier);
		System.out.print("Total Damage: " + damage);
		hit = affected.defendMagicalAttack(damage);
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
