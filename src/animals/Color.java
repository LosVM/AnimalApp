package animals;

import java.util.ArrayList;
import java.util.List;

public enum Color {

    BLACK("чёрный"),
    WHITE("белый"),
    GRAY("серый");

    //строка ниже вывод значения?
    private final String value;
    public static final List<String> VALUES = collectValues();


    Color(String value) {
        this.value = value;
    }

    private static List<String> collectValues() {
        List<String> result = new ArrayList<>();
        for (Color color : Color.values()) {
            result.add(color.getValue());
        }
        return result;
    }

    public static boolean doesNotContain(String CommandUnput) {
//      добавлена обработка значений кириллицей?
        if (CommandUnput == null) {
            return true;
        }

        String ruCheck = CommandUnput.trim();
        for (String valid : VALUES) {
            if (valid.equalsIgnoreCase(ruCheck)) {
                return false;
            }
        }
        return true;
    }

    public static Color fromString(String CommandUnput) {

        if (CommandUnput == null) {
            return null;
        }

        String ruCheck = CommandUnput.trim();
        for (Color color : Color.values()){
            if(color.value.equalsIgnoreCase(ruCheck)) {
                return color;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }


}
