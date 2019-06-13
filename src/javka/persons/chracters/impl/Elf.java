package javka.persons.chracters.impl;

import javka.persons.chracters.AbstractPerson;
import javka.persons.chracters.utils.Stamina;
import javka.persons.chracters.utils.Lvl;

public class Elf extends AbstractPerson {
    public Elf(){
        stamina = new Stamina(40);
        downHill=12;
        lvl=new Lvl();
    }

    @Override
    public void rest() {

    }

    @Override
    public void down() {

    }

    @Override
    public void fastDown() {

    }

    @Override
    public void special() {

    }
}
