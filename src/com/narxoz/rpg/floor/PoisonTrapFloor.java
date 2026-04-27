package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.state.PoisonedState;
import java.util.List;

public class PoisonTrapFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Venomous Chamber";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("  The air is thick with toxic gas...");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  💨 Poison gas fills the room!");

        int damageTaken = 0;
        for (Hero hero : party) {
            if (hero.isAlive()) {
                int dmg = 25;
                hero.takeDamage(dmg);
                damageTaken += dmg;
                System.out.println("  " + hero.getName() + " is poisoned by the trap!");
                hero.setState(new PoisonedState());
            }
        }

        return new FloorResult(true, damageTaken, "Survived the poison trap but heroes are poisoned.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("  🧪 Found antidote herbs! Partial healing.");
        party.forEach(h -> h.heal(15));
    }
}