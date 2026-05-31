package animals.tables;

import animals.Animal;
import animals.AnimalType;
import animals.Color;
import animals.factory.AnimalFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalTable extends AbsTable {

//    создание таблицы с животными
    public AnimalTable() throws SQLException {
        super("animals");

        create(
                "type VARCHAR(10) NOT NULL",
                "name VARCHAR(35) NOT NULL",
                "age INT NOT NULL",
                "weight INT NOT NULL",
                "color VARCHAR(25) NOT NULL"
        );
    }

//    вставить животного в БД
    public void insert(Animal animal, AnimalType type) throws SQLException {
        String sql = String.format("INSERT INTO animals (type, name, age, weight, color) VALUES ('%s', '%s', %d, %d, '%s')",

        type.name(),
        animal.getName(),
        animal.getAge(),
        animal.getWeight(),
        animal.getColor().getValue());

        this.dbConnector.execute(sql);
    }

//    чтение всех животных в БД
public List<Animal> getAll() throws SQLException {
    List<Map<String, String>> rows = listDataFromTable("","type", "name", "age", "weight", "color");
    List<Animal> animals = new ArrayList<>();
    AnimalFactory factory = new AnimalFactory();
    for (Map<String, String> row : rows) {
        AnimalType type = AnimalType.valueOf(row.get("type"));
        Animal animal = factory.create(type);
        animal.setName(row.get("name"));
        animal.setAge(Integer.parseInt(row.get("age")));
        animal.setWeight(Integer.parseInt(row.get("weight")));
        Color color = Color.fromString(row.get("color"));
        animal.setColor(color);
        animals.add(animal);
    }
    return animals;
}
}
