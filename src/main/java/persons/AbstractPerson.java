package persons;

import lvlAndStamina.Lvl;
import lvlAndStamina.Stamina;

public abstract class AbstractPerson implements Action {
    protected final int down = 5;
    protected Stamina stamina;
    protected int specialAction;
    protected int downHill;
    protected Lvl lvl;
    @Override
    public boolean relaxation() {
        stamina.addStamina(3);
        return true;
    }
    @Override
    public boolean down() {
        return action(down, 1);
    }
    @Override
    public boolean downHill() {
        return action(downHill, 2);
    }

    public final boolean action(int subStamina, int addLvl) {
        //boolean переменные именуют начиная с is(was), типа isAct, так намного читабельнее
        boolean act = stamina.subStamina(subStamina);
//        if (act) {
//            //аналогично неймнинг addLvl вызывает большие вопросы
//            lvl.add(addLvl);
//            return true;
//        } else
//            return false;
        //лучше переписать так
        if(!act)
            return false;
        lvl.add(addLvl);
        return true;
    }
    public Stamina getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina.setStamina(stamina);
    }

    public void setDownHill(int downHill) {
        this.downHill = downHill;
    }

    public void setLvl(int lvl) {
        this.lvl.setLvl(lvl);
    }

    public void setSpecialAction(int specialAction) {
        this.specialAction = specialAction;
    }

    public int getDown() {
        return down;
    }

    public int getDownHill() {
        return downHill;
    }

    public int getSpecialAction() {
        return specialAction;
    }

    public Lvl getLvl() {
        return lvl;
    }
}
