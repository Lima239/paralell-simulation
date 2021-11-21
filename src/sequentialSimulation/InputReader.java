package sequentialSimulation;

import java.util.*;
import java.io.*;

public class InputReader {
    private Scanner scanner;
    private List<Double> tasks;
    private PrintStream out;
    private Random gaussianNumber;
    private int countOfNumbers;

    public InputReader() {
        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList();
    }

    public InputReader(int countOfNumbers) throws FileNotFoundException {
        this.countOfNumbers = countOfNumbers;
        this.out = new PrintStream("gaussianNumbers.txt");
        this.gaussianNumber = new Random();
    }

    void readFromTerminal() {
        while (scanner.hasNext()) {
            tasks.add(scanner.nextDouble());
        }
    }

    void generateGaussianNumbers() {
        for (int i = 0; i < countOfNumbers; i++){
            out.print(gaussianNumber.nextGaussian() + " ");
        }
    }
}
