import animals.Animal;
import animals.AnimalType;
import animals.Color;
import animals.factory.AnimalFactory;
import animals.tables.AnimalTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalApp {

    public static void main(String[] args) {

        AnimalFactory factory = new AnimalFactory();

        Scanner scanner = new Scanner(System.in);
        Command currentCommand = null;
        AnimalTable animalTable = null;

        try {
            animalTable = new AnimalTable();
        } catch (SQLException e) {
            System.out.println("Что-то не так с таблицей: " + e.getMessage());
            return;
        }

        do {
          currentCommand = getCommand(scanner);
          if (currentCommand == Command.LIST) {

              // читаем данные из БД
              try {
                  List<Animal> animalIsFromDB = animalTable.getAll();
                  if (animalIsFromDB.isEmpty()) {
                      System.out.println("В списке нет значений");
                  } else {
                      for (Animal animal : animalIsFromDB) {
                          System.out.println(animal);
                      }
                  }
              } catch (SQLException e) {
                  System.out.println("Ошибка при чтении БД: " + e.getMessage());
              }
          } else if (currentCommand == Command.ADD) {
              try {
              AnimalType animalType = getAnimalType(scanner);
              Animal animal = factory.create(animalType);
              // запросить все параметры ТУТ
              animal.setName(getName(scanner));
              animal.setAge(getAge(scanner));
              animal.setWeight(getWeight(scanner));
              animal.setColor(getColor(scanner));

              animalTable.insert(animal, animalType); // сохранение в БД
              animal.say();
              } catch (SQLException e) {
                  System.out.println("Ошибка при сохранении в БД: " + e.getMessage());
              }
          } else if (currentCommand == Command.EDIT) { //ветка редактирования в БД
              try {
                  List<Animal> animals = animalTable.getAll();
                  if (animals.isEmpty()) {
                      System.out.println("Нет животных для редактирования!");
                      continue;
                  }
                  System.out.println("Список животных доступных для редактирования:");
                  for (Animal a : animals) {
                      System.out.println(a.getId() + ": " + a.getName() + " (" + a.getType().name() + ")");
                  }
                  System.out.print("Введите ID животного для редактирования:");
                  int id = scanner.nextInt();
                  scanner.nextLine(); // очистка буфера
                  Animal animalToEdit = null;
                  for (Animal a : animals) {
                      if (a.getId() == id) {
                          animalToEdit = a;
                          break;
                      }
                  }
                  if (animalToEdit == null) {
                      System.out.println("Отсутствует животное с таким ID!");
                      continue;
                  }
                  // Запросить новые данные
                  AnimalType newType = getAnimalType(scanner);
                  String newName = getName(scanner);
                  int newAge = getAge(scanner);
                  int newWeight = getWeight(scanner);
                  Color newColor = getColor(scanner);

                  animalToEdit.setType(newType);
                  animalToEdit.setName(newName);
                  animalToEdit.setAge(newAge);
                  animalToEdit.setWeight(newWeight);
                  animalToEdit.setColor(newColor);

                  animalTable.update(animalToEdit);
                  System.out.println("Животное успешно обновлено.");
              } catch (SQLException e) {
                  System.out.println("Ошибка при редактировании: " + e.getMessage());
              }
          } else if (currentCommand == Command.FILTER) { // ветка с фильтрацией
              try {
                  System.out.println("Введите тип животного для фильтации (CAT/DOG/DUCK):");
                  AnimalType filterType = getAnimalType(scanner);
                  List<Animal> filteredAnimals = animalTable.getByType(filterType);
                  if (filteredAnimals.isEmpty()) {
                      System.out.println("Животные такого типа отсутствуют в БД");
                  } else {
                      System.out.println("Животные типа " + filterType.name() + ":");
                      for (Animal a : filteredAnimals) {
                          System.out.println(a);
                      }
                  }
              } catch (SQLException e) {
                  System.out.println("Ошибка при выполнении фильтрации: " + e.getMessage());
              }
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


    private static AnimalType getAnimalType(Scanner scanner) {
        String commandInput = null;
        while (AnimalType.doesNotContain(commandInput)){
            if (commandInput != null) {
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.println("Введите тип животного:");
            commandInput = scanner.next();

        }
        return AnimalType.fromString(commandInput);
    }

    private static String getName(Scanner scanner) {
        System.out.println("Введите имя:");
        return scanner.next();
    }

    private static int getAge(Scanner scanner) {
        System.out.println("Введите возраст: ");
        while (true) {
            String commandInput = scanner.next();

            try {
                int age = Integer.parseInt(commandInput);
                if (age > 0 && age <= 100) {
                    scanner.nextLine();
                    return age;
                } System.out.println("Возраст должен быть от 1 до 100");

            } catch (NumberFormatException e) {
                System.out.println("Для параметра 'Возраст' допускается только ввод целого числа. Введите число:");
            }
        }

    }

    private static int getWeight(Scanner scanner) {
        System.out.println("Введите вес: ");
        while (true) {
            String commandInput = scanner.next();

            try {
                int weight = Integer.parseInt(commandInput);
                if (weight > 0 && weight <= 200) {
                    scanner.nextLine();
                    return weight;
                } System.out.println("Вес должен быть от 1 до 200");
            } catch (NumberFormatException e) {
                System.out.println("Для параметра 'Вес' допускается только ввод целого числа. Введите число:");
            }
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
