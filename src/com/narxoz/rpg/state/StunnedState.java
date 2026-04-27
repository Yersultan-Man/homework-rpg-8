package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.HeroState;

public class StunnedState implements HeroState {
    private int turnsLeft = 2;

    @Override
    public String getName() {
        return "Stunned (" + turnsLeft + " turns left)";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return 0; // cannot deal damage while stunned
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return rawDamage;
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println("  😵 " + hero.getName() + " is stunned and cannot act!");
    }

    @Override
    public void onTurnEnd(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) {
            System.out.println("  ⚡ " + hero.getName() + " is no longer stunned!");
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() {
        return false;
    }
}