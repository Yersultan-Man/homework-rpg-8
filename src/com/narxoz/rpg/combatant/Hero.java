package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.states.NormalState;

/**
 * Represents a player-controlled hero participating in the tower climb.
 */
public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private HeroState state;

    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.state = new NormalState(); // default state
    }

    public String getName()        { return name; }
    public int getHp()             { return hp; }
    public int getMaxHp()          { return maxHp; }
    public int getAttackPower()    { return attackPower; }
    public int getDefense()        { return defense; }
    public boolean isAlive()       { return hp > 0; }
    public HeroState getState()    { return state; }

    public void setState(HeroState newState) {
        if (newState != null) {
            System.out.println("  🔄 " + name + " changed state: " + state.getName() + " → " + newState.getName());
            this.state = newState;
        }
    }

    public int getModifiedAttackPower() {
        return state.modifyOutgoingDamage(attackPower);
    }

    /**
     * Reduces this hero's HP by the given amount, clamped to zero.
     */
    public void takeDamage(int amount) {
        int finalDamage = state.modifyIncomingDamage(amount);
        hp = Math.max(0, hp - finalDamage);
    }

    /**
     * Restores this hero's HP by the given amount, clamped to maxHp.
     */
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public void onTurnStart() {
        state.onTurnStart(this);
    }

    public void onTurnEnd() {
        state.onTurnEnd(this);
    }

    public boolean canAct() {
        return state.canAct();
    }
}