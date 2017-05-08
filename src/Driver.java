import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Driver {

	public static Binder<BuffDebuff> buffBinder = new Binder();
	public static Binder<Effect> effectsBinder = new Binder();
	public static Binder<Ability> abilityBinder = new Binder();
	public static Binder<Character> characterBinder = new Binder();
	public static Scanner stdin = new Scanner(System.in);
	public static Random random = new Random();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		populateBuffs();
		populateAbilities();
		populateCharacters();
		
		Character tydus = characterBinder.getItem("Tydus");
		Character teslon = characterBinder.getItem("Teslon");
		
		tydus.levelUp(69);
		tydus.levelAbility(0);
		tydus.levelAbility(1);
		teslon.levelUp(69);
		teslon.levelAbility(0);
		teslon.levelAbility(1);
		
		tydus.maxOut();
		teslon.maxOut();
		
		tydus.resetTurnMeter();
		teslon.resetTurnMeter();
		
		System.out.println(tydus.toString() + teslon.toString());
		while(teslon.getCurrentHealth() > 0 && tydus.getCurrentHealth() > 0){
			
			int j = 0;
			int teslonTurn = 0;
			int tydusTurn = 0;
			Character min = null;
			ArrayList<Character> turn = new ArrayList();
			ArrayList<Character> stage = new ArrayList();
			stage.add(teslon);
			stage.add(tydus);
			for(Character hero: stage){
				if(j == 0){
					min = hero;
					turn.clear();
					turn.add(hero);
				} else {
					if (min.getTurnMeter() > hero.getTurnMeter()){
						min = hero;
						turn.clear();
						turn.add(min);
					} else if(min.getTurnMeter() == hero.getTurnMeter()){
						turn.add(hero);
					}
				}
				j++;
			}
			int turnMeterReduce = min.getTurnMeter();
			for(Character hero: stage){
				hero.setTurnMeter(hero.getTurnMeter() - turnMeterReduce);
			}
			Collections.shuffle(turn);
			System.out.println();
			for(Character c: stage){
				System.out.println(c.toString());
			}
			for(Character toGo: turn){
				System.out.print(toGo.currentStats() + toGo.abilitiesString() + "\nAbility Index: ");
				int index = stdin.nextInt();
				System.out.println();
				toGo.startTurn();
				if(toGo.getName().equals(tydus.getName())){
					toGo.useAbility(index, teslon);
					tydusTurn++;
				} else {
					toGo.useAbility(index, tydus);
					teslonTurn++;
				}
				toGo.endTurn();
			}
		}
		System.out.println(tydus.toString() + teslon.toString());
		
	}
	
	public static void populateCharacters(){
		
		String name = "Tydus";
		Character character = new Character(name, 110, 50, 110, 30, 15, 0, 0, .01, .01, .10, .10, .10, 
				new Stats(4, 2.5, 5.7, 4.8, 2.9, 4.2, 3.8, 4.9));
		character.addAbility(abilityBinder.getItem("Wild Slashing"));
		character.addAbility(abilityBinder.getItem("Quantium Blades"));
		character.getAbilities().get(0).setOwner(character);
		character.getAbilities().get(1).setOwner(character);
		characterBinder.addEntry(name, character);
		
		name = "Teslon";
		character = new Character(name, 200, 50, 95, 15, 15, 0, 10, .05, .05, .05, .10, .10, 
				new Stats(4, 2.9, 3.5, 5.0, 5.7, 3.2, 4.4, 3.2));
		character.addAbility(abilityBinder.getItem("Intimidating Rush"));
		character.addAbility(abilityBinder.getItem("Weapon Entangle"));
		character.getAbilities().get(0).setOwner(character);
		character.getAbilities().get(1).setOwner(character);
		characterBinder.addEntry(name, character);
	}
	
	public static void populateAbilities(){
		
		String name;
		Ability ability;
		
		name = "Wild Slashing";
		ability = new BasicPhysical(null, .08, 1.4, 0, 9){
			private int attackCounter = 0, totalAttacks = 2, hitAgain = 50;
			private double repeateMultipler = 0.5;
			@Override
			public void useAbility(Character affected){
				double oldMultiplier = this.getMultiplier();
				
				attackCounter++;
				super.useAbility(affected);
				while (random.nextInt(100) <= hitAgain && attackCounter < totalAttacks){
					attackCounter++;
					this.setMultiplier(oldMultiplier*repeateMultipler);
					super.useAbility(affected);
					this.setMultiplier(oldMultiplier);
				}
			}
			@Override
			public void levelUp(){
				double oldMultiplier = this.getMultiplier();
				switch(level){
				case 1:
					this.setMultiplier(oldMultiplier*1.1);
					break;
				case 2:
					hitAgain += 15;
					break;
				case 3:
					this.setMultiplier(oldMultiplier *1.15);
					break;
				case 4:
					repeateMultipler += .15;
					break;
				case 5:
					this.setMultiplier(oldMultiplier *1.1);
					break;
				case 6:
					hitAgain += 15;
					break;
				case 7:
					this.setMultiplier(oldMultiplier *1.1);
					break;
				case 8:
					repeateMultipler += .15;
					break;
				default:
					break;
				}
				if (level < maxLevel){
					level++;
				}
			}
			public String toString(){
				String s = String.format("Deal Physical damage with a %d%% chance to attack again. The second attack does %.0f%% damage",
							hitAgain, repeateMultipler*100);
				return s;
			}
		};
		ability.setName(name);
		abilityBinder.addEntry(name, ability);
		
		name = "Quantium Blades";
		ability = new Ability(null, .15, 0.1, 6, 9){
			BuffDebuff duelWield = buffBinder.getItem("Duel Wield");
			BuffDebuff offenceUp = null;
			@Override
			public void useAbility(Character affected) {
				duelWield.applyEffect(owner);
				owner.setTurnMeter((int) (owner.getTurnMeter() - (1000-owner.getSpeed())*multiplier));
				if (offenceUp != null){
					offenceUp.applyEffect(owner);
				}
				cooldownCounter = cooldown;
			}
			@Override
			public void levelUp() {
				double oldMultipler = this.getMultiplier();
				switch(level){
				case 0:
					duelWield.setDuration(2);
				case 1:
					setMultiplier(oldMultipler + 0.1);
					break;
				case 2:
					setMultiplier(oldMultipler + 0.1);
					break;
				case 3:
					duelWield.setDuration(duelWield.getDuration() + 1);
					break;
				case 4:
					setMultiplier(oldMultipler + 0.1);
					break;
				case 5:
					cooldown -= 1;
					setMultiplier(oldMultipler + 0.1);
					break;
				case 6:
					setMultiplier(oldMultipler + 0.1);
					break;
				case 7:
					setMultiplier(oldMultipler + 0.1);
					break;
				case 8:
					offenceUp = buffBinder.getItem("Offence Up");
					offenceUp.setDuration(3);
					break;
				default:
					break;
				}
				if (level < maxLevel){
					level++;
				}
			}
			@Override
			public String toString() {
				String s = String.format("Grants %s Duel Wield for %d turns and %.0f%% turn meter", owner.getName(), duelWield.getDuration()-1,
						multiplier*100);
				if(offenceUp != null){ 
					s+= ". ";
					s += String.format("Also grants Offence Up for %d turns", offenceUp.getDuration()-1);
				}
				return s;
			}
			
		};
		ability.setName(name);
		abilityBinder.addEntry(name, ability);
		
		name = "Intimidating Rush";
		ability = new BasicPhysical(null, .08, 1.4, 0, 9){
			int chance = 25;
			double reduceAmount = 0.10;
			@Override
			public void useAbility(Character affected){
				super.useAbility(affected);
				if(random.nextInt(100) < 25){
					affected.setTurnMeter((int) (affected.getTurnMeter() + ((1000-affected.getSpeed())*reduceAmount)));
				}
			}
			@Override
			public void levelUp(){
				double oldMultiplier = this.getMultiplier();
				switch(level){
				case 1:
					setMultiplier(oldMultiplier * 1.1);
					break;
				case 2:
					reduceAmount += .15;
					break;
				case 3:
					setMultiplier(oldMultiplier * 1.1);
					break;
				case 4:
					chance += 10;
					break;
				case 5:
					setMultiplier(oldMultiplier * 1.1);
					break;
				case 6:
					reduceAmount += .15;
					break;
				case 7:
					setMultiplier(oldMultiplier * 1.15);
					break;
				case 8:
					chance += 15;
					break;
				default:
					break;
				}
				if (level < maxLevel){
					level++;
				}
			}
			@Override
			public String toString(){
				String s = "Deal physical damage with a %d%% to remove %.0f%% turnmeter";
				s = String.format(s, chance, reduceAmount*100);
				return s;
			}
		};
		ability.setName(name);
		abilityBinder.addEntry(name, ability);
		
		name = "Weapon Entangle";
		ability = new BasicPhysical(null, .15, 1.5, 3, 9){
			BuffDebuff defenceDown = buffBinder.getItem("Defence Down");
			
			@Override
			public void useAbility(Character affected) {
				super.useAbility(affected);
				if(hit){
					defenceDown.applyEffect(affected);
				}
			}
			@Override
			public void levelUp() {
				double oldMultipler = this.getMultiplier();
				switch(level){
				case 0:
					defenceDown.setDuration(1);
				case 1:
					setMultiplier(oldMultipler*1.1);
					break;
				case 2:
					setMultiplier(oldMultipler*1.1);
					break;
				case 3:
					defenceDown.setDuration(defenceDown.getDuration() + 1);
					break;
				case 4:
					setMultiplier(oldMultipler*1.1);
					break;
				case 5:
					cooldown -= 1;
					break;
				case 6:
					setMultiplier(oldMultipler*1.1);
					break;
				case 7:
					setMultiplier(oldMultipler*1.1);
					break;
				case 8:
					defenceDown.setDuration(defenceDown.getDuration() + 1);
					break;
				default:
					break;
				}
				if (level < maxLevel){
					level++;
				}
			}
			@Override
			public String toString() {
				return "Deals Physical damage to target enemy and inflicts them with defence down";
			}
			
		};
		ability.setName(name);
		abilityBinder.addEntry(name, ability);
		
	}
	
	public static void populateBuffs(){
		
		String effectName, buffName;
		BuffDebuff buffDebuff;
		
		effectName = "Defence Down";
		effectsBinder.addEntry(effectName, new Effect(effectName, 1, -0.5, false, false){
			private double oldMagicDefence, oldPhysicalDefence;
			@Override
			public void apply(Character affected) {
				oldMagicDefence = affected.getBaseMagicDefence();
				oldPhysicalDefence = affected.getBasePhysicalDefence();
				affected.setBaseMagicDefence(oldMagicDefence+adder);
				affected.setBasePhysicalDefence(oldPhysicalDefence+adder);
			}@Override
			public void reverse(Character affected) {
				// TODO Auto-generated method stub
				affected.setBaseMagicDefence(oldMagicDefence);
				affected.setBasePhysicalDefence(oldPhysicalDefence);
			}
			@Override
			public void overTime(Character affected) {
				// TODO Auto-generated method stub
			}
			@Override
			public String toString() {
				String s = "Character has -50% defence";
				return s;
			}
			@Override
			public void onDamage(Character affected) {
				// TODO Auto-generated method stub
				
			}
		});
		buffDebuff = new BuffDebuff(effectName, false, false, 2);
		buffDebuff.addEffect(effectsBinder.getItem("Defence Down"));
		buffBinder.addEntry(effectName,buffDebuff);
		
		effectName = "Defence Up";
		effectsBinder.addEntry(effectName, new Effect(effectName, 1, 0.5, false, false){
			private double oldMagicDefence, oldPhysicalDefence;
			
			@Override
			public void apply(Character affected) {
				oldMagicDefence = affected.getBaseMagicDefence();
				oldPhysicalDefence = affected.getBasePhysicalDefence();
				affected.setBaseMagicDefence(oldMagicDefence+adder);
				affected.setBasePhysicalDefence(oldPhysicalDefence+adder);
			}
			@Override
			public void reverse(Character affected) {
				affected.setBaseMagicDefence(oldMagicDefence);
				affected.setBasePhysicalDefence(oldPhysicalDefence);
			}
			@Override
			public void overTime(Character affected) {
				
			}
			@Override
			public String toString() {
				String s = "";
				s += "Character has +50% defence";
				return s;
			}
			@Override
			public void onDamage(Character affected) {
				// TODO Auto-generated method stub
				
			}
		});
		buffDebuff =  new BuffDebuff(effectName, true, false, 4);
		buffDebuff.addEffect(effectsBinder.getItem(effectName));
		buffBinder.addEntry(effectName, buffDebuff);
		
		effectName = "Berzerk";
		effectsBinder.addEntry(effectName, new Effect(effectName, 1, 0, true, false){
			private double oldStrength;
			@Override
			public void apply(Character affected) {
				oldStrength = affected.getPlayerStats().getStrength();
				affected.getPlayerStats().setStrength(oldStrength*1.5);
			}
			@Override
			public void reverse(Character affected) {
				affected.getPlayerStats().setStrength(oldStrength);
			}
			@Override
			public void overTime(Character affected) {
				affected.setCurrentEnergy(affected.getCurrentEnergy() + ((int) (affected.getTotalEnergy()*0.1)));
				
			}
			@Override
			public String toString() {
				String s = "";
				s += "Character gains 50% more strength and restors 10% of total energy on the start of each turn";
				return s;
			}
			@Override
			public void onDamage(Character affected) {
				// TODO Auto-generated method stub
				
			}
		});
		buffDebuff =  new BuffDebuff(effectName, true, false, 2);
		buffDebuff.addEffect(effectsBinder.getItem(effectName));
		buffBinder.addEntry(effectName, buffDebuff);
		
		effectName = "Calm Mind";
		effectsBinder.addEntry(effectName, new Effect(effectName, 1, 0, false, false){
			private double oldIntellect, oldBaseSpeed;
			@Override
			public void apply(Character affected) {
				oldIntellect = affected.getPlayerStats().getIntellect();
				oldBaseSpeed = affected.getBaseSpeed();
				affected.getPlayerStats().setIntellect(oldIntellect * 1.5);
				affected.setTurnMeter((int) (affected.getTurnMeter() - affected.getSpeed()*0.1));
				affected.setBaseSpeed((int) (affected.getSpeed()*.1 + oldBaseSpeed));
			}
			@Override
			public void reverse(Character affected) {
				affected.getPlayerStats().setIntellect(oldIntellect);
				affected.setBaseSpeed((int) (oldBaseSpeed));
				affected.setTurnMeter((int) (affected.getTurnMeter() + affected.getSpeed()*0.1));
			}
			@Override
			public void overTime(Character affected) {
				
			}
			@Override
			public String toString() {
				String s = "Grants 50% more Intellect and 10% more speed";
				return s;
			}
			@Override
			public void onDamage(Character affected) {
				// TODO Auto-generated method stub
				
			}
			
		});
		buffDebuff = new BuffDebuff(effectName, true, false, 2);
		buffDebuff.addEffect(effectsBinder.getItem(effectName));
		buffBinder.addEntry(effectName, buffDebuff);
		
		buffName = "Stun";
		buffBinder.addEntry(buffName, new BuffDebuff(buffName, false, false, 1));
		
		buffName = "Duel Wield";
		buffBinder.addEntry(buffName, new BuffDebuff(buffName, true, false, 1));
		
		effectName = "Offence Up";
		effectsBinder.addEntry(effectName, new Effect(effectName, 0.5, 0, false, false){
			double oldBasePhysicalDamage, oldBaseMagicDamage;
			@Override
			public void apply(Character affected) {
				oldBasePhysicalDamage = affected.getBasePhysicalDamage();
				oldBaseMagicDamage = affected.getBaseMagicDamage();
				affected.setBasePhysicalDamage((int) (oldBasePhysicalDamage + affected.getPhysicalDamage()*multiplier));
				affected.setBaseMagicDamage((int) (oldBaseMagicDamage + affected.getMagicalDamage()*multiplier));
			}
			@Override
			public void reverse(Character affected) {
				affected.setBasePhysicalDamage((int) (oldBasePhysicalDamage));
				affected.setBaseMagicDamage((int) (oldBaseMagicDamage));
			}
			@Override
			public void overTime(Character affected) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onDamage(Character affected) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public String toString() {
				return "Grants player +50% Physical and Magic Damage";
			}
			
		});
		buffDebuff = new BuffDebuff(effectName, true, false, 2);
		buffDebuff.addEffect(effectsBinder.getItem(effectName));
		buffBinder.addEntry(effectName, buffDebuff);
		
		effectName = "Offence Down";
		effectsBinder.addEntry(effectName, new Effect(effectName, -0.5, 0, false, false){
			double oldBasePhysicalDamage, oldBaseMagicDamage;
			@Override
			public void apply(Character affected) {
				oldBasePhysicalDamage = affected.getBasePhysicalDamage();
				oldBaseMagicDamage = affected.getBaseMagicDamage();
				affected.setBasePhysicalDamage((int) (oldBasePhysicalDamage + affected.getPhysicalDamage()*multiplier));
				affected.setBaseMagicDamage((int) (oldBaseMagicDamage + affected.getMagicalDamage()*multiplier));
			}
			@Override
			public void reverse(Character affected) {
				affected.setBasePhysicalDamage((int) (oldBasePhysicalDamage));
				affected.setBaseMagicDamage((int) (oldBaseMagicDamage));
			}
			@Override
			public void overTime(Character affected) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onDamage(Character affected) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public String toString() {
				return "Grants player +50% Physical and Magic Damage";
			}
			
		});
		buffDebuff = new BuffDebuff(effectName, false, false, 2);
		buffDebuff.addEffect(effectsBinder.getItem(effectName));
		buffBinder.addEntry(effectName, new BuffDebuff(effectName, true, false, 2));
		buffBinder.getItem(effectName).addEffect(effectsBinder.getItem(effectName));
	}
}
