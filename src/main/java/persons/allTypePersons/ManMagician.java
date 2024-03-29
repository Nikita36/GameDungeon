package persons.allTypePersons;

import lvlAndStamina.Lvl;
import lvlAndStamina.Stamina;
import persons.AbstractPerson;

public class ManMagician extends AbstractPerson {
    public ManMagician(){
        stamina=new Stamina(30);
        downHill=13;
        lvl=new Lvl();
        specialAction=15;
    }

    public  boolean specialAction(AbstractPerson first,AbstractPerson second) {//обмен уровней первого с вторым
        Stamina copyOfStamina = new Stamina(stamina.getMaxStamina());
        copyOfStamina.setStamina(stamina.getStamina());
        if (second!=null) {//если есть с кем обмениваться,то меняемся
            if (copyOfStamina.subStamina(specialAction)) {
                int lvlOfSecond = second.getLvl().getLvl();
                second.getLvl().setLvl(first.getLvl().getLvl());
                first.getLvl().setLvl(lvlOfSecond);
                return true;
            }
        } else {//иначе спускаемся
            if (copyOfStamina.subStamina(specialAction)) {
                action(specialAction,1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean specialAction() {//Вот это издержки не идеальной архитектуры
        return false;
    }
    public ManMagician clone(){
        ManMagician clone=new ManMagician();
        clone.setStamina(stamina.getStamina());
        clone.setLvl(lvl.getLvl());
        clone.setDownHill(downHill);
        clone.setSpecialAction(specialAction);
        return clone;
    }
}
