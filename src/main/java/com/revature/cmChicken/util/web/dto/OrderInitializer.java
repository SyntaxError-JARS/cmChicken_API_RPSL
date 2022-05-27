package com.revature.cmChicken.util.web.dto;

import com.revature.cmChicken.customer.Customer;
import com.revature.cmChicken.menu.Menu;

import javax.persistence.*;

public class OrderInitializer {
    private String pokemonName;
    private int hp;
    private int atk;
    private int elementType;
    private String ability1;
    private String ability2;

    public PokemonInitializer() {
    }

    public PokemonInitializer(String pokemonName, int hp, int atk, int elementType, String ability1, String ability2) {
        this.pokemonName = pokemonName;
        this.hp = hp;
        this.atk = atk;
        this.elementType = elementType;
        this.ability1 = ability1;
        this.ability2 = ability2;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public String getAbility1() {
        return ability1;
    }

    public void setAbility1(String ability1) {
        this.ability1 = ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public void setAbility2(String ability2) {
        this.ability2 = ability2;
    }
}
