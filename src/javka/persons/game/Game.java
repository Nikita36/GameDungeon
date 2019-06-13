package javka.persons.game;

import javka.persons.AbstractPerson;
import javka.persons.PersonFactory;
import javka.persons.player.Player;

import java.util.Random;
import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;
    PersonFactory personFactory = new PersonFactory();

    public void startGame() {
        AbstractPerson person1 = null;
        AbstractPerson person2 = null;
        int classOfPersonage;
        Scanner input = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру Подземелье!");
        System.out.println("Правила: Необходимо дойти до 20 уровня; Нельзя выбрать 2 одинаковых героя; Больше никаких правил!");
        System.out.println("Игрок 1 введите имя:");
        String name = input.nextLine();
        while (person1 == null) {
            System.out.println("Выберите класс: 1 - Маг-человек");
            System.out.println("                2 - Гном воин");
            System.out.println("                3 - Эльф-разведчик");
            classOfPersonage = input.nextInt();
            person1 = personFactory.getPersonage(classOfPersonage);
        }
        player1 = new Player(name, person1);
        System.out.println("Игрок 2 введите имя:");
        name = input.next();
        while (person2 == null) {
            System.out.println("Выберите класс: 1 - Маг-человек");
            System.out.println("                2 - Гном воин");
            System.out.println("                3 - Эльф-разведчик");
            classOfPersonage = input.nextInt();
            person2 = personFactory.getPersonage(classOfPersonage);
        }
        player2 = new Player(name, person2);
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

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
