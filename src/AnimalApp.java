import animals.Animal;
import animals.AnimalType;
import animals.Color;
import animals.factory.AnimalFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalApp {

//  вывод меню?
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        AnimalFactory factory = new AnimalFactory();

        Scanner scanner = new Scanner(System.in);
        Command currentCommand = null;

        do {
          currentCommand = getCommand(scanner);
          if (currentCommand == Command.LIST) {
              if (animals.isEmpty()) {
                  System.out.println("Список пуст");
              }
              for (Animal animal: animals) {
                  System.out.println(animal);
              }
          } else if (currentCommand == Command.ADD) {
              AnimalType animalType = getAnimalType(scanner);
              Animal animal = factory.create(animalType);
              // запросить все параметры ТУТ
              animal.setName(getName(scanner));
              animal.setAge(getAge(scanner));
              animal.setWeight(getWeight(scanner));
              animal.setColor(getColor(scanner));
              animals.add(animal);
              animal.say();
          }
        } while (currentCommand != Command.EXIT);
    }

//   возвращает команду пользователя?
    private static Command getCommand(Scanner scanner) {
        String commandInput = null;
        while (Command.doesNotContain(commandInput)) {
            if (commandInput != null) {
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.printf("Введите одну из команд (%s): ", String.join("/", Command.VALUES));
            commandInput = scanner.next();
        }
        return Command.fromString(commandInput);
    }

//    private static AnimalType getAnimalType(Scanner scanner) {
//        return AnimalType.CAT; // Это заглушка. Дописать абсолютно аналогично как выше в getCommand
//    }

    private static AnimalType getAnimalType(Scanner scanner) {
        String commandInput = null;
        while (AnimalType.doesNotContain(commandInput)){
            if (commandInput != null) {
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.println("Введите тип животного:");
            commandInput = scanner.next();
//            if (commandInput.equals("CAT")) return AnimalType.CAT;
//            if (commandInput.equals("DOG")) return AnimalType.DOG;
//            if (commandInput.equals("DUCK")) return AnimalType.DUCK;
        }
        return AnimalType.fromString(commandInput);
    }

    private static String getName(Scanner scanner) {
//        return "Барсик"; // заглушка
        System.out.println("Введите имя:");
        return scanner.next();
    }

    private static int getAge(Scanner scanner) {
        System.out.println("Введите возраст: ");
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Для параметра 'Возраст' допускается только ввод числа. Введите число");
                scanner.next();
            }

            int age = scanner.nextInt();
            scanner.nextLine();

            if (age > 0) {
                return age;
            }
            System.out.println("Введите целое положительное число");
        }
    }

    private static int getWeight(Scanner scanner) {
        System.out.println("Введите вес: ");
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Для параметра 'Вес' допускается только ввод числа. Введите число");
                scanner.next();
            }

            int weight = scanner.nextInt();
            scanner.nextLine();

            if (weight > 0) {
                return weight;
            }
            System.out.println("Введите целое положительное число");
        }
    }

    private static Color getColor(Scanner scanner) {
        String commandInput = null;
        while (Color.doesNotContain(commandInput)){
            if (commandInput != null) {
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.println("Введите цвет (Чёрный, Белый, Серый): ");
            commandInput = scanner.next();
        }
        return Color.fromString(commandInput);
    }
}
