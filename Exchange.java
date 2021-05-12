import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exchange {
    private double ruble;
    private double dollar;

    public Exchange() throws FileNotFoundException {
        this.inputConfig();
    }

    public double getRuble() {
        return ruble;
    }

    public double getDollar() {
        return dollar;
    }

    public double getRubleToDollar(double ruble) {
        return this.dollar * ruble;
    }

    public double getDollarToRuble(double dollar) {
        return this.ruble * dollar;
    }

    public void inputConfig() throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\project\\TestProject\\src\\files\\config.txt"));
        List<String> lines = new ArrayList<String>();
        try {
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }
        } finally {
            input.close();
        }
        this.ruble = Double.valueOf(lines.get(0).substring(0, lines.get(0).length() - 1));
        this.dollar = Double.valueOf(lines.get(1).substring(1, lines.get(0).length()));
    }
}
