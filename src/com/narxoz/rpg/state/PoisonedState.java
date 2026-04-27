package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.HeroState;

public class PoisonedState implements HeroState {
    private int turnsLeft = 4;

    public String getName() {
        return "Poisoned (" + turnsLeft + " turns left)";
    }

    public int modifyOutgoingDamage(int basePower) {
        return (int) (basePower * 0.8);
    }

    public int modifyIncomingDamage(int rawDamage) {
        return (int) (rawDamage * 1.1);
    }

    public void onTurnStart(Hero hero) {
        int poisonDamage = 8;
        System.out.println("  ☠️ " + hero.getName() + " suffers " + poisonDamage + " poison damage!");
        hero.takeDamage(poisonDamage);
    }

    public void onTurnEnd(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) {
            System.out.println("  💊 " + hero.getName() + " recovered from poison!");
            hero.setState(new NormalState());
        }
    }

    public boolean canAct() {
        return true;
    }
}