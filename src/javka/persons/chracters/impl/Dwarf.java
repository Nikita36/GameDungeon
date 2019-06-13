package javka.persons.chracters.impl;

import javka.persons.chracters.AbstractPerson;
import javka.persons.chracters.utils.Stamina;
import javka.persons.chracters.utils.Lvl;

public class Dwarf extends AbstractPerson {

    /*
    * Соблюдай кодстайл или тебя порэжут)
    * хотя бы черезе ctrl + L
    * */
    public Dwarf() {
        stamina = new Stamina(50);
        downHill = 15;
        lvl = new Lvl();
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
