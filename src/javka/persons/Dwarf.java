package javka.persons;

import javka.persons.lvlAndStamina.Stamina;
import javka.persons.lvlAndStamina.Lvl;

public class Dwarf extends AbstractPerson {
    public Dwarf(){
        stamina=new Stamina(50);
        downHill=15;
        lvl=new Lvl();
    }
}
