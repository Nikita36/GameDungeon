package javka.persons;

public class PersonFactory {
    boolean man = false;
    boolean elf = false;
    boolean dwarf = false;

    public AbstractPerson getPersonage(int typeOfPerson) {
        AbstractPerson personToReturn = null;
        switch (typeOfPerson) {
            case 1:
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
