package javka.persons;

import javka.persons.lvlAndStamina.Lvl;
import javka.persons.lvlAndStamina.Stamina;

public class ManMagician extends AbstractPerson{
    public ManMagician(){
        stamina=new Stamina(30);
        downHill=12;
        lvl=new Lvl();
    }
}
