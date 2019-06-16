package persons;

import java.util.Arrays;
import java.util.function.Predicate;

// Название какое-то упоротое, непонятно, что под ним подразумевается
// Название ActionEnum четко говорит о том, что это enum (Enum в названиие) действий (Action)
public enum ActionEnum {

    /*
    * Во-первых enum-константы, поэтому пишутся большими буквами
    * Во-вторых в столбец, так читабельнее
    * */
    DOWN(1, "Спуск"),
    DOWNHILL(2,"Быстрый спуск"),
    RELAXATION(3,"Отдых"),
    SPECIAL(4,"Специальное умение"),
    UNKNOWN(-1, "");


    /*
    * Поля делай final, чтобы компилятор их наверняка инициализировал при создании инстансов
    * К тому же вместо примитива лучше использовать его классовый аналог, но это неточно, поэтому менять не стал
    * */
    private final int number;
    private final String tittle;

    ActionEnum(int number, String tittle) {
        this.number = number;
        this.tittle = tittle;
    }

    public int getNumber() {
        return number;
    }

    public String getTittle() {
        return tittle;
    }

    /*
    * Тут все просто:
    * 1)Берутся все инстансы
    * 2)Отбираем только инстансы с совпашим номером (по идее это id-шники действий, поэтому совпадений быть не должно)
    * 3)Возвращаем инстанс, если такой есть и UNKNOWN в противном случае
    * */
    public static ActionEnum byNumber(int number) {
        return Arrays.stream(ActionEnum.values())
                .filter(x -> x.number == number)
                .findFirst()
                .orElse(UNKNOWN);
    }

    public static ActionEnum byTitle(String tittle) {
        return Arrays.stream(ActionEnum.values())
                .filter(x -> tittle.equals(x.tittle))
                .findFirst()
                .orElse(UNKNOWN);
    }

    /*
    * Сходство этих 2 функций кстати, четко показывает что их можно вынести в какой-нибудь
    * статик класс и применять к другием enum или сделать так
    * */
    public static ActionEnum getBy(Predicate<? super ActionEnum> predicate) {
        return Arrays.stream(ActionEnum.values())
                .filter(predicate)
                .findFirst()
                .orElse(UNKNOWN);
    }
    // Это тип так, чтобы не дублировать, не особо обязательно, но мб тебе интересно
    public static ActionEnum byNumber1(int number) {
        return getBy(x -> x.number == number);
    }

    public static ActionEnum byTitle1(String tittle) {
        return getBy(x -> tittle.equals(x.tittle));
    }
}

