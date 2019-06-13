package javka.persons.chracters;

import javka.persons.chracters.utils.Lvl;
import javka.persons.chracters.utils.Stamina;

public abstract class AbstractPerson implements PersonI{
    protected Stamina stamina;
    protected int downHill;

    /*
    * Lvl я бы все таки цифрой отсавил не вижу там особой логики
    * */
    protected Lvl lvl;
}
