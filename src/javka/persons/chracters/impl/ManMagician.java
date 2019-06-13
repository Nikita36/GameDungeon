package javka.persons.chracters.impl;

import javka.persons.chracters.AbstractPerson;
import javka.persons.chracters.utils.Lvl;
import javka.persons.chracters.utils.Stamina;

public class ManMagician extends AbstractPerson {
    public ManMagician(){
        stamina=new Stamina(30);
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
