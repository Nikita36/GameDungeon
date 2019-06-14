package javka.lvlAndStamina;

public class Stamina {
    private int stamina;
    private final int maxStamina;

    public Stamina(int maxStamina) {
        this.maxStamina = maxStamina;
        stamina = maxStamina;

    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int addStamina(int value) {
        stamina += value;
        if (stamina > maxStamina)
            stamina = maxStamina;
        return stamina;
    }

    public boolean subStamina(int value) {
        if (stamina - value < 0) {
            System.out.println("Невозможно выполнить: Для данного действия недостаточно выносливости");
            return false;
        } else
            stamina -= value;
        return true;
    }
}
