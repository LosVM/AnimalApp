package animals.factory;

import animals.Animal;
import animals.AnimalType;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;

public class AnimalFactory {

    public Animal create(AnimalType type) {
        Animal animal;
        if (type == AnimalType.CAT) {
            animal = new Cat();
        } else if (type == AnimalType.DOG) {
            animal = new Dog();
        } else if (type == AnimalType.DUCK) {
            animal = new Duck();
        } else  {
            return null;
        }
    animal.setType(type); // сохранение типа
    return animal;
}
}