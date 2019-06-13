package javka.persons.game;

import javka.persons.chracters.AbstractPerson;
import javka.persons.chracters.utils.PersonFactory;
import javka.persons.player.Player;

import java.util.Random;
import java.util.Scanner;

public class Game {
    /*
    * Такое объявление строго ограничивает игру на 2, можно порассуждать над созданием пула игроков
    * в который можно свободно добавить нового и для этого понадобится механизм генерации, про который я писал ниже
     */

    private Player player1 = new Player();
    private Player player2 = new Player();
    private PersonFactory personFactory = new PersonFactory();

    // можно завести отдельный класс для генерации таких имен, но пока можно ограничиться константами
    private static final String P1 = "Игрок 1";
    private static final String P2 = "Игрок 2";

    public void startGame() {
        greeting();
        createPlayer(P1, player1);
        createPlayer(P2, player2);

        System.out.println("И первый начинает...");
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2) + 1;
        try {
            Thread.sleep(1000);//создаём интригу
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Игрок "+randomInt);
    }

    private void newPlayer(Player player) {
        AbstractPerson person = null;
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        while (person == null) {
            System.out.println("Выберите класс: 1 - Маг-человек");
            System.out.println("                2 - Гном воин");
            System.out.println("                3 - Эльф-разведчик");
            person = personFactory.getPersonage(input.nextInt());
        }
        player.setName(name);
        player.setPersonage(person);
    }

    private void createPlayer(String playerStr, Player player) {
        System.out.println(playerStr + " введите имя:");
        newPlayer(player);
    }

    private void greeting() {
        System.out.println("Добро пожаловать в игру Подземелье!");
        System.out.println("Правила: Необходимо дойти до 20 уровня; Нельзя выбрать 2 одинаковых героя; Больше никаких правил!");
    }
}
