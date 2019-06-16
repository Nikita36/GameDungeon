package game;

import database.DAO.LogDAO;
import database.entity.Log;
import database.services.LogService;
import persons.*;
import persons.allTypePersons.Dwarf;
import persons.allTypePersons.Elf;
import persons.allTypePersons.ManMagician;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    int lvlOfDwarf;
    int lvlOfPersonForSwap;
    Player player1;
    Player player2;
    List<Player> playersInGame;//можно и без него, но для возможности расширения на больее кол-во игроков это понадобится
    List<Integer> sequenceOfPlayers;
    PersonFactory personFactory = new PersonFactory();
    boolean isEnd = false;
    int countOfPlayers = 2;
    final int AllLvlInGame = 3;
    private int step = 0;
    private LogService logService;

    public Game() {
        logService = new LogService();
        playersInGame = new ArrayList<>(countOfPlayers);
        sequenceOfPlayers = new ArrayList<>(countOfPlayers);
    }

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
        if (randomInt == 1) {//не очень красиво, но для обобщения на N игроков придётся делать много доп логики
            playersInGame.add(player1);
            playersInGame.add(player2);
            sequenceOfPlayers.add(1);
            sequenceOfPlayers.add(2);
        } else {
            playersInGame.add(player2);
            playersInGame.add(player1);
            sequenceOfPlayers.add(2);
            sequenceOfPlayers.add(1);
        }
        Scanner input = new Scanner(System.in);
        TranslaterForAction translaterForAction = new TranslaterForAction();
        while (!isEnd) {
            Dwarf.isSpecialAction = false;//обнуляем действие умения гнома
            List<Integer> actions= new ArrayList<>();
            int j;
            for (j=0;j < playersInGame.size(); j++) {
                System.out.println("Ходит игрок: " + sequenceOfPlayers.get(j));
                actions.add(j,doItAction(input, translaterForAction, j, step));
                if (isEnd){//выходим из цикла,если есть победитель
                    break;
                }

            }
            if (isEnd) {//сохраняем последние действие и выходим из цикла ходов

                AbstractPerson person = playersInGame.get(j).getPersonage();
                NumberOfActionToString actionEnum = NumberOfActionToString.valueOf(actions.get(j));
                Log log = new Log(j+1, person.getLvl().getLvl(), person.getStamina().getStamina(), " Конец: " + step + " шага", " Было использовано умение: "+actionEnum.name());
                logService.save(log);
                break;
            }
            for (int i = 0; i < playersInGame.size(); i++) {//В конце хода добавляем выносливотсь
                playersInGame.get(i).getPersonage().getStamina().addStamina(2);
                AbstractPerson person = playersInGame.get(i).getPersonage();
                NumberOfActionToString actionEnum = NumberOfActionToString.valueOf(actions.get(i));
                Log log = new Log(i+1, person.getLvl().getLvl(), person.getStamina().getStamina(), " Конец: " + step + " шага", " Было использовано умение: "+actionEnum.name());
                logService.save(log);
            }
            step++;
        }
        System.out.println("Хотите посмотреть запись игры? 1 - да 2 - нет");
        int record = input.nextInt();
        if (record==1){
            List<Log> recordList = logService.findAll();
            for (Log x: recordList){
                System.out.println(x);
            }
        }
    }

    public int doItAction(Scanner input, TranslaterForAction translaterForAction, int i, int step) {

        AbstractPerson person = playersInGame.get(i).getPersonage();
        Log log = new Log(i+1, person.getLvl().getLvl(), person.getStamina().getStamina(), " Начало:" + step + " шага", "");
        logService.save(log);
        boolean isAction = false;
        int numberOfAction = 0;
        while (!isAction) { //Пока не совершит действие, не переходим к новому игроку
            AbstractPerson curPersonage = playersInGame.get(i).getPersonage();
            displayOfAction(curPersonage);
            numberOfAction = input.nextInt();

            if (numberOfAction == 3) {// Если не идёт в разрез с  умением гнома,то можно делать
                isAction = translaterForAction.useAction(curPersonage, numberOfAction, null);
            } else {
                if (Dwarf.isSpecialAction) {// если применено умение гнома
                    AbstractPerson copyOfPerson;
                    if (curPersonage instanceof Elf) {//при расширении на большее количество персонажей,пришлось бы это убирать(неэффективно)
                        copyOfPerson = ((Elf) curPersonage).clone();
                    } else {
                        copyOfPerson = ((ManMagician) curPersonage).clone();
                    }
                    AbstractPerson personageForSwap;//Для умения мага мы выбираем ему с кем меняться,если кто-то ходил до него,то
                    //  он в приоритете. Если такого нет,омбениваемся с тем кто ходит после него.(обобщение для n игроков)
                    if (i - 1 >= 0) {
                        personageForSwap = playersInGame.get(i - 1).getPersonage();
                        lvlOfPersonForSwap = playersInGame.get(i - 1).getPersonage().getLvl().getLvl();
                    } else {
                        personageForSwap = playersInGame.get(i + 1).getPersonage();
                        lvlOfPersonForSwap = playersInGame.get(i + 1).getPersonage().getLvl().getLvl();
                    }
                    if (curPersonage instanceof ManMagician) {
                        if (numberOfAction == 4) {//если маг использует спец. умение
                            if (curPersonage.getLvl().getLvl() == personageForSwap.getLvl().getLvl() + 1) {//т.е. находится на уровень ниже
                                isAction = checkSkillDwarfAndMove(translaterForAction, curPersonage, copyOfPerson, numberOfAction, personageForSwap);
                            } else {//если под ним никого нет,надо проверить может ли он спуститься
                                isAction = checkSkillDwarfAndMove(translaterForAction, curPersonage, copyOfPerson, numberOfAction, null);
                            }
                        } else {//для всех действий не спец умений мага
                            isAction = checkSkillDwarfAndMove(translaterForAction, curPersonage, copyOfPerson, numberOfAction, null);
                        }
                    } else {
                        isAction = checkSkillDwarfAndMove(translaterForAction, curPersonage, copyOfPerson, numberOfAction, null);
                    }
                } else {// еслм умение гнома не применено(возможно тут гном)
                    AbstractPerson personageForSwap;//Для умения мага мы выбираем ему с кем меняться,если кто-то ходил до него,то
                    //  он в приоритете. Если такого нет,омбениваемся с тем кто ходит после него.(обобщение для n игроков)
                    if (i - 1 >= 0) {
                        personageForSwap = playersInGame.get(i - 1).getPersonage();
                        lvlOfPersonForSwap = playersInGame.get(i - 1).getPersonage().getLvl().getLvl();
                    } else {
                        personageForSwap = playersInGame.get(i + 1).getPersonage();
                        lvlOfPersonForSwap = playersInGame.get(i + 1).getPersonage().getLvl().getLvl();
                    }

                    if (curPersonage instanceof ManMagician) {//если маг,надо проверить спец. умение
                        if (numberOfAction == 4) {//если маг использует спец. умение
                            if (curPersonage.getLvl().getLvl() == personageForSwap.getLvl().getLvl() + 1) {//т.е. находится на уровень ниже
                                isAction = translaterForAction.useAction(curPersonage, numberOfAction, personageForSwap);
                            } else {//просто спускаем
                                isAction = translaterForAction.useAction(curPersonage, numberOfAction, null);
                            }
                        } else {//для всех действий не спец умений
                            isAction = translaterForAction.useAction(curPersonage, numberOfAction, null);
                        }
                    } else {//если не маг ,то свободно делай
                        isAction = translaterForAction.useAction(curPersonage, numberOfAction, null);
                        if (curPersonage instanceof Dwarf) {
                            lvlOfDwarf = curPersonage.getLvl().getLvl();
                        }
                    }
                }
            }
        }
        if (playersInGame.get(i).getPersonage().getLvl().getLvl() >= AllLvlInGame) {
            isEnd = true;
            System.out.println(playersInGame.get(i).getName() + " поздравляем, вы победили!");
        }
        return numberOfAction;
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
        System.out.println("Вы на уровне:" + curPersonage.getLvl().getLvl() + " Ваша выносливость: " + curPersonage.getStamina().getStamina());
        System.out.println("Выбирите действие: 1 - Спуск(прдовигает на 1 уровень ниже): " + curPersonage.getDown() + " выносливости");
        System.out.println("                   2 - Быстрый спуск(продвигает на 2 уровня ниже): " + curPersonage.getDownHill() + " выносливости");
        System.out.println("                   3 - Отдых(добавляет 3 выносливости): " + 0 + " выносливости");
        System.out.println("                   4 - Специальное умение: " + curPersonage.getSpecialAction() + " выносливости");
    }

    public boolean checkSkillDwarfAndMove(TranslaterForAction translaterForAction, AbstractPerson
            curPersonage, AbstractPerson copyOfPerson, int numberOfAction, AbstractPerson personageForSwap) {

        translaterForAction.useAction(copyOfPerson, numberOfAction, personageForSwap);// Проверяем что будет,если мы сходим
        if (copyOfPerson.getLvl().getLvl() >= lvlOfDwarf) {
            System.out.println("Невозможно применить из-за навыка гнома");
            return false;
        } else {
            return translaterForAction.useAction(curPersonage, numberOfAction, null);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}