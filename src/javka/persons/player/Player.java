package javka.persons.player;

import javka.persons.chracters.AbstractPerson;

public class Player {
   private String name;
   private AbstractPerson personage;

    public Player(String name, AbstractPerson personage) {
        this.name = name;
        this.personage = personage;
    }

    // Пустой конструктор лучше всегда делать, иначе ты ограничиваешь себя в шаблонных действиях, как например при создании игроков
    // у тебя дублировался код
    public Player() {}

    public AbstractPerson getPersonage() {
        return personage;
    }

    public void setPersonage(AbstractPerson personage) {
        this.personage = personage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
