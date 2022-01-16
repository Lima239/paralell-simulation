package sequentialSimulation;

import java.util.*;
import java.io.*;

public class InputReader {
    private Scanner scannerTerminal;
    private Scanner scannerFile;
    private ArrayList<Double> tasks;
    private PrintStream out;
    private Random gaussianNumber;
    private int countOfNumbers;

    public InputReader() {
        this.scannerTerminal = new Scanner(System.in);
        this.tasks = new ArrayList();
    }

    public InputReader(int countOfNumbers) {
        this.countOfNumbers = countOfNumbers;
        this.tasks = new ArrayList();
        try {
            this.out = new PrintStream("gaussianNumbers.txt");
        } catch (IOException e){}
        this.gaussianNumber = new Random();
        this.scannerFile = new Scanner("gaussianNumbers.txt");
    }

    public void readFromTerminal() {
        while (scannerTerminal.hasNext()) {
            tasks.add(scannerTerminal.nextDouble());
        }
    }

    public void generateGaussianNumbers() {
        for (int i = 0; i < countOfNumbers; i++){
            out.print(gaussianNumber.nextGaussian() + " ");
        }
    }

    public void readFromFile() {
        while (scannerFile.hasNext()) {
            tasks.add(scannerFile.nextDouble());
        }
    }

    public ArrayList<Double> getTasks(){
        return this.tasks;
    }
}
