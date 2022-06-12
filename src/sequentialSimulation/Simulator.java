package sequentialSimulation;

import java.util.*;
import java.io.*;

public class Simulator {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        PrintStream out = new PrintStream("output.txt");
        double latency = 0.3;
        int parameter = 1;
        float min = scanner.nextFloat();
        float max = scanner.nextFloat();
        ArrayList<Double> tasks = new ArrayList<>();
        while (scanner.hasNext()){
            tasks.add(scanner.nextDouble());
        }

        for (int i = 1; i <= 100; i++){
            Algorithms chunking = new Chunking(tasks, i, latency, parameter);
            Algorithms workStealing = new WorkStealing(tasks, i, latency);
            float factor = 1 + (max / min) * (i - 1);
            Algorithms factoring = new Factoring(tasks, i, latency, factor);

            out.print(i + " ");
            out.print(chunking.redistributeTasks() + " ");
            out.print(workStealing.redistributeTasks() + " ");
            out.print(factoring.redistributeTasks() + " ");
            out.println();
        }

        out.close();
    }
}
