package javka.persons;

import javka.persons.lvlAndStamina.Stamina;
import javka.persons.lvlAndStamina.Lvl;

public class Elf extends AbstractPerson {
    public Elf(){
        stamina = new Stamina(40);
        downHill=12;
        lvl=new Lvl();
    }
}
