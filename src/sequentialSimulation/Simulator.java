package sequentialSimulation;

import java.util.*;
import java.io.*;

public class Simulator {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        int numberOfProcessors = scanner.nextInt();
        Double latency = scanner.nextDouble();
        int parameter = scanner.nextInt();
        float factor = scanner.nextFloat();
        ArrayList<Double> tasks = new ArrayList<>();
        while (scanner.hasNext()){
            tasks.add(scanner.nextDouble());
        }

        Algorithms chunking = new Chunking(tasks, numberOfProcessors, latency, parameter);
        Algorithms workStealing = new WorkStealing(tasks, numberOfProcessors, latency);
        Algorithms factoring = new Factoring(tasks, numberOfProcessors, latency, factor);

        System.out.println("Chunking: " + chunking.redistributeTasks());
        System.out.println("Work stealing: " + workStealing.redistributeTasks());
        System.out.println("Factoring: " + factoring.redistributeTasks());

    }
}
