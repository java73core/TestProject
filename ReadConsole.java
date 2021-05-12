import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReadConsole {
    private Exchange course;

    public ReadConsole() throws FileNotFoundException {
        course = new Exchange();
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String toDollars(String dollar) {
        double value = Double.parseDouble(dollar.substring(0, dollar.length()));
        return "$" + round(course.getRubleToDollar(value), 2);
    }

    public String toRubles(String ruble) {
        double value = Double.parseDouble(ruble.substring(0, ruble.length()));
         return round(course.getDollarToRuble(value), 2) + "p";
    }

    public void printResult(String result) {
        System.out.println("Результат вычисления: " + result);
    }
}
