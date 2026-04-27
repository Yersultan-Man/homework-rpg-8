package com.narxoz.rpg.runner;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.tower.TowerRunResult;
import java.util.ArrayList;
import java.util.List;

public class TowerRunner {

    private final List<TowerFloor> floors = new ArrayList<>();

    public void addFloor(TowerFloor floor) {
        floors.add(floor);
    }

    public TowerRunResult run(List<Hero> party) {
        System.out.println("\n=== THE HAUNTED TOWER CLIMB BEGINS ===\n");

        int floorsCleared = 0;
        boolean towerCleared = true;

        for (TowerFloor floor : floors) {
            if (party.stream().noneMatch(Hero::isAlive)) {
                towerCleared = false;
                break;
            }

            FloorResult result = floor.explore(party);
            if (result.isCleared()) {
                floorsCleared++;
            } else {
                towerCleared = false;
                break;
            }
        }

        int heroesSurviving = (int) party.stream().filter(Hero::isAlive).count();

        System.out.println("\n=== TOWER CLIMB COMPLETED ===\n");
        return new TowerRunResult(floorsCleared, heroesSurviving, towerCleared);
    }
}