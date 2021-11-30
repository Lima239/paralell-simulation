package sequentialSimulation;

import java.util.*;
import java.io.*;

public class InputReader {
    private Scanner scannerTerminal;
    private Scanner scannerFile;
    private List<Double> tasks;
    private PrintStream out;
    private Random gaussianNumber;
    private int countOfNumbers;

    public InputReader() {
        this.scannerTerminal = new Scanner(System.in);
        this.tasks = new ArrayList();
    }

    public InputReader(int countOfNumbers) throws FileNotFoundException {
        this.countOfNumbers = countOfNumbers;
        this.out = new PrintStream("gaussianNumbers.txt");
        this.gaussianNumber = new Random();
        this.scannerFile = new Scanner("gaussianNumbers.txt");
    }

    void readFromTerminal() {
        while (scannerTerminal.hasNext()) {
            tasks.add(scannerTerminal.nextDouble());
        }
    }

    void generateGaussianNumbers() {
        for (int i = 0; i < countOfNumbers; i++){
            out.print(gaussianNumber.nextGaussian() + " ");
        }
    }

    void readFromFile() {
        while (scannerFile.hasNext()) {
            tasks.add(scannerFile.nextDouble());
        }
    }
}
