import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testStats();
		testCharacter();
	}

	private static void testCharacter(){
		
		Hero tank = new Hero("Tank", 175, 25, 90, 15, 0, 0, 5, .05, .05, .25, .10, .10, (new Stats(2, 3.0, 3.9, 5.4, 5.7, 3.5, 4.7, 3.5)));
		Hero dps = new Hero("DPS", 95, 36, 110, 25, 0, 0, 0, .01, .01, .25, .1, .1, (new Stats(7, 2.8, 5.4, 4.8, 2.9, 4.7, 3.5, 5.7)));
		Hero healer = new Hero("Healer", 125, 50, 100, 15, 0, 0, 0, .025, .025, .25, .1, .1, (new Stats(4, 5.1, 3.9, 4.6, 3.6, 3.8, 5.7, 2.9)));
		tank.resetTurnMeter();
		dps.resetTurnMeter();
		healer.resetTurnMeter();
		
		tank.addAbility(new BasicPhysical(tank, .12, 2.5));
		dps.addAbility(new BasicPhysical(dps, .12, 2.7));
		healer.addAbility(new BasicMagic(healer, .12, 2.4));
		healer.addAbility(new BasicHeal(healer, .12, 2.1));
		
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
		
		BuffDebuff buff = new StatModifier("Offence Up", true, false, 1, 1.5, 1, 1, 1, 1, 1.5);
		buff.applyEffect(dps);
		while(tank.getCurrentHealth() > 0 && dps.getCurrentHealth() > 0){
			k++;
			if(k == 5){
				buff.reverseEffect(dps);
			}
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
				toGo.resetTurnMeter();
			}
			turn.clear();

		}
		
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
