package player;

import persons.AbstractPerson;

public class Player {
   private String name;
   private AbstractPerson personage;

    public Player(String name, AbstractPerson personage) {
        this.name = name;
        this.personage = personage;
    }

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
