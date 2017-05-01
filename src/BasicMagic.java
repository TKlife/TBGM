
public class BasicMagic extends Ability {

	public BasicMagic(Character owner, double energyCost, double multiplier) {
		super(owner, energyCost, multiplier);
	}

	@Override
	public void applyEffect(Character affected) {
		// TODO Auto-generated method stub
		int damage = (int) (owner.magicalAttack()*multiplier);
		System.out.print("Total Damage: " + damage);
		affected.defendMagicalAttack(damage);
		owner.setCurrentEnergy((int) (owner.getCurrentEnergy()-(owner.getTotalEnergy()*energyCost)));
	}

}
