package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.CombatFloor;
import com.narxoz.rpg.floor.PoisonTrapFloor;
import com.narxoz.rpg.floor.RestSanctuary;
import com.narxoz.rpg.runner.TowerRunner;
import com.narxoz.rpg.state.BerserkState;
import com.narxoz.rpg.state.StunnedState;
import com.narxoz.rpg.tower.TowerRunResult;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Hero aragorn = new Hero("Aragorn", 150, 35, 10);
        Hero legolas = new Hero("Legolas", 120, 40, 5);

        legolas.setState(new StunnedState());
        aragorn.setState(new BerserkState());

        List<Hero> party = List.of(aragorn, legolas);

        TowerRunner runner = new TowerRunner();
        runner.addFloor(new CombatFloor("Floor 1: Goblin Ambush"));
        runner.addFloor(new PoisonTrapFloor());
        runner.addFloor(new RestSanctuary());
        runner.addFloor(new CombatFloor("Floor 4: Shadow Fiend Lair"));
        runner.addFloor(new CombatFloor("Floor 5: Final Guardian"));

        TowerRunResult result = runner.run(party);

        System.out.println("🏆 FINAL TOWER RUN RESULT:");
        System.out.println("   Floors Cleared: " + result.getFloorsCleared());
        System.out.println("   Heroes Surviving: " + result.getHeroesSurviving());
        System.out.println("   Reached the Top: " + (result.isReachedTop() ? "YES" : "NO"));

        System.out.println("\nFinal states:");
        for (Hero h : party) {
            if (h.isAlive()) {
                System.out.println("   " + h.getName() + " - HP: " + h.getHp() + "/" + h.getMaxHp() +
                        " | State: " + h.getState().getName());
            } else {
                System.out.println("   " + h.getName() + " - DEAD");
            }
        }
    }
}