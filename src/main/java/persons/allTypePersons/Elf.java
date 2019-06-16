package persons.allTypePersons;

import lvlAndStamina.Stamina;
import lvlAndStamina.Lvl;
import persons.AbstractPerson;

public class Elf extends AbstractPerson {
    public Elf() {
        stamina = new Stamina(40);
        downHill = 12;
        lvl = new Lvl();
        specialAction=24;
    }

    @Override
    public boolean specialAction() {
       return action(specialAction,3);
    }
    public Elf clone(){
        Elf clone=new Elf();
        clone.setStamina(stamina.getStamina());
        clone.setLvl(lvl.getLvl());
        clone.setDownHill(downHill);
        clone.setSpecialAction(specialAction);
        return clone;
    }
}
