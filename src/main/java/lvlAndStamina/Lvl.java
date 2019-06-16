package lvlAndStamina;

public class Lvl {
    private int lvl;

    public Lvl() {
        lvl = 0;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int add(int value) {
        lvl += value;
        return lvl;
    }

}
