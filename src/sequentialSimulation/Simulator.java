package sequentialSimulation;

import java.util.*;

public class Simulator {
    public static void main(String[] args) {
        System.out.println("Do you want to enter a sequence of tasks? yes/no");
        Scanner scanner = new Scanner(System.in);
        InputReader inputReader;

        if (scanner.next() == "yes"){
            System.out.println("Please enter a sequence of tasks.");
            inputReader = new InputReader();
            inputReader.readFromTerminal();
        } else {
            System.out.println("The simulator will generate a sequence of tasks, please enter number of tasks.");
            inputReader = new InputReader(scanner.nextInt());
            inputReader.generateGaussianNumbers();
        }

        System.out.println("Please enter the name of the algorithm you want the simulator to simulate, " +
                "number of processors and latency.");
        String nameOfAlgorithm = scanner.next();
        int numberOfProcessors = scanner.nextInt();
        int latency = scanner.nextInt();
        Algorithms algorithm;

        switch (nameOfAlgorithm){
            case "Chunking" : algorithm = new Chunking(inputReader.getTasks(), numberOfProcessors, latency);
            case "Factoring" :
                int factor = scanner.nextInt();
                algorithm = new Factoring(inputReader.getTasks(), numberOfProcessors, latency, factor);
            case "Work stealing" : algorithm = new WorkStealing(inputReader.getTasks(), numberOfProcessors, latency);
        }

    }
}
