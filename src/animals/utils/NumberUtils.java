package animals.utils;

// определение валидного ввода

public class NumberUtils {

    public boolean isNotNumber(String value) {
        if (value == null) {
            return true;
        }
        return  !value.matches("\\d+");
        // шаблон для регулярный выражений? regex101.com
    }
}
