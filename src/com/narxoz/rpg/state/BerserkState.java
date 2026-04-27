package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.HeroState;

public class BerserkState implements HeroState {

    public String getName() {
        return "Berserk";
    }

    public int modifyOutgoingDamage(int basePower) {
        return (int) (basePower * 1.6);
    }

    public int modifyIncomingDamage(int rawDamage) {
        return (int) (rawDamage * 1.3);
    }

    public void onTurnStart(Hero hero) {
        if (hero.getHp() <= hero.getMaxHp() * 0.4) {
            System.out.println("  🔥 " + hero.getName() + " enters BERSERK RAGE from low HP!");
        }
    }

    public void onTurnEnd(Hero hero) {
    }

    public boolean canAct() {
        return true;
    }
}