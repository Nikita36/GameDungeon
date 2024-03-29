package persons.allTypePersons;

import lvlAndStamina.Stamina;
import lvlAndStamina.Lvl;
import persons.AbstractPerson;

public class Dwarf extends AbstractPerson {
    public static boolean isSpecialAction = false;//(применено ли умение)не то чтобы красиво, но по скольку 1 экземпляр класса,то и так нормально

    public Dwarf() {
        stamina = new Stamina(50);
        downHill = 15;
        specialAction = 20;
        lvl = new Lvl();
    }

    @Override
    public boolean specialAction() {
        isSpecialAction = action(specialAction, 1);
        return isSpecialAction;
    }
    public Dwarf clone(){
        Dwarf clone=new Dwarf();
        clone.setStamina(stamina.getStamina());
        clone.setLvl(lvl.getLvl());
        clone.setDownHill(downHill);
        clone.setSpecialAction(specialAction);
        return clone;
    }
}
