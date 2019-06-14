package javka.game;

import javka.persons.AbstractPerson;
import javka.persons.allTypePersons.Dwarf;
import javka.persons.PersonFactory;
import javka.persons.TranslaterForAction;
import javka.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    int lvlOfDwarf;
    Player player1;
    Player player2;
    List<Player> playersInGame;//можно и без него, но для возможности расширения на больее кол-во игроков это понадобится
    PersonFactory personFactory = new PersonFactory();
    boolean isEnd = false;
    final int AllLvlInGame = 20;

    public void startGame() {
        System.out.println("Добро пожаловать в игру Подземелье!");
        System.out.println("Правила: Необходимо дойти до 20 уровня; Нельзя выбрать 2 одинаковых героя; Больше никаких правил!");
        player1 = createNewPlayer(1);
        player2 = createNewPlayer(2);
        System.out.println("И первый начинает...");
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2) + 1;
        try {
            Thread.sleep(1000);//создаём интригу
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Игрок " + randomInt);
        playersInGame = new ArrayList<>(2);
        if (randomInt == 1) {//не очень красиво, но для обобщения на N игроков придётся делать много доп логики
            playersInGame.add(player1);
            playersInGame.add(player2);
        } else {
            playersInGame.add(player2);
            playersInGame.add(player1);
        }
        Scanner input = new Scanner(System.in);
        TranslaterForAction translaterForAction = new TranslaterForAction();
        while (!isEnd) {
            Dwarf.isSpecialAction = false;//обнуляем действие умения гнома
            for (int i = 0; i < playersInGame.size(); i++) {
                boolean isAction = false;
                int numberOfAction;
                System.out.println("Ходит игрок: ");//как номер то его назвать?..
                while (!isAction) { //Пока не совершит действие, не переходим к новому игроку
                    AbstractPerson curPersonage = playersInGame.get(i).getPersonage();
                    displayOfAction(curPersonage);
                    numberOfAction = input.nextInt();
                    if (numberOfAction == 3 || !Dwarf.isSpecialAction) {// Если не идёт в разрез с  умением гнома,то можно делать
                        isAction = translaterForAction.useAction(curPersonage, numberOfAction,null);

                    } else {
                        if (curPersonage instanceof Dwarf) {//если он сам гном,то ему на всё пофиг
                            isAction = translaterForAction.useAction(curPersonage, numberOfAction,null);
                            if (curPersonage.getLvl().getLvl() >= AllLvlInGame) {//дошёл до конца
                                isEnd = true;
                                break;
                            }
                            lvlOfDwarf = curPersonage.getLvl().getLvl();// Тут гном мог применить спец.умение, поэтому важно знать на каком он уровне.
                        } else {// если у гнома работает спец. умение и мы хотим спуститься
                            int lvlCurPersonage = curPersonage.getLvl().getLvl();

                            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! на этом остановился



                        }
                    }

                }
            }
        }
    }

    public Player createNewPlayer(int numberOfPlayer) {
        AbstractPerson person = null;
        int classOfPersonage;
        Scanner input = new Scanner(System.in);
        System.out.println("Игрок " + numberOfPlayer + " введите имя:");
        String name = input.nextLine();
        while (person == null) {
            System.out.println("Выберите класс: 1 - Маг-человек");
            System.out.println("                2 - Гном воин");
            System.out.println("                3 - Эльф-разведчик");
            classOfPersonage = input.nextInt();
            person = personFactory.getPersonage(classOfPersonage);
        }
        return new Player(name, person);
    }

    public void displayOfAction(AbstractPerson curPersonage) {
        System.out.println("Ваша выносливость: " + curPersonage.getStamina().getStamina());
        System.out.println("Выбирите действие: 1 - Спуск(прдовигает на 1 уровень ниже): " + curPersonage.getDown() + " выносливости");
        System.out.println("                   2 - Быстрый спуск(продвигает на 2 уровня ниже): " + curPersonage.getDownHill() + " выносливости");
        System.out.println("                   3 - Отдых(добавляет 3 выносливости): " + 0 + " выносливости");
        System.out.println("                   4 - Специальное умение: " + curPersonage.getSpecialAction() + " выносливости");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
