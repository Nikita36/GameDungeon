package javka.persons.allTypePersons;

import javka.lvlAndStamina.Stamina;
import javka.lvlAndStamina.Lvl;
import javka.persons.AbstractPerson;

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
}
