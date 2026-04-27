package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import java.util.List;

public class CombatFloor extends TowerFloor {

    private final String floorName;
    private Monster monster;

    public CombatFloor(String floorName) {
        this.floorName = floorName;
    }

    @Override
    protected String getFloorName() {
        return floorName;
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("  Preparing for combat...");
        monster = new Monster("Shadow Fiend", 120, 25);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  ⚔️ Combat begins against " + monster.getName() + "!");

        int totalDamageTaken = 0;
        boolean cleared = false;

        while (monster.isAlive() && party.stream().anyMatch(Hero::isAlive)) {
            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.canAct()) {
                    int damage = hero.getModifiedAttackPower();
                    System.out.println("  " + hero.getName() + " attacks for " + damage + " damage!");
                    monster.takeDamage(damage);
                } else {
                    System.out.println("  " + hero.getName() + " cannot act this turn.");
                }

                if (!monster.isAlive()) break;

                Hero target = party.stream().filter(Hero::isAlive).findFirst().orElse(null);
                if (target != null) {
                    System.out.println("  " + monster.getName() + " attacks " + target.getName() + "!");
                    int before = target.getHp();
                    monster.attack(target);
                    totalDamageTaken += (before - target.getHp());
                }

                hero.onTurnEnd();
            }
        }

        cleared = !monster.isAlive();
        String summary = cleared ? "Defeated the " + monster.getName() + "!" : "Party was defeated...";
        return new FloorResult(cleared, totalDamageTaken, summary);
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        if (result.isCleared()) {
            System.out.println("  💰 Loot awarded: Each hero healed 30 HP!");
            party.forEach(h -> h.heal(30));
        }
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("  Floor cleanup completed.");
    }
}