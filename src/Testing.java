import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import javax.swing.event.ListSelectionEvent;

public class Testing {

	public static Binder<BuffDebuff> buffBinder = new Binder();
	public static Binder<Effect> effectsBinder = new Binder();
	public static Binder<Character> characterBinder = new Binder();
	public static Random random = new Random();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testStats();
		//testCharacter();
		
		testBinder();
		testBuffDebuffAbility();
		
		BuffDebuff buff = buffBinder.getItem(0);
		BuffDebuff buff2 = buffBinder.getItem(0);
		buff2.setName("New Buff");
		System.out.println(buff.getName() + " " + buff2.getName());
	}
	
	private static void testBuffDebuffAbility(){
		
		Hero tank = new Hero("Tank", 175, 25, 90, 15, 0, 0, 5, .05, .05, .25, .10, .10, (new Stats(7, 3.0, 3.9, 5.4, 5.7, 3.5, 4.7, 3.5)));
		Hero support = new Hero("Support", 95, 36, 110, 25, 0, 0, 0, .01, .01, .25, .1, .1, (new Stats(7, 2.8, 3.4, 5.9, 3.9, 5.0, 3.5, 3.5)));
		Hero dps = new Hero("DPS", 95, 36, 110, 25, 0, 0, 0, .01, .01, .25, .1, .1, (new Stats(7, 2.8, 6.4, 4.8, 2.9, 8.5, 3.5, 3.7)));
		Hero healer = new Hero("Healer", 125, 50, 100, 15, 0, 0, 0, .025, .025, .25, .1, .1, (new Stats(7, 5.1, 3.9, 4.6, 3.6, 3.8, 5.7, 2.9)));
		
		tank.levelUp(69);
		support.levelUp(69);
		dps.levelUp(69);
		healer.levelUp(69);
		tank.resetTurnMeter();
		support.resetTurnMeter();
		dps.resetTurnMeter();
		healer.resetTurnMeter();
		
		tank.addAbility(new BasicPhysical(tank, .12, 2.5, 0, 8));
		tank.addAbility(new BasicMagic(tank, .12, 2.5, 0, 8));
		dps.addAbility(new BasicPhysical(dps, .12, 3.2, 0, 8));
		dps.addAbility(new BasicMagic(dps, .12, 2.7, 0, 8));
		support.addAbility(new BasicEnergize(support, .10, .2, 3, 8));
		System.out.println(dps.toString());
		System.out.println(support.toString());
		System.out.println(tank.toString());
		System.out.println(healer.toString());
		
		BuffDebuff defenceUp = (BuffDebuff) Utilities.copy(buffBinder.getItem("Defence Up"));
		BuffDebuff berzerk = (BuffDebuff) Utilities.copy(buffBinder.getItem("Berzerk"));
		BuffDebuff defenceDown = (BuffDebuff) Utilities.copy(buffBinder.getItem("Defence Down"));
		
		defenceUp.applyEffect(dps);
		berzerk.applyEffect(tank);
		defenceDown.applyEffect(tank);
		buffBinder.getItem("Calm Mind").applyEffect(dps);
		System.out.println(dps.toString());
		System.out.println(tank.toString());
		ArrayList<Character> stage = new ArrayList();
		stage.add(tank);
		stage.add(dps);
		stage.add(support);

		Character min = null;
		Stack<Character> turn = new Stack();
		int dpsTurn = 0, tankTurn = 0, k = 0;
		while(tank.getCurrentHealth() > 0 && dps.getCurrentHealth() > 0){
			
			int j = 0;
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
			
			if(turn.contains(support)){
				System.out.println("\nSupports turn");
				if (support.getAbilities().get(0).getCooldownCounter() == 0){
					int whosTurn = random.nextInt(2);
					if(whosTurn == 0){
						support.useAbility(0, dps);
					} else if(whosTurn == 1) {
						support.useAbility(0, tank);
					} else {
						support.useAbility(0, support);
					}
				}
				support.endTurn();
				turn.remove(support);
			}
			
			for(Character toGo: turn){
				int stun = random.nextInt(k+3);
				if(stun == 1){
					buffBinder.getItem("Stun").applyEffect(toGo);
				}
				System.out.println("\n" + dps.currentStats());
				System.out.println(tank.currentStats());
				System.out.println(support.currentStats());
				System.out.println(toGo.getName() + "'s Attacking:\n");
				toGo.startTurn();
				if(toGo.hasDebuff("Stun") == null){
					if(toGo.getName().equals(dps.getName())){
						toGo.useAbility(dpsTurn%2, tank);
						dpsTurn++;
					} else {
						toGo.useAbility(tankTurn%2, dps);
						tankTurn++;
					}
				}
				toGo.endTurn();
				k++;
			}
		}
		System.out.println("\n" + dps.toString());
		System.out.println(tank.toString());
	}
	
	private static void testCharacter(){
		
		Hero tank = new Hero("Tank", 175, 25, 90, 15, 0, 0, 5, .05, .05, .25, .10, .10, (new Stats(2, 3.0, 3.9, 5.4, 5.7, 3.5, 4.7, 3.5)));
		Hero dps = new Hero("DPS", 95, 36, 110, 25, 0, 0, 0, .01, .01, .25, .1, .1, (new Stats(7, 2.8, 5.4, 4.8, 2.9, 4.7, 3.5, 5.7)));
		Hero healer = new Hero("Healer", 125, 50, 100, 15, 0, 0, 0, .025, .025, .25, .1, .1, (new Stats(4, 5.1, 3.9, 4.6, 3.6, 3.8, 5.7, 2.9)));
		tank.resetTurnMeter();
		dps.resetTurnMeter();
		healer.resetTurnMeter();
		
		tank.addAbility(new BasicPhysical(tank, .12, 2.5, 0, 8));
		dps.addAbility(new BasicPhysical(dps, .12, 2.7, 0, 8));
		healer.addAbility(new BasicMagic(healer, .12, 2.4, 0, 8));
		healer.addAbility(new BasicHeal(healer, .12, 2.1, 0, 8));
		
		ArrayList<Character> stage = new ArrayList();
		stage.add(tank);
		stage.add(dps);
		stage.add(healer);
		
		System.out.println("Tank: \n" + tank.toString());
		System.out.println("Healer: \n" + healer.toString());
		System.out.println("DPS: \n" + dps.toString());
		
		
		for (int i = 1; i < 70; i++){
			if(i%20 == 0 && i != 0){
				healer.promote();
			}
			tank.levelUp();
			healer.levelUp();
			dps.levelUp();
		}
		
		tank.promote();
		tank.promote();
		tank.promote();
		tank.promote(); 
		tank.promote();
		
		System.out.println("Tank: \n" + tank.toString());
		System.out.println("Healer: \n" + healer.toString());
		System.out.println("DPS: \n" + dps.toString());
		
		ArrayList<Character> tankUseOn = new ArrayList<Character>();
		ArrayList<Character> dpsUseOn = new ArrayList<Character>();
		tankUseOn.add(dps);
		dpsUseOn.add(tank);
		int i = 1, k = 0;
		Character min = null;
		Stack<Character> turn = new Stack();
		
		tank.resetTurnMeter();
		dps.resetTurnMeter();
		healer.resetTurnMeter();
		
		BuffDebuff buff = new StatModifier("Offence Up", true, false, 4, false, 1, 1.5, 1, 1, 1, 1, 1.5);
		buff.applyEffect(dps);
		while(tank.getCurrentHealth() > 0 && dps.getCurrentHealth() > 0){
			k++;
			int j = 0;
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
			for(Character toGo: turn){
				System.out.printf("Turn Number: %d\nTank: %d\nDPS: %d\nHealer: %d\n\n", k, tank.getTurnMeter(), dps.getTurnMeter(), healer.getTurnMeter());
				if(toGo.getName().equals("Tank")){
					System.out.println("Tank Attacking DPS");
					toGo.useAbility(0, tankUseOn);
					System.out.println(dps.toString());
				} else if(toGo.getName().equals("DPS")){
					System.out.println("DPS Attacking Tank");
					toGo.useAbility(0, dpsUseOn);
					System.out.printf(tank.currentStats() + "\n");
				} else {
					if( i%5 == 0){
						if(i%2 == 0){
							System.out.println("Healer Healing Tank");
							toGo.useAbility(1, dpsUseOn);
							System.out.printf(tank.currentStats() + "\n");
						} else {
							System.out.println("Healer Healing DPS");
							toGo.useAbility(1, tankUseOn);
							System.out.println(dps.toString());
						}
					} else {
						if(i%2 == 0){
							System.out.println("Healer Attacking Tank");
							toGo.useAbility(0, dpsUseOn);
							System.out.printf(tank.currentStats() + "\n");
						} else {
							System.out.println("Healer Attacking DPS");
							toGo.useAbility(0, tankUseOn);
							System.out.printf(dps.currentStats() + "\n");
						}
					}
					i++;
				}
				toGo.endTurn();
			}
			turn.clear();

		}
		
	}
	
	private static void testBinder(){
		
		String effect, buff;
		effect = "Defence Down";
		effectsBinder.addEntry("Defence Down", new Effect("Defence Down", 1, -0.5, false, false){
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
		buffBinder.addEntry("Defence Down", new BuffDebuff("Defence Down", false, false, 2));
		buffBinder.getItem("Defence Down").addEffect(effectsBinder.getItem("Defence Down"));
		
		effectsBinder.addEntry("Defence Up", new Effect("Defence Down", 1, 0.5, false, false){
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
		buffBinder.addEntry("Defence Up", new BuffDebuff("Defence Up", true, false, 4));
		buffBinder.getItem("Defence Up").addEffect(effectsBinder.getItem("Defence Up"));
		
		effectsBinder.addEntry("Berzerk", new Effect("Berzerk", 1, 0, true, false){
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
		buffBinder.addEntry("Berzerk", new BuffDebuff("Berzerk", true, false, 2));
		buffBinder.getItem("Berzerk").addEffect(effectsBinder.getItem("Berzerk"));
		
		effectsBinder.addEntry("Calm Mind", new Effect("Calm Mind", 1, 0, false, false){
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
		buffBinder.addEntry("Calm Mind", new BuffDebuff("Calm Mind", true, false, 2));
		buffBinder.getItem("Calm Mind").addEffect(effectsBinder.getItem("Calm Mind"));
		buffBinder.addEntry("Stun", new BuffDebuff("Stun", false, false, 1));
		System.out.println(buffBinder.toString());
	}
	
	private static void testStats(){
		
		Stats playerStats = new Stats(7, 25, 25, 25, 25, 25, 25, 25);
		/*
		for (int i = 1; i < 21; i++){
			playerStats = new Stats(14 + i*i);
			System.out.printf("Agi: %d, Dex: %d, Eth: %d, Int: %d, Psc: %d, Stam: %d, Str: %d\nHealth: %d | Energy: %d | Speed: %d | Pysical Damage: %d\n\n", playerStats.agility,
				playerStats.dexterity, playerStats.ethics, playerStats.intellect, playerStats.pschotics, playerStats.stamina,
				playerStats.strength, playerStats.getHealth(), playerStats.getEnergy(), playerStats.getSpeed(), playerStats.getPysicalDamage());
		}
		*/
		playerStats = new Stats(7, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0);
		
		for (int i = 0; i < 71; i++){
			if ((i%5 == 0 || i == 1) && i != 0){
				System.out.printf("Level: %d\nAgi: %.0f, Dex: %.0f, Eth: %.0f, Int: %.0f, Psc: %.0f, Stam: %.0f, Str: %.0f\nHealth: %d | Energy: %d | Speed: %d\n"
						+ "Pysical Damage: %d | Magic Damgage: %d | Healing: %d | Healability: %d\nPysical Defence: %.2f%% | Magic Defence: %.2f%%\n\n", i, 
						playerStats.agility, playerStats.dexterity, playerStats.ethics, playerStats.intellect, playerStats.psychotics, playerStats.stamina, playerStats.strength, 
						playerStats.getHealth(), playerStats.getEnergy(), playerStats.getSpeed(), playerStats.getPhysicalDamage(), playerStats.getMagicDamage(), 
						playerStats.getHealing(), playerStats.getHealability(), 100*playerStats.getPhysicalDefence(), 100*playerStats.getMagicDefence());
			}
			playerStats.levelUp();
		}
	}
}
