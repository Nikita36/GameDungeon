package javka.persons.chracters.utils;

import javka.persons.chracters.AbstractPerson;
import javka.persons.chracters.impl.Dwarf;
import javka.persons.chracters.impl.Elf;
import javka.persons.chracters.impl.ManMagician;

public class PersonFactory {

    /*
    * Под это лучше enum завести
    * И я не совсем понял назначение этого класса
     */

    boolean man = false;
    boolean elf = false;
    boolean dwarf = false;

    public AbstractPerson getPersonage(int typeOfPerson) {
        AbstractPerson personToReturn = null;
        switch (typeOfPerson) {
            case 1:
                //тут даже idea просит упростить выражение на if(man) так читабельнее
                if (man == true)
                    return null;
                else {
                    personToReturn = new ManMagician();
                    man = true;
                }
                break;
            case 2:
                if (dwarf == true)
                    return null;
                else {
                    personToReturn = new Dwarf();
                    dwarf = true;
                }
                break;
            case 3:

                if (elf == true)
                    return null;
                else {
                    personToReturn = new Elf();
                    elf = true;
                }
                break;
                default: personToReturn=null;
        }
        return personToReturn;
    }
}
