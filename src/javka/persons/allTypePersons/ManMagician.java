package javka.persons.allTypePersons;

import javka.lvlAndStamina.Lvl;
import javka.lvlAndStamina.Stamina;
import javka.persons.AbstractPerson;

public class ManMagician extends AbstractPerson {
    public ManMagician(){
        stamina=new Stamina(30);
        downHill=12;
        lvl=new Lvl();
        specialAction=15;
    }

    public  boolean specialAction(AbstractPerson first,AbstractPerson second) {//обмен уровней первого с вторым
        if (stamina.subStamina(specialAction)){
            int lvlOfSecond = second.getLvl().getLvl();
            second.getLvl().setLvl(first.getLvl().getLvl());
            first.getLvl().setLvl(lvlOfSecond);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean specialAction() {//Вот это издержки не идеальной архитектуры
        return false;
    }
}
