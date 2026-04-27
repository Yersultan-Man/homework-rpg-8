package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import java.util.List;

public class RestSanctuary extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Sanctuary of Light";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("  A peaceful glowing sanctuary...");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  The party rests and recovers.");
        return new FloorResult(true, 0, "Full rest - no combat");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("  ✨ Holy energy restores the party!");
        party.forEach(h -> h.heal(50));
    }
}