import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static boolean parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда");
         oper = detectOperation (expression);
        if (oper == null ) throw new Exception("Неподдерживаемая математическая операция");
        // если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            // конвертируем оба числа в арабские для вычесления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // если одно число римское,а другое - арабское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10  || num2 > 10 ) {
            throw new Exception("Числа должныбыть от 1 до 10");
        }
        int arabian = calc(num1, num2,oper);
        if (isRoman ) {
            // если римское число получилось меньше или равно нулю,генерируем ошибку
            if (arabian <=0 ) {
                throw new Exception("Римское число должо быть больше нуля");
            }
            //конвертируем результат операции из арабского в римское

            result = Roman.convertToRoman(arabian);
        } else {
            // Конвертируем арабское число в тип String
            result = java.lang.String.valueOf(arabian);
        }
        // возвращаем результат
        return Boolean.parseBoolean(result);
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")  ) return "+";
        else if (expression.contains("-")==true) return "-";
        else if (expression.contains("*")==true ) return "*";
        else if (expression.contains("/") ==true) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXVI", "XL", "XLII", "XLV",
            "L", "LIV", "LVI", "LX", "LXIII", "LXIV", "LXX", "LXXII", "LXXX", "LXXXI", "XC", "XCI", "XCV", "C"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[1])) {
                return true;
            }
        }
        return false;
    }


    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[1])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];}
}