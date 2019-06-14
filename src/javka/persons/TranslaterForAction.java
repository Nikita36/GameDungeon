package javka.persons;

import javka.persons.allTypePersons.ManMagician;

public class TranslaterForAction {

    public boolean useAction(AbstractPerson personage, int numberOfAction, AbstractPerson personForExchange) {
        boolean isAction = false;
        switch (numberOfAction) {
            case 1: {
                isAction = personage.down();
                break;
            }
            case 2: {
                isAction = personage.downHill();
                break;
            }
            case 3: {
                isAction = personage.relaxation();
                break;
            }
            case 4: {
                if (personForExchange != null && personage instanceof ManMagician) {//маг обменивается с кем-то и перепроверим маг ли
                    isAction = ((ManMagician) personage).specialAction(personage, personForExchange);
                } else {
                    isAction = personage.specialAction();
                }
                break;
            }
            default:
                isAction = false;
        }
        return isAction;
    }

}
