package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.HeroState;

public class BerserkState implements HeroState {

    @Override
    public String getName() {
        return "Berserk";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return (int) (basePower * 1.6); // +60% damage
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return (int) (rawDamage * 1.3); // takes 30% more damage
    }

    @Override
    public void onTurnStart(Hero hero) {
        if (hero.getHp() <= hero.getMaxHp() * 0.4) {
            System.out.println("  🔥 " + hero.getName() + " enters BERSERK RAGE from low HP!");
        }
    }

    @Override
    public void onTurnEnd(Hero hero) {
        // stays in berserk until manually changed or hero dies
    }

    @Override
    public boolean canAct() {
        return true;
    }
}