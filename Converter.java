import java.io.FileNotFoundException;
import java.util.Scanner;

public class Converter {
    private final ReadConsole readConsole;
    private final Exchange exchange;
    private final String toDollars = "toDollars";
    private final String toRubles = "toRubles";

    public Converter() throws FileNotFoundException {
        readConsole = new ReadConsole();
        exchange = new Exchange();
    }

    public String getToDollars() {
        return toDollars;
    }

    public String getToRubles() {
        return toRubles;
    }

    public double sumRubles(String firstString, String secondString) {
        double firstValue = Double.parseDouble(firstString.substring(0, firstString.length() - 1));
        double secondValue = Double.parseDouble(secondString.substring(0, secondString.length() - 1));
        return firstValue + secondValue;
    }

    public double diffRubles(String firstString, String secondString) {
        double firstValue = Double.parseDouble(firstString.substring(0, firstString.length() - 1));
        double secondValue = Double.parseDouble(secondString.substring(0, secondString.length() - 1));
        return firstValue - secondValue;
    }

    public double sumDollars(String firstString, String secondString) {
        double firstValue = Double.parseDouble(firstString.substring(1, firstString.length()));
        double secondValue = Double.parseDouble(secondString.substring(1, secondString.length()));
        return firstValue + secondValue;
    }

    public double diffDollars(String firstString, String secondString) {
        double firstValue = Double.parseDouble(firstString.substring(1, firstString.length()));
        double secondValue = Double.parseDouble(secondString.substring(1, secondString.length()));
        return firstValue - secondValue;
    }

    public void calculatorRub(String strRub) throws FileNotFoundException {
        Converter converter = new Converter();
        String firstString = null, secondString = null;
        double result = 0;
        if (strRub.contains("+")) {
            firstString = strRub.substring(0, strRub.indexOf("+"));
            secondString = strRub.substring(strRub.indexOf("+") + 1, strRub.length());
        } else if (strRub.contains("-")) {
            firstString = strRub.substring(0, strRub.indexOf("-"));
            secondString = strRub.substring(strRub.indexOf("-") + 1, strRub.length());
        }
        if (firstString.contains(converter.getToDollars())) {
            firstString =  converter.readHardLine(firstString);
        }
        if (secondString.contains(converter.getToDollars())) {
            secondString = converter.readHardLine(secondString);
        }
        if (strRub.contains("+")) {
            result = converter.sumDollars(firstString, secondString);
        } else if (strRub.contains("-")) {
            result = converter.diffDollars(firstString, secondString);
        }
        readConsole.printResult(readConsole.round(exchange.getDollarToRuble(result), 2) + "p");
    }

    public void calculatorDol(String strDol) throws FileNotFoundException {
        Converter converter = new Converter();
        String firstString = null, secondString = null;
        double result = 0;
        if (strDol.contains("+")) {
            firstString = strDol.substring(0, strDol.indexOf("+"));
            secondString = strDol.substring(strDol.indexOf("+") + 1, strDol.length());
        } else if (strDol.contains("-")) {
            firstString = strDol.substring(0, strDol.indexOf("-"));
            secondString = strDol.substring(strDol.indexOf("-") + 1, strDol.length());
        }
        if (firstString.contains(converter.getToRubles())) {
           firstString =  converter.readHardLine(firstString);
        }
        if (secondString.contains(converter.getToRubles())) {
           secondString = converter.readHardLine(secondString);
        }
        if (strDol.contains("+")) {
           result = converter.sumRubles(firstString, secondString);
        } else if (strDol.contains("-")) {
           result = converter.diffRubles(firstString, secondString);
        }
        readConsole.printResult("$" + readConsole.round(exchange.getRubleToDollar(result), 2));
    }

    public String readHardLine(String inputString) throws FileNotFoundException {
        String operation = inputString.substring(0, inputString.indexOf("("));
        String result = null;
        operation = operation.trim();
        String resources = inputString.substring(inputString.indexOf("(") + 1, inputString.lastIndexOf(")"));
        resources = resources.replaceAll("\\s+", "");
        if (operation.equalsIgnoreCase(toDollars)) {
            if (resources.contains("+") || resources.contains("-")) {
                calculatorDol(resources);
            } else {
                result = readConsole.toDollars(resources.substring(0, resources.length() - 1));
            }
        } else if (operation.equalsIgnoreCase(toRubles)) {
            if (resources.contains("+") || resources.contains("-")) {
                calculatorRub(resources);
            } else {
                result = readConsole.toRubles(resources.substring(1, resources.length()));
            }
        } else {
            System.out.println("команда не найдена");
        }
        return result;
    }

    public void readLine(String inputString) throws FileNotFoundException {
        String operation = inputString.substring(0, inputString.indexOf("("));
        operation = operation.trim();
        String resources = inputString.substring(inputString.indexOf("(") + 1, inputString.lastIndexOf(")"));
        resources = resources.replaceAll("\\s+", "");
            if (operation.equalsIgnoreCase(toDollars)) {
            if (resources.contains("+") || resources.contains("-")) {
                calculatorDol(resources);
            } else {
                readConsole.printResult(readConsole.toDollars(resources.substring(0, resources.length() - 1)));
            }
        } else if (operation.equalsIgnoreCase(toRubles)) {
            if (resources.contains("+") || resources.contains("-")) {
                calculatorRub(resources);
            } else {
                readConsole.printResult(readConsole.toRubles(resources.substring(1, resources.length())));
            }
        } else {
                System.out.println("команда не найдена");
            }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Exchange course = new Exchange();
        Converter converter = new Converter();
        Scanner in = new Scanner(System.in);
        System.out.println("курс валют: " + "1$ = " + course.getRuble() + "p");
        System.out.println("курс валют: " + "1p = $" + course.getDollar());
        System.out.print("Введите строку: ");
        converter.readLine(in.nextLine());
    }
}
