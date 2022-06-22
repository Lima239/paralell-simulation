package sequentialSimulation;

import java.util.*;
import java.io.*;
import java.util.Collections;

// command line arguments NameOfInputTXT Algorithm(C/F/W) Latency N(number of processors) [Parameter(optional)]
public class Simulator {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            Scanner scanner = new Scanner(new FileInputStream(args[0]));
            String algorithm = args[1];
            double latency = Double.parseDouble(args[2]);
            int numberOfProcessors = Integer.parseInt(args[3]);
            int parameter = Integer.parseInt(args[4]);

            ArrayList<Double> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(scanner.nextDouble());
            }

            double avg = average(tasks);
            double min = Collections.min(tasks);
            double max = Collections.max(tasks);

            switch(algorithm){
                case "C":
                    PrintStream outC = new PrintStream("outputChunking.txt");
                    outC.println(tasks.size());
                    outC.println(numberOfProcessors);
                    outC.println(avg);
                    outC.println(min);
                    outC.println(max);
                    outC.println(latency);
                    for (int i = 1; i <= numberOfProcessors; i++) {
                        Algorithms chunking = new Chunking(tasks, i, latency, parameter);
                        outC.print(i + " ");
                        outC.print(chunking.redistributeTasks() + " ");
                        outC.println();
                    }
                    outC.close();
                    break;

                case "F":
                    PrintStream outF = new PrintStream("outputFactoring.txt");
                    outF.println(tasks.size());
                    outF.println(numberOfProcessors);
                    outF.println(avg);
                    outF.println(min);
                    outF.println(max);
                    outF.println(latency);
                    for (int i = 1; i <= numberOfProcessors; i++) {
                        double factor = 1 + (max / min) * (i - 1);
                        Algorithms factoring = new Factoring(tasks, i, latency, factor);
                        outF.print(i + " ");
                        outF.print(factoring.redistributeTasks() + " ");
                        outF.println();
                    }
                    outF.close();
                    break;

                case "W":
                    PrintStream outW = new PrintStream("outputWorkStealing.txt");
                    outW.println(tasks.size());
                    outW.println(numberOfProcessors);
                    outW.println(avg);
                    outW.println(min);
                    outW.println(max);
                    outW.println(latency);
                    for (int i = 1; i <= numberOfProcessors; i++) {
                        Algorithms workStealing = new WorkStealing(tasks, i, latency);
                        outW.print(i + " ");
                        outW.print(workStealing.redistributeTasks() + " ");
                        outW.println();
                    }
                    outW.close();
                    break;
            }

        } else {
            System.out.println("No command line arguments found.");
        }
    }

    static double average(ArrayList<Double> tasks){
        double resultSum = 0;
        for(Double task : tasks){
            resultSum += task;
        }
        return resultSum/tasks.size();
    }
}
